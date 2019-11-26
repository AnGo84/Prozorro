package ua.prozorro.parser.prozorro;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import ua.prozorro.entity.EventResultData;
import ua.prozorro.entity.PageElement;
import ua.prozorro.entity.mappers.prozorroObjectMapper.PlanMapper;
import ua.prozorro.entity.mappers.prozorroObjectMapper.pages.PlanPageMapper;
import ua.prozorro.entity.pages.PlanPageDTO;
import ua.prozorro.entity.plans.PlanDTO;
import ua.prozorro.entity.plans.PlanJSONDTO;
import ua.prozorro.entity.tenders.TenderJSONDTO;
import ua.prozorro.properties.AppProperty;
import ua.prozorro.properties.PropertyFields;
import ua.prozorro.prozorro.model.plans.PlanData;
import ua.prozorro.sourceService.prozorro.ProzorroPlanDataService;
import ua.prozorro.repositories.PageRepository;
import ua.prozorro.repositories.PlanRepository;

public class PlanDataParserAndSaverImp extends AbstractProzorroDataParserAndSaver {
	private static final Logger logger = LogManager.getRootLogger();
	private ProzorroPlanDataService planDataService;
	
	public PlanDataParserAndSaverImp(PropertyFields propertyFields) {
		super(propertyFields);
		planDataService = new ProzorroPlanDataService(propertyFields);
	}
	
	@Override
	public EventResultData parseAndSave(PageElement pageElement, Session session) throws Exception {
		PlanPageMapper planPageMapper = new PlanPageMapper();
		PlanMapper planMapper = new PlanMapper();
		PlanPageDTO planPageDTO = planPageMapper.convertToEntity(pageElement);
		PageRepository pageRepository = new PageRepository(session);
		//String text = "";
		EventResultData eventResultData = new EventResultData();
		PlanData planData = null;
		PlanDTO planDTO = null;
		try {
			boolean updatedPage = pageRepository.savePlanPage(planPageDTO, session);
			if (updatedPage) {
				
				PlanJSONDTO planJSONDTO = parseAndSaveAsJSON(pageElement, session);
				
				if (!propertyFields.getPropertiesStringValue(AppProperty.PROZORRO_EXPORT_JSON_ONLY).equals("true")) {
					
					PlanRepository planRepository = new PlanRepository(session);

                /*PlanDataServiceProzorro planDataServiceProzorro = new PlanDataServiceProzorro(
                        propertyFields.getPropertiesStringValue(AppProperty.PLAN_START_PAGE) + "/");*/
					
					//text = pageElement.getId() + "\n";
					//planData = planDataService.getObjectByPageElement(pageElement);
					planData = planDataService.getObjectFromStringJSON(planJSONDTO.getModel());
					
					
					//text = text + planData.toString();
					//text = planData.toString();
					//PlanDTO planDTO = PlanDTOUtils.getPlanDTO(planData.getPlan());
					planDTO = planMapper.convertToEntity(planData.getPlan());
					planRepository.savePlan(planDTO, session);
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
			logger.error("ERROR on Plan: " + planPageDTO);
			//logger.error("ERROR for element ID: " + pageElement.getId());
			logger.error("ERROR on Prozorro Plan: " + planData);
			logger.error("ERROR on converted Plan: " + planDTO);
			//logger.error("ERROR message : " + e.getClass());
			throw e;
		}
		return eventResultData;
	}
	
	public PlanJSONDTO parseAndSaveAsJSON(PageElement pageElement, Session session) throws Exception {
		if (session == null || !session.isOpen()) {
			throw new Exception("Session did not set");
		}
		PlanJSONDTO planJSONDTO;
		try {
			String modelJSON = planDataService.getJSONModelByPageElement(pageElement);
			planJSONDTO = new PlanJSONDTO(pageElement.getId(), pageElement.getDateModified(), modelJSON);
			session.saveOrUpdate(planJSONDTO);
			logger.info("UpdateOrSave PlanJSON: " + planJSONDTO + "\n");
            /*
		session.flush();
		session.clear();
         */
		} catch (Exception e) {
			logger.error("Error on UpdateOrSave PlanJSON for PAGE: " + pageElement + "\n", e);
			throw e;
		}
		
		return planJSONDTO;
	}
	
}
