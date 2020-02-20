package ua.prozorro.hibernate;
/**
link https://o7planning.org/en/11223/generate-tables-from-entity-classes-in-hibernate
*/
import lombok.extern.log4j.Log4j2;
import org.hibernate.boot.Metadata;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import java.io.File;

@Log4j2
public class HibernateSchemaGenerator {
	
	public static void main(String[] args) {
		// Using Oracle Database.
		String configFileName = "hibernate_mysql.cfg.xml";
		
		String tempDialectEngine = System.getProperty(HibernateSession.HIBERNATE_DIALECT_STORAGE_ENGINE);
		System.setProperty(HibernateSession.HIBERNATE_DIALECT_STORAGE_ENGINE,
						   HibernateSession.HIBERNATE_DIALECT_STORAGE_ENGINE_NAME);
		
		Metadata metadata = HibernateSession.getMetaData(configFileName);
		
		// Script file.
		File outputFile = new File("MySQL_" + HibernateSession.SCRIPT_FILE);
		String outputFilePath = outputFile.getAbsolutePath();
		SchemaExport export = HibernateSession.getSchemaExport(outputFilePath);
		
		log.info("Drop Database...");
		// Drop Database
		HibernateSession.dropDataBase(export, metadata);
		
		log.info("Create Database...");
		// Create tables
		HibernateSession.createDataBase(export, metadata);
		
		//SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
		//sessionFactory.close();
		
		//System.setProperty(HibernateSession.HIBERNATE_DIALECT_STORAGE_ENGINE, tempDialectEngine);
		//HibernateSession.closeSession();
	}
}
