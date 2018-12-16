package ua.prozorro.prozorro.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.prozorro.entity.TenderDTOUtils;
import ua.prozorro.entity.pages.PageDTO;
import ua.prozorro.entity.tenders.TenderDTO;
import ua.prozorro.prozorro.PageServiceProzorro;
import ua.prozorro.prozorro.TenderDataServiceProzorro;
import ua.prozorro.prozorro.model.pages.ProzorroPageContent;
import ua.prozorro.prozorro.model.pages.ProzorroPageElement;
import ua.prozorro.prozorro.model.tenders.TenderData;
import ua.prozorro.service.PageService;
import ua.prozorro.service.TenderService;
import ua.prozorro.utils.DateUtils;

import javax.net.ssl.SSLHandshakeException;
import java.util.Date;

public class TenderParser implements DataParser {
    private static final Logger logger = LogManager.getRootLogger();

    private Session session;

    private PageServiceProzorro pageServiceProzorro;
    private TenderDataServiceProzorro tenderDataServiceProzorro;


    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public PageServiceProzorro getPageServiceProzorro() {
        return pageServiceProzorro;
    }

    public void setPageServiceProzorro(PageServiceProzorro pageServiceProzorro) {
        this.pageServiceProzorro = pageServiceProzorro;
    }

    public TenderDataServiceProzorro getTenderDataServiceProzorro() {
        return tenderDataServiceProzorro;
    }

    public void setTenderDataServiceProzorro(TenderDataServiceProzorro tenderDataServiceProzorro) {
        this.tenderDataServiceProzorro = tenderDataServiceProzorro;
    }

    @Override
    public boolean parseAndSave(Date dateFrom, Date dateTill) throws Exception {
        logger.info("Start parsing for period from " + DateUtils.dateToString(dateFrom) + " till " +
                    DateUtils.dateToString(dateTill));
        PageService pageService = new PageService(session);
        TenderService tenderService = new TenderService(session);

        String startPageURL = pageServiceProzorro.getPageURL(dateFrom);
        logger.info("Start parsing from URL " + startPageURL);
        Transaction transaction = null;
        TenderData tenderData = null;
        PageDTO page = null;
        TenderDTO tenderDTO = null;
        try {
            dateTill = pageServiceProzorro.getDateTill(dateTill);

            ProzorroPageContent pageContent = pageServiceProzorro.getPageContentFromURL(startPageURL);

            Date nextOffsetDate = pageServiceProzorro.getDateFromPageOffset(pageContent.getNextPage().getOffset());
            //logger.info("Get first ProzorroPage: " + pageContent);
            logger.info("Get first ProzorroPage");
            int pageCount = 0;


            while (dateTill.compareTo(nextOffsetDate) >= 0 && pageContent.getPageElementList() != null &&
                   !pageContent.getPageElementList().isEmpty()) {
                pageCount++;

                if (!session.isOpen()) {
                    session = session.getSessionFactory().openSession();
                }
                //transaction = session.beginTransaction();

                int tenderCount = 0;
                logger.info("Start parsing page: ");
                for (ProzorroPageElement pageElement : pageContent.getPageElementList()) {
                    if (!session.isOpen()) {
                        session = session.getSessionFactory().openSession();
                        logger.info("Opened session: " + session.isOpen());
                    }
                    transaction = session.beginTransaction();


                    tenderCount++;
                    //tenderDataServiceProzorro.getTenderDatasFromPageContent(pageContent);
                    tenderData = tenderDataServiceProzorro.getTenderDataFromPageElement(pageElement);
                    logger.info("Session open?: " + session.isOpen());
                    page = TenderDTOUtils.getPageDTO(pageElement);
                    pageService.savePage(page, session);

                    tenderDTO = TenderDTOUtils.getTenderDTO(tenderData.getTender());
                    tenderService.saveTender(tenderDTO, session);

                    logger.info("ProzorroPage " + pageCount + ", tender " + tenderCount);

                    transaction.commit();

                    //logger.info("Get next page with URL: " + pageContent.getNextPage().getUri());
                    pageContent = pageServiceProzorro.getPageContentFromURL(pageContent.getNextPage().getUri());
                    nextOffsetDate = pageServiceProzorro.getDateFromPageOffset(pageContent.getNextPage().getOffset());

                    //transaction.commit();

                }
            }

        } catch (SSLHandshakeException e) {
            //catch (ParseException | IOException | Exception e){
            e.printStackTrace();
            logger.info("Connection error to page: " + page + ", msg: " + e.getMessage());

            logger.info("Tender: " + tenderData.getTender());
            logger.info("TenderDTO: " + tenderDTO);
            if (transaction != null) {
                transaction.rollback();
            }


            throw new Exception(e);
        } catch (Exception e) {
            //catch (ParseException | IOException | Exception e){
            e.printStackTrace();
            logger.info("Parse error: " + page + ", msg: " + e.getMessage());

            logger.info("Tender: " + tenderData.getTender());
            logger.info("TenderDTO: " + tenderDTO);
            if (transaction != null) {
                transaction.rollback();
            }
           throw new Exception(e);
        }

        return true;
    }
}
