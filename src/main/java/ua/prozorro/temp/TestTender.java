package ua.prozorro.temp;

import org.apache.commons.io.IOUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ua.prozorro.ProzorroApp;
import ua.prozorro.properties.AppProperty;
import ua.prozorro.properties.PropertyFields;
import ua.prozorro.sourceService.prozorro.ProzorroPageDataService;
import ua.prozorro.sourceService.prozorro.ProzorroTenderDataService;
import ua.prozorro.sourceService.DataType;
import ua.prozorro.sql.HibernateDataBaseType;
import ua.prozorro.sql.HibernateFactory;
import ua.prozorro.utils.FileUtils;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.Date;
import java.util.Properties;


public class TestTender {
	static String start_page = "https://public.api.openprocurement.org/api/2.4/tenders";
	static String page_limit = "limit";
	static String page_limit_value = "100";
	static String page_offset = "offset";
	static String page_end = "T00%3A00%3A00.000000%2B03%3A00";
	static String date_formate = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX";
	static String short_date_formate = "yyyy-MM-dd";
	
	
	public static void main(String[] args) {
		PropertyFields propertyFields = null;
		try {
			propertyFields = Test.getTestPropertyFields("2016-10-06", "2016-10-11", DataType.TENDERS);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		String url = "https://public.api.openprocurement.org/api/2.4/tenders";
		String url2 =
				"https://public.api.openprocurement.org//api/2.4/tenders?offset=2016-10-10T10%3A42%3A04.250983%2B03%3A00";
		//"https://public.api.openprocurement.org/api/2.4/tenders?descending=1&offset=2018-11-30T21%3A00%3A03.340891%2B03%3A00";
		
		//JSONParser(url);
		//GSONPareser(url2);
		
		/*try {
			Date dateFrom = DateUtils.parseProzorroPageDateFromString("2016-11-05", short_date_formate);
			Date dateTill = DateUtils.parseProzorroPageDateFromString("2016-11-05", short_date_formate);
			checkDataForPeriod(dateFrom, dateTill);
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}*/
		//parseAndSaveDataByURL("https://public.api.openprocurement.org/api/2
		// .4/tenders?offset=2018-04-06T16%3A28%3A34.996915%2B02%3A00");
		//parseAndSaveData();
		parseAndSaveTenderByURL("https://public.api.openprocurement.org/api/2.5/tenders/dc67724a599643948baa4f1d0ebf911e");
		//ProzorroPageContent pageContent = getPageContentFromURL(pageContent.getNextPage().getUri());
		//ProzorroPageContent pageContent = PageServiceProzorro.getPageContentFromURL(startPageURL);

		/*

		TenderService tenderService = new TenderService(session);
                        TenderData tenderData = tenderDataServiceProzorro.getTenderDataFromPageElement(pageElement);
                        text = tenderData.toString();
                        TenderDTO tenderDTO = TenderDTOUtils.getTenderDTO(tenderData.getTender());
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

		tenderService.saveTender(tenderDTO, session);

		session.flush();
		session.clear();
		transaction.commit();
		session.close();

		 */
	}
	
	private static void parseAndSaveData(PropertyFields propertyFields) throws ParseException {
		//https://www.google.com/search?ei=SN0TXJPOAvDrrgS55JeoBQ&q=java+hibernate+%40onetoone+saveorupdate+child+first&oq=java+hibernate+%40onetoone+saveorupdate+child+first&gs_l=psy-ab.3...18094.18570..19344...0.0..0.94.270.3......0....1..gws-wiz.......0i71.ThMyYku4_E4
		
		SessionFactory sessionFactory = getSessionFactoryByDBName("mysql");


		ProzorroPageDataService pageServiceProzorro = new ProzorroPageDataService(new PropertyFields(getStartProperties()));
		/*TenderDataServiceProzorro tenderDataServiceProzorro =
				new TenderDataServiceProzorro(start_page + File.separator);*/
		ProzorroTenderDataService tenderDataServiceProzorro = new ProzorroTenderDataService(propertyFields);
		/*TenderParser tenderParser = new TenderParser();
		tenderParser.setSessionFactory(sessionFactory);
		tenderParser.setPageServiceProzorro(pageServiceProzorro);
		tenderParser.setTenderDataServiceProzorro(tenderDataServiceProzorro);
		
		try {
			tenderParser.parseAndSave(DateUtils.parseProzorroPageDateFromString("2016-10-06", short_date_formate),
									  DateUtils.parseProzorroPageDateFromString("2016-10-11", short_date_formate));
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			sessionFactory.close();
		}*/
	}



	private static void parseAndSaveDataByURL(String url, PropertyFields propertyFields) throws ParseException {
		//https://www.google.com/search?ei=SN0TXJPOAvDrrgS55JeoBQ&q=java+hibernate+%40onetoone+saveorupdate+child+first&oq=java+hibernate+%40onetoone+saveorupdate+child+first&gs_l=psy-ab.3...18094.18570..19344...0.0..0.94.270.3......0....1..gws-wiz.......0i71.ThMyYku4_E4
		
		SessionFactory sessionFactory = getSessionFactoryByDBName("mysql");

		ProzorroPageDataService pageServiceProzorro = new ProzorroPageDataService(new PropertyFields(getStartProperties()));
		/*TenderDataServiceProzorro tenderDataServiceProzorro =
				new TenderDataServiceProzorro(start_page + File.separator);*/
		/*TenderDataServiceProzorro tenderDataServiceProzorro = new TenderDataServiceProzorro(start_page + "/");
		TenderParser tenderParser = new TenderParser();
		tenderParser.setSessionFactory(sessionFactory);
		tenderParser.setPageServiceProzorro(pageServiceProzorro);
		tenderParser.setTenderDataServiceProzorro(tenderDataServiceProzorro);
		
		try {
			tenderParser.parseAndSaveURL(url);
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			sessionFactory.close();
		}*/
	}
	private static void parseAndSaveTenderByURL(String url) {
		//https://www.google.com/search?ei=SN0TXJPOAvDrrgS55JeoBQ&q=java+hibernate+%40onetoone+saveorupdate+child+first&oq=java+hibernate+%40onetoone+saveorupdate+child+first&gs_l=psy-ab.3...18094.18570..19344...0.0..0.94.270.3......0....1..gws-wiz.......0i71.ThMyYku4_E4
		
		/*SessionFactory sessionFactory = getSessionFactoryByDBName("mysql");
		
		PageServiceProzorro pageServiceProzorro = new PageServiceProzorro(new PropertyFields(getStartProperties()));
		*//*TenderDataServiceProzorro tenderDataServiceProzorro =
				new TenderDataServiceProzorro(start_page + File.separator);*//*
		TenderDataServiceProzorro tenderDataServiceProzorro = new TenderDataServiceProzorro(start_page + "/");
		TenderParser tenderParser = new TenderParser();
		tenderParser.setSessionFactory(sessionFactory);
		tenderParser.setPageServiceProzorro(pageServiceProzorro);
		tenderParser.setTenderDataServiceProzorro(tenderDataServiceProzorro);
		
		try {
			tenderParser.parseAndSaveTender(url);
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			sessionFactory.close();
		}*/
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
	
	private static void checkDataForPeriod(Date dateFrom, Date dateTill) {
		/*PageServiceProzorro pageServiceProzorro = new PageServiceProzorro(new PropertyFields(getStartProperties()));
		TenderDataServiceProzorro tenderDataServiceProzorro = new TenderDataServiceProzorro(start_page + "/");
		try {
			long startTime = System.nanoTime();
			
			List<ProzorroPageContent> list =
					pageServiceProzorro.getPagesList(DataType.TENDERS, dateFrom, dateTill, false);
			System.out.println("Pages list size: " + list.size());
			long endTime = System.nanoTime();
			long timeForPages = (endTime - startTime) / 1000000000;
			System.out.println("Time for page without data: " + timeForPages);

			startTime = System.nanoTime();
			long timeForPageTenders;
			if (list != null && !list.isEmpty()) {
				ProzorroPageContent pageContent = pageServiceProzorro.getPageContentFromURL(pageServiceProzorro
																									.getPageURL(
																											DateUtils
																													.parseProzorroPageDateFromString(
																															"2018-12-10",
																															short_date_formate)));
				tenderDataServiceProzorro.getTendersDataFromPageContent(pageContent);
			}
			endTime = System.nanoTime();
			timeForPageTenders = (endTime - startTime) / 1000000000;
			System.out.println("Time for ProzorroPage Tenders: " + timeForPageTenders);
			
			long totalTime = timeForPages + list.size() * timeForPageTenders;
			System.out.println("Total time: " + totalTime + "sec, " + (totalTime / 60) + "m");
			
			
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	}
	
	private static void JSONParser(String url) {
		/*try {
			String genreJson = getStringFromURL(url);
			//JSONObject genreJsonObject = (JSONObject) JSONValue.parseWithException(genreJson);
			PageContentURL pageContent = Prozorro.getPageContent(new URL(url), genreJson);
			System.out.println(pageContent);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	}
	
	private static void GSONParser(String url) {
		/*try {
			String getUrl = url;
			PageServiceProzorro pageServiceProzorro = new PageServiceProzorro(new PropertyFields(getStartProperties()));
			TenderDataServiceProzorro tenderDataServiceProzorro = new TenderDataServiceProzorro(start_page + "/");
			
			System.out.println("Read from URL: " + getUrl);
			//String genreJson = getStringFromURL(getUrl);
			
			long startTime = System.nanoTime();
			long startTime2 = startTime;
			
			
			ProzorroPageContent pageContent = pageServiceProzorro.getPageContentFromURL(getUrl);
			System.out.println("Read from URL: " + getUrl);
			int pageCount = 0;
			int tenderCount = 0;
			while (pageContent.getPageElementList() != null && !pageContent.getPageElementList().isEmpty() &&
				   pageCount < 10) {
				pageCount++;
				System.out.println("Read from URL: " + getUrl);
				
				for (ProzorroPageElement element : pageContent.getPageElementList()) {
					tenderCount++;
					String tenderURL = "https://public.api.openprocurement.org/api/2.4/tenders/" + element.getId();
					System.out.println("Get TenderDTO URL: " + tenderURL);
					TenderData tenderData = tenderDataServiceProzorro.getPageContentFromURL(tenderURL);
					System.out.println("TenderDTO " + tenderCount + ": " + tenderData);
				}
				long endTime = System.nanoTime();
				
				System.out.println("Time: " + (endTime - startTime) / 1000000000);
				
				startTime = System.nanoTime();
				
				pageContent = pageServiceProzorro.getPageContentFromURL(getUrl);
				getUrl = pageContent.getNextPage().getUri();
				System.out.println("Read from URL: " + getUrl);
			}
			System.out
					.println(new Date() + ": Total page count: " + pageContent + " with tenders count: " + tenderCount);
			System.out.println("Total time: " + ((startTime2 - System.nanoTime()) / 1000000000) + " seconds");

		} catch (IOException e) {
			e.printStackTrace();
		}*/
	}
	
	private static String getStringFromURL(String url) throws IOException {
		return IOUtils.toString(new URL(url), Charset.forName("UTF-8"));
	}
	
	private static Properties getStartProperties() {
		Properties properties = new Properties();
		properties.setProperty(AppProperty.TENDER_START_PAGE.getPropertyName(), start_page);
		properties.setProperty(AppProperty.TENDER_PAGE_LIMIT.getPropertyName(), page_limit);
		properties.setProperty(AppProperty.TENDER_PAGE_LIMIT_VALUE.getPropertyName(), page_limit_value);
		properties.setProperty(AppProperty.TENDER_PAGE_OFFSET.getPropertyName(), page_offset);
		properties.setProperty(AppProperty.TENDER_PAGE_END.getPropertyName(), page_end);
		properties.setProperty(AppProperty.PROZORRO_DATE_FORMAT.getPropertyName(), date_formate);
		properties.setProperty(AppProperty.PROZORRO_SHORT_DATE_FORMAT.getPropertyName(), short_date_formate);

		return properties;
	}
	
}
