package ua.prozorro.entity.mappers.prozorro;

import org.junit.jupiter.api.Test;
import ua.prozorro.source.DataPage;
import ua.prozorro.source.prozorro.ProzorroPageContent;

import static org.junit.jupiter.api.Assertions.*;

class ProzorroPageContentToDataPageMapperTest {
	private ProzorroPageContentToDataPageMapper mapper = new ProzorroPageContentToDataPageMapper();
	
	@Test
	void convertToEntity() {
		ProzorroPageContent prozorroPageContent = TestProzorroData.testTenderProzorroPageContent();
		DataPage dataPage = TestProzorroData.testTenderDataPage();
		
		assertNotNull(mapper.convertToEntity(prozorroPageContent));
		
		assertTrue(TestProzorroDataUtils.isDataURLEqualProzorroPage(dataPage.getCurrentDataURL(),
																	prozorroPageContent.getCurrentPage()));
		assertTrue(TestProzorroDataUtils
						   .isDataURLEqualProzorroPage(dataPage.getNextDataURL(), prozorroPageContent.getNextPage()));
		assertTrue(TestProzorroDataUtils.isDataURLEqualProzorroPage(dataPage.getCurrentDataURL(),
																	prozorroPageContent.getCurrentPage()));
		assertTrue(TestProzorroDataUtils
						   .isDataURLEqualProzorroPage(dataPage.getPrevDataURL(), prozorroPageContent.getPrevPage()));
		assertFalse(dataPage.getPageContentData().isEmpty());
		assertTrue(dataPage.getPageContentData().size() == 1);
		
	}
	
	@Test
	void convertToEntity_with_missing_data() {
		ProzorroPageContent prozorroPageContent = TestProzorroData.testTenderProzorroPageContent();
		prozorroPageContent.setNextPage(null);
		DataPage dataPage = mapper.convertToEntity(prozorroPageContent);
		
		assertNotNull(dataPage);
		assertNull(dataPage.getNextDataURL());
	}
	
	@Test
	void convertToEntity_when_null() {
		assertNull(mapper.convertToEntity(null));
		assertNotNull(mapper.convertToEntity(new ProzorroPageContent()));
	}
	
	@Test
	void convertToObject() {
		assertNull(mapper.convertToObject(null));
		assertNull(mapper.convertToObject(DataPage.builder().build()));
	}
	
	
}