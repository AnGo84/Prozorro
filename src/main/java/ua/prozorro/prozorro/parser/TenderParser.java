package ua.prozorro.prozorro.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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

    private SessionFactory sessionFactory;

    private PageServiceProzorro pageServiceProzorro;
    private TenderDataServiceProzorro tenderDataServiceProzorro;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
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



        String startPageURL = pageServiceProzorro.getPageURL(dateFrom);
        logger.info("Start parsing from URL " + startPageURL);
        Transaction transaction = null;
        TenderData tenderData = null;
        PageDTO page = null;
        TenderDTO tenderDTO = null;
	    Session session = null;

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

                int tenderCount = 0;
                logger.info("Start parsing page: ");
                for (ProzorroPageElement pageElement : pageContent.getPageElementList()) {
                    /*if (!session.isOpen()) {
                        session = session.getSessionFactory().openSession();
                        logger.info("Opened session: " + session.isOpen());
                    }*/
	                session = sessionFactory.openSession();

	                PageService pageService = new PageService(session);
	                TenderService tenderService = new TenderService(session);

                    transaction = session.beginTransaction();

                    tenderCount++;
                    //tenderDataServiceProzorro.getTenderDatasFromPageContent(pageContent);
                    tenderData = tenderDataServiceProzorro.getTenderDataFromPageElement(pageElement);
                    //logger.info("Session open?: " + session.isOpen());
                    page = TenderDTOUtils.getPageDTO(pageElement);

                    //boolean updatedPage = pageService.savePage(page);
                    boolean updatedPage = pageService.savePage(page, session);
					if(updatedPage) {
						tenderDTO = TenderDTOUtils.getTenderDTO(tenderData.getTender());
						tenderService.saveTender(tenderDTO, session);
					}
	                logger.info("ProzorroPage № " + pageCount + ", tender on page № " + tenderCount + ", added/updated: " + updatedPage);

					session.flush();
	                session.clear();
                    transaction.commit();

					session.close();

                    //transaction.commit();
                }
	            logger.info("Get next page with URL: " + pageContent.getNextPage().getUri());
	            pageContent = pageServiceProzorro.getPageContentFromURL(pageContent.getNextPage().getUri());
	            nextOffsetDate = pageServiceProzorro.getDateFromPageOffset(pageContent.getNextPage().getOffset());

            }

        }
        catch (Exception e) {
            //catch (ParseException | IOException | Exception e){
            e.printStackTrace();
            logger.error("Parse error: " + page + ", msg: " + e.getMessage());

            logger.error("Tender: " + tenderData.getTender());
            logger.error("TenderDTO: " + tenderDTO);
            if (transaction != null) {
                transaction.rollback();
            }

           throw new Exception(e);
        }
        finally {
	        session.close();
        }

        return true;
    }
}
