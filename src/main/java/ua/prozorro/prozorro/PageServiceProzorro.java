package ua.prozorro.prozorro;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.prozorro.prozorro.model.pages.ProzorroPageContent;
import ua.prozorro.properties.AppProperty;
import ua.prozorro.properties.PropertyFields;
import ua.prozorro.utils.DateUtils;
import ua.prozorro.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//http://www.jsonschema2pojo.org/
public class PageServiceProzorro {
	private static final Logger logger = LogManager.getRootLogger();

	private PropertyFields propertyFields;

	public PageServiceProzorro(PropertyFields propertyFields) {
		this.propertyFields = propertyFields;
	}

	public ProzorroPageContent getPageContentFromStringJSON(String stringJSON) throws JsonParseException {
		Gson gson = new Gson();
		return gson.fromJson(stringJSON, ProzorroPageContent.class);
	}

	public ProzorroPageContent getPageContentFromURL(String url) throws JsonParseException, IOException {
		String genreJson = FileUtils.getStringFromURL(url);
		return getPageContentFromStringJSON(genreJson);
	}


	public List<ProzorroPageContent> getPagesList(Date dateFrom, Date dateTill) throws IOException, java.text.ParseException {
		return getPagesList(dateFrom,dateTill,true);
	}

	public List<ProzorroPageContent> getPagesList(Date dateFrom, Date dateTill, boolean withPageElements) throws IOException, java.text.ParseException {
		if (propertyFields == null || propertyFields.getProperties() == null) {
			return null;
		}
		String startPageURL = getPageURL(dateFrom);

		dateTill = getDateTill(dateTill);

		List<ProzorroPageContent> pageContentList = new ArrayList<>();

		ProzorroPageContent pageContent = getPageContentFromURL(startPageURL);

		Date nextOffsetDate = getDateFromPageOffset(pageContent.getNextPage().getOffset());

		while (dateTill.compareTo(nextOffsetDate) >= 0 && pageContent.getPageElementList() != null && !pageContent.getPageElementList().isEmpty()) {
			if (!withPageElements) {
				pageContent.getPageElementList().clear();
			}
			pageContentList.add(pageContent);

			//logger.info("Get next page with URL: " + pageContent.getNextPage().getUri());
			pageContent = getPageContentFromURL(pageContent.getNextPage().getUri());
			//nextOffsetDate = DateUtils.parseDateFromString(pageContent.getNextPage().getOffset(), propertyFields.getPropertiesStringValue(AppProperty.SHORT_DATE_FORMAT));
			nextOffsetDate =  getDateFromPageOffset(pageContent.getNextPage().getOffset());

		}
		return pageContentList;
	}

	public Date getDateFromPageOffset(String offset) throws ParseException {
		return DateUtils.parseDateFromString(offset, propertyFields.getPropertiesStringValue(AppProperty.SHORT_DATE_FORMAT));
	}

	public Date getDateTill(Date dateTill) throws ParseException {
		if (dateTill == null) {
			dateTill = DateUtils.parseDateToFormate(new Date(), propertyFields.getPropertiesStringValue(AppProperty.SHORT_DATE_FORMAT));
		}
		return dateTill;
	}

	public String getPageURL(Date date) {
		if (date == null) {
			return propertyFields.getPropertiesStringValue(AppProperty.START_PAGE);
		}
		String pageURL = propertyFields.getPropertiesStringValue(AppProperty.START_PAGE)
				                 + "?" + propertyFields.getPropertiesStringValue(AppProperty.PAGE_OFFSET)
				                 + "=" + DateUtils.parseDateToString(date, propertyFields.getPropertiesStringValue(AppProperty.SHORT_DATE_FORMAT))
				                 + propertyFields.getPropertiesStringValue(AppProperty.PAGE_END);
		//logger.info("Get page from date "+ DateUtils.dateToString(date) +" with URL: " + pageURL);
		return pageURL;
	}

	public String getPageURLWithLimit(Date date) {
		if (date == null) {
			return propertyFields.getPropertiesStringValue(AppProperty.START_PAGE);
		}
		String pageURL = propertyFields.getPropertiesStringValue(AppProperty.START_PAGE) + "?" +
				                 propertyFields.getPropertiesStringValue(AppProperty.PAGE_LIMIT) + "=" + propertyFields.getPropertiesStringValue(AppProperty.PAGE_LIMIT_VALUE)
				                 + "&" + propertyFields.getPropertiesStringValue(AppProperty.PAGE_OFFSET) + "=" + DateUtils.parseDateToString(date, propertyFields.getPropertiesStringValue(AppProperty.SHORT_DATE_FORMAT))
				                 + propertyFields.getPropertiesStringValue(AppProperty.PAGE_END);
		logger.info("Get page from date " + DateUtils.dateToString(date) + " with URL: " + pageURL);
		return pageURL;
	}

	public ParsingResultData getApproximatelyParsingTimeForPeriod(Date dateFrom, Date dateTill) throws IOException, ParseException {
		TenderDataServiceProzorro tenderDataServiceProzorro = new TenderDataServiceProzorro(propertyFields.getPropertiesStringValue(AppProperty.START_PAGE) + File.separator);
		long startTime = System.nanoTime();
		List<ProzorroPageContent> list = getPagesList(dateFrom, dateTill, false);
		//System.out.println("Pages list size: " + list.size());
		long timeForPages = (System.nanoTime() - startTime);
		//System.out.println("Time for page without data: " + timeForPages/1000000000);

		startTime = System.nanoTime();
		long timeForPageTenders;
		if (list != null && !list.isEmpty()) {
			ProzorroPageContent pageContent = getPageContentFromURL(getPageURL(dateFrom));
			tenderDataServiceProzorro.getTenderDatasFromPageContent(pageContent);
		}
		timeForPageTenders = (System.nanoTime() - startTime);
		//System.out.println("Time for ProzorroPage Tenders: " + timeForPageTenders/ 1000000000);

		long totalTime = timeForPages + list.size() * timeForPageTenders;

		ParsingResultData resultData = new ParsingResultData();
		resultData.setParsingTime(totalTime);
		resultData.setListSize(list.size());
		resultData.setHasData((list!=null&&!list.isEmpty()));
		return resultData;
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
