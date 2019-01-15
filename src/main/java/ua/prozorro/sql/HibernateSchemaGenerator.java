package ua.prozorro.sql;
//https://o7planning.org/en/11223/generate-tables-from-entity-classes-in-hibernate

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;

import java.io.File;
import java.util.EnumSet;

public class HibernateSchemaGenerator {
/*	public static final String SCRIPT_FILE = "exportScript.sql";
	public static final String HIBERNATE_DIALECT_STORAGE_ENGINE = "hibernate.dialect.storage_engine";

	private static SchemaExport getSchemaExport(String outputFilePath) {

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

	}*/

    /*public static Metadata getMetaData(String configFileName) {
        // Create the ServiceRegistry from hibernate-xxx.cfg.xml
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()//
                .configure(configFileName).build();

        // Create a metadata sources using the specified service registry.
        Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
        return metadata;
    }*/


	public static void main(String[] args) {
		// Using Oracle Database.
		String configFileName = "hibernate_mysql.cfg.xml";

		String tempDialectEngine = System.getProperty(HibernateSession.HIBERNATE_DIALECT_STORAGE_ENGINE);
		System.setProperty(HibernateSession.HIBERNATE_DIALECT_STORAGE_ENGINE, "innodb");

		Metadata metadata = HibernateSession.getMetaData(configFileName);

		// Script file.
		File outputFile = new File("MySQL_" + HibernateSession.SCRIPT_FILE);
		String outputFilePath = outputFile.getAbsolutePath();
		SchemaExport export = HibernateSession.getSchemaExport(outputFilePath);

		System.out.println("Drop Database...");
		// Drop Database
		HibernateSession.dropDataBase(export, metadata);

		System.out.println("Create Database...");
		// Create tables
		HibernateSession.createDataBase(export, metadata);

		SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
		//sessionFactory.close();

		//System.setProperty(HibernateSession.HIBERNATE_DIALECT_STORAGE_ENGINE, tempDialectEngine);
		HibernateSession.closeSession();
	}
}
