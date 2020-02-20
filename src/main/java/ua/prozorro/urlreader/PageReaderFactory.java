package ua.prozorro.urlreader;

import ua.prozorro.source.DataPage;
import ua.prozorro.source.SourceLink;
import ua.prozorro.source.SourceType;
import ua.prozorro.urlreader.nburate.NBURatePageReader;
import ua.prozorro.urlreader.prozorro.ProzorroDataReader;

public class PageReaderFactory {
	public static AbstractPageReader getPageReader(SourceLink sourceLink, DataPage dataPage) {
		
		switch (sourceLink.getType()) {
			case NBU_RATE:
				return new NBURatePageReader(sourceLink, dataPage);
			
			case PROZORRO_CONTRACT:
			case PROZORRO_PLAN:
			case PROZORRO_TENDER:
				return new ProzorroDataReader(sourceLink, dataPage);
			default:
				return null;
		}
	}
	
	
}
