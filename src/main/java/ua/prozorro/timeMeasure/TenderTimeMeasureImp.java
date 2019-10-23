package ua.prozorro.timeMeasure;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.prozorro.properties.PropertyFields;
import ua.prozorro.prozorro.model.pages.ProzorroPageContent;
import ua.prozorro.prozorro.service.PageServiceProzorro;
import ua.prozorro.prozorro.service.ProzorroPageDataService;

import java.util.Date;
import java.util.List;

public class TenderTimeMeasureImp implements TimeMeasureInterface {
    private static final Logger logger = LogManager.getRootLogger();
    //2000- approximately time in milliseconds for saving one tender to database
    private static final long TIME_FOR_SAVING_ONE_ITEMS = 2000;

    private PropertyFields propertyFields;

    public TenderTimeMeasureImp(PropertyFields propertyFields) {
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

        ProzorroPageContent pageContent = pageServiceProzorro
                .getPageContentFromURL(pageServiceProzorro.getTenderPageURL(propertyFields.getSearchDateFrom()));

        TenderDataServiceProzorro tenderDataServiceProzorro = new TenderDataServiceProzorro(
                propertyFields.getPropertiesStringValue(AppProperty.TENDER_START_PAGE) + "/");
        logger.info("Start getting Tenders from page \n");
        updateMessage("Start getting Tenders from page \n");
        List<TenderData> tenderDataOnPageList =
                tenderDataServiceProzorro.getTendersDataFromPageContent(pageContent);

        if (tenderDataOnPageList != null) {
            int i = 0;
            for (TenderData tenderData : tenderDataOnPageList) {
                i++;

                    //logger.info("Get Tender № " + i + "\n");

                try {
                    Thread.sleep(10);
                } catch (InterruptedException interrupted) {
                    if (isCancelled()) {
                        updateMessage("Cancelled");
                        break;
                    }
                }
                updateMessage("Get Tender № " + i + "\n");

                //TenderDTO tenderDTO = TenderDTOUtils.getTenderDTO(tenderData.getTender());
            }
        }

        Date finish = new Date();
        long timeForPageRecords = finish.getTime() - start.getTime();

        //return timeForPageRecords;
        */
        return 100;
    }

    @Override
    public long getTimeForSavingOneItems() {
        return TIME_FOR_SAVING_ONE_ITEMS;
    }
}
