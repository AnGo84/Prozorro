package ua.prozorro.prozorro.service;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import ua.prozorro.entity.PageElement;
import ua.prozorro.properties.AppProperty;
import ua.prozorro.properties.PropertyFields;
import ua.prozorro.sourceService.AbstractSourceDataService;
import ua.prozorro.sourceService.PageURLFactory;
import ua.prozorro.sourceService.SourceDataService;
import ua.prozorro.utils.DateUtils;
import ua.prozorro.utils.FileUtils;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

public abstract class AbstractProzorroSourceDataService<T> extends AbstractSourceDataService<T> {
    protected Class<? extends T> theClass;

    protected PropertyFields propertyFields;
    protected PageURLFactory pageURLFactory;

    public AbstractProzorroSourceDataService(Class<? extends T> theClass, PropertyFields propertyFields) {
        super(theClass, propertyFields);
        this.theClass = theClass;
        this.propertyFields = propertyFields;
        this.pageURLFactory = new PageURLFactory(propertyFields);
    }

    public T getObjectFromStringJSON(String stringJSON) throws JsonParseException {
        Gson gson = new Gson();
        return gson.fromJson(stringJSON, theClass);
    }

    public T getObjectFromURL(String url) throws JsonParseException, IOException {
        String genreJson = FileUtils.getStringFromURL(url);
        return getObjectFromStringJSON(genreJson);
    }

    public T getObjectByPageElement(PageElement pageElement) throws IOException {
        if (pageElement == null) {
            return null;
        }
        String currentURL = pageURLFactory.getPageURL(null) + "/" + pageElement.getId();
        T data = getObjectFromURL(currentURL);
        return data;
    }
    public Date getDate(Date date) throws ParseException {
        if (date == null) {
            date = DateUtils.parseDateToFormat(new Date(), propertyFields
                    .getPropertiesStringValue(AppProperty.PROZORRO_SHORT_DATE_FORMAT));
        }
        return date;
    }
}
