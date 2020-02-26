package ua.prozorro.entity.mappers.prozorro;

import org.junit.jupiter.api.Test;
import ua.prozorro.source.ContentData;
import ua.prozorro.source.prozorro.ProzorroPageElement;

import static org.junit.jupiter.api.Assertions.*;

class ProzorroPageElementToContentDataMapperTest {
	private ProzorroPageElementToContentDataMapper mapper = new ProzorroPageElementToContentDataMapper();
	
	@Test
	void convertToEntity() {
		ProzorroPageElement prozorroPageElement = TestProzorroData.testTenderProzorroPageElement();
		ContentData contentData = mapper.convertToEntity(prozorroPageElement);
		assertNotNull(contentData);
		
		assertTrue(TestProzorroDataUtils.isContentDataEqualProzorroPageElement(contentData, prozorroPageElement));
		
		/*assertEquals(contentData.getId(), prozorroPageElement.getId());
		assertEquals(contentData.getDataURL().getDate(), prozorroPageElement.getDateModified());*/
		
		ProzorroPageElement prozorroPageElementWithWrongDate =
				TestProzorroData.testTenderProzorroPageElementWithWrongDate();
		ContentData contentDataWithFixedDate = mapper.convertToEntity(prozorroPageElementWithWrongDate);
		assertNotNull(contentDataWithFixedDate);
		assertEquals(contentDataWithFixedDate.getId(), prozorroPageElementWithWrongDate.getId());
		assertEquals(contentDataWithFixedDate.getDataURL().getDate(),
					 ProzorroDateUtils.fixProzorroDateString(prozorroPageElementWithWrongDate.getDateModified()));
	}
	
	@Test
	void convertToObject() {
		assertNull(mapper.convertToObject(null));
		assertNull(mapper.convertToObject(new ContentData()));
	}
}