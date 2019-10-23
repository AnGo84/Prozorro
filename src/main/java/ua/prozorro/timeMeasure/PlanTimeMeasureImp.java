package ua.prozorro.timeMeasure;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.prozorro.properties.PropertyFields;
import ua.prozorro.prozorro.model.pages.ProzorroPageContent;
import ua.prozorro.prozorro.service.PageServiceProzorro;
import ua.prozorro.prozorro.service.ProzorroPageDataService;

import java.util.Date;
import java.util.List;

public class PlanTimeMeasureImp implements TimeMeasureInterface {
    private static final Logger logger = LogManager.getRootLogger();

    //2000- approximately time in milliseconds for saving one plan to database
    private static final long TIME_FOR_SAVING_ONE_ITEMS = 2000;

    private PropertyFields propertyFields;

    public PlanTimeMeasureImp(PropertyFields propertyFields) {
        this.propertyFields = propertyFields;
    }

    @Override
    public ParsingResultData getListParsingData() throws Exception {
        Date start = new Date();
        /*PageServiceProzorro pageServiceProzorro = new PageServiceProzorro(propertyFields);
        List<ProzorroPageContent> list = pageServiceProzorro
                .getPagesList(propertyFields.getSearchDateType(), propertyFields.getSearchDateFrom(),
                        propertyFields.getSearchDateTill(), false);*/
        ProzorroPageDataService prozorroPageDataService = new ProzorroPageDataService(propertyFields);
        List<ProzorroPageContent> list = prozorroPageDataService.getPagesList();
        Date finish = new Date();
        long timeForPages = finish.getTime() - start.getTime();

        int listSize = 0;

        if (list != null && !list.isEmpty()) {
            listSize = list.size();
        }

        ParsingResultData resultData = new ParsingResultData();
        resultData.setParsingTime(timeForPages);
        resultData.setListSize(listSize);
        resultData.setHasData(listSize > 0);

        //return listSize;
        return resultData;
    }

    @Override
    public long getPageContentParseTime() throws Exception {
        /*Date start = new Date();
        PageServiceProzorro pageServiceProzorro = new PageServiceProzorro(propertyFields);
        ProzorroPageContent pageContent = pageServiceProzorro
                .getPageContentFromURL(pageServiceProzorro.getPageURL(propertyFields.getSearchDateFrom()));

            PlanDataServiceProzorro planDataServiceProzorro = new PlanDataServiceProzorro(
                    propertyFields.getPropertiesStringValue(AppProperty.PLAN_START_PAGE) + "/");
            logger.info("Start getting Plans from page \n");
            updateMessage("Start getting Plans from page \n");
            List<PlanData> planDataOnPageList =
                    planDataServiceProzorro.getPlansDataFromPageContent(pageContent);

            if (planDataOnPageList != null) {
                int i = 0;
                for (PlanData planData : planDataOnPageList) {
                    i++;
                    PlanDTO planDTO = PlanDTOUtils.getPlanDTO(planData.getPlan());
                }
            }

        Date finish = new Date();
        long timeForPageRecords = finish.getTime() - start.getTime();
         */
        //return timeForPageRecords;
        return 100;
    }

    @Override
    public long getTimeForSavingOneItems() {
        return TIME_FOR_SAVING_ONE_ITEMS;
    }
}
