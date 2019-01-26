package ua.prozorro.temp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ua.prozorro.ProzorroApp;
import ua.prozorro.exchangeRates.ExchangeRateNBU;
import ua.prozorro.exchangeRates.ExchangeRateServiceNBU;
import ua.prozorro.exchangeRates.parser.ExchangeRateNBUParser;
import ua.prozorro.properties.AppProperty;
import ua.prozorro.properties.PropertyFields;
import ua.prozorro.prozorro.model.DataType;
import ua.prozorro.prozorro.model.pages.ProzorroPageContent;
import ua.prozorro.prozorro.model.plans.PlanData;
import ua.prozorro.prozorro.parser.PlanParser;
import ua.prozorro.prozorro.service.PageServiceProzorro;
import ua.prozorro.prozorro.service.PlanDataServiceProzorro;
import ua.prozorro.sql.HibernateDataBaseType;
import ua.prozorro.sql.HibernateFactory;
import ua.prozorro.utils.DateUtils;
import ua.prozorro.utils.FileUtils;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class TestNBURates {

	static String start_page = "https://bank.gov.ua/NBU_Exchange/exchange";
	static String page_prefix= "date";
	static String page_end = "json";
	static String date_formate = "dd.MM.yyyy";

	public static void main(String[] args) {
		String url1 = "https://bank.gov.ua/NBU_Exchange/exchange?date=14.01.2019&json";

		//GSONPareser(url1);
		//checkDataForPeriod();
		parseAndSaveData();

	}

	private static void parseAndSaveData() {
		//https://www.google.com/search?ei=SN0TXJPOAvDrrgS55JeoBQ&q=java+hibernate+%40onetoone+saveorupdate+child+first&oq=java+hibernate+%40onetoone+saveorupdate+child+first&gs_l=psy-ab.3...18094.18570..19344...0.0..0.94.270.3......0....1..gws-wiz.......0i71.ThMyYku4_E4

		SessionFactory sessionFactory = getSessionFactoryByDBName("mysql");

		ExchangeRateServiceNBU exchangeRateServiceNBU = new ExchangeRateServiceNBU(new PropertyFields(getStartProperties()));

		ExchangeRateNBUParser exchangeRateNBUParser = new ExchangeRateNBUParser();
		exchangeRateNBUParser.setSessionFactory(sessionFactory);
		exchangeRateNBUParser.setExchangeRateServiceNBU(exchangeRateServiceNBU);

		try {
			exchangeRateNBUParser.parseAndSave(DateUtils.parseDateFromString("01.01.2019", date_formate),
					DateUtils.parseDateFromString("10.01.2019", date_formate));
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			sessionFactory.close();
		}
	}

	private static Session getSessionByDBName(String dbName) {
		SessionFactory factory = getSessionFactoryByDBName(dbName);
		return factory.getCurrentSession();
	}

	private static SessionFactory getSessionFactoryByDBName(String dbName) {
		System.out.println("DBName = " + dbName);
		if (dbName == null || dbName.equals("")) {
			return null;
		}
		HibernateDataBaseType baseType = HibernateDataBaseType.valueOf(dbName.toUpperCase());
		System.out.println("HibernateDataBaseType = " + baseType);
		URL url = FileUtils.getLocation(ProzorroApp.class);
		SessionFactory factory = HibernateFactory.getHibernateSession(url, baseType);
		return factory;
	}

	private static void GSONPareser(String url) {
		try {
			String getUrl = url;
			PageServiceProzorro pageServiceProzorro = new PageServiceProzorro(new PropertyFields(getStartProperties()));

			ExchangeRateServiceNBU exchangeRateServiceNBU = new ExchangeRateServiceNBU();

			System.out.println("Read from URL: " + getUrl);

			List<ExchangeRateNBU> rateNBUList = exchangeRateServiceNBU.getRateContentFromURL(getUrl);
			System.out.println("Result Read from URL: " + rateNBUList);


		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void checkDataForPeriod() {

		ExchangeRateServiceNBU exchangeRateServiceNBU = new ExchangeRateServiceNBU(new PropertyFields(getStartProperties()));
		try {
			Date start = new Date();

			List<String> list = exchangeRateServiceNBU
					                    .getRatePagesList(DataType.NBU_RATES, DateUtils.parseDateFromString("01.01.2019", date_formate),
							                    DateUtils.parseDateFromString("10.01.2019", date_formate));
			System.out.println("Pages list size: " + list.size());
			Date finish = new Date();
			long timeForPages = finish.getTime() - start.getTime();

			System.out.println("Time for NBU Rates pages: " + timeForPages);

			//1000- approximately time in milliseconds for saving daily rates to database
			long totalTime = timeForPages + list.size() * 1000;
			System.out.println("Total Time: " + totalTime);

		} catch (java.text.ParseException|IOException e) {
			e.printStackTrace();
		}
	}


	private static Properties getStartProperties() {
		Properties properties = new Properties();

		properties.setProperty(AppProperty.NBU_START_PAGE.getPropertyName(), start_page);
		properties.setProperty(AppProperty.NBU_PAGE_PREFIX.getPropertyName(), page_prefix);
		properties.setProperty(AppProperty.NBU_PAGE_END.getPropertyName(), page_end);
		properties.setProperty(AppProperty.NBU_DATE_FORMAT.getPropertyName(), date_formate);

		return properties;
	}
}
