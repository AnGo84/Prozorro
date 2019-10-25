package ua.prozorro.timeMeasure;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.prozorro.sourceService.exchangeRates.ExchangeRateServiceNBU;
import ua.prozorro.properties.PropertyFields;
import ua.prozorro.sourceService.DataType;

import java.util.Date;
import java.util.List;

public class NBURatesTimeMeasureImp implements TimeMeasureInterface {
    private static final Logger logger = LogManager.getRootLogger();

    //2000- approximately time in milliseconds for saving one NBU rate to database
    private static final long TIME_FOR_SAVING_ONE_ITEMS = 1000;

    private PropertyFields propertyFields;

    public NBURatesTimeMeasureImp(PropertyFields propertyFields) {
        this.propertyFields = propertyFields;
    }

    @Override
    public ParsingResultData getListParsingData() throws Exception {
        Date start = new Date();
        ExchangeRateServiceNBU exchangeRateServiceNBU = new ExchangeRateServiceNBU(propertyFields);
        List<String> list = exchangeRateServiceNBU
                .getRatePagesList(DataType.NBU_RATES, propertyFields.getSearchDateFrom(),
                        propertyFields.getSearchDateTill());
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
        return 1;
    }

    @Override
    public long getTimeForSavingOneItems() {
        return TIME_FOR_SAVING_ONE_ITEMS;
    }
}
