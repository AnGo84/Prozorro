package ua.prozorro.sourceService.prozorro;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.prozorro.entity.PageElement;
import ua.prozorro.properties.AppProperty;
import ua.prozorro.properties.PropertyFields;
import ua.prozorro.prozorro.model.pages.ProzorroPageContent;
import ua.prozorro.utils.DateUtils;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//http://www.jsonschema2pojo.org/
public class ProzorroPageDataService extends AbstractProzorroSourceDataService<ProzorroPageContent> {
    private static final Logger logger = LogManager.getRootLogger();

    public ProzorroPageDataService(PropertyFields propertyFields) {
        super(ProzorroPageContent.class, propertyFields);
    }

	/*public String getPageURL(Date date) {
		return pageURLFactory.getPageURL(date);
	}*/

    public Date getDateFromPageOffset(String offset) throws ParseException {
        return DateUtils
                .parseProzorroPageDateFromString(offset, propertyFields.getPropertiesStringValue(AppProperty.PROZORRO_SHORT_DATE_FORMAT));
    }


    @Override
    public List<ProzorroPageContent> getPagesList() throws IOException, ParseException {
        if (propertyFields == null || propertyFields.getProperties() == null) {
            return null;
        }
        String startPageURL = pageURLFactory.getPageURL(propertyFields.getSearchDateFrom());

        logger.info("Page startPageURL: " + startPageURL);

        Date dateTill = getDate(propertyFields.getSearchDateTill());

        List<ProzorroPageContent> pageContentList = new ArrayList<>();

        ProzorroPageContent pageContent = getObjectFromURL(startPageURL);

        String nextOffset = pageContent.getNextPage().getOffset();
        Date nextOffsetDate = getDateFromPageOffset(nextOffset);
        if (nextOffsetDate.compareTo(dateTill) > 0) {
            nextOffsetDate = dateTill;
        }
        //logger.info("nextOffsetDate: " + nextOffsetDate + ", nextOffset: " + nextOffset);
        while (dateTill.compareTo(nextOffsetDate) >= 0 && (pageContent.getPageElementList() != null &&
                !pageContent.getPageElementList().isEmpty())) {
			/*if (!withPageElements) {
				pageContent.getPageElementList().clear();
			}*/
            pageContentList.add(pageContent);
            logger.info("Get next page with URL: " + pageContent.getNextPage().getUri());
            nextOffset = pageContent.getNextPage().getOffset();
            pageContent = getObjectFromURL(pageContent.getNextPage().getUri());
            //nextOffsetDate = DateUtils.parseProzorroPageDateFromString(pageContent.getNextPage().getOffset(), propertyFields.getPropertiesStringValue(AppProperty.SHORT_DATE_FORMAT));
            nextOffsetDate = getDateFromPageOffset(nextOffset);
        }
        return pageContentList;
    }

    @Override
    public ProzorroPageContent getObjectByPageElement(PageElement pageElement) throws IOException {
        return null;
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
