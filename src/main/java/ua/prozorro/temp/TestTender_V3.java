package ua.prozorro.temp;

import org.hibernate.SessionFactory;
import ua.prozorro.service.Service;
import ua.prozorro.service.ServiceFactory;
import ua.prozorro.service.prozorro.TenderService;
import ua.prozorro.source.*;
import ua.prozorro.urlreader.ExportData;
import ua.prozorro.urlreader.ActionResult;
import ua.prozorro.urlreader.FilterData;
import ua.prozorro.urlreader.prozorro.ProzorroDataReader;
import ua.prozorro.utils.DateUtils;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class TestTender_V3 {
	
	public static void main(String[] args) throws ParseException {
		SourceLink sourceLink = SourceLinkFactory
				.getSourceLink(SourceType.PROZORRO_CONTRACT, TestPropertyFieldsFactory.getStartProperties());
		Date start = new Date();
		Date dateFrom = DateUtils.addDaysToDate(new Date(), -1);
		try {
			dateFrom = DateUtils.parseDateToFormat(dateFrom, sourceLink.getPageDateFormat());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		String date = DateUtils.parseDateToString(dateFrom, sourceLink.getPageDateFormat());
		
		
		/*
		System.out.println("dateFrom: " + dateFrom);
		System.out.println("String Date: " + date);
		
		DataURL startUrl = getDataURL(sourceLink, date);
		DataPage dataPage = DataPage.builder().currentDataURL(startUrl).build();
		ProzorroDataReader prozorroDataReader = new ProzorroDataReader(sourceLink, dataPage);
		prozorroDataReader.readDataPage();
		Date start = new Date();
		prozorroDataReader.readPageContent();
		Date finish = new Date();
		long timeForPages = (finish.getTime() - start.getTime());
		//tenderPageReader.nextPage();
		
		//System.out.println("Result: " + tenderPageReader.getDataPage().toString());
		System.out.println("Result: " + prozorroDataReader.getDataPage().getPageContentData().size());
		System.out.println("Result time: " + DateUtils.getTextTime(timeForPages));
		
		System.out.println("-----------------------------------------------------");
		System.out.println(prozorroDataReader.getDataPage().getPageContentData().get(50));
		*/
		
		System.out.println("dateFrom: " + dateFrom);
		System.out.println("String Date: " + date);
		
		DataPage dataPage = DataPage.builder().currentDataURL(sourceLink.getDataURL(sourceLink, date)).build();
		ProzorroDataReader prozorroDataReader = new ProzorroDataReader(sourceLink, dataPage);
		System.out.println("dataPage: " + dataPage);
		System.out.println("ProzorroDataReader: " + prozorroDataReader.getDataPage());
		
		Date dateTill;
		try {
			dateTill = DateUtils.parseDateToFormat(new Date(), sourceLink.getPageDateFormat());
		} catch (ParseException e) {
			dateTill = null;
			e.printStackTrace();
		}
		FilterData filterData = new FilterData(dateFrom, dateTill, true, false);
		System.out.println("Filter data: " + filterData);
		ExportData exportData = new ExportData(prozorroDataReader, filterData);
		//NBURateParser nbuRateParser = new NBURateParser();
		
		SessionFactory sessionFactory = TestSession.getSessionFactoryByDBName("mysql");
		//Session session = TestSession.getSessionByDBName("mysql");
		
		Service service = ServiceFactory.getService(sessionFactory, sourceLink.getType());
		//NBURateRepository nbuRateRepository = new NBURateRepository(sessionFactory);
		
		Date finish = new Date();
		long timeForPages = (finish.getTime() - start.getTime());
		System.out.println("Prepared time: " + DateUtils.getTextTime(timeForPages));
		start = new Date();
		while (exportData.hasData()) {
			Date localStart = new Date();
			try {
				exportData.read();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			System.out.println("Result page '" + exportData.getPageCount() + "': " +
							   exportData.getPageReader().getDataPage().toString());
			System.out.println("---------------------------------------------------");
			
			DataPage currentDataPage = exportData.getCurrentURLData();
			
			if(!filterData.isReadOnly()) {
				List<ActionResult> results = null;
				try {
					results = service.saveOrUpdate(currentDataPage.getPageContentData());
					results.stream().forEach(System.out::println);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}

			System.out.println("Saved list: ");
			exportData.next();
			System.out.println("NEXT Data: " + exportData.getCurrentURLData());
			System.out.println("---------------------------------------------------");
			Date localFinish = new Date();
			long localTimeForPage = (localFinish.getTime() - localStart.getTime());
			System.out.println("Read page '" + exportData.getPageCount() +"': " + DateUtils.getTextTime(localTimeForPage));
			
		}
		sessionFactory.close();
		finish = new Date();
		timeForPages = (finish.getTime() - start.getTime());
		System.out.println("Total pages read time: " + DateUtils.getTextTime(timeForPages));
		
	}
	
	private static DataURL getDataURL(SourceLink sourceLink, String date) {
		System.out.println("SourceLink: " + sourceLink);
		System.out.println("String date: " + date);
		return DataURL.builder().url(sourceLink.getURL(date)).date(date).dateFormat(sourceLink.getPageDateFormat())
					  .type(sourceLink.getType()).build();
	}
}
