package ua.prozorro.entity.mappers.prozorro;

import ua.prozorro.entity.mappers.AbstractMapper;
import ua.prozorro.source.ContentData;
import ua.prozorro.source.DataURL;
import ua.prozorro.source.prozorro.ProzorroPageElement;

public class ProzorroPageElementToContentDataMapper extends AbstractMapper<ProzorroPageElement, ContentData> {
	@Override
	public ContentData convertToEntity(ProzorroPageElement object) {
		if (object == null) {
			return null;
		}
		ContentData contentData = new ContentData();
		contentData.setId(object.getId());
		String dateModified = ProzorroDateUtils.fixProzorroDateString(object.getDateModified());
		DataURL dataURL = DataURL.builder().date(dateModified).build();
		contentData.setDataURL(dataURL);
		return contentData;
	}

	
	@Override
	public ProzorroPageElement convertToObject(ContentData entity) {
		return null;
	}
}
