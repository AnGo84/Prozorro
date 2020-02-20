package ua.prozorro.source;

import lombok.Data;

@Data
public class ContentData {
	private String id;
	private DataURL dataURL;
	private String dataJSON;
	private ReadResult readResult;
}
