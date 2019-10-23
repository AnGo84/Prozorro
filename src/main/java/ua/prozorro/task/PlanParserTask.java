package ua.prozorro.task;

import javafx.concurrent.Task;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ua.prozorro.entity.PageElement;
import ua.prozorro.entity.mappers.prozorroObjectMapper.PlanMapper;
import ua.prozorro.entity.mappers.prozorroObjectMapper.pages.PlanPageMapper;
import ua.prozorro.entity.pages.PlanPageDTO;
import ua.prozorro.entity.plans.PlanDTO;
import ua.prozorro.properties.AppProperty;
import ua.prozorro.properties.PropertyFields;
import ua.prozorro.timeMeasure.ParsingResultData;
import ua.prozorro.prozorro.model.pages.ProzorroPageContent;
import ua.prozorro.prozorro.model.plans.PlanData;
import ua.prozorro.prozorro.service.PageServiceProzorro;
import ua.prozorro.prozorro.service.PlanDataServiceProzorro;
import ua.prozorro.repositories.PageRepository;
import ua.prozorro.repositories.PlanRepository;
import ua.prozorro.utils.DateUtils;

import java.util.Date;
@Deprecated
public class PlanParserTask extends Task<Boolean> {
    private static final Logger logger = LogManager.getRootLogger();

    private SessionFactory sessionFactory;
    private PropertyFields propertyFields;
    private ParsingResultData resultData;

    public PlanParserTask() {
    }

    public PlanParserTask(PropertyFields propertyFields) {
        this.propertyFields = propertyFields;
    }

    public PlanParserTask(SessionFactory sessionFactory, PropertyFields propertyFields, ParsingResultData resultData) {
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

        PlanPageDTO page = null;

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
                PlanPageMapper planPageMapper = new PlanPageMapper();
                PlanMapper planMapper = new PlanMapper();

                for (PageElement pageElement : pageContent.getPageElementList()) {

                    Date pageDate = DateUtils.parseProzorroPageDateFromString(pageElement.getDateModified(),
                            propertyFields.getPropertiesStringValue(
                                    AppProperty.PROZORRO_DATE_FORMAT));
                    if (propertyFields.getSearchDateTill().
                            compareTo(DateUtils.parseDateToFormat(pageDate, propertyFields
                                    .getPropertiesStringValue(AppProperty.PROZORRO_SHORT_DATE_FORMAT))) < 0) {
                        logger.info(
                                propertyFields.getSearchDateType().getTypeName() + ": Страница № " + pageCount + "/" +
                                        resultData.getListSize() + ", текущий № " + (pageElementCount + 1) + " c id: " +
                                        pageElement.getId() + ", date: " +

                                        pageElement.getDateModified() + ". Отклонён по дате \n");
                        updateMessage(
                                propertyFields.getSearchDateType().getTypeName() + ": Страница № " + pageCount + "/" +
                                        resultData.getListSize() + ", текущий № " + (pageElementCount + 1) + " c id: " +
                                        pageElement.getId() + ", date:" + " " + pageElement.getDateModified() +
                                        ". Отклонён по дате \n");
                        break;
                    }
                    pageElementCount++;
                    //page = PlanDTOUtils.getPageDTO(pageElement);
                    page = planPageMapper.convertToEntity(pageElement);
                    PageRepository pageRepository = new PageRepository(session);

                    transaction = session.beginTransaction();
                    boolean updatedPage = pageRepository.savePlanPage(page, session);
                    if (updatedPage) {
                        PlanRepository planRepository = new PlanRepository(session);

                        PlanDataServiceProzorro planDataServiceProzorro = new PlanDataServiceProzorro(
                                propertyFields.getPropertiesStringValue(AppProperty.PLAN_START_PAGE) + "/");
                        text = pageElement.getId() + "\n";
                        PlanData planData = planDataServiceProzorro.getPlanDataFromPageElement(pageElement);
                        text = text + planData.toString();
                        //PlanDTO planDTO = PlanDTOUtils.getPlanDTO(planData.getPlan());
                        PlanDTO planDTO = planMapper.convertToEntity(planData.getPlan());
                        planRepository.savePlan(planDTO, session);
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
            logger.error("ERROR message: " + e.getClass());

            updateMessage("ERROR on URL: " + currentPageURL);
            updateMessage("ERROR on Page: " + page);
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
