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
import ua.prozorro.properties.PropertyFields;
import ua.prozorro.prozorro.model.tenders.TenderData;
import ua.prozorro.prozorro.service.ProzorroTenderDataService;
import ua.prozorro.repositories.PageRepository;
import ua.prozorro.repositories.TenderRepository;
import ua.prozorro.timeMeasure.ParsingResultData;

public class TenderDataParserAndSaverImp extends AbstractProzorroDataParserAndSaver {
    private static final Logger logger = LogManager.getRootLogger();

    public TenderDataParserAndSaverImp(PropertyFields propertyFields, ParsingResultData resultData) {
        super(propertyFields, resultData);
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
                TenderRepository TenderRepository = new TenderRepository(session);

                /*TenderDataServiceProzorro TenderDataServiceProzorro = new TenderDataServiceProzorro(
                        propertyFields.getPropertiesStringValue(AppProperty.TENDER_START_PAGE) + "/");*/
                ProzorroTenderDataService prozorroTenderDataService = new ProzorroTenderDataService(propertyFields);
                //text = pageElement.getId() + "\n";
                tenderData = prozorroTenderDataService.getObjectByPageElement(pageElement);
                //text = text + TenderData.toString();
                //text = TenderData.toString();
                //TenderDTO TenderDTO = TenderDTOUtils.getTenderDTO(TenderData.getTender());
                tenderDTO = tenderMapper.convertToEntity(tenderData.getTender());
                TenderRepository.saveTender(tenderDTO, session);
            }

            eventResultData.setId(currentPageURL);
            eventResultData.setHasResult(true);
            eventResultData.setEventResult(propertyFields.getSearchDateType().getTypeName() + ": Страница № %d /" +
                    resultData.getListSize() + ", текущий № %d c id: " +
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


}
