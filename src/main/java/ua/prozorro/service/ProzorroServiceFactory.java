package ua.prozorro.service;

import org.hibernate.SessionFactory;
import ua.prozorro.properties.AppProperty;
import ua.prozorro.properties.PropertyFields;
import ua.prozorro.prozorro.PageServiceProzorro;
import ua.prozorro.prozorro.ParsingResultData;
import ua.prozorro.prozorro.TenderDataServiceProzorro;
import ua.prozorro.prozorro.model.DataType;
import ua.prozorro.prozorro.parser.TenderParser;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Properties;

public class ProzorroServiceFactory {
	//https://stackoverflow.com/questions/1970239/in-java-how-do-i-get-the-difference-in-seconds-between-2-dates
	public static ParsingResultData getApproximatelyParsingTimeForPeriod(PropertyFields propertyFields, DataType dataType, Date dateFrom, Date dateTill) throws IOException, ParseException {
		if (dataType.equals(DataType.TENDERS)) {
			PageServiceProzorro pageServiceProzorro = new PageServiceProzorro(propertyFields);
			return pageServiceProzorro.getApproximatelyParsingTimeForPeriod(dateFrom, dateTill);

		} else if (dataType.equals(DataType.CONTRACTS)) {
			return null;
		} else if (dataType.equals(DataType.PLANS)) {
			return null;
		}
		return null;
	}

	public static void parseAndSaveData(SessionFactory sessionFactory, Properties properties, Date dateFrom, Date dateTill) {
		//https://www.google.com/search?ei=SN0TXJPOAvDrrgS55JeoBQ&q=java+hibernate+%40onetoone+saveorupdate+child+first&oq=java+hibernate+%40onetoone+saveorupdate+child+first&gs_l=psy-ab.3...18094.18570..19344...0.0..0.94.270.3......0....1..gws-wiz.......0i71.ThMyYku4_E4
		//SessionFactory sessionFactory = getSessionFactoryByDBName("mysql");

		PageServiceProzorro pageServiceProzorro = new PageServiceProzorro(new PropertyFields(properties));
		/*TenderDataServiceProzorro tenderDataServiceProzorro =
				new TenderDataServiceProzorro(start_page + File.separator);*/
		TenderDataServiceProzorro tenderDataServiceProzorro = new TenderDataServiceProzorro(properties.getProperty(AppProperty.TENDER_START_PAGE.getPropertyName()) + "/");
		TenderParser tenderParser = new TenderParser();
		tenderParser.setSessionFactory(sessionFactory);
		tenderParser.setPageServiceProzorro(pageServiceProzorro);
		tenderParser.setTenderDataServiceProzorro(tenderDataServiceProzorro);

		try {
			/*tenderParser.parseAndSave(DateUtils.parseDateFromString("2018-12-11", short_date_formate),
					DateUtils.parseDateFromString("2018-12-11", short_date_formate));*/
			tenderParser.parseAndSave(dateFrom, dateTill);
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			sessionFactory.close();
		}
	}
}
