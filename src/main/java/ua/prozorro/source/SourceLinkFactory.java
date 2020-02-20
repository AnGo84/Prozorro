package ua.prozorro.source;

import ua.prozorro.source.nburate.NBURateSourceLinkBuilder;
import ua.prozorro.source.prozorro.ContractSourceLinkBuilder;
import ua.prozorro.source.prozorro.PlanSourceLinkBuilder;
import ua.prozorro.source.prozorro.TenderSourceLinkBuilder;

import java.util.Properties;

public class SourceLinkFactory {
	public static SourceLink getSourceLink(SourceType sourceType, Properties properties) {
		AbstractSourceLinkBuilder abstractSourceLinkBuilder = getSourceLinkBuilder(sourceType, properties);
		if (abstractSourceLinkBuilder != null) {
			return abstractSourceLinkBuilder.getSourceLink();
		}
		return null;
	}
	
	private static AbstractSourceLinkBuilder getSourceLinkBuilder(SourceType sourceType, Properties properties) {
		
		switch (sourceType) {
			case NBU_RATE:
				return new NBURateSourceLinkBuilder(properties);
				
			case PROZORRO_CONTRACT:
				return new ContractSourceLinkBuilder(properties);
			case PROZORRO_PLAN:
				return new PlanSourceLinkBuilder(properties);
			case PROZORRO_TENDER:
				return new TenderSourceLinkBuilder(properties);
			default:
				return null;
		}
	}
	
	
}
