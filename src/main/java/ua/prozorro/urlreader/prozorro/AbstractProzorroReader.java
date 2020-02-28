package ua.prozorro.urlreader.prozorro;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import ua.prozorro.source.SourceLink;
import ua.prozorro.urlreader.URLSourceReader;
import ua.prozorro.utils.DateUtils;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

@Deprecated
public abstract class AbstractProzorroReader<T> {
    protected Class<? extends T> theClass;
    protected SourceLink sourceLink;
    protected URLSourceReader urlSourceReader = new URLSourceReader();

    public AbstractProzorroReader(Class<? extends T> theClass, SourceLink sourceLink) {
        this.theClass = theClass;
        this.sourceLink = sourceLink;
    }

    public void setUrlSourceReader(URLSourceReader urlSourceReader) {
        this.urlSourceReader = urlSourceReader;
    }

    public T getObjectFromStringJSON(String stringJSON) throws JsonParseException {
        Gson gson = new Gson();
        return gson.fromJson(stringJSON, theClass);
    }

    public T getObjectFromURL(String url) throws JsonParseException, IOException {
        String genreJson = urlSourceReader.read(url);
        return getObjectFromStringJSON(genreJson);
    }

    public Date getURLDate(Date date) throws ParseException {
        return getDate(date, sourceLink.getUrlDateFormat());
    }

    public Date getPageDate(Date date) throws ParseException {
        return getDate(date, sourceLink.getPageDateFormat());
    }

    private Date getDate(Date date, String dateFormat) throws ParseException {
        if (date == null) {
            date = new Date();
        }
        return DateUtils.parseDateToFormat(date, dateFormat);
    }
}
