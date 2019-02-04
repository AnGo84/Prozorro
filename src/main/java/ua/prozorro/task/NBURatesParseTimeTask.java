package ua.prozorro.task;

import javafx.concurrent.Task;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.prozorro.exchangeRates.ExchangeRateServiceNBU;
import ua.prozorro.properties.PropertyFields;
import ua.prozorro.prozorro.ParsingResultData;
import ua.prozorro.prozorro.model.DataType;

import java.util.Date;
import java.util.List;


public class NBURatesParseTimeTask extends Task<ParsingResultData> {
	private static final Logger logger = LogManager.getRootLogger();
	private PropertyFields propertyFields;
	
	public NBURatesParseTimeTask(PropertyFields propertyFields) {
		this.propertyFields = propertyFields;
	}
	
	@Override
	protected ParsingResultData call() throws Exception {
		updateMessage("Start searching for '" + propertyFields.getSearchDateType().name() + "' \n");
		
		ExchangeRateServiceNBU exchangeRateServiceNBU = new ExchangeRateServiceNBU(propertyFields);
		
		Date start = new Date();
		
		List<String> list = exchangeRateServiceNBU
				.getRatePagesList(DataType.NBU_RATES, propertyFields.getSearchDateFrom(),
								  propertyFields.getSearchDateTill());
		logger.info("Found pages: " + list.size() + " \n");
		updateMessage("Found pages: " + list.size() + " \n");
		Date finish = new Date();
		long timeForPages = finish.getTime() - start.getTime();
		
		//System.out.println("Time for NBU Rates pages: " + timeForPages);
		
		//1000- approximately time in milliseconds for saving daily rates to database
		long totalTime = timeForPages + list.size() * 1000;
		
		logger.info("Finished. Total Time: " + totalTime + "ms \n");
		updateMessage("Finished Total Time: " + totalTime + "ms \n");
		
		ParsingResultData resultData = new ParsingResultData();
		resultData.setParsingTime(totalTime);
		resultData.setListSize(list.size());
		resultData.setHasData((list != null && !list.isEmpty()));
		return resultData;
	}
	
}
