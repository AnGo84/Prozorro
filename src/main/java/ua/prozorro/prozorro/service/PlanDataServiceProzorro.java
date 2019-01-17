package ua.prozorro.prozorro.service;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.prozorro.prozorro.model.pages.ProzorroPageContent;
import ua.prozorro.prozorro.model.pages.ProzorroPageElement;
import ua.prozorro.prozorro.model.plans.PlanData;
import ua.prozorro.utils.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlanDataServiceProzorro {
	private static final Logger logger = LogManager.getRootLogger();

	private String planURL;

	public PlanDataServiceProzorro(String planURL) {
		this.planURL = planURL;
	}

	public PlanData getPlanDataContentFromStringJSON(String stringJSON) throws JsonParseException {
		Gson gson = new Gson();

		return gson.fromJson(stringJSON, PlanData.class);
	}

	public PlanData getPageContentFromURL(String url) throws JsonParseException, IOException {
		String genreJson = FileUtils.getStringFromURL(url);
		return getPlanDataContentFromStringJSON(genreJson);
	}

	public List<PlanData> getPlansDataFromPageContent(ProzorroPageContent pageContent) throws IOException {
		if (pageContent == null || pageContent.getPageElementList() == null) {
			return null;
		}
		List<PlanData> planDataList = new ArrayList<>();
		for (ProzorroPageElement element : pageContent.getPageElementList()) {
			String currentURL = planURL + element.getId();

			PlanData planData = getPageContentFromURL(currentURL);
			planDataList.add(planData);
		}

		return planDataList;
	}

	public PlanData getPlanDataFromPageElement(ProzorroPageElement pageElement) throws IOException {
		if (pageElement == null) {
			return null;
		}
		String currentURL = planURL + pageElement.getId();

		PlanData data = getPageContentFromURL(currentURL);
		return data;
	}
}
