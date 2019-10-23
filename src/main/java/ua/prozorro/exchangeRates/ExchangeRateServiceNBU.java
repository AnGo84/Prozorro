package ua.prozorro.exchangeRates;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.prozorro.properties.AppProperty;
import ua.prozorro.properties.PropertyFields;
import ua.prozorro.sourceService.PageURLFactory;
import ua.prozorro.sourceService.DataType;
import ua.prozorro.utils.DateUtils;
import ua.prozorro.utils.FileUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.*;
@Deprecated
public class ExchangeRateServiceNBU {
	private static final Logger logger = LogManager.getRootLogger();
	
	private PropertyFields propertyFields;
	private PageURLFactory pageURLFactory;
	
	public ExchangeRateServiceNBU() {
	}
	
	public ExchangeRateServiceNBU(PropertyFields propertyFields) {
		this.propertyFields = propertyFields;
		this.pageURLFactory = new PageURLFactory(propertyFields);
	}
	
	public List<ExchangeRateNBU> getExchangeRatesNBUFromStringJSON(String stringJSON) throws JsonParseException {
		//https://futurestud.io/tutorials/gson-mapping-of-arrays-and-lists-of-objects
		//logger.info("Get STRING: " + stringJSON);
		Gson gson = new Gson();
		//ExchangeRateNBU[] nbus =gson.fromJson(stringJSON, ExchangeRateNBU[].class);
		
		Type founderListType = new TypeToken<ArrayList<ExchangeRateNBU>>() {}.getType();
		
		List<ExchangeRateNBU> rateNBUList = gson.fromJson(stringJSON, founderListType);
		
		return rateNBUList;
	}
	
	public List<ExchangeRateNBU> getRateContentFromURL(String url) throws JsonParseException, IOException {
		//logger.info("Get URL: " + url);
		String genreJson = FileUtils.getStringFromURL(url);
		return getExchangeRatesNBUFromStringJSON(genreJson);
	}
	
	public List<String> getRatePagesList(DataType dataType, Date dateFrom, Date dateTill)
			throws IOException, java.text.ParseException {
		return getRatePagesList(dataType, dateFrom, dateTill, false);
	}
	
	public List<String> getRatePagesList(DataType dataType, Date dateFrom, Date dateTill, boolean withPageElements)
			throws IOException, java.text.ParseException {
		if (propertyFields == null || propertyFields.getProperties() == null) {
			return null;
		}
		String startPageURL = pageURLFactory.getPageURL(dateFrom);
		
		logger.info("NBU Rates startPageURL: " + startPageURL);
		Date date = dateFrom;
		dateTill = getDate(dateTill);
		
		List<String> ratesURLList = new ArrayList<>();
		
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(dateFrom);
		
		Calendar endCalendar = new GregorianCalendar();
		endCalendar.setTime(dateTill);
		
		while (calendar.before(endCalendar) || calendar.equals(endCalendar)) {
			ratesURLList.add(startPageURL);
			if (withPageElements) {
				List<ExchangeRateNBU> pageContentList = getRateContentFromURL(startPageURL);
			}
			calendar.add(Calendar.DATE, 1);
			Date result = calendar.getTime();
			startPageURL = pageURLFactory.getPageURL(result);
		}
		
		return ratesURLList;
	}

	public Date getDate(Date date) throws ParseException {
		if (date == null) {
			date = DateUtils.parseDateToFormat(new Date(),
												propertyFields.getPropertiesStringValue(AppProperty.NBU_DATE_FORMAT));
		}
		return date;
	}
}
