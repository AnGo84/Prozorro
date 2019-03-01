package ua.prozorro.temp;

import ua.prozorro.utils.DateUtils;

import java.text.ParseException;
import java.util.Date;

public class Test {
	public static void main(String[] args) {
		try {
			Date pageDate = DateUtils.parseProzorroPageDateFromString("2018-01-31T10:35:16+02:00", "yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX");
			System.out.println("Date: " + pageDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
