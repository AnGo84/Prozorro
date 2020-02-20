package ua.prozorro.urlreader;

import lombok.Getter;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import ua.prozorro.service.ResultType;
import ua.prozorro.source.ContentData;
import ua.prozorro.source.DataPage;
import ua.prozorro.utils.DateUtils;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Getter
public class ExportData {
	
	private AbstractPageReader pageReader;
	private FilterData filterData;
	
	private int pageCount;
	
	private Date currentDate;
	
	public ExportData(@NonNull AbstractPageReader pageReader, @NonNull FilterData filterData) throws ParseException {
		this.pageReader = pageReader;
		this.filterData = filterData;
		updateCurrentDate();
		pageCount = 0;
	}
	
	public boolean hasData() {
		if (currentDate == null) {
			return false;
		}
		Date compareDate;
		try {
			compareDate = DateUtils.parseDateToFormat(currentDate, filterData.getDateFormat());
		} catch (ParseException e) {
			log.error("Can't format date '{}' to '{}': {}", currentDate, filterData.getDateFormat(), e.getMessage());
			return false;
		}
		return compareDate.compareTo(filterData.getDateTill()) <= 0;
	}
	
	public void read() throws IOException {
		pageReader.readDataPage();
		//TODO
		log.info("Page with content: {}",
				 (!ResultType.ERROR.equals(pageReader.getReadResult()) && filterData.isWithContent() &&
				  pageReader.pageHasContent()));
		if (ResultType.SUCCESS.equals(pageReader.getReadResult()) && filterData.isWithContent() &&
			pageReader.pageHasContent() && filterData.getDateTill() != null) {
			
			List<ContentData> pageContentData = pageReader.dataPage.getPageContentData().parallelStream()
																   .filter(content -> getDateInFilterFormat(content)
																							  .compareTo(filterData
																												 .getDateTill()) <=
																					  0).collect(Collectors.toList());
			
			pageReader.dataPage.setPageContentData(pageContentData);
			log.info("Filtered ContentData: " + pageContentData);
			pageReader.readPageContent();
		}
		pageCount++;
	}
	
	private Date getDateInFilterFormat(ContentData content) {
		try {
			return DateUtils
					.parseDateToFormat(pageReader.getDataURLDate(content.getDataURL()), filterData.getDateFormat());
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public void next() throws ParseException {
		pageReader.nextPage();
		//TODO remove
		log.info("DataPage: ");
		log.info("DataPage prev: {}", pageReader.dataPage.getPrevDataURL());
		log.info("DataPage current: {}", pageReader.dataPage.getCurrentDataURL());
		log.info("DataPage next: {}", pageReader.dataPage.getNextDataURL());
		updateCurrentDate();
	}
	
	public DataPage getCurrentURLData() {
		return pageReader.getDataPage();
	}
	
	private void updateCurrentDate() throws ParseException {
		currentDate = pageReader.getDataURLDate(pageReader.getDataPage().getCurrentDataURL());
	}
	
}
