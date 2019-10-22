package ua.prozorro.parser.prozorro;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import ua.prozorro.entity.EventResultData;
import ua.prozorro.entity.PageElement;
import ua.prozorro.entity.mappers.prozorroObjectMapper.ProzorroPageElementListMapper;
import ua.prozorro.properties.AppProperty;
import ua.prozorro.properties.PropertyFields;
import ua.prozorro.timeMeasure.ParsingResultData;
import ua.prozorro.prozorro.model.pages.ProzorroPageContent;
import ua.prozorro.prozorro.service.PageServiceProzorro;
import ua.prozorro.parser.AbstractDataParserAndSaver;
import ua.prozorro.utils.DateUtils;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public abstract class AbstractProzorroDataParserAndSaver extends AbstractDataParserAndSaver {
    private static final Logger logger = LogManager.getRootLogger();

    private ProzorroPageElementListMapper prozorroPageElementListMapper;

    private PageServiceProzorro pageServiceProzorro;

    private ProzorroPageContent pageContent;

    private Date nextPageDate;

    public AbstractProzorroDataParserAndSaver(PropertyFields propertyFields, ParsingResultData resultData) {
        this.propertyFields = propertyFields;
        this.resultData = resultData;
        this.pageServiceProzorro = new PageServiceProzorro(propertyFields);
        this.prozorroPageElementListMapper = new ProzorroPageElementListMapper();
    }

    @Override
    public String startPageURL() {
        currentPageURL = pageServiceProzorro.getPageURL(propertyFields.getSearchDateFrom());
        return currentPageURL;
    }

    @Override
    public void initDataByURL(String url) throws IOException, ParseException {
        pageContent = pageServiceProzorro.getPageContentFromURL(url);

        nextPageDate = pageServiceProzorro.getDateFromPageOffset(pageContent.getNextPage().getOffset());
        if (nextPageDate.compareTo(propertyFields.getSearchDateTill()) > 0) {
            nextPageDate = propertyFields.getSearchDateTill();
        }
    }

    @Override
    public boolean hasNextData() {
        return propertyFields.getSearchDateTill().compareTo(nextPageDate) >= 0 &&
                pageContent.getPageElementList() != null && !pageContent.getPageElementList().isEmpty();
    }

    @Override
    public List<PageElement> getPageElementList() {
        List<PageElement> pageElements = prozorroPageElementListMapper.convertToEntitiesList(pageContent.getPageElementList());
        return pageElements;
    }

    @Override
    public EventResultData checkExpireElement(PageElement pageElement) throws ParseException {
        Date pageElementDate = DateUtils.parseProzorroPageDateFromString(pageElement.getDateModified(),
                propertyFields.getPropertiesStringValue(
                        AppProperty.DATE_FORMAT));
        EventResultData eventResultData = new EventResultData();
        if (propertyFields.getSearchDateTill().
                compareTo(DateUtils.parseDateToFormate(pageElementDate, propertyFields
                        .getPropertiesStringValue(AppProperty.SHORT_DATE_FORMAT))) < 0) {

            eventResultData.setHasResult(true);
            eventResultData.setEventResult(propertyFields.getSearchDateType().getTypeName() + ": Страница № %d /" +
                    resultData.getListSize() + ", текущий № %d c id: " + pageElement.getId() + ", date: "
                    + pageElement.getDateModified() + ". Отклонён по дате \n");
        } else {
            eventResultData.setHasResult(false);
        }
        return eventResultData;
    }

    @Override
    public abstract EventResultData parseAndSave(PageElement pageElement, Session session) throws Exception;

    @Override
    public boolean getNextData() throws Exception {
        nextPageDate = pageServiceProzorro.getDateFromPageOffset(pageContent.getNextPage().getOffset());
        currentPageURL = pageContent.getNextPage().getUri();
        pageContent = pageServiceProzorro.getPageContentFromURL(pageContent.getNextPage().getUri());
        return true;
    }
}
