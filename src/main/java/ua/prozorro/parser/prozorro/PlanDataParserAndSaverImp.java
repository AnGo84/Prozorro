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
import ua.prozorro.properties.AppProperty;
import ua.prozorro.properties.PropertyFields;
import ua.prozorro.timeMeasure.ParsingResultData;
import ua.prozorro.prozorro.model.plans.PlanData;
import ua.prozorro.prozorro.service.PlanDataServiceProzorro;
import ua.prozorro.service.PageService;
import ua.prozorro.service.PlanService;

public class PlanDataParserAndSaverImp extends AbstractProzorroDataParserAndSaver {
    private static final Logger logger = LogManager.getRootLogger();

    public PlanDataParserAndSaverImp(PropertyFields propertyFields, ParsingResultData resultData) {
        super(propertyFields, resultData);
    }

    @Override
    public EventResultData parseAndSave(PageElement pageElement, Session session) throws Exception {
        PlanPageMapper planPageMapper = new PlanPageMapper();
        PlanMapper planMapper = new PlanMapper();
        PlanPageDTO planPageDTO = planPageMapper.convertToEntity(pageElement);
        PageService pageService = new PageService(session);
        //String text = "";
        EventResultData eventResultData = new EventResultData();
        PlanData planData = null;
        PlanDTO planDTO = null;
        try {
            boolean updatedPage = pageService.savePlanPage(planPageDTO, session);
            if (updatedPage) {
                PlanService planService = new PlanService(session);

                PlanDataServiceProzorro planDataServiceProzorro = new PlanDataServiceProzorro(
                        propertyFields.getPropertiesStringValue(AppProperty.PLAN_START_PAGE) + "/");
                //text = pageElement.getId() + "\n";
                planData = planDataServiceProzorro.getPlanDataFromPageElement(pageElement);
                //text = text + planData.toString();
                //text = planData.toString();
                //PlanDTO planDTO = PlanDTOUtils.getPlanDTO(planData.getPlan());
                planDTO = planMapper.convertToEntity(planData.getPlan());
                planService.savePlan(planDTO, session);
            }

            eventResultData.setId(currentPageURL);
            eventResultData.setHasResult(true);
            eventResultData.setEventResult(propertyFields.getSearchDateType().getTypeName() + ": Страница № %d /" +
                    resultData.getListSize() + ", текущий № %d c id: " +
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


}
