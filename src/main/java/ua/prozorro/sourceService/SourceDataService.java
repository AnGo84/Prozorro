package ua.prozorro.sourceService;

import com.google.gson.JsonParseException;
import ua.prozorro.entity.PageElement;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface SourceDataService<T> {
    T getObjectFromStringJSON(String stringJSON) throws JsonParseException;

    T getObjectFromURL(String url) throws JsonParseException, IOException;

    T getObjectByPageElement(PageElement pageElement) throws IOException;

    List<T> getPagesList() throws IOException, ParseException;

    Date getDate(Date date) throws ParseException;
}
