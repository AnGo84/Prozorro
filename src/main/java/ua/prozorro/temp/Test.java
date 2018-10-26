package ua.prozorro.temp;

import org.apache.commons.io.IOUtils;

import org.json.simple.parser.ParseException;
import ua.prozorro.Prozorro;
import ua.prozorro.model.pages.PageContent;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;


public class Test {
	public static void main(String[] args) {
		String url="https://public.api.openprocurement.org/api/2.4/tenders";

		try {
			String genreJson = IOUtils.toString(new URL(url), Charset.forName("UTF-8"));
			//JSONObject genreJsonObject = (JSONObject) JSONValue.parseWithException(genreJson);
			PageContent pageContent =	Prozorro.getPageContent(new URL(url), genreJson);
			System.out.println(pageContent);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}



}
