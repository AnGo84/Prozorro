package ua.prozorro.parser.exchangeRates;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import ua.prozorro.properties.PropertyFields;
import ua.prozorro.sourceService.DataType;
import ua.prozorro.temp.TestPropertyFieldsFactory;

import java.io.IOException;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExchangeRateNBUDataParserAndSaverTest {
    ExchangeRateNBUDataParserAndSaver exchangeRateNBUDataParserAndSaver;


    @Before
    public void beforeEach() {
        try {
            PropertyFields propertyFields = TestPropertyFieldsFactory.getTestPropertyFields("2019-10-15", "2019-10-17", DataType.NBU_RATES);
            exchangeRateNBUDataParserAndSaver = new ExchangeRateNBUDataParserAndSaver(propertyFields);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Test
    void startPageURL() {
        String url = exchangeRateNBUDataParserAndSaver.startPageURL();
        assertEquals("https://bank.gov.ua/NBU_Exchange/exchange?date=15.10.2019&json", url, "NBU URL for 15.10.2019");

    }

    @Test
    void initDataByURL() {
        try {
            exchangeRateNBUDataParserAndSaver.initDataByURL("https://bank.gov.ua/NBU_Exchange/exchange?date=15.10.2019&json");
            //fail();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

    }
    /*@Test
    void initDataByURLWillFail() {
        try {
            exchangeRateNBUDataParserAndSaver.initDataByURL(null);
            fail();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }*/

    @Test
    void hasNextData() throws IOException, ParseException {
        String url = exchangeRateNBUDataParserAndSaver.startPageURL();
        exchangeRateNBUDataParserAndSaver.initDataByURL(url);
        boolean hasNext = exchangeRateNBUDataParserAndSaver.hasNextData();
        assertTrue(hasNext, "Must have next date for 15.10.2019");
    }

    @Test
    void getPageElementList() throws IOException, ParseException {
        String url = exchangeRateNBUDataParserAndSaver.startPageURL();
        exchangeRateNBUDataParserAndSaver.initDataByURL(url);
        exchangeRateNBUDataParserAndSaver.getPageElementList();
    }

    @Test
    void checkExpireElement() {
    }

    @Test
    void parseAndSave() {
    }

    @Test
    void getNextData() throws Exception {

    }
}