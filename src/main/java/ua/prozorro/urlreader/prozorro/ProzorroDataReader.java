package ua.prozorro.urlreader.prozorro;

import lombok.extern.log4j.Log4j2;
import ua.prozorro.service.ResultType;
import ua.prozorro.source.DataPage;
import ua.prozorro.source.ReadResult;
import ua.prozorro.source.SourceLink;

import java.io.IOException;

@Log4j2
public class ProzorroDataReader extends AbstractProzorroPageReader {

	public ProzorroDataReader(SourceLink sourceLink, DataPage dataPage) {
		super(sourceLink, dataPage);
	}

	@Override
	public void readDataPage() throws IOException {
		try {
			dataPage = readProzorroPage(dataPage.getCurrentDataURL());
			updateDataPageTypes();
			dataPage.setReadResult(new ReadResult(ResultType.SUCCESS));

		} catch (IOException e) {
			dataPage.setReadResult(new ReadResult(ResultType.ERROR, e.getMessage()));
			log.error("ERROR on read DataPage: {}", dataPage, e);
			throw e;
		}
	}

	@Override
	public DataPage nextPage() {
		log.info(dataPage.getNextDataURL().getUrl());
		log.info(dataPage.getCurrentDataURL().getUrl());
		if(dataPage.getNextDataURL().getUrl().equals(dataPage.getCurrentDataURL().getUrl())){
			dataPage.getNextDataURL().setDate("");
		}
		dataPage= DataPage.builder().currentDataURL(dataPage.getNextDataURL()).prevDataURL(dataPage.getCurrentDataURL()).build();
		return dataPage;
	}
}
