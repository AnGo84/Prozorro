package ua.prozorro.parser.prozorro;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import ua.prozorro.entity.EventResultData;
import ua.prozorro.entity.PageElement;
import ua.prozorro.entity.mappers.prozorroObjectMapper.ContractMapper;
import ua.prozorro.entity.mappers.prozorroObjectMapper.pages.ContractPageMapper;
import ua.prozorro.entity.pages.ContractPageDTO;
import ua.prozorro.entity.tenders.ContractDTO;
import ua.prozorro.entity.tenders.ContractJSONDTO;
import ua.prozorro.properties.AppProperty;
import ua.prozorro.properties.PropertyFields;
import ua.prozorro.prozorro.model.contracts.ContractData;
import ua.prozorro.repositories.ContractRepository;
import ua.prozorro.repositories.PageRepository;
import ua.prozorro.sourceService.prozorro.ProzorroContractDataService;

public class ContractDataParserAndSaverImp extends AbstractProzorroDataParserAndSaver {
	private static final Logger logger = LogManager.getRootLogger();
	private ProzorroContractDataService contractDataService;
	
	public ContractDataParserAndSaverImp(PropertyFields propertyFields) {
		super(propertyFields);
		contractDataService = new ProzorroContractDataService(propertyFields);
	}
	
	@Override
	public EventResultData parseAndSave(PageElement pageElement, Session session) throws Exception {
		ContractPageMapper contractPageMapper = new ContractPageMapper();
		ContractMapper contractMapper = new ContractMapper();
		
		ContractPageDTO contractPageDTO = contractPageMapper.convertToEntity(pageElement);
		PageRepository pageRepository = new PageRepository(session);
		//String text = "";
		EventResultData eventResultData = new EventResultData();
		ContractData contractData = null;
		ContractDTO contractDTO = null;
		try {
			boolean updatedPage = pageRepository.saveContractPage(contractPageDTO, session);
			if (updatedPage) {
				ContractJSONDTO contractJSONDTO = parseAndSaveAsJSON(pageElement, session);
				
				if (!propertyFields.getPropertiesStringValue(AppProperty.PROZORRO_EXPORT_JSON_ONLY).equals("true")) {
					
					ContractRepository contractRepository = new ContractRepository(session);

                /*ContractDataServiceProzorro contractDataServiceProzorro = new ContractDataServiceProzorro(
                        propertyFields.getPropertiesStringValue(AppProperty.CONTRACT_START_PAGE) + "/");*/
					
					//text = pageElement.getId() + "\n";
					//contractData = contractDataService.getObjectByPageElement(pageElement);
					contractData = contractDataService.getObjectFromStringJSON(contractJSONDTO.getModel());;
					
					//text = text + planData.toString();
					//text = planData.toString();
					//PlanDTO planDTO = PlanDTOUtils.getPlanDTO(planData.getPlan());
					contractDTO = contractMapper.convertToEntity(contractData.getContract());
					contractRepository.saveContract(contractDTO, session);
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
			logger.error("ERROR on Plan: " + contractPageDTO);
			//logger.error("ERROR for element ID: " + pageElement.getId());
			logger.error("ERROR on Prozorro Plan: " + contractData);
			logger.error("ERROR on converted Plan: " + contractDTO);
			//logger.error("ERROR message : " + e.getClass());
			throw e;
		}
		return eventResultData;
	}
	
	public ContractJSONDTO parseAndSaveAsJSON(PageElement pageElement, Session session) throws Exception {
		if (session == null || !session.isOpen()) {
			throw new Exception("Session did not set");
		}
		ContractJSONDTO contractJSONDTO;
		try {
			String modelJSON = contractDataService.getJSONModelByPageElement(pageElement);
			contractJSONDTO = new ContractJSONDTO(pageElement.getId(), pageElement.getDateModified(), modelJSON);
			session.saveOrUpdate(contractJSONDTO);
			logger.info("UpdateOrSave ContractJSON: " + contractJSONDTO + "\n");
            /*
		session.flush();
		session.clear();
         */
		}
		catch (Exception e){
			logger.error("Error on UpdateOrSave ContractJSON for PAGE: " + pageElement + "\n", e);
			throw e;
		}
		
		return contractJSONDTO;
	}
	
}
