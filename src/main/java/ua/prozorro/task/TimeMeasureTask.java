package ua.prozorro.task;

import javafx.concurrent.Task;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.prozorro.properties.PropertyFields;
import ua.prozorro.timeMeasure.ParsingResultData;
import ua.prozorro.timeMeasure.TimeMeasureFactory;
import ua.prozorro.timeMeasure.TimeMeasureInterface;

public class TimeMeasureTask extends Task<ParsingResultData> {
    private static final Logger logger = LogManager.getRootLogger();
    private PropertyFields propertyFields;
    private TimeMeasureInterface timeMeasureInterface;

    public TimeMeasureTask(PropertyFields propertyFields) {
        this.propertyFields = propertyFields;
        this.timeMeasureInterface = TimeMeasureFactory.getTimeMeasurer(propertyFields);
    }

    @Override
    protected ParsingResultData call() throws Exception {
        updateMessage("Start searching for '" + propertyFields.getSearchDateType().name() + "' \n");

        ParsingResultData resultData = timeMeasureInterface.getListParsingData();

        long timeForPageRecords = 0;
        String dataInfo = "";
        if (resultData.isHasData()) {
            dataInfo = "Found pages: " + resultData.getListSize() + " \n";
            timeForPageRecords = timeMeasureInterface.getPageContentParseTime();
        } else {
            dataInfo = "Data for '" + propertyFields.getSearchDateType().name() + "' not found! \n";
        }
        logger.info(dataInfo);
        updateMessage(dataInfo);

        //long totalTime = timeForPages + listSize * (timeForPageRecords + 100 * 2000);
        long totalTime = resultData.getParsingTime() + resultData.getListSize() * (timeForPageRecords * timeMeasureInterface.getTimeForSavingOneItems());
        logger.info("Finished. Total Time: " + totalTime + "ms \n");
        updateMessage("Finished. Total Time: " + totalTime + "ms \n");

        resultData.setParsingTime(totalTime);
        return resultData;
    }

}
