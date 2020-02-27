package ua.prozorro.temp;

import org.hibernate.SessionFactory;
import ua.prozorro.service.Service;
import ua.prozorro.service.ServiceFactory;
import ua.prozorro.source.*;
import ua.prozorro.urlreader.AbstractPageReader;
import ua.prozorro.urlreader.ActionResult;
import ua.prozorro.urlreader.ExportData;
import ua.prozorro.urlreader.FilterData;
import ua.prozorro.urlreader.nburate.NBURatePageReader;
import ua.prozorro.urlreader.prozorro.ProzorroDataReader;
import ua.prozorro.utils.DateUtils;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class TestExportData {
	
	public static void main(String[] args) {
		SourceLink sourceLink =
				//SourceLinkFactory.getSourceLink(SourceType.NBU_RATE, TestPropertyFieldsFactory.getStartProperties());
				SourceLinkFactory.getSourceLink(SourceType.PROZORRO_CONTRACT,
												TestPropertyFieldsFactory.getStartProperties());
		
		Date nextDate = DateUtils.addDaysToDate(new Date(), -2);
		
		String date = DateUtils.parseDateToString(nextDate, sourceLink.getPageDateFormat());
		
		System.out.println("nextDate: " + nextDate);
		System.out.println("String Date: " + date);
		
		DataPage dataPage = DataPage.builder().currentDataURL(getDataURL(sourceLink, date)).build();
		AbstractPageReader pageReader;
		if (SourceType.NBU_RATE.equals(sourceLink.getType())) {
			pageReader = new NBURatePageReader(sourceLink, dataPage);
		} else {
			pageReader = new ProzorroDataReader(sourceLink, dataPage);
		}
		
		Date dateTill;
		try {
			dateTill = DateUtils.parseDateToFormat(new Date(), sourceLink.getPageDateFormat());
		}
		catch (ParseException e) {
			dateTill = null;
			e.printStackTrace();
		}
		FilterData filterData = new FilterData(nextDate, dateTill, true, true);
		System.out.println("Filter data: " + filterData);
		ExportData exportData = null;
		try {
			exportData = new ExportData(pageReader, filterData);
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		SessionFactory sessionFactory = TestSession.getSessionFactoryByDBName("mysql");
		Service service = ServiceFactory.getService(sessionFactory, sourceLink.getType());
		
		Date start = new Date();
		try {
			while (exportData.hasData()) {
				Date localStart = new Date();
				exportData.read();
				
				DataPage currentDataPage = exportData.getCurrentURLData();
				List<ActionResult> results = null;
				if (!filterData.isReadOnly()) {
					try {
						results = service.saveOrUpdate(currentDataPage.getPageContentData());
					}
					catch (IOException e) {
						System.out.println("Error on Data save: " + e.getMessage());
						throw e;
					}
				}
				exportData.next();
				Date localFinish = new Date();
				
				long timeForPage = (localFinish.getTime() - localStart.getTime());
				
				System.out.println(results);
				System.out.println("timeForPage: " + timeForPage);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			sessionFactory.close();
		}
		Date finish = new Date();
		long totalTime = (finish.getTime() - start.getTime());
		
		String finishMessage = String.format("Finished. Total Time: %s ms \n", DateUtils.getTextTime(totalTime));
		System.out.println(finishMessage);
		
	}
	
	private static DataURL getDataURL(SourceLink sourceLink, String date) {
		System.out.println("SourceLink: " + sourceLink);
		System.out.println("String date: " + date);
		return DataURL.builder().url(sourceLink.getURL(date)).date(date).dateFormat(sourceLink.getPageDateFormat())
					  .type(sourceLink.getType()).build();
	}
}
