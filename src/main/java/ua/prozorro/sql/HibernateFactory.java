package ua.prozorro.sql;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import ua.prozorro.utils.FileUtils;

import java.io.File;
import java.net.URL;

public class HibernateFactory {
	private static final Logger logger = LogManager.getRootLogger();
	
	public static SessionFactory getHibernateSession(URL pathToFiles, HibernateDataBaseType dbType) {
		File propertiesFile = FileUtils.getFileWithName(pathToFiles, dbType.getConfigFileName());
		logger.info("Properties file for type " + dbType.name() + ": " + propertiesFile.getAbsolutePath());
		
		if (pathToFiles == null) {
			if (dbType.equals(HibernateDataBaseType.ORACLE)) {
				return HibernateSession.instance(HibernateDataBaseType.ORACLE.getConfigFileName());
			} else if (dbType.equals(HibernateDataBaseType.MYSQL)) {
				return HibernateSession.instance(HibernateDataBaseType.MYSQL.getConfigFileName());
			}
			return HibernateSession.instance(HibernateDataBaseType.DEFAULT.getConfigFileName());
		} else {
			return HibernateSession.instance(propertiesFile.getAbsolutePath());
		}
	}
}
