package ua.prozorro.prozorro;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import ua.prozorro.model.pages.PageContent;
import ua.prozorro.model.tenders.TenderData;
import ua.prozorro.utils.FileUtils;

import java.io.IOException;

public class TenderDataService {

	public TenderData getTenderDataContentFromStringJSON(String stringJSON) throws JsonParseException {
		Gson gson = new Gson();

		return gson.fromJson(stringJSON, TenderData.class);
	}
	public TenderData getPageContentFromURL(String url) throws JsonParseException, IOException {
		String genreJson = FileUtils.getStringFromURL(url);
		return getTenderDataContentFromStringJSON(genreJson);
	}
}
