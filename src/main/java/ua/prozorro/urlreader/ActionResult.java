package ua.prozorro.urlreader;

import lombok.Builder;
import lombok.Data;
import ua.prozorro.service.ResultType;
import ua.prozorro.source.DataURL;
import ua.prozorro.source.SourceType;

@Data
@Builder
public class ActionResult {
	private Object object;
	private String url;
	private String date;
	private String content;
	private SourceType sourceType;
	private DataURL dataURL;
	private ResultType resultType;
	private String resultDescription;
}
