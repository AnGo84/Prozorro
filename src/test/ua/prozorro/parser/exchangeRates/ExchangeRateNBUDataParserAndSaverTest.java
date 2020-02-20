package ua.prozorro.parser.exchangeRates;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.prozorro.entity.EventResultData;
import ua.prozorro.entity.PageElement;
import ua.prozorro.sourceService.DataType;
import ua.prozorro.temp.TestPropertyFieldsFactory;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ExchangeRateNBUDataParserAndSaverTest {
    private static final Logger logger = LogManager.getRootLogger();
    private ExchangeRateNBUDataParserAndSaver exchangeRateNBUDataParserAndSaver;

    @Mock
    private ExchangeRateNBURepository exchangeRateNBURepository;

    @BeforeEach
    public void beforeEach() {
        logger.info("Start before Each ");
        try {
            PropertyFields propertyFields = TestPropertyFieldsFactory.getTestPropertyFields("2019-10-15", "2019-10-17", DataType.NBU_RATES);
            exchangeRateNBUDataParserAndSaver = new ExchangeRateNBUDataParserAndSaver(propertyFields);
            //logger.info("ExchangeRateNBUDataParserAndSaver: " + exchangeRateNBUDataParserAndSaver);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        MockitoAnnotations.initMocks(this);
    }

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void startPageURL() {
        logger.info("Start startPageURL");
        String url = exchangeRateNBUDataParserAndSaver.startPageURL();
        assertEquals("https://bank.gov.ua/NBU_Exchange/exchange?date=15.10.2019&json", url, "NBU URL for 15.10.2019");

    }

    @Test
    public void initDataByURL() {
        try {
            exchangeRateNBUDataParserAndSaver.initDataByURL("https://bank.gov.ua/NBU_Exchange/exchange?date=15.10.2019&json");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

    }
    //@Test(expected = NullPointerException.class)
   /* @Test(expected = NullPointerException.class)
    void initDataByURLWillFail() {

        try {
            exchangeRateNBUDataParserAndSaver.initDataByURL(null);
            //fail();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }*/

    @Test
    public void hasNextData() throws IOException, ParseException {
        String url = exchangeRateNBUDataParserAndSaver.startPageURL();
        exchangeRateNBUDataParserAndSaver.initDataByURL(url);
        boolean hasNext = exchangeRateNBUDataParserAndSaver.hasNextData();
        logger.info("Current url: " + url);
        assertTrue(hasNext, "Must have next date for 15.10.2019");
    }

    @Test
    public void getPageElementList() throws IOException, ParseException {
        String url = exchangeRateNBUDataParserAndSaver.startPageURL();
        exchangeRateNBUDataParserAndSaver.initDataByURL(url);
        List<PageElement> pageElements = exchangeRateNBUDataParserAndSaver.getPageElementList();
        logger.info("List<PageElement>: " + pageElements);
        assertNotNull(pageElements);
        assertNotNull(pageElements.isEmpty());
        logger.info("List<PageElement>[0]: " + pageElements.get(0));
        assertFalse(pageElements.get(0) == null);
    }

    @Test
    void checkExpireElement() {
        String url = exchangeRateNBUDataParserAndSaver.startPageURL();
        try {
            exchangeRateNBUDataParserAndSaver.initDataByURL(url);
            List<PageElement> pageElements = exchangeRateNBUDataParserAndSaver.getPageElementList();
            logger.info("List<PageElement>[0]: " + pageElements.get(0));
            EventResultData eventResultData = exchangeRateNBUDataParserAndSaver.checkExpireElement(pageElements.get(0));
            logger.info("EventResultData: " + eventResultData);
            assertFalse(eventResultData.isHasResult(), "Page '" + url + "' must have data");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Test
    void parseAndSave() throws Exception {
        /*doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                Boolean saveResult = true;
                return null;
            }
        }).when(exchangeRateNBURepository.saveExchangeRateNBUList(any()));
        Boolean saveResult = new Boolean(false);*/
        when(exchangeRateNBURepository.saveExchangeRateNBUList(any())).thenReturn(new Boolean(true));
        assertTrue(exchangeRateNBURepository.saveExchangeRateNBUList(null), "Saving must happen without mistakes");
    }

    //Throwing an exception from the mocked method
    @Test
    public void parseAndSave_throwsException() throws Exception {
        when(exchangeRateNBURepository.saveExchangeRateNBUList(any())).thenThrow(Exception.class);
        assertThrows(Exception.class, () -> {
            logger.info("Try to throw Exception");
            exchangeRateNBURepository.saveExchangeRateNBUList(null);
        });
    }

    @Test
    void getNextData() throws Exception {
        String url = exchangeRateNBUDataParserAndSaver.startPageURL();
        exchangeRateNBUDataParserAndSaver.initDataByURL(url);
        boolean returnNext = exchangeRateNBUDataParserAndSaver.getNextData();
        logger.info("Returned next: " + returnNext);
        logger.info("Next data: " + exchangeRateNBUDataParserAndSaver.getCurrentPageURL());
        assertTrue(returnNext, "Should have been return next data");
    }
}