package ua.prozorro.source;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DataURL {
	private String url;
	private String date;
	private String dateFormat;
	private SourceType type;
}
