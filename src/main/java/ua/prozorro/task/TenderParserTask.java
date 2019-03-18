package ua.prozorro.task;

import javafx.concurrent.Task;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ua.prozorro.entity.TenderDTOUtils;
import ua.prozorro.entity.pages.TenderPageDTO;
import ua.prozorro.entity.tenders.TenderDTO;
import ua.prozorro.properties.AppProperty;
import ua.prozorro.properties.PropertyFields;
import ua.prozorro.prozorro.ParsingResultData;
import ua.prozorro.prozorro.model.pages.ProzorroPageContent;
import ua.prozorro.prozorro.model.pages.ProzorroPageElement;
import ua.prozorro.prozorro.model.tenders.TenderData;
import ua.prozorro.prozorro.service.PageServiceProzorro;
import ua.prozorro.prozorro.service.TenderDataServiceProzorro;
import ua.prozorro.service.PageService;
import ua.prozorro.service.TenderService;
import ua.prozorro.utils.DateUtils;

import java.util.Date;

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
		
		String currentPageURL = pageServiceProzorro.getTenderPageURL(propertyFields.getSearchDateFrom());
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
				
				for (ProzorroPageElement pageElement : pageContent.getPageElementList()) {
					
					Date pageDate = DateUtils.parseProzorroPageDateFromString(pageElement.getDateModified(),
																			  propertyFields.getPropertiesStringValue(
																					  AppProperty.DATE_FORMAT));
					if (propertyFields.getSearchDateTill().
							compareTo(DateUtils.parseDateToFormate(pageDate, propertyFields
									.getPropertiesStringValue(AppProperty.SHORT_DATE_FORMAT))) < 0) {
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
					pageElementCount++;
					page = TenderDTOUtils.getPageDTO(pageElement);
					PageService pageService = new PageService(session);
					
					transaction = session.beginTransaction();
					boolean updatedPage = pageService.saveTenderPage(page, session);
					if (updatedPage) {
						TenderService tenderService = new TenderService(session);
						
						TenderDataServiceProzorro tenderDataServiceProzorro = new TenderDataServiceProzorro(
								propertyFields.getPropertiesStringValue(AppProperty.TENDER_START_PAGE) + "/");
						text = pageElement.getId() + "\n";
						TenderData tenderData = tenderDataServiceProzorro.getTenderDataFromPageElement(pageElement);
						text = text + tenderData.toString();
						TenderDTO tenderDTO = TenderDTOUtils.getTenderDTO(tenderData.getTender());
						tenderService.saveTender(tenderDTO, session);
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
