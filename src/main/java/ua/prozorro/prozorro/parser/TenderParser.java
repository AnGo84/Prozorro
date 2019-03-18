package ua.prozorro.prozorro.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ua.prozorro.entity.TenderDTOUtils;
import ua.prozorro.entity.pages.TenderPageDTO;
import ua.prozorro.entity.tenders.TenderDTO;
import ua.prozorro.prozorro.model.pages.ProzorroPageContent;
import ua.prozorro.prozorro.model.pages.ProzorroPageElement;
import ua.prozorro.prozorro.model.tenders.TenderData;
import ua.prozorro.prozorro.service.PageServiceProzorro;
import ua.prozorro.prozorro.service.TenderDataServiceProzorro;
import ua.prozorro.service.PageService;
import ua.prozorro.service.TenderService;
import ua.prozorro.utils.DateUtils;

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
		String startPageURL = pageServiceProzorro.getTenderPageURL(dateFrom);
		logger.info("Start parsing from URL " + startPageURL);
		
		TenderPageDTO page = null;
		
		Session session = null;
		Transaction transaction = null;
		
		String text = "";
		
		int pageCount = 0;
		int pageElementCount = 0;
		try {
			dateTill = pageServiceProzorro.getDateTill(dateTill);
			
			ProzorroPageContent pageContent = pageServiceProzorro.getPageContentFromURL(startPageURL);
			
			Date nextOffsetDate = pageServiceProzorro.getDateFromPageOffset(pageContent.getNextPage().getOffset());
			if (nextOffsetDate.compareTo(dateTill) > 0) {
				nextOffsetDate = dateTill;
			}
			//logger.info("Get first ProzorroPage: " + pageContent);
			logger.info("Get first ProzorroPage");
			
			session = sessionFactory.openSession();
			while (dateTill.compareTo(nextOffsetDate) >= 0 && pageContent.getPageElementList() != null &&
				   !pageContent.getPageElementList().isEmpty()) {
				pageCount++;
				
				pageElementCount = 0;
				logger.info("Start parsing page №" + pageCount + ": ");
				
				for (ProzorroPageElement pageElement : pageContent.getPageElementList()) {
					pageElementCount++;
					
					PageService pageService = new PageService(session);
					
					transaction = session.beginTransaction();
					page = TenderDTOUtils.getPageDTO(pageElement);
					
					boolean updatedPage = pageService.saveTenderPage(page, session);
					if (updatedPage) {
						TenderService tenderService = new TenderService(session);
						TenderData tenderData = tenderDataServiceProzorro.getTenderDataFromPageElement(pageElement);
						text = tenderData.toString();
						TenderDTO tenderDTO = TenderDTOUtils.getTenderDTO(tenderData.getTender());
						tenderService.saveTender(tenderDTO, session);
					}
					logger.info("ProzorroPage № " + pageCount + ", tender on page № " + pageElementCount +
								", added/updated: " + updatedPage);
					
					session.flush();
					session.clear();
					transaction.commit();
				}
				logger.info("Get next page with URL: " + pageContent.getNextPage().getUri());
				pageContent = pageServiceProzorro.getPageContentFromURL(pageContent.getNextPage().getUri());
				nextOffsetDate = pageServiceProzorro.getDateFromPageOffset(pageContent.getNextPage().getOffset());
			}
			
		} catch (Exception e) {
			//catch (ParseException | IOException | Exception e){
			e.printStackTrace();
			logger.error(
					"Page № " + pageCount + ", tender № " + pageElementCount + ", Parse error: " + page + ", msg: " +
					e.getMessage());
			logger.error("Tender: " + text);
			
			if (transaction != null) {
				transaction.rollback();
			}
			
			throw new Exception(e);
		} finally {
			session.close();
		}
		
		return true;
	}
	
	public boolean parseAndSaveURL(String url) throws Exception {
		logger.info("Start parsing for URL " + url);
		
		TenderPageDTO page = null;
		
		Session session = null;
		Transaction transaction = null;
		
		String text = "";
		
		int pageCount = 0;
		int pageElementCount = 0;
		try {
			ProzorroPageContent pageContent = pageServiceProzorro.getPageContentFromURL(url);
			
			session = sessionFactory.openSession();
			
			pageElementCount = 0;
			logger.info("Start parsing page №" + pageCount + ": ");
			
			for (ProzorroPageElement pageElement : pageContent.getPageElementList()) {
				pageElementCount++;
				
				PageService pageService = new PageService(session);
				
				transaction = session.beginTransaction();
				page = TenderDTOUtils.getPageDTO(pageElement);
				
				boolean updatedPage = pageService.saveTenderPage(page, session);
				if (updatedPage) {
					TenderService tenderService = new TenderService(session);
					TenderData tenderData = tenderDataServiceProzorro.getTenderDataFromPageElement(pageElement);
					text = tenderData.toString();
					TenderDTO tenderDTO = TenderDTOUtils.getTenderDTO(tenderData.getTender());
					tenderService.saveTender(tenderDTO, session);
				}
				logger.info(
						"ProzorroPage № " + pageCount + ", tender on page № " + pageElementCount + ", added/updated: " +
						updatedPage);
				
				session.flush();
				session.clear();
				transaction.commit();
			}
			logger.info("Get next page with URL: " + pageContent.getNextPage().getUri());
			
		} catch (Exception e) {
			//catch (ParseException | IOException | Exception e){
			e.printStackTrace();
			logger.error(
					"Page № " + pageCount + ", tender № " + pageElementCount + ", Parse error: " + page + ", msg: " +
					e.getMessage());
			logger.error("Tender: " + text);
			
			if (transaction != null) {
				transaction.rollback();
			}
			
			throw new Exception(e);
		} finally {
			session.close();
		}
		
		return true;
	}
	
	public boolean parseAndSaveTender(String url) throws Exception {
		logger.info("Start parsing for URL " + url);
		
		TenderPageDTO page = null;
		
		Session session = null;
		Transaction transaction = null;
		
		String text = "";
		
		try {
			
			session = sessionFactory.openSession();
			transaction =session.beginTransaction();
			
			TenderService tenderService = new TenderService(session);
			logger.info("Start TENDER parse");
			TenderData tenderData = tenderDataServiceProzorro.getTenderDataFromURL(url);
			logger.info("TENDER: " + tenderData);
			text = "TENDER: " + tenderData;
			TenderDTO tenderDTO = TenderDTOUtils.getTenderDTO(tenderData.getTender());
			logger.info("TENDER DTO: " + tenderDTO);
			text = "TENDER DTO: " + tenderDTO;
			tenderService.saveTender(tenderDTO, session);
			
			session.flush();
			session.clear();
			transaction.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			
			logger.error("Error on Tender: " + text);
			logger.error("Error message: " + e.getMessage(), e);
			
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
