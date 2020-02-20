package ua.prozorro.task;

import javafx.concurrent.Task;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import ua.prozorro.service.Service;
import ua.prozorro.service.ServiceFactory;
import ua.prozorro.source.DataPage;
import ua.prozorro.source.SourceLink;
import ua.prozorro.urlreader.*;
import ua.prozorro.utils.DateUtils;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Log4j2
public class ExportDataTask extends Task<TaskResult> {
	public static final String START_SEARCHING_FOR_FILTER_DATA = "Start searching for '%s' for period from %s to %s \n";
	public static final String PAGE_READ_RESULT = "Page № %d on %s. Items on page: %d. Time: %s \n";
	public static final String PAGE_READ_RESULT_FULL = "Page № %d on %s : added %d, error %d. Time %s \n";
	public static final String PAGE_READ_RESULT_WITH_CONTENT_ONLY = "Page № %d on %s. Content lines %d. Time %s \n";
	public static final String FINISH_MESSAGE = "Finished. Total Time: %s ms \n";
	public static final int HOURS_IN_DAY = 24;
	private static final Logger logger = LogManager.getRootLogger();
	private SourceLink sourceLink;
	private FilterData filterData;
	private SessionFactory sessionFactory;
	private Service service;
	private AbstractPageReader pageReader;
	private long maxExportProgress;
	
	public ExportDataTask(SourceLink sourceLink, FilterData filterData, SessionFactory sessionFactory) {
		this.sourceLink = sourceLink;
		this.filterData = filterData;
		this.sessionFactory = sessionFactory;
		this.service = ServiceFactory.getService(sessionFactory, sourceLink.getType());
		initDataPage(sourceLink, filterData);
		
		maxExportProgress =
				(DateUtils.daysBetween(filterData.getDateFrom(), filterData.getDateTill()) + 1) * HOURS_IN_DAY;
		
	}
/*
	public ExportDataTask(SourceLink sourceLink, FilterData filterData) {
		this.sourceLink = sourceLink;
		this.filterData = filterData;
		initData(sourceLink, filterData);
	}
	
	private static DataURL getDataURL(SourceLink sourceLink, String date) {
		log.info("SourceLink: {}", sourceLink);
		log.info("String date: {}", date);
		return DataURL.builder().url(sourceLink.getURL(date)).date(date).dateFormat(sourceLink.getPageDateFormat())
					  .type(sourceLink.getType()).build();
	}*/
	
	private void initDataPage(SourceLink sourceLink, FilterData filterData) {
		String date = DateUtils.parseDateToString(filterData.getDateFrom(), sourceLink.getPageDateFormat());
		DataPage dataPage = DataPage.builder().currentDataURL(sourceLink.getDataURL(sourceLink, date)).build();
		this.pageReader = PageReaderFactory.getPageReader(sourceLink, dataPage);
	}
	
	@Override
	protected TaskResult call() throws Exception {
		ExportData exportData = new ExportData(pageReader, filterData);
		
		Date start = new Date();
		while (exportData.hasData()) {
			Date localStart = new Date();
			exportData.read();
			
			DataPage currentDataPage = exportData.getCurrentURLData();
			List<ActionResult> results = null;
			if (!filterData.isReadOnly()) {
				try {
					results = service.saveOrUpdate(currentDataPage.getPageContentData());
				} catch (IOException e) {
					log.error("Error on Data save: {}", e.getMessage(), e);
					throw e;
				}
			}
			exportData.next();
			Date localFinish = new Date();
			
			long timeForPage = (localFinish.getTime() - localStart.getTime());
			
			String pageResultMessage = getPageResultMessage(exportData, results, timeForPage);
			logger.info(pageResultMessage);
			updateMessage(pageResultMessage);
			//updateMessage(getCurrentExportProgress(exportData.getCurrentDate()) + " of " + maxExportProgress + "\n");
			updateProgress(getCurrentExportProgress(exportData.getCurrentDate()), maxExportProgress);
		}
		Date finish = new Date();
		long totalTime = (finish.getTime() - start.getTime());
		String finishMessage = String.format(FINISH_MESSAGE, DateUtils.getTextTime(totalTime));
		log.info(finishMessage);
		updateMessage(finishMessage);
		
		TaskResult taskResult = new TaskResult(exportData.getPageCount(), totalTime);
		
		return taskResult;
	}
	
	private long getCurrentExportProgress(Date currentDate) {
		if (currentDate == null) {
			return maxExportProgress;
		}
		long currentPosition = DateUtils.daysBetween(filterData.getDateFrom(), currentDate) * HOURS_IN_DAY +
							   DateUtils.getHoursFromDate(currentDate);
		return currentPosition;
	}
	
	private String getPageResultMessage(ExportData exportData, List<ActionResult> results, long localTimeForPage) {
		long totalItems = 0;
		if (results != null && !results.isEmpty()) {
			totalItems = results.size();
		}
		String time = DateUtils.getTextTime(localTimeForPage);
		String resultMessage = String.format(PAGE_READ_RESULT, exportData.getPageCount(),
											 exportData.getCurrentURLData().getCurrentDataURL().getDate(), totalItems,
											 time);
		return resultMessage;
	}
	
	
}
