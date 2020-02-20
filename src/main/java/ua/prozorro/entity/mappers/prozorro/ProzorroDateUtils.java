package ua.prozorro.entity.mappers.prozorro;

import org.apache.logging.log4j.util.Strings;

public class ProzorroDateUtils {
	public static String fixProzorroDateString(String date) {
		if (Strings.isBlank(date)){
			return null;
		}
		String dateModified = date;
		if(!dateModified.contains(".")){
			String str1 = dateModified.substring(0, 19);
			String str2 = dateModified.substring(19);
			dateModified= new StringBuilder(str1).append(".000000").append(str2).toString();
		}
		return dateModified;
	}
}
