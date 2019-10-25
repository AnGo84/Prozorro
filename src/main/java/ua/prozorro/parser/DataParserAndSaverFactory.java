package ua.prozorro.parser;

import ua.prozorro.parser.exchangeRates.ExchangeRateNBUDataParserAndSaver;
import ua.prozorro.parser.prozorro.ContractDataParserAndSaverImp;
import ua.prozorro.parser.prozorro.PlanDataParserAndSaverImp;
import ua.prozorro.parser.prozorro.TenderDataParserAndSaverImp;
import ua.prozorro.properties.PropertyFields;
import ua.prozorro.sourceService.DataType;

public class DataParserAndSaverFactory {
	
	public static AbstractDataParserAndSaver getDataParserAndSaver(PropertyFields propertyFields) {
		if (propertyFields.getSearchDateType().equals(DataType.TENDERS)) {
			return new TenderDataParserAndSaverImp(propertyFields);
			
		} else if (propertyFields.getSearchDateType().equals(DataType.CONTRACTS)) {
			return new ContractDataParserAndSaverImp(propertyFields);
		} else if (propertyFields.getSearchDateType().equals(DataType.PLANS)) {
			return new PlanDataParserAndSaverImp(propertyFields);
		} else if (propertyFields.getSearchDateType().equals(DataType.NBU_RATES)) {
			return new ExchangeRateNBUDataParserAndSaver(propertyFields);
		}
		return null;
	}
	
}
