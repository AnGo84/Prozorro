package ua.prozorro.properties;

public enum AppProperty {
    DB_TYPE("db.type"),
	/*db.type = mysql
	db.host = localhost:3306
	db.name = prozorro
	db.login = prozorro
	db.password = prozorro*/

    TENDER_START_PAGE("prozorro.tender.start_page"), TENDER_SPAGE_LIMIT("prozorro.tender.page_limit"), TENDER_SPAGE_LIMIT_VALUE(
            "prozorro.tender.page_limit_value"), TENDER_SPAGE_OFFSET("prozorro.tender.page_offset"), TENDER_SPAGE_END(
            "prozorro.tender.page_end"),
    //
    DATE_FORMAT("prozorro.date_formate"), SHORT_DATE_FORMAT(
            "prozorro.short_date_formate");

    private String propertyName;

    AppProperty(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyName() {
        return propertyName;
    }
}
