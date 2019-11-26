package ua.prozorro.parser.prozorro;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import ua.prozorro.entity.EventResultData;
import ua.prozorro.entity.PageElement;
import ua.prozorro.entity.mappers.prozorroObjectMapper.TenderMapper;
import ua.prozorro.entity.mappers.prozorroObjectMapper.pages.TenderPageMapper;
import ua.prozorro.entity.pages.TenderPageDTO;
import ua.prozorro.entity.tenders.TenderDTO;
import ua.prozorro.entity.tenders.TenderJSONDTO;
import ua.prozorro.properties.AppProperty;
import ua.prozorro.properties.PropertyFields;
import ua.prozorro.prozorro.model.tenders.TenderData;
import ua.prozorro.sourceService.prozorro.ProzorroTenderDataService;
import ua.prozorro.repositories.PageRepository;
import ua.prozorro.repositories.TenderRepository;

public class TenderDataParserAndSaverImp extends AbstractProzorroDataParserAndSaver {
	private static final Logger logger = LogManager.getRootLogger();
	
	private ProzorroTenderDataService prozorroTenderDataService;
	
	public TenderDataParserAndSaverImp(PropertyFields propertyFields) {
		super(propertyFields);
		prozorroTenderDataService = new ProzorroTenderDataService(propertyFields);
	}
	
	
	@Override
	public EventResultData parseAndSave(PageElement pageElement, Session session) throws Exception {
		TenderPageMapper tenderPageMapper = new TenderPageMapper();
		TenderMapper tenderMapper = new TenderMapper();
		TenderPageDTO tenderPageDTO = tenderPageMapper.convertToEntity(pageElement);
		
		PageRepository pageRepository = new PageRepository(session);
		//String text = "";
		EventResultData eventResultData = new EventResultData();
		TenderData tenderData = null;
		TenderDTO tenderDTO = null;
		try {
			boolean updatedPage = pageRepository.saveTenderPage(tenderPageDTO, session);
			if (updatedPage) {
                TenderJSONDTO tenderJSONDTO = parseAndSaveAsJSON(pageElement,session);
                
                if (!propertyFields.getPropertiesStringValue(AppProperty.PROZORRO_EXPORT_JSON_ONLY).equals("true")) {
					TenderRepository TenderRepository = new TenderRepository(session);
    
                /*TenderDataServiceProzorro TenderDataServiceProzorro = new TenderDataServiceProzorro(
                        propertyFields.getPropertiesStringValue(AppProperty.TENDER_START_PAGE) + "/");*/
					
					//text = pageElement.getId() + "\n";
                    
                    //tenderData = prozorroTenderDataService.getObjectByPageElement(pageElement);
					tenderData = prozorroTenderDataService.getObjectFromStringJSON(tenderJSONDTO.getModel());
					
					
					//text = text + TenderData.toString();
					//text = TenderData.toString();
					//TenderDTO TenderDTO = TenderDTOUtils.getTenderDTO(TenderData.getTender());
					tenderDTO = tenderMapper.convertToEntity(tenderData.getTender());
					TenderRepository.saveTender(tenderDTO, session);
				}
			}
			
			eventResultData.setId(currentPageURL);
			eventResultData.setHasResult(true);
			eventResultData.setEventResult(
					propertyFields.getSearchDateType().getTypeName() + ": Страница № %d / %d, текущий № %d c id: " +
					pageElement.getId() + ", date: " + pageElement.getDateModified() + ". added/updated: " +
					updatedPage + " \n");
		} catch (Exception e) {
			logger.error("ERROR on URL: " + this.currentPageURL);
			logger.error("ERROR on Tender: " + tenderPageDTO);
			//logger.error("ERROR for element ID: " + pageElement.getId());
			logger.error("ERROR on Prozorro Tender: " + tenderData);
			logger.error("ERROR on converted Tender: " + tenderDTO);
			//logger.error("ERROR message : " + e.getClass());
			throw e;
		}
		return eventResultData;
	}
	
	
	public TenderJSONDTO parseAndSaveAsJSON(PageElement pageElement, Session session) throws Exception {
		if (session == null || !session.isOpen()) {
			throw new Exception("Session did not set");
		}
        TenderJSONDTO tenderJSONDTO;
		try {
            String modelJSON = prozorroTenderDataService.getJSONModelByPageElement(pageElement);
            tenderJSONDTO = new TenderJSONDTO(pageElement.getId(), pageElement.getDateModified(), modelJSON);
            session.saveOrUpdate(tenderJSONDTO);
            logger.info("UpdateOrSave TenderJSON: " + tenderJSONDTO + "\n");
            /*
		session.flush();
		session.clear();
         */
        }
		catch (Exception e){
            logger.error("Error on UpdateOrSave TenderJSON for PAGE: " + pageElement + "\n", e);
            throw e;
        }
        
		return tenderJSONDTO;
	}
	
	
}
