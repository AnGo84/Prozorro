package ua.prozorro.entity.mappers.prozorro;

import org.junit.jupiter.api.Test;
import ua.prozorro.entity.prozorro.ContractJSON;
import ua.prozorro.source.ContentData;
import ua.prozorro.source.DataURL;

import static org.junit.jupiter.api.Assertions.*;

class ContentDataToContractJSONMapperTest {
	public static final String CONTRACT_ID = "2d06cc1ce9064bde8188cc71abcb8afe";
	public static final String DATE_MODIFIED = "2016-06-03T20:04:14.611094+03:00";
	public static final String JSON_DATA =
			"{\"id\": \"2d06cc1ce9064bde8188cc71abcb8afe\", \"dateModified\": \"2016-06-03T20:04:14.611094+03:00\"}";
	private ContentDataToContractJSONMapper mapper = new ContentDataToContractJSONMapper();
	
	
	@Test
	public void convertToEntity() {
		assertNull(mapper.convertToObject(null));
		ContractJSON contractJSON = mapper.convertToEntity(testContentDataForJSONObjects());
		assertNotNull(contractJSON);
		assertEquals(CONTRACT_ID, contractJSON.getId());
		assertEquals(DATE_MODIFIED, contractJSON.getDateModified());
		assertEquals(JSON_DATA, contractJSON.getModel());
	}
	
	@Test
	public void convertToObject() {
		assertNull(mapper.convertToObject(null));
		assertNull(mapper.convertToObject(new ContractJSON()));
	}
	
	
	private ContentData testContentDataForJSONObjects() {
		ContentData contentData = new ContentData();
		contentData.setId(CONTRACT_ID);
		DataURL dataURL = DataURL.builder().url(CONTRACT_ID).date(DATE_MODIFIED).build();
		contentData.setDataURL(dataURL);
		contentData.setDataJSON(JSON_DATA);
		return contentData;
	}
}