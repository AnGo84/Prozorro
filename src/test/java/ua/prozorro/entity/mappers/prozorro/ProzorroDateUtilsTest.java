package ua.prozorro.entity.mappers.prozorro;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ProzorroDateUtilsTest {
	
	private String validDate = "2016-06-16T20:52:08.753200+03:00";
	private String invalidDate = "2016-06-16T20:52:08+03:00";
	private String fixedInvalidDate = "2016-06-16T20:52:08.000000+03:00";
	
	@Test
	public void fixProzorroDateString_no_bull() {
		assertNull(ProzorroDateUtils.fixProzorroDateString(null));
		assertNull(ProzorroDateUtils.fixProzorroDateString(""));
	}
	
	@Test
	public void fixProzorroDateString_with_valid_date() {
		assertEquals(validDate, ProzorroDateUtils.fixProzorroDateString(validDate));
	}
	
	@Test
	public void fixProzorroDateString_with_invalid_date() {
		assertEquals(fixedInvalidDate, ProzorroDateUtils.fixProzorroDateString(invalidDate));
	}
}