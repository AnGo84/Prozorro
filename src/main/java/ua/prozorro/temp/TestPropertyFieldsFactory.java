package ua.prozorro.temp;

import ua.prozorro.properties.AppProperty;
import ua.prozorro.properties.PropertyFields;
import ua.prozorro.sourceService.DataType;
import ua.prozorro.utils.DateUtils;

import java.text.ParseException;
import java.util.Date;
import java.util.Properties;

public class TestPropertyFieldsFactory {
    static String short_date_format = "yyyy-MM-dd";

    public static PropertyFields getTestPropertyFields(String dateFrom, String dateTill, DataType dataType) throws ParseException {
        //PropertyFields propertyFields = new PropertyFields(properties);
        PropertyFields propertyFields = new PropertyFields(getStartProperties());
        propertyFields.setSearchDateFrom(DateUtils.parseProzorroPageDateFromString(dateFrom, short_date_format));
        propertyFields.setSearchDateTill(DateUtils.parseProzorroPageDateFromString(dateTill, short_date_format));
        propertyFields.setSearchDateType(dataType);
        return propertyFields;
    }
    public static PropertyFields getTestPropertyFields(Date dateFrom, Date dateTill, DataType dataType) throws ParseException {
        //PropertyFields propertyFields = new PropertyFields(properties);
        PropertyFields propertyFields = new PropertyFields(getStartProperties());
        propertyFields.setSearchDateFrom(dateFrom);
        propertyFields.setSearchDateTill(dateTill);
        propertyFields.setSearchDateType(dataType);
        return propertyFields;
    }

    public static Properties getStartProperties() {
        String start_page = "https://public.api.openprocurement.org/api/2.4/tenders";
        String page_limit = "limit";
        String page_limit_value = "100";
        String page_offset = "offset";
        String page_end = "T00%3A00%3A00.000000%2B03%3A00";
        String date_formate = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX";
        String short_date_formate = "yyyy-MM-dd";
        Properties properties = new Properties();
        properties.setProperty(AppProperty.TENDER_START_PAGE.getPropertyName(), start_page);
        properties.setProperty(AppProperty.TENDER_PAGE_LIMIT.getPropertyName(), page_limit);
        properties.setProperty(AppProperty.TENDER_PAGE_LIMIT_VALUE.getPropertyName(), page_limit_value);
        properties.setProperty(AppProperty.TENDER_PAGE_OFFSET.getPropertyName(), page_offset);
        properties.setProperty(AppProperty.TENDER_PAGE_END.getPropertyName(), page_end);
        properties.setProperty(AppProperty.PROZORRO_DATE_FORMAT.getPropertyName(), date_formate);
        properties.setProperty(AppProperty.PROZORRO_SHORT_DATE_FORMAT.getPropertyName(), short_date_formate);

        start_page = "https://public.api.openprocurement.org/api/2.4/plans";
        /*page_limit = "limit";
        page_limit_value = "100";
        page_offset = "offset";
        page_end = "T00%3A00%3A00.000000%2B03%3A00";
        date_formate = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX";
        short_date_formate = "yyyy-MM-dd";*/

        properties.setProperty(AppProperty.PLAN_START_PAGE.getPropertyName(), start_page);
        properties.setProperty(AppProperty.PLAN_PAGE_LIMIT.getPropertyName(), page_limit);
        properties.setProperty(AppProperty.PLAN_PAGE_LIMIT_VALUE.getPropertyName(), page_limit_value);
        properties.setProperty(AppProperty.PLAN_PAGE_OFFSET.getPropertyName(), page_offset);
        properties.setProperty(AppProperty.PLAN_PAGE_END.getPropertyName(), page_end);
        /*properties.setProperty(AppProperty.PROZORRO_DATE_FORMAT.getPropertyName(), date_formate);
        properties.setProperty(AppProperty.PROZORRO_SHORT_DATE_FORMAT.getPropertyName(), short_date_formate);*/

        start_page = "https://bank.gov.ua/NBU_Exchange/exchange";
        String page_prefix = "date";
        page_end = "json";
        date_formate = "dd.MM.yyyy";
        properties.setProperty(AppProperty.NBU_START_PAGE.getPropertyName(), start_page);
        properties.setProperty(AppProperty.NBU_PAGE_PREFIX.getPropertyName(), page_prefix);
        properties.setProperty(AppProperty.NBU_PAGE_END.getPropertyName(), page_end);
        properties.setProperty(AppProperty.NBU_DATE_FORMAT.getPropertyName(), date_formate);

        return properties;
    }
}
