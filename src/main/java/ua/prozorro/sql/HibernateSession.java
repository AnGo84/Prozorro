package ua.prozorro.sql;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;

import java.io.File;
import java.util.EnumSet;

public class HibernateSession {
	public static final String SCRIPT_FILE = "exportScript.sql";
	public static final String HIBERNATE_DIALECT_STORAGE_ENGINE = "hibernate.dialect.storage_engine";
	public static final String HIBERNATE_DIALECT_STORAGE_ENGINE_NAME = "innodb";
	private static final String DEFAULT_CONFIGURE_FILE_NAME = "hibernate.cfg.xml";
	private static SessionFactory sessionFactory = null;
	
	// Hibernate 5:
	private static SessionFactory buildSessionFactory(String configureFileName) throws ExceptionInInitializerError {
		System.out.println("ConfigFile: " + configureFileName);
		try {
			if (configureFileName == null || configureFileName.equals("")) {
				configureFileName = DEFAULT_CONFIGURE_FILE_NAME;
			}
			
			//Metadata metadata = getMetaData(configureFileName);
			//return metadata.getSessionFactoryBuilder().build();
			
			//new Configuration().configure(new File("/home/visruth/config/hibernate.cfg.xml)) ")
			SessionFactory sessionFactory =
					new Configuration().configure(new File(configureFileName)).buildSessionFactory();
			return sessionFactory;
			
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed. " + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public static Metadata getMetaData(String configFileName) throws ExceptionInInitializerError {
		try {
			// Create the ServiceRegistry from hibernate-xxx.cfg.xml
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()//
																				  .configure(configFileName).build();
			// Create a metadata sources using the specified service registry.
			Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
			return metadata;
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed. " + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public static SchemaExport getSchemaExport(String outputFilePath) {
		
		SchemaExport export = new SchemaExport();
		
		System.out.println("Export file: " + outputFilePath);
		
		export.setDelimiter(";");
		export.setOutputFile(outputFilePath);
		
		// No Stop if Error
		export.setHaltOnError(false);
		//
		return export;
	}
	
	public static void dropDataBase(SchemaExport export, Metadata metadata) {
		// TargetType.DATABASE - Execute on Databse
		// TargetType.SCRIPT - Write Script file.
		// TargetType.STDOUT - Write log to Console.
		EnumSet<TargetType> targetTypes = EnumSet.of(TargetType.DATABASE, TargetType.SCRIPT, TargetType.STDOUT);
		
		export.drop(targetTypes, metadata);
	}
	
	public static void createDataBase(SchemaExport export, Metadata metadata) {
		// TargetType.DATABASE - Execute on Databse
		// TargetType.SCRIPT - Write Script file.
		// TargetType.STDOUT - Write log to Console.
		
		EnumSet<TargetType> targetTypes = EnumSet.of(TargetType.DATABASE, TargetType.SCRIPT, TargetType.STDOUT);
		
		SchemaExport.Action action = SchemaExport.Action.CREATE;
		//
		export.execute(targetTypes, action, metadata);
		
		System.out.println("Export OK");
		
	}
	
	public static SessionFactory instance(String configureFileName) throws ExceptionInInitializerError {
		if (sessionFactory == null || sessionFactory.isClosed()) {
			sessionFactory = buildSessionFactory(configureFileName);
		}
		return sessionFactory;
	}
	
	public static void closeSession() {
		// Close caches and connection pools
		if (sessionFactory.isOpen()) {
			sessionFactory.close();
		}
	}
}
