package ua.prozorro.sourceService.prozorro;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.prozorro.properties.AppProperty;
import ua.prozorro.properties.PropertyFields;
import ua.prozorro.sourceService.DataType;
import ua.prozorro.sourceService.PageURLFactory;
import ua.prozorro.prozorro.model.pages.ProzorroPageContent;
import ua.prozorro.utils.DateUtils;
import ua.prozorro.utils.FileUtils;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//http://www.jsonschema2pojo.org/

@Deprecated
public class PageServiceProzorro {
	private static final Logger logger = LogManager.getRootLogger();
	
	private PropertyFields propertyFields;
	private PageURLFactory pageURLFactory;

	public PageServiceProzorro(PropertyFields propertyFields) {
		this.propertyFields = propertyFields;
		this.pageURLFactory = new PageURLFactory(propertyFields);
	}
	
	public ProzorroPageContent getPageContentFromStringJSON(String stringJSON) throws JsonParseException {
		Gson gson = new Gson();
		return gson.fromJson(stringJSON, ProzorroPageContent.class);
	}
	
	public ProzorroPageContent getPageContentFromURL(String url) throws JsonParseException, IOException {
		String genreJson = FileUtils.getStringFromURL(url);
		return getPageContentFromStringJSON(genreJson);
	}
	
	
	public List<ProzorroPageContent> getPagesList(DataType dataType, Date dateFrom, Date dateTill)
			throws IOException, java.text.ParseException {
		return getPagesList(dataType, dateFrom, dateTill, true);
	}
	
	public List<ProzorroPageContent> getPagesList(DataType dataType, Date dateFrom, Date dateTill, boolean withPageElements)
			throws IOException, ParseException {
		//logger.info("DataType:" + dataType + ", DateFrom: " + dateFrom + ", DateTill: " + dateTill);
		
		if (propertyFields == null || propertyFields.getProperties() == null) {
			return null;
		}
		String startPageURL = pageURLFactory.getPageURL(dateFrom);
		
		logger.info("Page startPageURL: " + startPageURL);
		
		dateTill = getDateTill(dateTill);
		
		List<ProzorroPageContent> pageContentList = new ArrayList<>();
		
		ProzorroPageContent pageContent = getPageContentFromURL(startPageURL);
		
		String nextOffset = pageContent.getNextPage().getOffset();
		Date nextOffsetDate = getDateFromPageOffset(nextOffset);
		if (nextOffsetDate.compareTo(dateTill) > 0) {
			nextOffsetDate = dateTill;
		}
		//logger.info("nextOffsetDate: " + nextOffsetDate + ", nextOffset: " + nextOffset);
		while (dateTill.compareTo(nextOffsetDate) >= 0 && (pageContent.getPageElementList() != null &&
			   !pageContent.getPageElementList().isEmpty())) {
			if (!withPageElements) {
				pageContent.getPageElementList().clear();
			}
			pageContentList.add(pageContent);
			logger.info("Get next page with URL: " + pageContent.getNextPage().getUri());
			nextOffset = pageContent.getNextPage().getOffset();
			pageContent = getPageContentFromURL(pageContent.getNextPage().getUri());
			//nextOffsetDate = DateUtils.parseProzorroPageDateFromString(pageContent.getNextPage().getOffset(), propertyFields.getPropertiesStringValue(AppProperty.SHORT_DATE_FORMAT));
			nextOffsetDate = getDateFromPageOffset(nextOffset);
		}
		return pageContentList;
	}

	public String getPageURL(Date date) {
		return pageURLFactory.getPageURL(date);
	}
	
	public Date getDateFromPageOffset(String offset) throws ParseException {
		return DateUtils
				.parseProzorroPageDateFromString(offset, propertyFields.getPropertiesStringValue(AppProperty.PROZORRO_SHORT_DATE_FORMAT));
	}
	
	public Date getDateTill(Date dateTill) throws ParseException {
		if (dateTill == null) {
			dateTill = DateUtils.parseDateToFormat(new Date(), propertyFields
					.getPropertiesStringValue(AppProperty.PROZORRO_SHORT_DATE_FORMAT));
		}
		return dateTill;
	}

	/*  private GSONParser gsonParser = new GSONParser();
		public ProzorroPageContent getPageContentFromStringJSON(String genreJson) {
		List<ProzorroPageContent> list = GSONParser.getList(genreJson, ProzorroPageContent[].class);
		if (list!=null) {
			return list.get(0);
		}
		return null;
	}*/
}
