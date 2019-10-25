package ua.prozorro.parser;

import org.hibernate.Session;
import ua.prozorro.entity.EventResultData;
import ua.prozorro.entity.PageElement;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface DataParserAndSaver {
	String startPageURL();

	void initDataByURL(String url) throws IOException, ParseException;
	boolean hasNextData();
	List<PageElement> getPageElementList();

	EventResultData checkExpireElement(PageElement pageElement) throws ParseException;

	EventResultData parseAndSave(PageElement pageElement, Session session) throws Exception;

	boolean getNextData() throws Exception;

	String getCurrentPageURL();
}
