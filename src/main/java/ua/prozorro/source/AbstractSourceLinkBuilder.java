package ua.prozorro.source;

import lombok.Getter;

import java.util.Properties;

@Getter
public abstract class AbstractSourceLinkBuilder {
	protected Properties properties;
	
	public AbstractSourceLinkBuilder(Properties properties) {
		this.properties = properties;
	}
	
	public abstract SourceLink getSourceLink();
}
