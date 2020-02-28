package ua.prozorro.urlreader.nburate;

import lombok.extern.log4j.Log4j2;
import ua.prozorro.service.ResultType;
import ua.prozorro.source.*;
import ua.prozorro.urlreader.AbstractPageReader;
import ua.prozorro.utils.DateUtils;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;

@Log4j2
public class NBURatePageReader extends AbstractPageReader {
	
	public NBURatePageReader(SourceLink sourceLink, DataPage dataPage) {
		super(sourceLink, dataPage);
	}
	
	@Override
	public void readDataPage() {
		ContentData contentData = new ContentData();
		contentData.setDataURL(dataPage.getCurrentDataURL());
		dataPage.setPageContentData(Arrays.asList(contentData));
		dataPage.setReadResult(new ReadResult(ResultType.SUCCESS));
	}

    @Override
	public DataPage nextPage() throws ParseException {
		DataURL prevDataURL = dataPage.getCurrentDataURL();
		Date nextDate = null;
		try {
			nextDate = DateUtils.addDaysToDate(DateUtils.parseDateFromString(dataPage.getCurrentDataURL().getDate(),
																			 sourceLink.getPageDateFormat()), 1);
		} catch (ParseException e) {
			log.error("Can't get next page: ", e.getMessage());
			throw e;
		}
		String date = DateUtils.parseDateToString(nextDate, sourceLink.getPageDateFormat());
		
		DataURL dataURL =
				DataURL.builder().date(date).url(sourceLink.getURL(date)).dateFormat(sourceLink.getPageDateFormat())
					   .type(sourceLink.getType()).build();
		
		dataPage= DataPage.builder().currentDataURL(dataURL).prevDataURL(prevDataURL).build();
		
		return dataPage;
	}
}
