package ua.prozorro.prozorro;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import ua.prozorro.model.pages.PageContent;
import ua.prozorro.utils.FileUtils;

import java.io.IOException;

//http://www.jsonschema2pojo.org/
public class PageService {

	public PageContent getPageContentFromStringJSON(String stringJSON) throws JsonParseException {
		Gson gson = new Gson();
		return gson.fromJson(stringJSON, PageContent.class);
	}

	public PageContent getPageContentFromURL(String url) throws JsonParseException, IOException {
		String genreJson = FileUtils.getStringFromURL(url);
		return getPageContentFromStringJSON(genreJson);
	}

	/*  private GSONParser gsonParser = new GSONParser();
		public PageContent getPageContentFromStringJSON(String genreJson) {
		List<PageContent> list = GSONParser.getList(genreJson, PageContent[].class);
		if (list!=null) {

			return list.get(0);
		}
		return null;
	}*/
}
