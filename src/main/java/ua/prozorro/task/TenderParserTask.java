package ua.prozorro.task;

import javafx.concurrent.Task;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ua.prozorro.entity.mappers.prozorroObjectMapper.TenderMapper;
import ua.prozorro.entity.mappers.prozorroObjectMapper.pages.TenderPageMapper;
import ua.prozorro.entity.pages.TenderPageDTO;
import ua.prozorro.entity.tenders.TenderDTO;
import ua.prozorro.properties.AppProperty;
import ua.prozorro.properties.PropertyFields;
import ua.prozorro.timeMeasure.ParsingResultData;
import ua.prozorro.prozorro.model.pages.ProzorroPageContent;
import ua.prozorro.prozorro.model.pages.ProzorroPageElement;
import ua.prozorro.prozorro.model.tenders.TenderData;
import ua.prozorro.prozorro.service.PageServiceProzorro;
import ua.prozorro.prozorro.service.TenderDataServiceProzorro;
import ua.prozorro.repositories.PageRepository;
import ua.prozorro.repositories.TenderRepository;
import ua.prozorro.utils.DateUtils;

import java.util.Date;
@Deprecated
public class TenderParserTask extends Task<Boolean> {
    private static final Logger logger = LogManager.getRootLogger();

    private SessionFactory sessionFactory;
    private PropertyFields propertyFields;
    private ParsingResultData resultData;

    public TenderParserTask() {
    }

    public TenderParserTask(PropertyFields propertyFields) {
        this.propertyFields = propertyFields;
    }

    public TenderParserTask(SessionFactory sessionFactory, PropertyFields propertyFields, ParsingResultData resultData) {
        this.propertyFields = propertyFields;
        this.sessionFactory = sessionFactory;
        this.resultData = resultData;
    }

    public PropertyFields getPropertyFields() {
        return propertyFields;
    }

    public void setPropertyFields(PropertyFields propertyFields) {
        this.propertyFields = propertyFields;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public ParsingResultData getResultData() {
        return resultData;
    }

    public void setResultData(ParsingResultData resultData) {
        this.resultData = resultData;
    }

    @Override
    protected Boolean call() throws Exception {
        PageServiceProzorro pageServiceProzorro = new PageServiceProzorro(propertyFields);

        String currentPageURL = pageServiceProzorro.getPageURL(propertyFields.getSearchDateFrom());
        logger.info("Start parsing from URL " + currentPageURL);
        //textArea.appendText("Start parsing from URL " + currentPageURL + "\n");
        updateMessage("Start parsing from URL " + currentPageURL + "\n");

        TenderPageDTO page = null;


        Session session = null;
        Transaction transaction = null;

        String text = "";

        ProzorroPageContent pageContent = null;

        int pageCount = 0;
        int pageElementCount = 0;

        try {

            updateProgress(pageCount, resultData.getListSize());
            pageContent = pageServiceProzorro.getPageContentFromURL(currentPageURL);

            Date nextOffsetDate = pageServiceProzorro.getDateFromPageOffset(pageContent.getNextPage().getOffset());
            if (nextOffsetDate.compareTo(propertyFields.getSearchDateTill()) > 0) {
                nextOffsetDate = propertyFields.getSearchDateTill();
            }
            //logger.info("Get first ProzorroPage: " + pageContent);
            logger.info("Get first ProzorroPage");

            session = sessionFactory.openSession();
            while (propertyFields.getSearchDateTill().compareTo(nextOffsetDate) >= 0 &&
                    pageContent.getPageElementList() != null && !pageContent.getPageElementList().isEmpty()) {

                if (isCancelled()) {
                    updateMessage("Cancelled \n");
                    break;
                }

                pageCount++;

                pageElementCount = 0;
                logger.info("Start parsing page №" + pageCount + ": ");
                updateMessage("Start parsing page №" + pageCount + ": \n");
                TenderPageMapper tenderPageMapper = new TenderPageMapper();
                TenderMapper tenderMapper = new TenderMapper();

                for (ProzorroPageElement pageElement : pageContent.getPageElementList()) {

                    Date pageDate = DateUtils.parseProzorroPageDateFromString(pageElement.getDateModified(),
                            propertyFields.getPropertiesStringValue(
                                    AppProperty.PROZORRO_DATE_FORMAT));
                    if (propertyFields.getSearchDateTill().
                            compareTo(DateUtils.parseDateToFormat(pageDate, propertyFields
                                    .getPropertiesStringValue(AppProperty.PROZORRO_SHORT_DATE_FORMAT))) < 0) {
                        logger.info(
                                propertyFields.getSearchDateType().getTypeName() + ": Страница № " + pageCount + "/" +
                                        resultData.getListSize() + ", текущий № " + (pageElementCount + 1) + " c id: " +
                                        pageElement.getId() + ", date: " + pageElement.getDateModified() +
                                        ". Отклонён по дате \n");
                        updateMessage(
                                propertyFields.getSearchDateType().getTypeName() + ": Страница № " + pageCount + "/" +
                                        resultData.getListSize() + ", текущий № " + (pageElementCount + 1) + " c id: " +
                                        pageElement.getId() + ", date:" + " " + pageElement.getDateModified() +
                                        ". Отклонён по дате \n");
                        break;
                    }
                    Date start = new Date();
                    pageElementCount++;
                    //page = TenderDTOUtils.getPageDTO(pageElement);
                    page = tenderPageMapper.convertToEntity(pageElement);

                    logger.debug("Parsing PAGE JSON to class (ms):" + ((new Date()).getTime() - start.getTime()));
                    PageRepository pageRepository = new PageRepository(session);

                    transaction = session.beginTransaction();
                    logger.info("Start saving to DB:");
                    start = new Date();
                    boolean updatedPage = pageRepository.saveTenderPage(page, session);
                    logger.info("Save Page (ms):" + ((new Date()).getTime() - start.getTime()));
                    if (updatedPage) {
                        start = new Date();
                        TenderRepository tenderRepository = new TenderRepository(session);

                        TenderDataServiceProzorro tenderDataServiceProzorro = new TenderDataServiceProzorro(
                                propertyFields.getPropertiesStringValue(AppProperty.TENDER_START_PAGE) + "/");
                        text = pageElement.getId() + "\n";
                        TenderData tenderData = tenderDataServiceProzorro.getTenderDataFromPageElement(pageElement);

                        logger.info("Take TENDER from PAGE:" + ((new Date()).getTime() - start.getTime()));
                        start = new Date();
                        text = text + tenderData.toString();

                        //TenderDTO tenderDTO = TenderDTOUtils.getTenderDTO(tenderData.getTender());
                        TenderDTO tenderDTO = tenderMapper.convertToEntity(tenderData.getTender());
                        logger.info("Take TENDERDAO from TENDER:" + ((new Date()).getTime() - start.getTime()));
                        start = new Date();
                        tenderRepository.saveTender(tenderDTO, session);
                        logger.info("Save TENDERDAO to DB:" + ((new Date()).getTime() - start.getTime()));
                    }
                    logger.info(propertyFields.getSearchDateType().getTypeName() + ": Страница № " + pageCount + "/" +
                            resultData.getListSize() + ", текущий № " + pageElementCount + " c id: " +
                            pageElement.getId() + ", date: " + pageElement.getDateModified() + ". added/updated: " +
                            updatedPage + " \n");
                    updateMessage(propertyFields.getSearchDateType().getTypeName() + ": Страница № " + pageCount + "/" +
                            resultData.getListSize() + ", текущий № " + pageElementCount + " c id: " +
                            pageElement.getId() + ", date: " + pageElement.getDateModified() +
                            ". added/updated: " + updatedPage + " \n");
                    session.flush();
                    session.clear();
                    transaction.commit();
                }
                logger.info("Get next page with URL: " + pageContent.getNextPage().getUri());
                //textArea.appendText("Get next page with URL: " + pageContent.getNextPage().getUri() + " \n");
                updateMessage("Get next page with URL: " + pageContent.getNextPage().getUri() + " \n");

                nextOffsetDate = pageServiceProzorro.getDateFromPageOffset(pageContent.getNextPage().getOffset());
                currentPageURL = pageContent.getNextPage().getUri();
                pageContent = pageServiceProzorro.getPageContentFromURL(pageContent.getNextPage().getUri());
                text = "";
                page = null;
                updateProgress(pageCount, resultData.getListSize());

                updateMessage("Найдено " + ((pageCount - 1) * 100 + pageElementCount) + " " +
                        propertyFields.getSearchDateType().getTypeName() + "\n");
            }
        } catch (Exception e) {
            //catch (ParseException | IOException | Exception e){
            e.printStackTrace();

            logger.error("ERROR on URL: " + currentPageURL);
            logger.error("ERROR on Page: " + page);
            logger.error("ERROR Объект: " + text);
            logger.error("ERROR message: " + e.getMessage());

            updateMessage("ERROR on URL: " + currentPageURL);
            updateMessage("ERROR on Page: " + pageContent);
            updateMessage("ERROR Объект: " + text);
            updateMessage("ERROR message: " + e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
            throw new Exception(e);

        } finally {
            session.close();
        }
        return true;
    }
}
