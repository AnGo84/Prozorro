package ua.prozorro.properties;

public enum AppProperty {
	DB_TYPE("db.type"), //
	TENDER_START_PAGE("prozorro.tender.start_page"), TENDER_PAGE_LIMIT("prozorro.tender.page_limit"),
	TENDER_PAGE_LIMIT_VALUE("prozorro.tender.page_limit_value"), TENDER_PAGE_OFFSET("prozorro.tender.page_offset"),
	TENDER_PAGE_END("prozorro.tender.page_end"), //
	CONTRACT_START_PAGE("prozorro.contract.start_page"), CONTRACT_PAGE_LIMIT("prozorro.contract.page_limit"),
	CONTRACT_PAGE_LIMIT_VALUE("prozorro.contract.page_limit_value"), CONTRACT_PAGE_OFFSET(
			"prozorro.contract.page_offset"), CONTRACT_PAGE_END("prozorro.contract.page_end"), //
	PLAN_START_PAGE("prozorro.plan.start_page"), PLAN_PAGE_LIMIT("prozorro.plan.page_limit"), PLAN_PAGE_LIMIT_VALUE(
			"prozorro.plan.page_limit_value"), PLAN_PAGE_OFFSET("prozorro.plan.page_offset"), PLAN_PAGE_END(
			"prozorro.plan.page_end"), //
	PROZORRO_DATE_FORMAT("prozorro.api.date_format"), PROZORRO_SHORT_DATE_FORMAT("prozorro.api.short_date_format"), //
	NBU_START_PAGE("nbu.exchangerates.start_page"), NBU_PAGE_PREFIX("nbu.exchangerates.page_prefix"), NBU_PAGE_END(
			"nbu.exchangerates.page_end"), NBU_DATE_FORMAT("nbu.exchangerates.date_format")
	//
	;
	
	private String propertyName;
	
	AppProperty(String propertyName) {
		this.propertyName = propertyName;
	}
	
	public String getPropertyName() {
		return propertyName;
	}
}
