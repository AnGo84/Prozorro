package ua.prozorro.urlreader.prozorro;

import com.google.gson.JsonSyntaxException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.prozorro.entity.mappers.prozorro.ProzorroPageToDataURLMapper;
import ua.prozorro.source.DataPage;
import ua.prozorro.source.SourceLink;
import ua.prozorro.source.SourceLinkFactory;
import ua.prozorro.source.SourceType;
import ua.prozorro.source.prozorro.ProzorroPage;
import ua.prozorro.source.prozorro.ProzorroPageContent;
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

class ProzorroPageContentReaderTest {
    public static final String TESTING_DATE = "20.02.2020";
    public static final String NEXT_DATE = "21.02.2020";
    private static String dateTxt;
    private static SourceLink sourceLink;
    private String fileContent;
    private URLSourceReader mockURLSourceReader;
    private ProzorroContentReader prozorroPageContentReader;
    private ProzorroPageToDataURLMapper prozorroPageToDataURLMapper = new ProzorroPageToDataURLMapper();

    @BeforeAll
    public static void before() throws ParseException {
        sourceLink = SourceLinkFactory.getSourceLink(SourceType.PROZORRO_TENDER,
                TestPropertyFieldsFactory.getStartProperties());

        Date date = DateUtils.parseDateFromString(TESTING_DATE, "dd.MM.yyyy");
        dateTxt = DateUtils.parseDateToString(date, sourceLink.getPageDateFormat());
    }
	
	@BeforeEach
	public void beforeEach() throws IOException {
		File inputFile = new File(this.getClass().getResource("/test_data/Tenders_2020.02.20.txt").getFile());
		fileContent = FileUtils.readFile(inputFile);
		mockURLSourceReader = mock(URLSourceReader.class);
	}
	
	@Test
	public void readPage() throws IOException {
        when(mockURLSourceReader.read(anyString())).thenReturn(fileContent);

        DataPage dataPage = DataPage.builder().currentDataURL(sourceLink.getDataURL(sourceLink, dateTxt)).build();
        ProzorroPage prozorroPage = prozorroPageToDataURLMapper.convertToObject(dataPage.getCurrentDataURL());
        prozorroPageContentReader = new ProzorroContentReader(ProzorroPageContent.class, sourceLink);
        prozorroPageContentReader.setUrlSourceReader(mockURLSourceReader);

        ProzorroPageContent prozorroPageContent = prozorroPageContentReader.readPage(prozorroPage);
        assertNotNull(prozorroPageContent);
        assertFalse(prozorroPageContent.getPageElementList().isEmpty());
        assertEquals(100, prozorroPageContent.getPageElementList().size());
        assertEquals(dataPage.getCurrentDataURL().getUrl(), prozorroPageContent.getCurrentPage().getUri());
    }
	
	@Test
	public void whenReadPage_throw_NPE() throws IOException {
        when(mockURLSourceReader.read(anyString())).thenReturn(null);

        DataPage dataPage = DataPage.builder().currentDataURL(sourceLink.getDataURL(sourceLink, dateTxt)).build();
        ProzorroPage prozorroPage = prozorroPageToDataURLMapper.convertToObject(dataPage.getCurrentDataURL());
        prozorroPageContentReader = new ProzorroContentReader(ProzorroPageContent.class, sourceLink);
        prozorroPageContentReader.setUrlSourceReader(mockURLSourceReader);
        assertThrows(NullPointerException.class, () -> {
            prozorroPageContentReader.readPage(prozorroPage);
        });
    }
	
	@Test
	public void whenReadPage_empty_throw_NPE() throws IOException {
        when(mockURLSourceReader.read(anyString())).thenReturn("");
        DataPage dataPage = DataPage.builder().currentDataURL(sourceLink.getDataURL(sourceLink, dateTxt)).build();
        ProzorroPage prozorroPage = prozorroPageToDataURLMapper.convertToObject(dataPage.getCurrentDataURL());
        prozorroPageContentReader = new ProzorroContentReader(ProzorroPageContent.class, sourceLink);
        prozorroPageContentReader.setUrlSourceReader(mockURLSourceReader);
        assertThrows(NullPointerException.class, () -> {
            prozorroPageContentReader.readPage(prozorroPage);
        });
    }
	
	@Test
	public void whenReadPage_wrong_JSON() throws IOException {
        when(mockURLSourceReader.read(anyString())).thenReturn("Wrong JSON");
        DataPage dataPage = DataPage.builder().currentDataURL(sourceLink.getDataURL(sourceLink, dateTxt)).build();
        ProzorroPage prozorroPage = prozorroPageToDataURLMapper.convertToObject(dataPage.getCurrentDataURL());
        prozorroPageContentReader = new ProzorroContentReader(ProzorroPageContent.class, sourceLink);
        prozorroPageContentReader.setUrlSourceReader(mockURLSourceReader);
        assertThrows(JsonSyntaxException.class, () -> {
            prozorroPageContentReader.readPage(prozorroPage);
        });
    }
}