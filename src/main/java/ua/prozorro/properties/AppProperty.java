package ua.prozorro.properties;

public enum AppProperty {
	DB_TYPE("db.type"),
	/*db.type = mysql
	db.host = localhost:3306
	db.name = prozorro
	db.login = prozorro
	db.password = prozorro*/

	START_PAGE("prozorro.start_page"),
	PAGE_LIMIT("prozorro.page_limit"),
	PAGE_LIMIT_VALUE("prozorro.page_limit_value"),
	PAGE_OFFSET("prozorro.page_offset"),
	PAGE_END("prozorro.page_end"),
	DATE_FORMAT("prozorro.date_formate"),
	SHORT_DATE_FORMAT("prozorro.short_date_formate");

	private String propertyName;

	AppProperty(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return propertyName;
	}
}
