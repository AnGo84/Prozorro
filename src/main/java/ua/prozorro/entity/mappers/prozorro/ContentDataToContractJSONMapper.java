package ua.prozorro.entity.mappers.prozorro;

import ua.prozorro.entity.mappers.AbstractMapper;
import ua.prozorro.entity.prozorro.ContractJSON;
import ua.prozorro.source.ContentData;

public class ContentDataToContractJSONMapper extends AbstractMapper<ContentData, ContractJSON> {
	@Override
	public ContractJSON convertToEntity(ContentData object) {
		if (object == null) {
			return null;
		}
		ContractJSON contractJSON = new ContractJSON();
		contractJSON.setId(object.getId());
		contractJSON.setDateModified(object.getDataURL().getDate());
		contractJSON.setModel(object.getDataJSON());
		return contractJSON;
	}
	
	@Override
	public ContentData convertToObject(ContractJSON entity) {
		return null;
	}
}
