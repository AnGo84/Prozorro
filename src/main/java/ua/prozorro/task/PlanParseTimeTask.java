package ua.prozorro.task;

import javafx.concurrent.Task;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.prozorro.entity.TenderDTOUtils;
import ua.prozorro.entity.tenders.TenderDTO;
import ua.prozorro.properties.AppProperty;
import ua.prozorro.properties.PropertyFields;
import ua.prozorro.prozorro.PageServiceProzorro;
import ua.prozorro.prozorro.ParsingResultData;
import ua.prozorro.prozorro.PlanDataServiceProzorro;
import ua.prozorro.prozorro.TenderDataServiceProzorro;
import ua.prozorro.prozorro.model.pages.ProzorroPageContent;
import ua.prozorro.prozorro.model.plans.PlanData;
import ua.prozorro.prozorro.model.tenders.TenderData;

import java.util.Date;
import java.util.List;


public class PlanParseTimeTask extends Task<ParsingResultData> {
    private static final Logger logger = LogManager.getRootLogger();
    private PropertyFields propertyFields;

    public PlanParseTimeTask(PropertyFields propertyFields) {
        this.propertyFields = propertyFields;
    }

    @Override
    protected ParsingResultData call() throws Exception {
        updateMessage("Start searching for '" + propertyFields.getSearchDateType().name() + "' \n");
        PageServiceProzorro pageServiceProzorro = new PageServiceProzorro(propertyFields);

        Date start = new Date();
        List<ProzorroPageContent> list = pageServiceProzorro
                .getPagesList(propertyFields.getSearchDateType(), propertyFields.getSearchDateFrom(), propertyFields.getSearchDateTill(), false);
        Date finish = new Date();

        long timeForPages = finish.getTime() - start.getTime();

        start = new Date();
        if (list != null && !list.isEmpty()) {
            logger.info("Found pages: " + list.size() + " \n");
            updateMessage("Found pages: " + list.size() + " \n");
            ProzorroPageContent pageContent = pageServiceProzorro
                    .getPageContentFromURL(pageServiceProzorro.getTenderPageURL(propertyFields.getSearchDateFrom()));

            PlanDataServiceProzorro planDataServiceProzorro = new PlanDataServiceProzorro(
                    propertyFields.getPropertiesStringValue(AppProperty.TENDER_START_PAGE) + "/");
            logger.info("Start getting Tenders from page \n");
            updateMessage("Start getting Tenders from page \n");
            List<PlanData> planDataOnPageList =
                    planDataServiceProzorro.getPlansDataFromPageContent(pageContent);

            if (planDataOnPageList != null) {
                int i = 0;
                for (PlanData planData : planDataOnPageList) {
                    i++;

                    /*logger.info("Get Tender № " + i + "\n");

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException interrupted) {
                        if (isCancelled()) {
                            updateMessage("Cancelled");
                            break;
                        }
                    }
                    */
                    updateMessage("Get Plan № " + i + "\n");

                    //TenderDTO tenderDTO = TenderDTOUtils.getTenderDTO(planData.getPlan());
                }
            }
        }else{
            updateMessage("'" + propertyFields.getSearchDateType().name() + "' not found! \n");
        }

        finish = new Date();
        long timeForPageTenders = finish.getTime() - start.getTime();

        //100- plans on page
        //1000- approximately time in milliseconds for saving one plan to database
        long totalTime = timeForPages + list.size() * (timeForPageTenders + 100 * 2000);
        logger.info("Finished. Total Time: " + totalTime + "\n");
        updateMessage("Finished Total Time: " + totalTime + "ms \n");

        ParsingResultData resultData = new ParsingResultData();
        resultData.setParsingTime(totalTime);
        resultData.setListSize(list.size());
        resultData.setHasData((list != null && !list.isEmpty()));
        return resultData;
    }

}
