package ua.prozorro.task;

import javafx.concurrent.Task;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.prozorro.properties.PropertyFields;
import ua.prozorro.prozorro.ParsingResultData;
import ua.prozorro.prozorro.model.pages.ProzorroPageContent;
import ua.prozorro.prozorro.service.PageServiceProzorro;

import java.util.Date;
import java.util.List;


public class TenderParseTimeTask extends Task<ParsingResultData> {
	private static final Logger logger = LogManager.getRootLogger();
	private PropertyFields propertyFields;
	
	public TenderParseTimeTask(PropertyFields propertyFields) {
		this.propertyFields = propertyFields;
	}
	
	@Override
	protected ParsingResultData call() throws Exception {
		updateMessage("Start searching for '" + propertyFields.getSearchDateType().name() + "' \n");
		PageServiceProzorro pageServiceProzorro = new PageServiceProzorro(propertyFields);
		
		Date start = new Date();
		List<ProzorroPageContent> list = pageServiceProzorro
				.getPagesList(propertyFields.getSearchDateType(), propertyFields.getSearchDateFrom(),
							  propertyFields.getSearchDateTill(), false);
		Date finish = new Date();
		
		long timeForPages = finish.getTime() - start.getTime();
		//System.out.println("Time for page without data: " + timeForPages);
		
		start = new Date();
        /*if (list != null && !list.isEmpty()) {
            logger.info("Found pages: " + list.size() + " \n");
            updateMessage("Found pages: " + list.size() + " \n");
            ProzorroPageContent pageContent = pageServiceProzorro
                    .getPageContentFromURL(pageServiceProzorro.getTenderPageURL(propertyFields.getSearchDateFrom()));

            TenderDataServiceProzorro tenderDataServiceProzorro = new TenderDataServiceProzorro(
                    propertyFields.getPropertiesStringValue(AppProperty.TENDER_START_PAGE) + "/");
            logger.info("Start getting Tenders from page \n");
            updateMessage("Start getting Tenders from page \n");
            List<TenderData> tenderDataOnPageList =
                    tenderDataServiceProzorro.getTendersDataFromPageContent(pageContent);

            if (tenderDataOnPageList != null) {
                int i = 0;
                for (TenderData tenderData : tenderDataOnPageList) {
                    i++;

                    *//*logger.info("Get Tender № " + i + "\n");

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException interrupted) {
                        if (isCancelled()) {
                            updateMessage("Cancelled");
                            break;
                        }
                    }
                    updateMessage("Get Tender № " + i + "\n");*//*

                    //TenderDTO tenderDTO = TenderDTOUtils.getTenderDTO(tenderData.getTender());
                }
            }
        }else{
            updateMessage("Tenders not found! \n");
        }*/
		//long timeForPageTenders = (System.nanoTime() - startTime);
		//System.out.println("Time for ProzorroPage Tenders: " + timeForPageTenders/ 1000000000);
		finish = new Date();
		long timeForPageTenders = finish.getTime() - start.getTime();
		
		//100- tenders on page
		//2000- approximately time in milliseconds for saving one tender to database
		//long totalTime = timeForPages + list.size() * (timeForPageTenders + 100 * 2000 * 1000000);
		long totalTime = timeForPages + list.size() * (timeForPageTenders + 100 * 2000);
		logger.info("Finished. Total Time: " + totalTime + "\n");
		updateMessage("Finished Total Time: " + totalTime + "ms \n");
		
		ParsingResultData resultData = new ParsingResultData();
		resultData.setParsingTime(totalTime);
		resultData.setListSize(list.size());
		resultData.setHasData((list != null && !list.isEmpty()));
		return resultData;
	}
	
}
