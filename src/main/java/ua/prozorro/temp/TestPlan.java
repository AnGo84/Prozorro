package ua.prozorro.temp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ua.prozorro.ProzorroApp;
import ua.prozorro.properties.AppProperty;
import ua.prozorro.properties.PropertyFields;
import ua.prozorro.prozorro.model.DataType;
import ua.prozorro.prozorro.model.pages.ProzorroPageContent;
import ua.prozorro.prozorro.model.pages.ProzorroPageElement;
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

public class TestPlan {
	
	static String start_page = "https://public.api.openprocurement.org/api/2.4/plans";
	static String page_limit = "limit";
	static String page_limit_value = "100";
	static String page_offset = "offset";
	static String page_end = "T00%3A00%3A00.000000%2B03%3A00";
	static String date_formate = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX";
	static String short_date_formate = "yyyy-MM-dd";
	
	public static void main(String[] args) {
		String url = "https://public.api.openprocurement.org/api/2.4/plans";
		String url2 =
				"https://public.api.openprocurement.org/api/2.4/plans?offset=2018-11-30T21%3A00%3A03.340891%2B03%3A00";
		//"https://public.api.openprocurement.org/api/2.4/tenders?descending=1&offset=2018-11-30T21%3A00%3A03.340891%2B03%3A00";
		
		
		//GSONPareser("https://public.api.openprocurement.org/api/2.4/plans?offset=2018-01-31T10%3A32%3A29.996915%2B02%3A00");
		//checkDataForPeriod();
		//parseAndSaveData();
		parseAndSaveDataByURL("https://public.api.openprocurement.org/api/2.4/plans?offset=2018-01-31T10%3A32%3A29.996915%2B02%3A00");
		
	}
	
	private static void parseAndSaveData() {
		//https://www.google.com/search?ei=SN0TXJPOAvDrrgS55JeoBQ&q=java+hibernate+%40onetoone+saveorupdate+child+first&oq=java+hibernate+%40onetoone+saveorupdate+child+first&gs_l=psy-ab.3...18094.18570..19344...0.0..0.94.270.3......0....1..gws-wiz.......0i71.ThMyYku4_E4
		
		SessionFactory sessionFactory = getSessionFactoryByDBName("mysql");
		
		PageServiceProzorro pageServiceProzorro = new PageServiceProzorro(new PropertyFields(getStartProperties()));
		
		PlanDataServiceProzorro planDataServiceProzorro = new PlanDataServiceProzorro(start_page + "/");
		PlanParser planParser = new PlanParser();
		planParser.setSessionFactory(sessionFactory);
		planParser.setPageServiceProzorro(pageServiceProzorro);
		planParser.setPlanDataServiceProzorro(planDataServiceProzorro);
		
		try {
			planParser.parseAndSave(DateUtils.parseProzorroPageDateFromString("2018-01-31", short_date_formate),
									DateUtils.parseProzorroPageDateFromString("2018-01-31", short_date_formate));
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			sessionFactory.close();
		}
	}
	private static void parseAndSaveDataByURL(String url) {
		//https://www.google.com/search?ei=SN0TXJPOAvDrrgS55JeoBQ&q=java+hibernate+%40onetoone+saveorupdate+child+first&oq=java+hibernate+%40onetoone+saveorupdate+child+first&gs_l=psy-ab.3...18094.18570..19344...0.0..0.94.270.3......0....1..gws-wiz.......0i71.ThMyYku4_E4
		
		SessionFactory sessionFactory = getSessionFactoryByDBName("mysql");
		
		PageServiceProzorro pageServiceProzorro = new PageServiceProzorro(new PropertyFields(getStartProperties()));
		
		PlanDataServiceProzorro planDataServiceProzorro = new PlanDataServiceProzorro(start_page + "/");
		PlanParser planParser = new PlanParser();
		planParser.setSessionFactory(sessionFactory);
		planParser.setPageServiceProzorro(pageServiceProzorro);
		planParser.setPlanDataServiceProzorro(planDataServiceProzorro);
		
		try {
			planParser.parseAndSaveURL(url);
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			sessionFactory.close();
		}
	}
	
	private static Session getSessionByDBName(String dbName) throws NullPointerException {
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
		//SessionFactory factory = HibernateFactory.getHibernateSession(baseType);
		URL url = FileUtils.getLocation(ProzorroApp.class);
		SessionFactory factory = HibernateFactory.getHibernateSession(url, baseType);
		return factory;
	}
	
	private static void GSONPareser(String url) {
		try {
			String getUrl = url;
			PageServiceProzorro pageServiceProzorro = new PageServiceProzorro(new PropertyFields(getStartProperties()));
			PlanDataServiceProzorro planDataServiceProzorro = new PlanDataServiceProzorro(start_page + "/");
			
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
					String planURL = "https://public.api.openprocurement.org/api/2.4/plans/" + element.getId();
					System.out.println("Get PlanData URL: " + planURL);
					PlanData planData = planDataServiceProzorro.getPageContentFromURL(planURL);
					System.out.println("PlanData " + tenderCount + ": " + planData);
				}
				long endTime = System.nanoTime();
				
				System.out.println("Time: " + (endTime - startTime));
				
				startTime = System.nanoTime();
				
				pageContent = pageServiceProzorro.getPageContentFromURL(getUrl);
				getUrl = pageContent.getNextPage().getUri();
				System.out.println("Read from URL: " + getUrl);
			}
			System.out.println(new Date() + ": Total page count: " + pageContent + " with plans count: " + tenderCount);
			System.out.println("Total time: " + (startTime2 - System.nanoTime()) + " seconds");
            /*System.out.println(pageContent);

            System.out.println("Read from next URL: " + pageContent.getNextPage().getUri());
            //genreJson =  IOUtils.toString(new URL(pageContent.getNextPage().getUri()), Charset.forName("UTF-8"));

            pageContent = pageServiceProzorro.getPageContentFromURL(pageContent.getNextPage().getUri());

            System.out.println(pageContent);

            //

            //getUrl = url +"/" +  pageContent.getPageElementList().get(0).getId();
            getUrl = "https://public.api.openprocurement.org/api/2.4/tenders/8689aed656e34ece8420559e50edaacb";
            System.out.println("Get TenderDTO URL: " + getUrl);
            TenderData tenderData = tenderDataServiceProzorro.getPageContentFromURL(getUrl);
            System.out.println("TenderDTO:");
            System.out.println(tenderData);*/
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void checkDataForPeriod() {
		
		PageServiceProzorro pageServiceProzorro = new PageServiceProzorro(new PropertyFields(getStartProperties()));
		PlanDataServiceProzorro planDataServiceProzorro = new PlanDataServiceProzorro(start_page + "/");
		try {
			Date start = new Date();
			
			List<ProzorroPageContent> list = pageServiceProzorro
					.getPagesList(DataType.PLANS, DateUtils.parseProzorroPageDateFromString("2019-01-08", short_date_formate),
								  DateUtils.parseProzorroPageDateFromString("2019-01-08", short_date_formate), false);
			System.out.println("Pages list size: " + list.size());
			Date finish = new Date();
			long timeForPages = finish.getTime() - start.getTime();
			System.out.println("Time for page without data: " + timeForPages);
			
			start = new Date();
			
			if (list != null && !list.isEmpty()) {
				ProzorroPageContent pageContent = pageServiceProzorro.getPageContentFromURL(pageServiceProzorro
																									.getPlanPageURL(
																											DateUtils
																													.parseProzorroPageDateFromString(
																															"2019-01-08",
																															short_date_formate)));
				List<PlanData> planDataList = planDataServiceProzorro.getPlansDataFromPageContent(pageContent);
			}
			finish = new Date();
			long timeForPagePlans = finish.getTime() - start.getTime();
			System.out.println("Time for ProzorroPage Plans: " + timeForPagePlans);
			
			//100- plans on page
			//1000- approximately time in milliseconds for saving one plan to database
			//long totalTime = timeForPages + list.size() * (timeForPagePlans + 100 * 2000 * 1000000);
			long totalTime = timeForPages + list.size() * (timeForPagePlans + 100 * 2000);
			System.out.println("Total Time: " + totalTime);
			
			
		} catch (java.text.ParseException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	private static Properties getStartProperties() {
		Properties properties = new Properties();
		properties.setProperty(AppProperty.PLAN_START_PAGE.getPropertyName(), start_page);
		properties.setProperty(AppProperty.PLAN_PAGE_LIMIT.getPropertyName(), page_limit);
		properties.setProperty(AppProperty.PLAN_PAGE_LIMIT_VALUE.getPropertyName(), page_limit_value);
		properties.setProperty(AppProperty.PLAN_PAGE_OFFSET.getPropertyName(), page_offset);
		properties.setProperty(AppProperty.PLAN_PAGE_END.getPropertyName(), page_end);
		properties.setProperty(AppProperty.DATE_FORMAT.getPropertyName(), date_formate);
		properties.setProperty(AppProperty.SHORT_DATE_FORMAT.getPropertyName(), short_date_formate);
		
		
		return properties;
	}
}
