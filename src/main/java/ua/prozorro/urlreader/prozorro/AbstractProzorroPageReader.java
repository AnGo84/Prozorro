package ua.prozorro.urlreader.prozorro;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import ua.prozorro.source.SourceLink;
import ua.prozorro.utils.DateUtils;
import ua.prozorro.utils.FileUtils;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

public abstract class AbstractProzorroPageReader<T> {
	protected Class<? extends T> theClass;
	protected SourceLink sourceLink;
	
	public AbstractProzorroPageReader(Class<? extends T> theClass, SourceLink sourceLink) {
		this.theClass = theClass;
		this.sourceLink = sourceLink;
	}
	
	public T getObjectFromStringJSON(String stringJSON) throws JsonParseException {
		Gson gson = new Gson();
		return gson.fromJson(stringJSON, theClass);
	}
	
	public T getObjectFromURL(String url) throws JsonParseException, IOException {
		String genreJson = FileUtils.getStringFromURL(url);
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
