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
import ua.prozorro.properties.AppProperty;
import ua.prozorro.properties.PropertyFields;
import ua.prozorro.timeMeasure.ParsingResultData;
import ua.prozorro.prozorro.model.contracts.ContractData;
import ua.prozorro.prozorro.service.ContractDataServiceProzorro;
import ua.prozorro.service.ContractService;
import ua.prozorro.service.PageService;

public class ContractDataParserAndSaverImp extends AbstractProzorroDataParserAndSaver {
    private static final Logger logger = LogManager.getRootLogger();

    public ContractDataParserAndSaverImp(PropertyFields propertyFields, ParsingResultData resultData) {
        super(propertyFields, resultData);
    }

    @Override
    public EventResultData parseAndSave(PageElement pageElement, Session session) throws Exception {
        ContractPageMapper contractPageMapper = new ContractPageMapper();
        ContractMapper contractMapper = new ContractMapper();

        ContractPageDTO contractPageDTO = contractPageMapper.convertToEntity(pageElement);
        PageService pageService = new PageService(session);
        //String text = "";
        EventResultData eventResultData = new EventResultData();
        ContractData contractData = null;
        ContractDTO contractDTO = null;
        try {
            boolean updatedPage = pageService.saveContractPage(contractPageDTO, session);
            if (updatedPage) {
                ContractService contractService = new ContractService(session);

                ContractDataServiceProzorro contractDataServiceProzorro = new ContractDataServiceProzorro(
                        propertyFields.getPropertiesStringValue(AppProperty.CONTRACT_START_PAGE) + "/");
                //text = pageElement.getId() + "\n";
                contractData = contractDataServiceProzorro.getContractDataFromPageElement(pageElement);
                //text = text + planData.toString();
                //text = planData.toString();
                //PlanDTO planDTO = PlanDTOUtils.getPlanDTO(planData.getPlan());
                contractDTO = contractMapper.convertToEntity(contractData.getContract());
                contractService.saveContract(contractDTO, session);
            }

            eventResultData.setId(currentPageURL);
            eventResultData.setHasResult(true);
            eventResultData.setEventResult(propertyFields.getSearchDateType().getTypeName() + ": Страница № %d /" +
                    resultData.getListSize() + ", текущий № %d c id: " +
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

}
