package ua.prozorro.parser;

import org.hibernate.Session;
import ua.prozorro.entity.EventResultData;
import ua.prozorro.entity.PageElement;
import ua.prozorro.properties.PropertyFields;
import ua.prozorro.timeMeasure.ParsingResultData;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public abstract class AbstractDataParserAndSaver implements DataParserAndSaver {

    protected String currentPageURL;
    protected PropertyFields propertyFields;
    protected ParsingResultData resultData;

    public AbstractDataParserAndSaver() {
    }

    public AbstractDataParserAndSaver(PropertyFields propertyFields, ParsingResultData resultData) {
        this.propertyFields = propertyFields;
        this.resultData = resultData;
    }

    public String getCurrentPageURL() {
        return currentPageURL;
    }

    @Override
    public abstract String startPageURL();

    @Override
    public abstract void initDataByURL(String url) throws IOException, ParseException;

    @Override
    public abstract boolean hasNextData();

    @Override
    public abstract List<PageElement> getPageElementList();

    @Override
    public abstract EventResultData checkExpireElement(PageElement pageElement) throws ParseException;

    @Override
    public abstract EventResultData parseAndSave(PageElement pageElement, Session session) throws Exception;

    @Override
    public abstract boolean getNextData() throws Exception;
}
