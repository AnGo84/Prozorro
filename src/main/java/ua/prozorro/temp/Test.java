package ua.prozorro.temp;

import ua.prozorro.properties.PropertyFields;
import ua.prozorro.sourceService.DataType;
import ua.prozorro.utils.DateUtils;

import java.text.ParseException;
import java.util.Date;
import java.util.Properties;

public class Test {
	static String short_date_formate = "yyyy-MM-dd";

	public static void main(String[] args) {
		/*try {
			Date pageDate = DateUtils.parseProzorroPageDateFromString("2018-01-31T10:35:16+02:00", "yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX");
			System.out.println("Date: " + pageDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}*/
		long number= (new Double("497.0")).longValue();
		System.out.println("Long: " + number);
	}

	public static PropertyFields getTestPropertyFields(String dateFrom, String dateTill, Properties properties, DataType dataType) throws ParseException {
		PropertyFields propertyFields = new PropertyFields(properties);
		propertyFields.setSearchDateFrom(DateUtils.parseProzorroPageDateFromString(dateFrom, short_date_formate));
		propertyFields.setSearchDateTill(DateUtils.parseProzorroPageDateFromString(dateTill, short_date_formate));
		propertyFields.setSearchDateType(dataType);
		return propertyFields;
	}
}
