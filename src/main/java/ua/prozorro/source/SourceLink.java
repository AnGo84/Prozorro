package ua.prozorro.source;

import lombok.Builder;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

@Data
@Builder
@Log4j2
public class SourceLink {
	private SourceType type;
	private String startPage;
	private String urlPatternOnDate;
	private String urlDateFormat;
	private String pageDateFormat;
	
	public String getURL(String date) {
		//String url = String.format(urlPatternOnDate, date);
		String url = urlPatternOnDate.replaceFirst("%s", date);
		return url;
	}
	
	public DataURL getDataURL(SourceLink sourceLink, String date) {
		log.info("SourceLink: {}", sourceLink);
		log.info("String date: {}", date);
		return DataURL.builder().url(sourceLink.getURL(date)).date(date).dateFormat(sourceLink.getPageDateFormat())
					  .type(sourceLink.getType()).build();
	}
}
