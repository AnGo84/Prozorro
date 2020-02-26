package ua.prozorro.entity.mappers.prozorro;

import ua.prozorro.entity.mappers.AbstractMapper;
import ua.prozorro.entity.prozorro.TenderJSON;
import ua.prozorro.source.ContentData;

public class ContentDataToTenderJSONMapper extends AbstractMapper<ContentData, TenderJSON> {
	@Override
	public TenderJSON convertToEntity(ContentData object) {
		if (object == null) {
			return null;
		}
		TenderJSON tenderJSON = new TenderJSON();
		tenderJSON.setId(object.getId());
		tenderJSON.setDateModified(object.getDataURL().getDate());
		tenderJSON.setModel(object.getDataJSON());
		return tenderJSON;
	}
	
	@Override
	public ContentData convertToObject(TenderJSON entity) {
		return null;
	}
}
