package ua.prozorro.parser;

import ua.prozorro.parser.exchangeRates.ExchangeRateNBUDataParserAndSaver;
import ua.prozorro.parser.prozorro.ContractDataParserAndSaverImp;
import ua.prozorro.parser.prozorro.PlanDataParserAndSaverImp;
import ua.prozorro.parser.prozorro.TenderDataParserAndSaverImp;
import ua.prozorro.properties.PropertyFields;
import ua.prozorro.prozorro.model.DataType;
import ua.prozorro.timeMeasure.ParsingResultData;

public class DataParserAndSaverFactory {

    public static AbstractDataParserAndSaver getDataParserAndSaver(PropertyFields propertyFields, ParsingResultData resultData) {
        if (propertyFields.getSearchDateType().equals(DataType.TENDERS)) {
            return new TenderDataParserAndSaverImp(propertyFields, resultData);

        } else if (propertyFields.getSearchDateType().equals(DataType.CONTRACTS)) {
            return new ContractDataParserAndSaverImp(propertyFields, resultData);
        } else if (propertyFields.getSearchDateType().equals(DataType.PLANS)) {
            return new PlanDataParserAndSaverImp(propertyFields, resultData);
        } else if (propertyFields.getSearchDateType().equals(DataType.NBU_RATES)) {
            return new ExchangeRateNBUDataParserAndSaver(propertyFields, resultData);
        }
        return null;
    }

}
