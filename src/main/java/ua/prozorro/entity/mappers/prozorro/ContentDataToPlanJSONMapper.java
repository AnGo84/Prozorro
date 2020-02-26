package ua.prozorro.entity.mappers.prozorro;

import ua.prozorro.entity.mappers.AbstractMapper;
import ua.prozorro.entity.prozorro.PlanJSON;
import ua.prozorro.source.ContentData;

public class ContentDataToPlanJSONMapper extends AbstractMapper<ContentData, PlanJSON> {
	@Override
	public PlanJSON convertToEntity(ContentData object) {
		if (object == null) {
			return null;
		}
		PlanJSON planJSON = new PlanJSON();
		planJSON.setId(object.getId());
		planJSON.setDateModified(object.getDataURL().getDate());
		planJSON.setModel(object.getDataJSON());
		return planJSON;
	}
	
	@Override
	public ContentData convertToObject(PlanJSON entity) {
		return null;
	}
}
