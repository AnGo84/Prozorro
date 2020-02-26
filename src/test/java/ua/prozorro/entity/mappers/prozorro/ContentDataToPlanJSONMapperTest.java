package ua.prozorro.entity.mappers.prozorro;

import org.junit.jupiter.api.Test;
import ua.prozorro.entity.prozorro.PlanJSON;
import ua.prozorro.source.ContentData;
import ua.prozorro.source.DataURL;

import static org.junit.jupiter.api.Assertions.*;

class ContentDataToPlanJSONMapperTest {
	public static final String PLAN_ID = "9f35a979ae4145e9a45025975c0cd2a6";
	public static final String DATE_MODIFIED = "2019-04-20T16:42:35.707454+03:00";
	public static final String JSON_DATA =
			"{\"id\": \"9f35a979ae4145e9a45025975c0cd2a6\", \"dateModified\": \"2019-04-20T16:42:35.707454+03:00\"}";
	private ContentDataToPlanJSONMapper mapper = new ContentDataToPlanJSONMapper();
	
	
	@Test
	public void convertToEntity() {
		assertNull(mapper.convertToObject(null));
		PlanJSON planJSON = mapper.convertToEntity(testContentDataForJSONObjects());
		assertNotNull(planJSON);
		assertEquals(PLAN_ID, planJSON.getId());
		assertEquals(DATE_MODIFIED, planJSON.getDateModified());
		assertEquals(JSON_DATA, planJSON.getModel());
	}
	
	@Test
	public void convertToObject() {
		assertNull(mapper.convertToObject(null));
		assertNull(mapper.convertToObject(new PlanJSON()));
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