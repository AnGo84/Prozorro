package ua.prozorro.language;

import org.apache.logging.log4j.util.Strings;
import ua.prozorro.properties.UTF8Control;

import java.util.Locale;
import java.util.ResourceBundle;

public class Messages {
	private final String MESSAGES_BUNDLE_PATH = "messages/MessagesBundle";
	
	public ResourceBundle getResourceBundle(String propertyLanguage) {
		if (Strings.isBlank(propertyLanguage)) {
			propertyLanguage = "en_EN";
		}
		String[] args = propertyLanguage.split("_");
		String language;
		String country;
		if (args.length < 2) {
			language = "en";
			country = "EN";
		} else {
			language = args[0];
			country = args[1];
		}
		return getResourceBundle(language, country);
	}
	
	private ResourceBundle getResourceBundle(String language, String country) {
		Locale locale = new Locale(language, country);
		return ResourceBundle.getBundle(MESSAGES_BUNDLE_PATH, locale, new UTF8Control());
	}
}
