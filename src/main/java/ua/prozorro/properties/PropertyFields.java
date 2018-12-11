package ua.prozorro.properties;

import java.util.Properties;

public class PropertyFields {
	private Properties properties;

	public PropertyFields(Properties properties) {
		this.properties = properties;
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
}
