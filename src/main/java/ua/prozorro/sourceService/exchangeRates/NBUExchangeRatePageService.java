package ua.prozorro.sourceService.exchangeRates;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.prozorro.entity.PageElement;
import ua.prozorro.exchangeRates.ExchangeRateNBU;
import ua.prozorro.exchangeRates.ExchangeRatesNBUPage;
import ua.prozorro.properties.AppProperty;
import ua.prozorro.properties.PropertyFields;
import ua.prozorro.sourceService.AbstractSourceDataService;
import ua.prozorro.utils.DateUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.*;

public class NBUExchangeRatePageService extends AbstractSourceDataService<ExchangeRatesNBUPage>{
    private static final Logger logger = LogManager.getRootLogger();


    public NBUExchangeRatePageService(PropertyFields propertyFields) {
        super(ExchangeRatesNBUPage.class, propertyFields);
    }

    @Override
    public ExchangeRatesNBUPage getObjectFromStringJSON(String stringJSON) throws JsonParseException {
        //https://futurestud.io/tutorials/gson-mapping-of-arrays-and-lists-of-objects
        //logger.info("Get STRING: " + stringJSON);
        Gson gson = new Gson();
        //ExchangeRateNBU[] nbus =gson.fromJson(stringJSON, ExchangeRateNBU[].class);

        Type founderListType = new TypeToken<ArrayList<ExchangeRateNBU>>() {
        }.getType();

        List<ExchangeRateNBU> rateNBUList = gson.fromJson(stringJSON, founderListType);
        ExchangeRatesNBUPage exchangeRatesNBUPage = new ExchangeRatesNBUPage();
        exchangeRatesNBUPage.setExchangeRateNBUs(rateNBUList);
        return exchangeRatesNBUPage;
    }


    @Override
    public List<ExchangeRatesNBUPage> getPagesList() throws IOException, ParseException {
        if (propertyFields == null || propertyFields.getProperties() == null) {
            return null;
        }
        String startPageURL = pageURLFactory.getPageURL(propertyFields.getSearchDateFrom());

        logger.info("NBU Rates startPageURL: " + startPageURL);
        //Date date = dateFrom;
        Date dateTill = getDate(propertyFields.getSearchDateTill());

		List<ExchangeRatesNBUPage> exchangeRatesNBUPages = new ArrayList<>();

        Calendar currentCalendar = new GregorianCalendar();
        currentCalendar.setTime(propertyFields.getSearchDateFrom());

        Calendar endCalendar = new GregorianCalendar();
        endCalendar.setTime(dateTill);

        while (currentCalendar.before(endCalendar) || currentCalendar.equals(endCalendar)) {
			String currentDateString = DateUtils.parseDateToString(currentCalendar.getTime(), propertyFields.getPropertiesStringValue(AppProperty.NBU_DATE_FORMAT));
			PageElement pageElement = new PageElement(startPageURL,currentDateString);
			ExchangeRatesNBUPage exchangeRatesNBUPage = getObjectFromURL(startPageURL);
			exchangeRatesNBUPage.setPageElement(pageElement);
			exchangeRatesNBUPages.add(exchangeRatesNBUPage);
            currentCalendar.add(Calendar.DATE, 1);
            Date result = currentCalendar.getTime();
            startPageURL = pageURLFactory.getPageURL(result);
        }

        return exchangeRatesNBUPages;
    }

    @Override
    public Date getDate(Date date) throws ParseException {
        if (date == null) {
            date = DateUtils.parseDateToFormat(new Date(), propertyFields.getPropertiesStringValue(AppProperty.NBU_DATE_FORMAT));
        }
        return date;
    }
}
