package ua.prozorro.entity.mappers.prozorro;

import ua.prozorro.entity.mappers.AbstractMapper;

import ua.prozorro.source.DataPage;
import ua.prozorro.source.prozorro.ProzorroPageContent;

public class ProzorroPageContentToDataPageMapper extends AbstractMapper<ProzorroPageContent, DataPage> {
	private  ProzorroPageToDataURLMapper prozorroPageToDataURLMapper = new ProzorroPageToDataURLMapper();
	private ProzorroPageElementListToContentDataListMapper contentDataListMapper =
			new ProzorroPageElementListToContentDataListMapper();
	
	@Override
	public DataPage convertToEntity(ProzorroPageContent object) {
		DataPage dataPage = DataPage.builder()
									.currentDataURL(prozorroPageToDataURLMapper.convertToEntity(object.getCurrentPage()))
									.prevDataURL(prozorroPageToDataURLMapper.convertToEntity(object.getPrevPage()))
									.nextDataURL(prozorroPageToDataURLMapper.convertToEntity(object.getNextPage()))
									.pageContentData(contentDataListMapper.convertToEntity(object.getPageElementList()))
									.build();
		return dataPage;
	}
	
	@Override
	public ProzorroPageContent convertToObject(DataPage entity) {
		return null;
	}
}
