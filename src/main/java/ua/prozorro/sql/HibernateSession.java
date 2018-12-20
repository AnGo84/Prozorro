package ua.prozorro.sql;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

public class HibernateSession {
	private static final String DEFAULT_CONFIGURE_FILE_NAME = "hibernate.cfg.xml";

	private static SessionFactory sessionFactory = null;

	// Hibernate 5:
	private static SessionFactory buildSessionFactory(String configureFileName) {
		try {
			if (configureFileName == null || configureFileName.equals("")) {
				configureFileName = DEFAULT_CONFIGURE_FILE_NAME;
			}
			// Create the ServiceRegistry from hibernate.cfg.xml
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()//
					                                  .configure(configureFileName).build();

			// Create a metadata sources using the specified service registry.
			Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();

			return metadata.getSessionFactoryBuilder().build();
		} catch (Throwable ex) {

			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory instance(String configureFileName) {
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
