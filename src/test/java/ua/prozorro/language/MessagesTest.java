package ua.prozorro.language;

import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MessagesTest {
	private Messages messages = new Messages();
	
	@Test
	void getResourceBundle() {
		Locale locale = (Locale.getDefault() == null ? new Locale("en", "EN") : Locale.getDefault());
		assertEquals(locale.getLanguage(), messages.getResourceBundle(null).getLocale().getLanguage());
		assertEquals(locale.getLanguage(), messages.getResourceBundle("").getLocale().getLanguage());
		assertEquals(locale.getLanguage(), messages.getResourceBundle("enEn").getLocale().getLanguage());
		assertEquals(locale.getLanguage(), messages.getResourceBundle("wrongProp").getLocale().getLanguage());
		assertEquals(locale.getLanguage(), messages.getResourceBundle("en_EN").getLocale().getLanguage());
		assertEquals("uk", messages.getResourceBundle("uk_UA").getLocale().getLanguage());
		assertEquals("UA", messages.getResourceBundle("uk_UA").getLocale().getCountry());
		
	}
}