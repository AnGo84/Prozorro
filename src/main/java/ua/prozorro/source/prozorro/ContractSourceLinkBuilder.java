package ua.prozorro.source.prozorro;

import ua.prozorro.properties.AppProperty;
import ua.prozorro.properties.PropertiesUtils;
import ua.prozorro.source.AbstractSourceLinkBuilder;
import ua.prozorro.source.SourceLink;
import ua.prozorro.source.SourceType;

import java.util.Properties;

public class ContractSourceLinkBuilder extends AbstractSourceLinkBuilder {
	public ContractSourceLinkBuilder(Properties properties) {
		super(properties);
	}
	
	@Override
	public SourceLink getSourceLink() {
		String startPage =
				PropertiesUtils.getPropertyString(properties, AppProperty.CONTRACT_START_PAGE.getPropertyName());
		
		String pageOffset =
				PropertiesUtils.getPropertyString(properties, AppProperty.CONTRACT_PAGE_OFFSET.getPropertyName());
		String pageEnd = PropertiesUtils.getPropertyString(properties, AppProperty.CONTRACT_PAGE_END.getPropertyName());
		String dateFormat =
				PropertiesUtils.getPropertyString(properties, AppProperty.PROZORRO_SHORT_DATE_FORMAT.getPropertyName());
		String pageDateFormat =
				PropertiesUtils.getPropertyString(properties, AppProperty.PROZORRO_DATE_FORMAT.getPropertyName());
		
		String pageOnDate =
				new StringBuilder(startPage).append("?").append(pageOffset).append("=%s").append(pageEnd).toString();
		
		SourceLink sourceLink =
				SourceLink.builder().type(SourceType.PROZORRO_CONTRACT).startPage(startPage).urlPatternOnDate(pageOnDate)
						  .pageDateFormat(pageDateFormat).urlDateFormat(dateFormat).build();
		return sourceLink;
	}
	
}
