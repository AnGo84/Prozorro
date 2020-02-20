package ua.prozorro.entity.mappers.prozorro;

import ua.prozorro.entity.mappers.AbstractMapper;
import ua.prozorro.source.DataURL;
import ua.prozorro.source.prozorro.ProzorroPage;

public class ProzorroPageToDataURLMapper extends AbstractMapper<ProzorroPage, DataURL> {
	@Override
	public DataURL convertToEntity(ProzorroPage object) {
		String dateOffset = ProzorroDateUtils.fixProzorroDateString(object.getOffset());
		DataURL dataURL = DataURL.builder().url(object.getUri()).date(dateOffset).build();
		return dataURL;
	}
	
	@Override
	public ProzorroPage convertToObject(DataURL entity) {
		ProzorroPage prozorroPage = ProzorroPage.builder().uri(entity.getUrl()).offset(entity.getDate()).build();
		return prozorroPage;
	}
}
