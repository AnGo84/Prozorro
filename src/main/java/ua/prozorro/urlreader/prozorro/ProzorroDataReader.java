package ua.prozorro.urlreader.prozorro;

import lombok.extern.log4j.Log4j2;
import ua.prozorro.entity.mappers.prozorro.ProzorroPageContentToDataPageMapper;
import ua.prozorro.entity.mappers.prozorro.ProzorroPageToDataURLMapper;
import ua.prozorro.service.ResultType;
import ua.prozorro.source.ContentData;
import ua.prozorro.source.DataPage;
import ua.prozorro.source.ReadResult;
import ua.prozorro.source.SourceLink;
import ua.prozorro.source.prozorro.ProzorroPage;
import ua.prozorro.source.prozorro.ProzorroPageContent;
import ua.prozorro.urlreader.AbstractPageReader;
import ua.prozorro.utils.FileUtils;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class ProzorroDataReader extends AbstractPageReader {
	private ProzorroPageToDataURLMapper prozorroPageToDataURLMapper = new ProzorroPageToDataURLMapper();
	private ProzorroPageContentToDataPageMapper prozorroPageContentToDataPageMapper =
			new ProzorroPageContentToDataPageMapper();
	private ProzorroPageContentReader prozorroPageContentReader;
	
	public ProzorroDataReader(SourceLink sourceLink, DataPage dataPage) {
		super(sourceLink, dataPage);
		prozorroPageContentReader = new ProzorroPageContentReader(ProzorroPageContent.class, sourceLink);
	}
	
	@Override
	public void readDataPage() throws IOException {
		try {
			ProzorroPage currentProzorroPage =
					prozorroPageToDataURLMapper.convertToObject(dataPage.getCurrentDataURL());
			log.info(currentProzorroPage);
			ProzorroPageContent pageContent = prozorroPageContentReader.readPage(currentProzorroPage);
			dataPage = prozorroPageContentToDataPageMapper.convertToEntity(pageContent);
			updateDataPageTypes();
			dataPage.setReadResult(new ReadResult(ResultType.SUCCESS));
			
		} catch (IOException e) {
			dataPage.setReadResult(new ReadResult(ResultType.ERROR, e.getMessage()));
			log.error("ERROR on read DataPage: {}", dataPage, e);
			throw e;
		}
	}
	
	@Override
	public void readPageContent() throws IOException {
		dataPage.getPageContentData().parallelStream().forEach(contentData -> {
			contentData.getDataURL().setUrl(sourceLink.getStartPage() + "/" + contentData.getId());
			String genreJson = null;
			try {
				genreJson = FileUtils.getStringFromURL(contentData.getDataURL().getUrl());
				contentData.setReadResult(new ReadResult(ResultType.SUCCESS));
			}
			catch (IOException e) {
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
