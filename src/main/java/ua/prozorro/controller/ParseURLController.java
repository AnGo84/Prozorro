package ua.prozorro.controller;

//import com.sun.deploy.uitoolkit.impl.fx.HostServicesFactory;
//import com.sun.javafx.application.HostServicesDelegate;

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
import ua.prozorro.exchangeRates.ExchangeRateNBU;
import ua.prozorro.fx.DialogText;
import ua.prozorro.fx.Dialogs;
import ua.prozorro.properties.PropertiesUtils;
import ua.prozorro.properties.PropertyFields;
import ua.prozorro.prozorro.model.contracts.ContractData;
import ua.prozorro.prozorro.model.pages.ProzorroPageContent;
import ua.prozorro.prozorro.model.tenders.TenderData;
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
import java.util.List;

public class ParseURLController {
	private static final Logger logger = LogManager.getRootLogger();
	
	private final String SITE_JSON_PARSER_ONLINE = "http://jsonparseronline.com/";
	private final String SITE_PROZORRO = "https://prozorro.gov.ua/";
	private final String SITE_PROZORRO_API = "https://public.api.openprocurement.org/api/2.4/";
	@FXML
	private Button buttonGetFromURL;
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
	
	private String dataFromURL;
	
	private ProzorroApp prozorroApp;
	private PropertyFields propertyFields;
	
	private Stage dialogStage;
	
	private boolean okClicked = false;
	
	private String urlAPIData = SITE_PROZORRO_API;
	private String urlProzorroData = SITE_PROZORRO;
	
	public Stage getDialogStage() {
		return dialogStage;
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
	
	public void onButtonGetFromURL(ActionEvent actionEvent) {
		
		logger.info("Выбран URL: " + textFieldURL.getText());
		textAreaURLData.clear();
		textAreaObjectData.clear();
		try {
			dataFromURL = FileUtils.getStringFromURL(textFieldURL.getText());
			
			showURLData(textAreaURLData, dataFromURL);
			
			showDTOObjectData(textFieldURL.getText(), dataFromURL);
			
		} catch (IOException e) {
			Dialogs.showErrorDialog(e, new DialogText("Ошибка чтения",
													  "Ошибка чтения данных по URL: " + textFieldURL.getText(),
													  "При чтение возникла ошибка: " + e.getMessage()), logger);
		}
	}
	
	private void showURLData(TextArea textArea, String dataFromURL) {
		String[] dataLines = getStringsFromURLData(dataFromURL);
		
		for (String line : dataLines) {
			textArea.appendText(line + "\n");
		}
	}
	
	private void showDTOObjectData(String url, String dataFromURL) throws IOException {
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
			urlAPIData = SITE_PROZORRO_API + "tenders/" + pageContent.getTender().getId();
			urlProzorroData = SITE_PROZORRO + "tender/" + pageContent.getTender().getTenderID();
		} else if (url.toLowerCase().contains("contracts")) {
			ProzorroContractDataService serviceProzorro = new ProzorroContractDataService(null);
			ContractData pageContent = serviceProzorro.getObjectFromStringJSON(dataFromURL);
			showURLData(textAreaObjectData, pageContent.toString());
			//textAreaObjectData.appendText(pageContent.toString());
			labelURLDataType.setText("Контракт");
			urlAPIData = SITE_PROZORRO_API + "contracts/" + pageContent.getContract().getId();
			urlProzorroData = SITE_PROZORRO + "contract/" + pageContent.getContract().getContractID();
			
		} else if (url.toLowerCase().contains("plans")) {
			ProzorroPlanDataService serviceProzorro = new ProzorroPlanDataService(null);
			ua.prozorro.prozorro.model.plans.PlanData pageContent =
					serviceProzorro.getObjectFromStringJSON(dataFromURL);
			showURLData(textAreaObjectData, pageContent.toString());
			//textAreaObjectData.appendText(pageContent.toString());
			labelURLDataType.setText("План");
			urlAPIData = SITE_PROZORRO_API + "plans/" + pageContent.getPlan().getId();
			urlProzorroData = SITE_PROZORRO + "plan/" + pageContent.getPlan().getPlanID();
		}
		
		
	}
	
	private String[] getStringsFromURLData(String dataFromURL) {
		String getJson = dataFromURL.replaceAll("\\{", "\\\\n\\{\\\\n").replaceAll("\\}", "\\\\n\\}\\\\n");
		
		return getJson.split("\\\\n");
	}
	
	
	public void setProzorroApp(ProzorroApp prozorroApp) {
		this.prozorroApp = prozorroApp;
		this.propertyFields = new PropertyFields(prozorroApp.getProperties());
		
		String dbName = PropertiesUtils.getPropertyString(prozorroApp.getProperties(), "db.type");
		HibernateDataBaseType dataBaseType = HibernateDataBaseType.valueOf(dbName.toUpperCase());
		
	}
	
	public boolean isOkClicked() {
		return okClicked;
	}
	
	public void onHyperLinkJsonParserOnline(ActionEvent actionEvent) {
		logger.info("Show " + SITE_JSON_PARSER_ONLINE);
		/*try {
			HostServicesDelegate hostServices = HostServicesFactory.getInstance(prozorroApp);
			hostServices.showDocument(SITE_JSON_PARSER_ONLINE);
		} catch (Exception e1) {
			e1.printStackTrace();
		}*/
		
		showURL(SITE_JSON_PARSER_ONLINE);
		
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
