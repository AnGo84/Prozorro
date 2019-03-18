package ua.prozorro.utils;

import ua.prozorro.ProzorroApp;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by AnGo on 01.03.2017.
 */
public class CommonUtils {
	public static final String PARSING_LOG_FILE = "parsing_error.txt";
	
	public static String getFullParsingLogFilePath() {
		String date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		String fileName = "logs" + File.separator + "parsing_error_" + date + ".txt";
		File logFile = FileUtils.getFileWithName(ProzorroApp.class, fileName);
		String path = logFile.getName();
		return path;
	}
	
	public static void saveParsingErrorLog(String object, String tableName, String id, String fieldName, String value) {
		String templateLogText = "Error in %s with %s table for id= %s in the field %s= ";
		String errorText = (new Date())+" : " + String.format(templateLogText, object, tableName, id, fieldName, value);
		try {
			FileUtils.appendToFile(CommonUtils.getFullParsingLogFilePath(), errorText);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
