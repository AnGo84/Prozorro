package ua.prozorro.sql;

import lombok.extern.log4j.Log4j2;
import org.hibernate.SessionFactory;
import ua.prozorro.utils.FileUtils;

import java.io.File;
import java.net.URL;

@Log4j2
public class HibernateFactory {
	
	public static SessionFactory getHibernateSession(URL pathToFiles, HibernateDataBaseType dbType) {
		File propertiesFile = FileUtils.getFileWithName(pathToFiles, dbType.getConfigFileName());
		log.info("Properties file for type {}: {}", dbType.name(), propertiesFile.getAbsolutePath());
		
		if (pathToFiles == null) {
			if (dbType.equals(HibernateDataBaseType.ORACLE)) {
				return HibernateSession.instance(HibernateDataBaseType.ORACLE.getConfigFileName());
			} else if (dbType.equals(HibernateDataBaseType.ORACLE_12)) {
				return HibernateSession.instance(HibernateDataBaseType.ORACLE_12.getConfigFileName());
			} else if (dbType.equals(HibernateDataBaseType.MYSQL)) {
				return HibernateSession.instance(HibernateDataBaseType.MYSQL.getConfigFileName());
			}
			return HibernateSession.instance(HibernateDataBaseType.DEFAULT.getConfigFileName());
		} else {
			return HibernateSession.instance(propertiesFile.getAbsolutePath());
		}
	}
}
