package ua.prozorro.source;

import java.util.ResourceBundle;

public class SourceTypeFactory {
	public static SourceType getSourceType(String url) {
		
		if (url.toUpperCase().contains("NBU")) {
			return SourceType.NBU_RATE;
			
		} else if (url.toLowerCase().contains("offset")) {
			if (url.toLowerCase().contains("tenders")) {
				return SourceType.PROZORRO_TENDER;
			} else if (url.toLowerCase().contains("contracts")) {
				return SourceType.PROZORRO_CONTRACT;
				
			} else if (url.toLowerCase().contains("plans")) {
				return SourceType.PROZORRO_PLAN;
			}
		}
		return null;
	}
	
	public static String getSourceTypeMessage(SourceType sourceType, ResourceBundle resourceBundle) {
		
		switch (sourceType) {
			case NBU_RATE:
				return resourceBundle.getString("source.type.name.nbu_rates");
			
			case PROZORRO_CONTRACT:
				return resourceBundle.getString("source.type.name.prozorro.contract");
			case PROZORRO_PLAN:
				return resourceBundle.getString("source.type.name.prozorro.plan");
			case PROZORRO_TENDER:
				return resourceBundle.getString("source.type.name.prozorro.tender");
			default:
				return null;
		}
	}
	
	
}
