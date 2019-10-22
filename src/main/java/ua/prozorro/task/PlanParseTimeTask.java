package ua.prozorro.task;

import javafx.concurrent.Task;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.prozorro.properties.PropertyFields;
import ua.prozorro.timeMeasure.ParsingResultData;
import ua.prozorro.prozorro.model.pages.ProzorroPageContent;
import ua.prozorro.prozorro.service.PageServiceProzorro;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Deprecated
public class PlanParseTimeTask extends Task<ParsingResultData> {
    private static final Logger logger = LogManager.getRootLogger();
    private PropertyFields propertyFields;

    public PlanParseTimeTask(PropertyFields propertyFields) {
        this.propertyFields = propertyFields;
    }

    @Override
    protected ParsingResultData call() throws Exception {
        updateMessage("Start searching for '" + propertyFields.getSearchDateType().name() + "' \n");

        ParsingResultData resultData = getListParsingData();

        long timeForPageRecords = 0;
        String dataInfo = "";
        if (resultData.isHasData()) {
            dataInfo = "Found pages: " + resultData.getListSize() + " \n";
            timeForPageRecords = getPageContentParseTime();
        } else {
            dataInfo = "Data for '" + propertyFields.getSearchDateType().name() + "' not found! \n";
        }
        logger.info(dataInfo);
        updateMessage(dataInfo);
        //100- plans on page
        //2000- approximately time in milliseconds for saving one plan to database
        //long totalTime = timeForPages + listSize * (timeForPageRecords + 100 * 2000);
        long totalTime = resultData.getParsingTime() + resultData.getListSize() * (timeForPageRecords * 2000);
        logger.info("Finished. Total Time: " + totalTime + "ms \n");
        updateMessage("Finished. Total Time: " + totalTime + "ms \n");

        /*ParsingResultData resultData = new ParsingResultData();
        resultData.setParsingTime(totalTime);
        resultData.setListSize(listSize);
        resultData.setHasData(listSize > 0);*/
        resultData.setParsingTime(totalTime);
        return resultData;
    }

    private ParsingResultData getListParsingData() {
        List<ProzorroPageContent> list = null;
        Date start = new Date();
        try {
            PageServiceProzorro pageServiceProzorro = new PageServiceProzorro(propertyFields);
            list = pageServiceProzorro
                    .getPagesList(propertyFields.getSearchDateType(), propertyFields.getSearchDateFrom(),
                            propertyFields.getSearchDateTill(), false);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
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

    private long getPageContentParseTime() {
        Date start = new Date();
        PageServiceProzorro pageServiceProzorro = new PageServiceProzorro(propertyFields);
        try {
            ProzorroPageContent pageContent = pageServiceProzorro
                    .getPageContentFromURL(pageServiceProzorro.getPageURL(propertyFields.getSearchDateFrom()));
        } catch (IOException e) {
            e.printStackTrace();
        }

            /*PlanDataServiceProzorro planDataServiceProzorro = new PlanDataServiceProzorro(
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
            }*/

        Date finish = new Date();
        long timeForPageRecords = finish.getTime() - start.getTime();

        //return timeForPageRecords;
        return 100;
    }
}
