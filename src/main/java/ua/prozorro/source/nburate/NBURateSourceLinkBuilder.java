package ua.prozorro.source.nburate;

import ua.prozorro.properties.AppProperty;
import ua.prozorro.properties.PropertiesUtils;
import ua.prozorro.source.AbstractSourceLinkBuilder;
import ua.prozorro.source.SourceLink;
import ua.prozorro.source.SourceType;

import java.util.Properties;

public class NBURateSourceLinkBuilder extends AbstractSourceLinkBuilder {
	public NBURateSourceLinkBuilder(Properties properties) {
		super(properties);
	}
	
	@Override
	public SourceLink getSourceLink() {
		
		String startPage = PropertiesUtils.getPropertyString(properties, AppProperty.NBU_START_PAGE.getPropertyName());
		String pagePrefix =
				PropertiesUtils.getPropertyString(properties, AppProperty.NBU_PAGE_PREFIX.getPropertyName());
		String pageEnd = PropertiesUtils.getPropertyString(properties, AppProperty.NBU_PAGE_END.getPropertyName());
		String dateFormat =
				PropertiesUtils.getPropertyString(properties, AppProperty.NBU_DATE_FORMAT.getPropertyName());
		
		String pageOnDate =
				new StringBuilder(startPage).append("?").append(pagePrefix).append("=%s&").append(pageEnd).toString();
		
		SourceLink sourceLink =
				SourceLink.builder().type(SourceType.NBU_RATE).startPage(startPage).urlPatternOnDate(pageOnDate)
						  .pageDateFormat(dateFormat).urlDateFormat(dateFormat).build();
		return sourceLink;
	}
}
