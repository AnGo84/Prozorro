package ua.prozorro.prozorro;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.prozorro.prozorro.model.pages.ProzorroPageContent;
import ua.prozorro.prozorro.model.pages.ProzorroPageElement;
import ua.prozorro.prozorro.model.tenders.TenderData;
import ua.prozorro.utils.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TenderDataServiceProzorro {
	private static final Logger logger = LogManager.getRootLogger();

	private String tenderURL;

	public TenderDataServiceProzorro(String tenderURL) {
		this.tenderURL = tenderURL;
	}

	public TenderData getTenderDataContentFromStringJSON(String stringJSON) throws JsonParseException {
		Gson gson = new Gson();

		return gson.fromJson(stringJSON, TenderData.class);
	}

	public TenderData getPageContentFromURL(String url) throws JsonParseException, IOException {
		String genreJson = FileUtils.getStringFromURL(url);
		return getTenderDataContentFromStringJSON(genreJson);
	}

	public List<TenderData> getTenderDatasFromPageContent(ProzorroPageContent pageContent) throws IOException {
		if (pageContent == null || pageContent.getPageElementList() == null) {
			return null;
		}
		List<TenderData> tenderDataList = new ArrayList<>();
		for (ProzorroPageElement element : pageContent.getPageElementList()) {
			String currentTenderURL = tenderURL + element.getId();

			TenderData tenderData = getPageContentFromURL(currentTenderURL);
			tenderDataList.add(tenderData);
		}

		return tenderDataList;
	}

	public TenderData getTenderDataFromPageElement(ProzorroPageElement pageElement) throws IOException {
		if (pageElement == null) {
			return null;
		}
		String currentTenderURL = tenderURL + pageElement.getId();

		TenderData tenderData = getPageContentFromURL(currentTenderURL);
		return tenderData;
	}


}
