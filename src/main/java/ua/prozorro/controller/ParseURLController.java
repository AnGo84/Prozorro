package ua.prozorro.controller;

//import com.sun.deploy.uitoolkit.impl.fx.HostServicesFactory;
//import com.sun.javafx.application.HostServicesDelegate;

import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.prozorro.ProzorroApp;
import ua.prozorro.dataparser.nburate.NBURateParser;
import ua.prozorro.exchangeRates.ExchangeRateNBU;
import ua.prozorro.fx.DialogText;
import ua.prozorro.fx.Dialogs;
import ua.prozorro.properties.PropertiesUtils;
import ua.prozorro.properties.PropertyFields;
import ua.prozorro.prozorro.model.contracts.ContractData;
import ua.prozorro.prozorro.model.pages.ProzorroPageContent;
import ua.prozorro.prozorro.model.tenders.TenderData;
import ua.prozorro.source.SourceType;
import ua.prozorro.source.SourceTypeFactory;
import ua.prozorro.source.nburate.NBURateDTO;
import ua.prozorro.sourceService.exchangeRates.ExchangeRateServiceNBU;
import ua.prozorro.sourceService.prozorro.ProzorroContractDataService;
import ua.prozorro.sourceService.prozorro.ProzorroPageDataService;
import ua.prozorro.sourceService.prozorro.ProzorroPlanDataService;
import ua.prozorro.sourceService.prozorro.ProzorroTenderDataService;
import ua.prozorro.sql.HibernateDataBaseType;
import ua.prozorro.utils.FileUtils;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParseURLController {
	private static final Logger logger = LogManager.getRootLogger();
	private static final String ONLINE_JSON_PARSER = "online.json.parser";
	private static final String PROZORRO_SITE = "prozorro.site";
	private static final String PROZORRO_API_LINK = "prozorro.api.link";
	private static final String NBU_RATES_API_LINK = "nburates.api.link";
	
	@FXML
	private Button buttonGetFromURL;
	@FXML
	private Button buttonReadURL;
	@FXML
	private TextField textFieldURL;
	@FXML
	private TextArea textAreaURLData;
	@FXML
	private TextArea textAreaObjectData;
	@FXML
	private Hyperlink hyperLinkJsonParserOnline;
	@FXML
	private Hyperlink hyperLinkAPIData;
	@FXML
	private Hyperlink hyperLinkProzorroData;
	@FXML
	private Label labelURLDataType;
	@FXML
	private Label labelURLData;
	@FXML
	private Label labelProcessedData;
	
	private Map<String, String> propertiesMap = new HashMap<>();
	private String dataJSONFromURL;
	
	private ProzorroApp prozorroApp;
	private PropertyFields propertyFields;
	
	private Stage dialogStage;
	
	private boolean okClicked = false;
	
	private String urlAPIData;
	private String urlProzorroData;
	
	/**
	 * New method
	 */
	private String getDataFromJSON(SourceType sourceType, String dataJSON) {
		StringBuilder stringBuilder = new StringBuilder();
		switch (sourceType) {
			case NBU_RATE:
				NBURateParser nbuRateParser = new NBURateParser();
				List<NBURateDTO> list = nbuRateParser.parse(dataJSON);
				list.forEach(nbuRateDTO -> stringBuilder.append(nbuRateDTO).append("\n"));
				return stringBuilder.toString();
			case PROZORRO_CONTRACT:
			case PROZORRO_TENDER:
			case PROZORRO_PLAN:
				Gson gson = new Gson();
				ua.prozorro.source.prozorro.ProzorroPageContent prozorroPageContent =
						gson.fromJson(dataJSON, ua.prozorro.source.prozorro.ProzorroPageContent.class);
				
				String getJson =
						prozorroPageContent.toString().replaceAll("\\(", "\\(\\\\n").replaceAll("\\)", "\\)\\\\n");
				
				Arrays.asList(getJson.split("\\\\n")).forEach(line -> stringBuilder.append(line).append("\n"));
				return stringBuilder.toString();
		}
		
		return null;
	}
	
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	@FXML
	private void initialize() {
		textAreaURLData.clear();
		textAreaObjectData.clear();
		textFieldURL.clear();
		textFieldURL.requestFocus();
		labelURLDataType.setText("");
		
	}
	
	@Deprecated
	public void onButtonGetFromURL(ActionEvent actionEvent) {
		logger.info("Choosed URL: " + textFieldURL.getText());
		textAreaURLData.clear();
		textAreaObjectData.clear();
		try {
			dataJSONFromURL = FileUtils.getStringFromURL(textFieldURL.getText());
			
			showURLData(textAreaURLData, dataJSONFromURL);
			
			showOldDTOObjectData(textFieldURL.getText(), dataJSONFromURL);
			
		} catch (IOException e) {
			Dialogs.showErrorDialog(e, new DialogText(prozorroApp.getMessages().getString("error.read"),
													  prozorroApp.getMessages().getString("error.on_url_read") + ": " +
													  textFieldURL.getText(),
													  prozorroApp.getMessages().getString("error.error") + ": " +
													  e.getMessage()), logger);
		}
	}
	
	public void onButtonReadURL(ActionEvent actionEvent) {
		logger.info("Choosed URL: " + textFieldURL.getText());
		textAreaURLData.clear();
		textAreaObjectData.clear();
		
		SourceType sourceType = SourceTypeFactory.getSourceType(textFieldURL.getText());
		try {
			dataJSONFromURL = FileUtils.getStringFromURL(textFieldURL.getText());
			urlAPIData = textFieldURL.getText();
			
			showURLData(textAreaURLData, dataJSONFromURL);
			if (sourceType != null) {
				textAreaObjectData.appendText(getDataFromJSON(sourceType, dataJSONFromURL));
				labelURLDataType.setText(SourceTypeFactory.getSourceTypeMessage(sourceType, prozorroApp.getMessages()));
			}
		} catch (IOException e) {
			Dialogs.showErrorDialog(e, new DialogText(prozorroApp.getMessages().getString("error.read"),
													  prozorroApp.getMessages().getString("error.on_url_read") + ": " +
													  textFieldURL.getText(),
													  prozorroApp.getMessages().getString("error.error") + ": " +
													  e.getMessage()), logger);
		}
		
	}
	
	private void showURLData(TextArea textArea, String dataFromURL) {
		String[] dataLines = getStringsFromURLData(dataFromURL);
		
		for (String line : dataLines) {
			textArea.appendText(line + "\n");
		}
	}
	
	
	@Deprecated
	private void showOldDTOObjectData(String url, String dataFromURL) throws IOException {
		urlAPIData = url;
		if (url.toUpperCase().contains("NBU")) {
			ExchangeRateServiceNBU serviceNBU = new ExchangeRateServiceNBU();
			List<ExchangeRateNBU> list = serviceNBU.getExchangeRatesNBUFromStringJSON(dataFromURL);
			
			for (ExchangeRateNBU rate : list) {
				textAreaObjectData.appendText(rate.toString() + "\n");
			}
			labelURLDataType.setText("Курсы НБУ");
			
		} else if (url.toLowerCase().contains("offset")) {
			//PageServiceProzorro serviceProzorro = new PageServiceProzorro(null);
			ProzorroPageDataService serviceProzorro = new ProzorroPageDataService(null);
			ProzorroPageContent pageContent = serviceProzorro.getObjectFromStringJSON(dataFromURL);
			textAreaObjectData.appendText(pageContent.toString());
			labelURLDataType.setText("Список");
			
		} else if (url.toLowerCase().contains("tenders")) {
			ProzorroTenderDataService serviceProzorro = new ProzorroTenderDataService(null);
			TenderData pageContent = serviceProzorro.getObjectFromStringJSON(dataFromURL);
			pageContent.getTender().getTenderID();
			showURLData(textAreaObjectData, pageContent.toString());
			//textAreaObjectData.appendText(pageContent.toString());
			labelURLDataType.setText("Тендер");
			urlAPIData = propertiesMap.get(PROZORRO_API_LINK) + "tenders/" + pageContent.getTender().getId();
			urlProzorroData = propertiesMap.get(PROZORRO_SITE) + "tender/" + pageContent.getTender().getTenderID();
		} else if (url.toLowerCase().contains("contracts")) {
			ProzorroContractDataService serviceProzorro = new ProzorroContractDataService(null);
			ContractData pageContent = serviceProzorro.getObjectFromStringJSON(dataFromURL);
			showURLData(textAreaObjectData, pageContent.toString());
			//textAreaObjectData.appendText(pageContent.toString());
			labelURLDataType.setText("Контракт");
			urlAPIData = propertiesMap.get(PROZORRO_API_LINK) + "contracts/" + pageContent.getContract().getId();
			urlProzorroData =
					propertiesMap.get(PROZORRO_SITE) + "contract/" + pageContent.getContract().getContractID();
			
		} else if (url.toLowerCase().contains("plans")) {
			ProzorroPlanDataService serviceProzorro = new ProzorroPlanDataService(null);
			ua.prozorro.prozorro.model.plans.PlanData pageContent =
					serviceProzorro.getObjectFromStringJSON(dataFromURL);
			showURLData(textAreaObjectData, pageContent.toString());
			//textAreaObjectData.appendText(pageContent.toString());
			labelURLDataType.setText("План");
			urlAPIData = propertiesMap.get(PROZORRO_API_LINK) + "plans/" + pageContent.getPlan().getId();
			urlProzorroData = propertiesMap.get(PROZORRO_SITE) + "plan/" + pageContent.getPlan().getPlanID();
		}
		
		
	}
	
	private String[] getStringsFromURLData(String dataFromURL) {
		String getJson = dataFromURL.replaceAll("\\{", "\\\\n\\{\\\\n").replaceAll("}", "\\\\n\\}\\\\n");
		return getJson.split("\\\\n");
	}
	
	
	public void setProzorroApp(ProzorroApp prozorroApp) {
		this.prozorroApp = prozorroApp;
		this.propertyFields = new PropertyFields(prozorroApp.getProperties());
		
		String dbName = PropertiesUtils.getPropertyString(prozorroApp.getProperties(), "db.type");
		HibernateDataBaseType dataBaseType = HibernateDataBaseType.valueOf(dbName.toUpperCase());
		
		initPropertiesMap();
		
		//TODO
		labelURLData.setText(prozorroApp.getMessages().getString ("label.URL_data"));
		labelProcessedData.setText(prozorroApp.getMessages().getString ("label.processed_data"));
		buttonReadURL.setText(prozorroApp.getMessages().getString ("read.URL"));
	}
	
	private void initPropertiesMap() {
		propertiesMap.put(ONLINE_JSON_PARSER,
						  PropertiesUtils.getPropertyString(propertyFields.getProperties(), ONLINE_JSON_PARSER));
		propertiesMap
				.put(PROZORRO_SITE, PropertiesUtils.getPropertyString(propertyFields.getProperties(), PROZORRO_SITE));
		propertiesMap.put(PROZORRO_API_LINK,
						  PropertiesUtils.getPropertyString(propertyFields.getProperties(), PROZORRO_API_LINK));
		propertiesMap.put(NBU_RATES_API_LINK,
						  PropertiesUtils.getPropertyString(propertyFields.getProperties(), NBU_RATES_API_LINK));
		
		urlAPIData = propertiesMap.get(PROZORRO_API_LINK);
		urlProzorroData = propertiesMap.get(PROZORRO_SITE);
	}
	
	public boolean isOkClicked() {
		return okClicked;
	}
	
	public void onHyperLinkJsonParserOnline(ActionEvent actionEvent) {
		logger.info("Show " + propertiesMap.get(ONLINE_JSON_PARSER));
		/*try {
			HostServicesDelegate hostServices = HostServicesFactory.getInstance(prozorroApp);
			hostServices.showDocument(SITE_JSON_PARSER_ONLINE);
		} catch (Exception e1) {
			e1.printStackTrace();
		}*/
		
		showURL(propertiesMap.get(ONLINE_JSON_PARSER));
		
	}
	
	private void showURL(String url) {
		logger.info("Show URL:" + url);
		if (Desktop.isDesktopSupported()) {
			logger.info("DesktopSupported");
			try {
				Desktop.getDesktop().browse(new URL(url).toURI());
			} catch (URISyntaxException | IOException e1) {
				logger.error("Error: " + e1.getMessage());
				e1.printStackTrace();
			} catch (Exception e1) {
				logger.error("Error: " + e1.getMessage());
				e1.printStackTrace();
			}
		}
	}
	
	public void onHyperLinkProzorroData(ActionEvent actionEvent) {
		logger.info("Show " + urlProzorroData);
		/*try {
			HostServicesDelegate hostServices = HostServicesFactory.getInstance(prozorroApp);
			hostServices.showDocument(urlProzorroData);
		} catch (Exception e1) {
			e1.printStackTrace();
		}*/
		showURL(urlProzorroData);
	}
	
	public void onHyperLinkAPIData(ActionEvent actionEvent) {
		logger.info("Show " + urlAPIData);
		/*try {
			HostServicesDelegate hostServices = HostServicesFactory.getInstance(prozorroApp);
			hostServices.showDocument(urlAPIData);
		} catch (Exception e1) {
			e1.printStackTrace();
		}*/
		showURL(urlAPIData);
	}
}
