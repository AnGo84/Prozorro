package ua.prozorro.source;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DataPage {
	private DataURL prevDataURL;
	private DataURL currentDataURL;
	private DataURL nextDataURL;
	private List<ContentData> pageContentData;
	private ReadResult readResult;
	
	public boolean hasPageContentData() {
		return pageContentData != null && !pageContentData.isEmpty();
	}
}
