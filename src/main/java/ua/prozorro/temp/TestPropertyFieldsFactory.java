package ua.prozorro.temp;

import ua.prozorro.properties.AppPropertyKey;

import java.util.Properties;

public class TestPropertyFieldsFactory {
	static String short_date_format = "yyyy-MM-dd";
	
	public static Properties getStartProperties() {
		String start_page = "https://public.api.openprocurement.org/api/2.5/tenders";
		String page_limit = "limit";
		String page_limit_value = "100";
		String page_offset = "offset";
		String page_end = "T00%3A00%3A00.000000%2B03%3A00";
		String date_formate = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX";
		String short_date_formate = "yyyy-MM-dd";
		Properties properties = new Properties();
		properties.setProperty(AppPropertyKey.TENDER_START_PAGE.getPropertyName(), start_page);
		properties.setProperty(AppPropertyKey.TENDER_PAGE_LIMIT.getPropertyName(), page_limit);
		properties.setProperty(AppPropertyKey.TENDER_PAGE_LIMIT_VALUE.getPropertyName(), page_limit_value);
		properties.setProperty(AppPropertyKey.TENDER_PAGE_OFFSET.getPropertyName(), page_offset);
		properties.setProperty(AppPropertyKey.TENDER_PAGE_END.getPropertyName(), page_end);
		properties.setProperty(AppPropertyKey.PROZORRO_DATE_FORMAT.getPropertyName(), date_formate);
		properties.setProperty(AppPropertyKey.PROZORRO_SHORT_DATE_FORMAT.getPropertyName(), short_date_formate);
		
		start_page = "https://public.api.openprocurement.org/api/2.5/plans";
        /*page_limit = "limit";
        page_limit_value = "100";
        page_offset = "offset";
        page_end = "T00%3A00%3A00.000000%2B03%3A00";
        date_formate = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX";
        short_date_formate = "yyyy-MM-dd";*/
		
		properties.setProperty(AppPropertyKey.PLAN_START_PAGE.getPropertyName(), start_page);
		properties.setProperty(AppPropertyKey.PLAN_PAGE_LIMIT.getPropertyName(), page_limit);
		properties.setProperty(AppPropertyKey.PLAN_PAGE_LIMIT_VALUE.getPropertyName(), page_limit_value);
		properties.setProperty(AppPropertyKey.PLAN_PAGE_OFFSET.getPropertyName(), page_offset);
		properties.setProperty(AppPropertyKey.PLAN_PAGE_END.getPropertyName(), page_end);
        /*properties.setProperty(AppProperty.PROZORRO_DATE_FORMAT.getPropertyName(), date_formate);
        properties.setProperty(AppProperty.PROZORRO_SHORT_DATE_FORMAT.getPropertyName(), short_date_formate);*/
		
		start_page = "https://public.api.openprocurement.org/api/2.5/contracts";
        /*page_limit = "limit";
        page_limit_value = "100";
        page_offset = "offset";
        page_end = "T00%3A00%3A00.000000%2B03%3A00";
        date_formate = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX";
        short_date_formate = "yyyy-MM-dd";*/
		
		properties.setProperty(AppPropertyKey.CONTRACT_START_PAGE.getPropertyName(), start_page);
		properties.setProperty(AppPropertyKey.CONTRACT_PAGE_LIMIT.getPropertyName(), page_limit);
		properties.setProperty(AppPropertyKey.CONTRACT_PAGE_LIMIT_VALUE.getPropertyName(), page_limit_value);
		properties.setProperty(AppPropertyKey.CONTRACT_PAGE_OFFSET.getPropertyName(), page_offset);
		properties.setProperty(AppPropertyKey.CONTRACT_PAGE_END.getPropertyName(), page_end);
        /*properties.setProperty(AppProperty.PROZORRO_DATE_FORMAT.getPropertyName(), date_formate);
        properties.setProperty(AppProperty.PROZORRO_SHORT_DATE_FORMAT.getPropertyName(), short_date_formate);*/
		
		start_page = "https://bank.gov.ua/NBU_Exchange/exchange";
		String page_prefix = "date";
		page_end = "json";
		date_formate = "dd.MM.yyyy";
		properties.setProperty(AppPropertyKey.NBU_START_PAGE.getPropertyName(), start_page);
		properties.setProperty(AppPropertyKey.NBU_PAGE_PREFIX.getPropertyName(), page_prefix);
		properties.setProperty(AppPropertyKey.NBU_PAGE_END.getPropertyName(), page_end);
		properties.setProperty(AppPropertyKey.NBU_DATE_FORMAT.getPropertyName(), date_formate);
		
		
		return properties;
	}
}
