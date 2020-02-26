package ua.prozorro.entity.mappers.prozorro;

import org.junit.jupiter.api.Test;
import ua.prozorro.entity.prozorro.TenderJSON;
import ua.prozorro.source.ContentData;
import ua.prozorro.source.DataURL;

import static org.junit.jupiter.api.Assertions.*;

class ContentDataToTenderJSONMapperTest {
	public static final String PLAN_ID = "2b56920fbebf44d9831b43d285e14238";
	public static final String DATE_MODIFIED = "2019-06-19T21:52:14.149462+03:00";
	public static final String JSON_DATA =
			"{\"id\": \"2b56920fbebf44d9831b43d285e14238\", \"dateModified\": \"2019-06-19T21:52:14.149462+03:00\"}";
	private ContentDataToTenderJSONMapper mapper = new ContentDataToTenderJSONMapper();
	
	
	@Test
	public void convertToEntity() {
		assertNull(mapper.convertToObject(null));
		TenderJSON tenderJSON = mapper.convertToEntity(testContentDataForJSONObjects());
		assertNotNull(tenderJSON);
		assertEquals(PLAN_ID, tenderJSON.getId());
		assertEquals(DATE_MODIFIED, tenderJSON.getDateModified());
		assertEquals(JSON_DATA, tenderJSON.getModel());
	}
	
	@Test
	public void convertToObject() {
		assertNull(mapper.convertToObject(null));
		assertNull(mapper.convertToObject(new TenderJSON()));
	}
	
	
	private ContentData testContentDataForJSONObjects() {
		ContentData contentData = new ContentData();
		contentData.setId(PLAN_ID);
		DataURL dataURL = DataURL.builder().url(PLAN_ID).date(DATE_MODIFIED).build();
		contentData.setDataURL(dataURL);
		contentData.setDataJSON(JSON_DATA);
		return contentData;
	}
}