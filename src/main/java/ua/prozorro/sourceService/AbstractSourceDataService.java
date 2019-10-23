package ua.prozorro.sourceService;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import ua.prozorro.entity.PageElement;
import ua.prozorro.properties.PropertyFields;
import ua.prozorro.utils.FileUtils;

import java.io.IOException;
import java.util.Date;

public abstract class AbstractSourceDataService<T> implements SourceDataService<T> {
    protected Class<? extends T> theClass;

    protected PropertyFields propertyFields;
    protected PageURLFactory pageURLFactory;

    public AbstractSourceDataService(Class<? extends T> theClass, PropertyFields propertyFields) {
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

    public String getPageURL(Date date) {
        return pageURLFactory.getPageURL(date);
    }
}
