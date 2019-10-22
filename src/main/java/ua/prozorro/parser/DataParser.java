package ua.prozorro.parser;

import java.util.Date;

public interface DataParser {
	boolean parseAndSave(Date dateFrom, Date dateTill) throws Exception;
}
