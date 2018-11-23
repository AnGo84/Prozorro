package ua.prozorro.utils;

public class ParseUtil {
	public static String returnString(Object object) {
		if (object == null)
			return "";
		return object.toString().replaceAll("\"", "\\\\\"").replaceAll("\'", "\\\\\'");
	}
}
