package ua.prozorro.controller;

import com.google.gson.Gson;
import com.sun.deploy.uitoolkit.impl.fx.HostServicesFactory;
import com.sun.javafx.application.HostServicesDelegate;
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
import ua.prozorro.fx.DialogText;
import ua.prozorro.fx.Dialogs;
import ua.prozorro.gson.nburate.NBURateParser;
import ua.prozorro.properties.PropertiesUtils;
import ua.prozorro.source.SourceType;
import ua.prozorro.source.SourceTypeFactory;
import ua.prozorro.source.nburate.NBURateDTO;
import ua.prozorro.source.prozorro.ProzorroPageContent;
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
	
	private Stage dialogStage;
	
	private boolean okClicked = false;
	
	private String urlAPIData;
	private String urlProzorroData;
	
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
				ProzorroPageContent prozorroPageContent = gson.fromJson(dataJSON, ProzorroPageContent.class);
				
				String getJson = prozorroPageContent.toString().replaceAll("\\(", "\\(\\\\n").replaceAll("\\)",
																										 "\\)\\\\n");
				
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
		}
		catch (IOException e) {
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
	
	private String[] getStringsFromURLData(String dataFromURL) {
		String getJson = dataFromURL.replaceAll("\\{", "\\\\n\\{\\\\n").replaceAll("}", "\\\\n\\}\\\\n");
		return getJson.split("\\\\n");
	}
	
	
	public void setProzorroApp(ProzorroApp prozorroApp) {
		this.prozorroApp = prozorroApp;
		
		initPropertiesMap();
		initComponentText();
	}
	
	private void initComponentText() {
		labelURLData.setText(prozorroApp.getMessages().getString("label.URL_data"));
		labelProcessedData.setText(prozorroApp.getMessages().getString("label.processed_data"));
		buttonReadURL.setText(prozorroApp.getMessages().getString("read.URL"));
	}
	
	private void initPropertiesMap() {
		propertiesMap.put(ONLINE_JSON_PARSER,
						  PropertiesUtils.getPropertyString(prozorroApp.getProperties(), ONLINE_JSON_PARSER));
		propertiesMap.put(PROZORRO_SITE, PropertiesUtils.getPropertyString(prozorroApp.getProperties(), PROZORRO_SITE));
		propertiesMap.put(PROZORRO_API_LINK,
						  PropertiesUtils.getPropertyString(prozorroApp.getProperties(), PROZORRO_API_LINK));
		propertiesMap.put(NBU_RATES_API_LINK,
						  PropertiesUtils.getPropertyString(prozorroApp.getProperties(), NBU_RATES_API_LINK));
		
		urlAPIData = propertiesMap.get(PROZORRO_API_LINK);
		urlProzorroData = propertiesMap.get(PROZORRO_SITE);
	}
	
	public boolean isOkClicked() {
		return okClicked;
	}
	
	public void onHyperLinkJsonParserOnline(ActionEvent actionEvent) {
		logger.info("Show " + propertiesMap.get(ONLINE_JSON_PARSER));
		
		showURL(propertiesMap.get(ONLINE_JSON_PARSER));
	}
	
	private void hostServicesShowDocument(String url) {
		try {
			HostServicesDelegate hostServices = HostServicesFactory.getInstance(prozorroApp);
			hostServices.showDocument(url);
		}
		catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	
	private void showURL(String url) {
		logger.info("Show URL:" + url);
		if (Desktop.isDesktopSupported()) {
			logger.info("DesktopSupported");
			try {
				Desktop.getDesktop().browse(new URL(url).toURI());
			}
			catch (URISyntaxException | IOException e1) {
				logger.error("Error: " + e1.getMessage());
				e1.printStackTrace();
			}
			catch (Exception e1) {
				logger.error("Error: " + e1.getMessage());
				e1.printStackTrace();
			}
		}
	}
	
	public void onHyperLinkProzorroData(ActionEvent actionEvent) {
		logger.info("Show " + urlProzorroData);
		showURL(urlProzorroData);
	}
	
	public void onHyperLinkAPIData(ActionEvent actionEvent) {
		logger.info("Show " + urlAPIData);
		showURL(urlAPIData);
	}
}
