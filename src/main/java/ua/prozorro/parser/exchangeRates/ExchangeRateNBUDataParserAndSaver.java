package ua.prozorro.parser.exchangeRates;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import ua.prozorro.entity.EventResultData;
import ua.prozorro.entity.PageElement;
import ua.prozorro.entity.exchangeRates.ExchangeRateNBUDTO;
import ua.prozorro.entity.mappers.exchangeRates.ExchangeRateNBUListMapper;
import ua.prozorro.exchangeRates.ExchangeRateNBU;
import ua.prozorro.exchangeRates.ExchangeRatesNBUPage;
import ua.prozorro.sourceService.exchangeRates.NBUExchangeRatePageService;
import ua.prozorro.parser.AbstractDataParserAndSaver;
import ua.prozorro.properties.AppProperty;
import ua.prozorro.properties.PropertyFields;
import ua.prozorro.repositories.ExchangeRateNBURepository;
import ua.prozorro.utils.DateUtils;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

public class ExchangeRateNBUDataParserAndSaver extends AbstractDataParserAndSaver {
    private static final Logger logger = LogManager.getRootLogger();

    //private ExchangeRateServiceNBU exchangeRateServiceNBU;
    private NBUExchangeRatePageService nbuExchangeRatePageService;

    private Calendar currentCalendar;
    private Calendar endCalendar;

    public ExchangeRateNBUDataParserAndSaver(PropertyFields propertyFields) {
        super(propertyFields);
        this.nbuExchangeRatePageService = new NBUExchangeRatePageService(propertyFields);
    }

    @Override
    public String startPageURL() {
        currentPageURL = nbuExchangeRatePageService.getPageURL(propertyFields.getSearchDateFrom());
        return currentPageURL;
    }

    @Override
    public void initDataByURL(String url) throws IOException, ParseException {
        currentCalendar = new GregorianCalendar();
        currentCalendar.setTime(propertyFields.getSearchDateFrom());
        endCalendar = new GregorianCalendar();
        endCalendar.setTime(nbuExchangeRatePageService.getDate(propertyFields.getSearchDateTill()));
    }

    @Override
    public boolean hasNextData() {
        return (currentCalendar.before(endCalendar) || currentCalendar.equals(endCalendar));
    }

    @Override
    public List<PageElement> getPageElementList() {
        List<PageElement> pageElements = new ArrayList<>();
        String currentDate = DateUtils.parseDateToString(currentCalendar.getTime(), propertyFields.getPropertiesStringValue(AppProperty.NBU_DATE_FORMAT));
        PageElement pageElement = new PageElement(currentPageURL, currentDate);
        pageElements.add(pageElement);
        return pageElements;
    }

    @Override
    public EventResultData checkExpireElement(PageElement pageElement) throws ParseException {
        Date pageElementDate = DateUtils.parseProzorroPageDateFromString(pageElement.getDateModified(),
                propertyFields.getPropertiesStringValue(AppProperty.NBU_DATE_FORMAT));
        EventResultData eventResultData = new EventResultData();
        if (propertyFields.getSearchDateTill().
                compareTo(DateUtils.parseDateToFormat(pageElementDate, propertyFields
                        .getPropertiesStringValue(AppProperty.NBU_DATE_FORMAT))) < 0) {

            eventResultData.setHasResult(true);
            eventResultData.setEventResult(propertyFields.getSearchDateType().getTypeName() + ": Страница № %d / %d, текущий № %d c id: " + pageElement.getId() + ", date: "
                    + pageElement.getDateModified() + ". Отклонён по дате \n");
        } else {
            eventResultData.setHasResult(false);
        }
        return eventResultData;
    }

    @Override
    public EventResultData parseAndSave(PageElement pageElement, Session session) throws Exception {
        ExchangeRateNBUListMapper exchangeRateNBUListMapper = new ExchangeRateNBUListMapper();
        EventResultData eventResultData = new EventResultData();
        String currentURL = pageElement.getId();
        List<ExchangeRateNBU> rateNBUList = null;
        try {
            ExchangeRatesNBUPage exchangeRatesNBUPage = nbuExchangeRatePageService.getObjectFromURL(currentURL);
            rateNBUList = exchangeRatesNBUPage.getExchangeRateNBUs();
            //text = rateNBUList.toString();
            ExchangeRateNBURepository nbuService = new ExchangeRateNBURepository(session);
            List<ExchangeRateNBUDTO> exchangeRateNBUDTOS =
                    exchangeRateNBUListMapper.convertToEntitiesList(rateNBUList);

            boolean saveResult = nbuService.saveExchangeRateNBUList(exchangeRateNBUDTOS);

            eventResultData.setId(currentPageURL);
            eventResultData.setHasResult(true);
            eventResultData.setEventResult(propertyFields.getSearchDateType().getTypeName() + ": Страница № %d / %d, текущий № %d c id: " +
                    pageElement.getId() + ", date: " + pageElement.getDateModified() + ". added/updated: " +
                    saveResult + " \n");
        } catch (Exception e) {
            logger.error("ERROR on URL: " + this.currentPageURL);
            logger.error("ERROR on Rates: " + rateNBUList);
            throw e;
        }
        return eventResultData;
    }

    @Override
    public boolean getNextData() throws Exception {
        currentCalendar.add(Calendar.DATE, 1);
        currentPageURL = nbuExchangeRatePageService.getPageURL(currentCalendar.getTime());
        return true;
    }
}
