package ua.prozorro.urlreader.nburate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import ua.prozorro.service.ResultType;
import ua.prozorro.source.*;
import ua.prozorro.temp.TestPropertyFieldsFactory;
import ua.prozorro.urlreader.URLSourceReader;
import ua.prozorro.utils.DateUtils;
import ua.prozorro.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * https://www.journaldev.com/21866/mockito-mock-examples
 */
public class NBURatePageReaderTest {
	public static final String TESTING_DATE = "20.02.2020";
	public static final String NEXT_DATE = "21.02.2020";
	private static String dateTxt;
	private static SourceLink sourceLink;
	private String fileContent;
	private URLSourceReader mockURLSourceReader;
	private NBURatePageReader nbuRatePageReader;
	
	@BeforeAll
	public static void before() throws ParseException {
		sourceLink = SourceLinkFactory.getSourceLink(SourceType.NBU_RATE,
													 TestPropertyFieldsFactory.getStartProperties());
		
		Date date = DateUtils.parseDateFromString(TESTING_DATE, "dd.MM.yyyy");
		dateTxt = DateUtils.parseDateToString(date, sourceLink.getPageDateFormat());
	}
	
	@BeforeEach
	public void beforeEach() throws IOException {
		File inputFile = new File(this.getClass().getResource("/test_data/NBURates_2020.02.20.txt").getFile());
		fileContent = FileUtils.readFile(inputFile);
		mockURLSourceReader = mock(URLSourceReader.class);
	}
	
	@Test
	public void readDataPage() throws IOException {
		
		when(mockURLSourceReader.read(anyString())).thenReturn(fileContent);
		
		DataPage dataPage = DataPage.builder().currentDataURL(sourceLink.getDataURL(sourceLink, dateTxt)).build();
		nbuRatePageReader = new NBURatePageReader(sourceLink, dataPage);
		nbuRatePageReader.setUrlSourceReader(mockURLSourceReader);
		
		nbuRatePageReader.readDataPage();
		assertNotNull(nbuRatePageReader.getDataPage().getPageContentData());
		assertFalse(nbuRatePageReader.getDataPage().getPageContentData().isEmpty());
		assertEquals(ResultType.SUCCESS, nbuRatePageReader.getDataPage().getReadResult().getResultType());
	}
	
	@Test
	public void readPageContent() throws IOException {
		when(mockURLSourceReader.read(anyString())).thenReturn(fileContent);
		
		DataPage dataPage = DataPage.builder().currentDataURL(sourceLink.getDataURL(sourceLink, dateTxt)).build();
		nbuRatePageReader = new NBURatePageReader(sourceLink, dataPage);
		nbuRatePageReader.setUrlSourceReader(mockURLSourceReader);
		nbuRatePageReader.readDataPage();
		nbuRatePageReader.readPageContent();
		assertNotNull(nbuRatePageReader.getDataPage().getPageContentData());
		assertFalse(nbuRatePageReader.getDataPage().getPageContentData().isEmpty());
		
		ContentData contentData = nbuRatePageReader.getDataPage().getPageContentData().get(0);
		
		assertEquals(TESTING_DATE, contentData.getDataURL().getDate());
		assertEquals(fileContent, contentData.getDataJSON());
	}
	
	@Test
	public void nextPage() throws IOException, ParseException {
		when(mockURLSourceReader.read(anyString())).thenReturn(fileContent);
		
		DataPage dataPage = DataPage.builder().currentDataURL(sourceLink.getDataURL(sourceLink, dateTxt)).build();
		nbuRatePageReader = new NBURatePageReader(sourceLink, dataPage);
		nbuRatePageReader.setUrlSourceReader(mockURLSourceReader);
		nbuRatePageReader.readDataPage();
		nbuRatePageReader.readPageContent();
		
		DataPage nextDataPage = nbuRatePageReader.nextPage();
		
		assertNotNull(nextDataPage);
		assertNull(nextDataPage.getNextDataURL());
		assertNull(nextDataPage.getPageContentData());
		assertEquals(SourceType.NBU_RATE, nextDataPage.getCurrentDataURL().getType());
		assertEquals(TESTING_DATE, nextDataPage.getPrevDataURL().getDate());
		assertEquals(NEXT_DATE, nextDataPage.getCurrentDataURL().getDate());
		
	}
	
	
	@Test
	public void whenReadPage_throw_NPE() throws IOException {
		when(mockURLSourceReader.read(anyString())).thenReturn(null);
		
		DataPage dataPage = DataPage.builder().currentDataURL(sourceLink.getDataURL(sourceLink, dateTxt)).build();
		nbuRatePageReader = new NBURatePageReader(sourceLink, dataPage);
		nbuRatePageReader.setUrlSourceReader(mockURLSourceReader);
		
		nbuRatePageReader.readDataPage();
		nbuRatePageReader.readPageContent();
		
		assertNotNull(nbuRatePageReader.getDataPage().getPageContentData());
		assertFalse(nbuRatePageReader.getDataPage().getPageContentData().isEmpty());
		
		ContentData contentData = nbuRatePageReader.getDataPage().getPageContentData().get(0);
		assertNotNull(contentData);
		assertNull(contentData.getDataJSON());
	}
	
	@Test
	public void whenReadPage_empty_throw_NPE() throws IOException {
		when(mockURLSourceReader.read(anyString())).thenReturn("");
		
		DataPage dataPage = DataPage.builder().currentDataURL(sourceLink.getDataURL(sourceLink, dateTxt)).build();
		nbuRatePageReader = new NBURatePageReader(sourceLink, dataPage);
		nbuRatePageReader.setUrlSourceReader(mockURLSourceReader);
		
		nbuRatePageReader.readDataPage();
		nbuRatePageReader.readPageContent();
		
		assertNotNull(nbuRatePageReader.getDataPage().getPageContentData());
		assertFalse(nbuRatePageReader.getDataPage().getPageContentData().isEmpty());
		
		ContentData contentData = nbuRatePageReader.getDataPage().getPageContentData().get(0);
		assertNotNull(contentData);
		assertTrue(StringUtils.isBlank(contentData.getDataJSON()));
	}
	
	@Test
	public void whenReadPage_wrong_JSON() throws IOException {
		String wrongJSON = "Wrong JSON";
		when(mockURLSourceReader.read(anyString())).thenReturn(wrongJSON);
		
		DataPage dataPage = DataPage.builder().currentDataURL(sourceLink.getDataURL(sourceLink, dateTxt)).build();
		nbuRatePageReader = new NBURatePageReader(sourceLink, dataPage);
		nbuRatePageReader.setUrlSourceReader(mockURLSourceReader);
		
		nbuRatePageReader.readDataPage();
		nbuRatePageReader.readPageContent();
		
		assertNotNull(nbuRatePageReader.getDataPage().getPageContentData());
		assertFalse(nbuRatePageReader.getDataPage().getPageContentData().isEmpty());
		
		ContentData contentData = nbuRatePageReader.getDataPage().getPageContentData().get(0);
		assertNotNull(contentData);
		assertEquals(wrongJSON, contentData.getDataJSON());
	}
}