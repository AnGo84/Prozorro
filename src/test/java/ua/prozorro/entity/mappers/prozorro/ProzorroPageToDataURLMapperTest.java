package ua.prozorro.entity.mappers.prozorro;

import org.junit.jupiter.api.Test;
import ua.prozorro.source.DataURL;
import ua.prozorro.source.prozorro.ProzorroPage;

import static org.junit.jupiter.api.Assertions.*;

class ProzorroPageToDataURLMapperTest {
	private ProzorroPageToDataURLMapper prozorroPageToDataURLMapper = new ProzorroPageToDataURLMapper();
	
	@Test
	public void convertToEntity() {
		assertEquals(TestProzorroData.testDataURL(),
					 prozorroPageToDataURLMapper.convertToEntity(TestProzorroData.testProzorroPage()));
	}
	
	@Test
	public void convertToEntity_when_null() {
		assertNull(prozorroPageToDataURLMapper.convertToEntity(null));
		assertNotNull(prozorroPageToDataURLMapper.convertToEntity(ProzorroPage.builder().build()));
	}
	
	@Test
	public void convertToObject() {
		ProzorroPage prozorroPage = prozorroPageToDataURLMapper.convertToObject(TestProzorroData.testDataURL());
		assertNotNull(prozorroPage);
		assertEquals(TestProzorroData.testProzorroPage().getUri(), prozorroPage.getUri());
		assertEquals(TestProzorroData.testProzorroPage().getOffset(), prozorroPage.getOffset());
	}
	
	@Test
	public void convertToObject_when_null() {
		assertNull(prozorroPageToDataURLMapper.convertToObject(null));
		assertNotNull(prozorroPageToDataURLMapper.convertToObject(DataURL.builder().build()));
	}
}