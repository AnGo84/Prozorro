package ua.prozorro.properties;

import ua.prozorro.prozorro.model.DataType;

import java.util.Date;
import java.util.Properties;

public class PropertyFields {
	private Properties properties;
	private Date searchDateFrom;
	private Date searchDateTill;
	private DataType searchDateType;
	
	public PropertyFields(Properties properties) {
		this.properties = properties;
		this.searchDateFrom = null;
		this.searchDateTill = null;
		this.searchDateType = null;
	}
	
	public Properties getProperties() {
		return properties;
	}
	
	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	
	public String getPropertiesStringValue(AppProperty appProperty) {
		String value = PropertiesUtils.getPropertyString(this.properties, appProperty.getPropertyName());
		return value;
	}
	
	public Date getSearchDateFrom() {
		return searchDateFrom;
	}
	
	public void setSearchDateFrom(Date searchDateFrom) {
		this.searchDateFrom = searchDateFrom;
	}
	
	public Date getSearchDateTill() {
		return searchDateTill;
	}
	
	public void setSearchDateTill(Date searchDateTill) {
		this.searchDateTill = searchDateTill;
	}
	
	public DataType getSearchDateType() {
		return searchDateType;
	}
	
	public void setSearchDateType(DataType searchDateType) {
		this.searchDateType = searchDateType;
	}
}
