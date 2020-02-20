package ua.prozorro.temp;

import org.hibernate.SessionFactory;
import ua.prozorro.service.Service;
import ua.prozorro.service.ServiceFactory;
import ua.prozorro.service.nburate.NBURateService;
import ua.prozorro.urlreader.ExportData;
import ua.prozorro.urlreader.ActionResult;
import ua.prozorro.urlreader.FilterData;
import ua.prozorro.urlreader.nburate.NBURatePageReader;
import ua.prozorro.source.*;
import ua.prozorro.utils.DateUtils;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class TestNBURates_V3 {
	
	public static void main(String[] args) {
		SourceLink sourceLink =
				SourceLinkFactory.getSourceLink(SourceType.NBU_RATE, TestPropertyFieldsFactory.getStartProperties());
		
		Date nextDate = DateUtils.addDaysToDate(new Date(), -2);
		
		String date = DateUtils.parseDateToString(nextDate, sourceLink.getPageDateFormat());
		/*
		System.out.println("nextDate: " + nextDate);
		System.out.println("String Date: " + date);
		
		DataURL startUrl = getDataURL(sourceLink, date);
		DataPage dataPage = DataPage.builder().currentDataURL(startUrl).build();
		NBURatePageReader nbuRatePageReader = new NBURatePageReader(sourceLink, dataPage);
		nbuRatePageReader.readDataPage();
		nbuRatePageReader.readPageContent();
		
		nbuRatePageReader.nextPage();
		
		System.out.println("Result: " + nbuRatePageReader.getDataPage().toString());
		System.out.println("-----------------------------------------------------");
		*/
		System.out.println("nextDate: " + nextDate);
		System.out.println("String Date: " + date);
		
		DataPage dataPage = DataPage.builder().currentDataURL(getDataURL(sourceLink, date)).build();
		NBURatePageReader nbuRatePageReader = new NBURatePageReader(sourceLink, dataPage);
		System.out.println("dataPage: " + dataPage);
		System.out.println("nbuRatePageReader: " + nbuRatePageReader.getDataPage());
		
		Date filterDate;
		Date dateTill;
		try {
			dateTill = DateUtils.parseDateToFormat(new Date(), sourceLink.getPageDateFormat());
		} catch (ParseException e) {
			dateTill = null;
			e.printStackTrace();
		}
		FilterData filterData = new FilterData(nextDate, dateTill, true, false);
		System.out.println("Filter data: " + filterData);
		ExportData exportData = null;
		try {
			exportData = new ExportData(nbuRatePageReader, filterData);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		SessionFactory sessionFactory = TestSession.getSessionFactoryByDBName("mysql");
		Service service = ServiceFactory.getService(sessionFactory, sourceLink.getType());
		
		while (exportData.hasData()) {
			try {
				exportData.read();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			System.out.println("Result page '" + exportData.getPageCount() + "': " +
							   exportData.getPageReader().getDataPage().toString());
			System.out.println("---------------------------------------------------");
			
			DataPage currentDataPage = exportData.getCurrentURLData();
			
			List<ActionResult> results= null;
			if(!filterData.isReadOnly()) {
				try {
					results = service.saveOrUpdate(currentDataPage.getPageContentData());
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				results.stream().forEach(System.out::println);
			}
			/*List<NBURatesPageDTO> nbuRatesPageDTOList = new ArrayList<>();
			
			
			for (ContentData contentData : currentDataPage.getPageContentData()) {
				nbuRatesPageDTOList.add(nbuRateParser.parse(contentData));
			}
			
			System.out.println("NBU Rates DTO list: " + nbuRatesPageDTOList);
			System.out.println("---------------------------------------------------");
			
			List<NBURate> nbuRatesList = null;
			NBURateDTOToNBURateMapper mapper = new NBURateDTOToNBURateMapper();
			for (NBURatesPageDTO nbuRatesPageDTO : nbuRatesPageDTOList) {
				nbuRatesList =
						nbuRatesPageDTO.getNbuRates().stream().map(nbuRateDTO -> mapper.convertToEntity(nbuRateDTO)).collect(
								Collectors.toList());
			}
			
			System.out.println("NBU Rates list: " + nbuRatesPageDTOList);
			System.out.println("---------------------------------------------------");
			System.out.println("Save list: ");
			nbuRateRepository.saveAll(nbuRatesList);
			*/
			System.out.println("Saved list: ");
			try {
				exportData.next();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			System.out.println("NEXT Data: " + exportData.getCurrentURLData());
			System.out.println("---------------------------------------------------");
			
		}
		sessionFactory.close();
		
		
	}
	
	private static DataURL getDataURL(SourceLink sourceLink, String date) {
		System.out.println("SourceLink: " + sourceLink);
		System.out.println("String date: " + date);
		return DataURL.builder().url(sourceLink.getURL(date)).date(date).dateFormat(sourceLink.getPageDateFormat())
					  .type(sourceLink.getType()).build();
	}
}
