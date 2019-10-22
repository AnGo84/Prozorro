package ua.prozorro.timeMeasure;

public interface TimeMeasureInterface {
    ParsingResultData getListParsingData() throws Exception;

    long getPageContentParseTime() throws Exception;

    long getTimeForSavingOneItems();
}
