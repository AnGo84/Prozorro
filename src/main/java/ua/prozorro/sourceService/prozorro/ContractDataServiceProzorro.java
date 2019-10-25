package ua.prozorro.sourceService.prozorro;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.prozorro.entity.PageElement;
import ua.prozorro.prozorro.model.contracts.ContractData;
import ua.prozorro.prozorro.model.pages.ProzorroPageContent;
import ua.prozorro.prozorro.model.pages.ProzorroPageElement;
import ua.prozorro.utils.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Deprecated
public class ContractDataServiceProzorro {
	private static final Logger logger = LogManager.getRootLogger();
	
	private String contractURL;
	
	public ContractDataServiceProzorro(String planURL) {
		this.contractURL = planURL;
	}
	
	public ContractData getContractDataContentFromStringJSON(String stringJSON) throws JsonParseException {
		Gson gson = new Gson();
		
		return gson.fromJson(stringJSON, ContractData.class);
	}
	
	public ContractData getContractContentFromURL(String url) throws JsonParseException, IOException {
		String genreJson = FileUtils.getStringFromURL(url);
		return getContractDataContentFromStringJSON(genreJson);
	}
	
	public List<ContractData> getContractsDataFromPageContent(ProzorroPageContent pageContent) throws IOException {
		if (pageContent == null || pageContent.getPageElementList() == null) {
			return null;
		}
		List<ContractData> contractDataList = new ArrayList<>();
		for (ProzorroPageElement element : pageContent.getPageElementList()) {
			ContractData contractData = getContractDataFromPageElement(element);
			contractDataList.add(contractData);
		}
		
		return contractDataList;
	}
	
	public ContractData getContractDataFromPageElement(PageElement pageElement) throws IOException {
		if (pageElement == null) {
			return null;
		}
		String currentURL = contractURL + pageElement.getId();
		ContractData data = getContractContentFromURL(currentURL);
		return data;
	}
}
