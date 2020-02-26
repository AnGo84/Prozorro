package ua.prozorro.gson;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

public class GSONParser {
	
	public static final <T> List<T> getList(final String json, final Class<T[]> clazz) {
		final T[] jsonToObject = new Gson().fromJson(json, clazz);
		
		return Arrays.asList(jsonToObject);
	}
}
