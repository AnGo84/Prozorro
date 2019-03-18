package ua.prozorro.prozorro.service;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.prozorro.prozorro.model.pages.ProzorroPageContent;
import ua.prozorro.prozorro.model.pages.ProzorroPageElement;
import ua.prozorro.prozorro.model.tenders.TenderData;
import ua.prozorro.utils.DateUtils;
import ua.prozorro.utils.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
		/*System.out.println("getPageContentFromURL " +
						   (DateUtils.parseDateToString(new Date(), DateUtils.PROZORRO_DATE_FORMATE_WITHOUT_SSS)));*/
		String genreJson = FileUtils.getStringFromURL(url);
		/*System.out.println("getPageContentFromURL 2" +
						   (DateUtils.parseDateToString(new Date(), DateUtils.PROZORRO_DATE_FORMATE_WITHOUT_SSS)));*/
		return getTenderDataContentFromStringJSON(genreJson);
	}
	
	public List<TenderData> getTendersDataFromPageContent(ProzorroPageContent pageContent) throws IOException {
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
	
	public TenderData getTenderDataFromURL(String url) throws IOException {
		if (url == null || url.isEmpty()) {
			return null;
		}
		//System.out.println("Start getTenderDataFromURL " + (DateUtils.parseDateToString(new Date(),DateUtils.PROZORRO_DATE_FORMATE_WITHOUT_SSS )));
		TenderData tenderData = getPageContentFromURL(url);
		return tenderData;
	}
	
	
}
