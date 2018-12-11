package ua.prozorro.prozorro.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.prozorro.model.pages.PageContent;
import ua.prozorro.model.pages.PageElement;
import ua.prozorro.model.tenders.TenderData;
import ua.prozorro.prozorro.PageServiceProzorro;
import ua.prozorro.prozorro.TenderDataServiceProzorro;
import ua.prozorro.service.PageService;
import ua.prozorro.service.TenderService;
import ua.prozorro.utils.DateUtils;

import java.io.IOException;
import java.text.ParseException;
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
		logger.info("Start parsing for period from " + DateUtils.dateToString(dateFrom) + " till " + DateUtils.dateToString(dateTill));
		PageService pageService = new PageService(session);
		TenderService tenderService = new TenderService(session);

		String startPageURL = pageServiceProzorro.getPageURL(dateFrom);
		logger.info("Start parsing from URL " + startPageURL);
		Transaction transaction = null;
		try {
			dateTill = pageServiceProzorro.getDateTill(dateTill);

			PageContent pageContent = pageServiceProzorro.getPageContentFromURL(startPageURL);

			Date nextOffsetDate = pageServiceProzorro.getDateFromPageOffset(pageContent.getNextPage().getOffset());
			//logger.info("Get first Page: " + pageContent);
			logger.info("Get first Page");
			int page = 0;


			while (dateTill.compareTo(nextOffsetDate) >= 0 && pageContent.getPageElementList() != null && !pageContent.getPageElementList().isEmpty()) {
				page++;

				if (!session.isOpen()) {
					session = session.getSessionFactory().openSession();
				}
				transaction = session.beginTransaction();

				int tender = 0;
				logger.info("Start parsing page: ");
				for (PageElement pageElement : pageContent.getPageElementList()) {
					tender++;
					//tenderDataServiceProzorro.getTenderDatasFromPageContent(pageContent);
					TenderData tenderData = tenderDataServiceProzorro.getTenderDataFromPageElement(pageElement);

					//pageService.savePageElement(pageElement);
					//tenderService.saveTender(tenderData.getTender());
					logger.info("Page " + page + ", tender " + tender);
				}

				//logger.info("Get next page with URL: " + pageContent.getNextPage().getUri());
				pageContent = pageServiceProzorro.getPageContentFromURL(pageContent.getNextPage().getUri());
				nextOffsetDate = pageServiceProzorro.getDateFromPageOffset(pageContent.getNextPage().getOffset());

				transaction.commit();

			}

		} catch (ParseException | IOException e) {
			e.printStackTrace();
			logger.info("Parse error: " + e.getMessage());
			if (transaction != null) {
				transaction.rollback();
			}


			throw new Exception(e);
		}

		return true;
	}
}
