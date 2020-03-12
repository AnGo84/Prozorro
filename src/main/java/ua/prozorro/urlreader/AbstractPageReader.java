package ua.prozorro.urlreader;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.util.Strings;
import ua.prozorro.service.ResultType;
import ua.prozorro.source.*;
import ua.prozorro.utils.DateUtils;

import java.io.IOException;
import java.text.ParseException;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Getter
public abstract class AbstractPageReader {
	protected DataPage dataPage;
	protected SourceLink sourceLink;
	protected URLSourceReader urlSourceReader;
	
	public AbstractPageReader(SourceLink sourceLink, DataPage dataPage) {
		this.sourceLink = sourceLink;
		this.dataPage = dataPage;
		this.urlSourceReader = new URLSourceReader();
	}
	
	public void setUrlSourceReader(URLSourceReader urlSourceReader) {
		this.urlSourceReader = urlSourceReader;
	}
	
	public abstract void readDataPage() throws IOException;
	
	public abstract DataPage nextPage() throws ParseException;
	
	public void readPageContent(Date dateTill) throws IOException {
		readPageContent();
	}
	
	public Date getDataURLDate(DataURL dataURL) throws ParseException {
		if(Strings.isBlank(dataURL.getDate())){
			return null;
		}
		try {
			return DateUtils.parseDateFromString(dataURL.getDate(), dataURL.getDateFormat());
		} catch (ParseException e) {
			log.error("Can't parse date from '" + dataPage.getCurrentDataURL() + "': ", e.getMessage(), e);
			throw e;
		}
	}

	public ResultType getReadResult() {
		if (dataPage == null || dataPage.getReadResult() == null || dataPage.getReadResult().getResultType() == null) {
			return null;
		}
		return dataPage.getReadResult().getResultType();
	}

	public void readPageContent() throws IOException {
		dataPage.getPageContentData().parallelStream().forEach(contentData -> {
			String genreJson = null;
			try {
				genreJson = urlSourceReader.read(contentData.getDataURL().getUrl());
				contentData.setReadResult(new ReadResult(ResultType.SUCCESS));
			} catch (IOException e) {
				contentData.setReadResult(new ReadResult(ResultType.ERROR, e.getMessage()));
				log.error("Error message: {}", e.getMessage(), e);
				throw new RuntimeException("Error on read content " + e.getMessage(), e);
			}
			contentData.setDataJSON(genreJson);
		});

		checkContentReadErrors();

		List<ContentData> sortedList =
				dataPage.getPageContentData().stream().sorted(Comparator.comparing(p -> p.getDataURL().getDate()))
						.collect(Collectors.toList());
		dataPage.setPageContentData(sortedList);
	}

	public boolean pageHasContent() {
		return dataPage.getPageContentData() != null && !dataPage.getPageContentData().isEmpty();
	}

	public void checkContentReadErrors() throws IOException {
		List<ContentData> errors = dataPage.getPageContentData().stream()
				.filter((data) -> data.getReadResult().getResultType().equals(ResultType.ERROR)).collect(
						Collectors.toList());
		if (errors != null && !errors.isEmpty()) {
			errors.stream().forEach((data) -> log.error("Error Content Data: {}", data));
			throw new IOException("Content Data read error");
		}
	}

}
