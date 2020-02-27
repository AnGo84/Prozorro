package ua.prozorro.language;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MessagesTest {
	private Messages messages = new Messages();
	
	@Test
	void getResourceBundle() {
		assertEquals("uk", messages.getResourceBundle("uk_UA").getLocale().getLanguage());
		assertEquals("UA", messages.getResourceBundle("uk_UA").getLocale().getCountry());
		
	}
	
	@Disabled("Do not work in Travis CI")
	@Test
	void getResourceBundleFromDefaultLocale() {
		if (Locale.getDefault() == null) {
			Locale.setDefault(new Locale("en", "EN"));
		}
		Locale locale = Locale.getDefault();
		assertEquals(locale.getLanguage(), messages.getResourceBundle(null).getLocale().getLanguage());
		assertEquals(locale.getLanguage(), messages.getResourceBundle("").getLocale().getLanguage());
		assertEquals(locale.getLanguage(), messages.getResourceBundle("enEn").getLocale().getLanguage());
		assertEquals(locale.getLanguage(), messages.getResourceBundle("wrongProp").getLocale().getLanguage());
		assertEquals(locale.getLanguage(), messages.getResourceBundle("en_EN").getLocale().getLanguage());
	}
}