package ua.prozorro.entity.mappers.prozorro;

import ua.prozorro.entity.mappers.AbstractMapper;

import ua.prozorro.source.ContentData;
import ua.prozorro.source.prozorro.ProzorroPageElement;

import java.util.List;
import java.util.stream.Collectors;

public class ProzorroPageElementListToContentDataListMapper
		extends AbstractMapper<List<ProzorroPageElement>, List<ContentData>> {
	private ProzorroPageElementToContentDataMapper mapper = new ProzorroPageElementToContentDataMapper();
	
	@Override
	public List<ContentData> convertToEntity(List<ProzorroPageElement> object) {
		if (object == null || object.isEmpty()) {
			return null;
		}
		List<ContentData> contentDataList =
				object.stream().parallel().map(pageElement -> mapper.convertToEntity(pageElement))
					  .collect(Collectors.toList());
		return contentDataList;
	}
	
	@Override
	public List<ProzorroPageElement> convertToObject(List<ContentData> entity) {
		return null;
	}
}
