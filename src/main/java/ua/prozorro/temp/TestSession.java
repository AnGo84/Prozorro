package ua.prozorro.temp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ua.prozorro.ProzorroApp;
import ua.prozorro.hibernate.HibernateDataBaseType;
import ua.prozorro.hibernate.HibernateFactory;
import ua.prozorro.utils.FileUtils;

import java.net.URL;

public class TestSession {
	
	public static Session getSessionByDBName(String dbName) {
		SessionFactory factory = getSessionFactoryByDBName(dbName);
		return factory.getCurrentSession();
	}
	
	public static SessionFactory getSessionFactoryByDBName(String dbName) {
		System.out.println("DBName = " + dbName);
		if (dbName == null || dbName.equals("")) {
			return null;
		}
		HibernateDataBaseType baseType = HibernateDataBaseType.valueOf(dbName.toUpperCase());
		System.out.println("HibernateDataBaseType = " + baseType);
		URL url = FileUtils.getLocation(ProzorroApp.class);
		SessionFactory factory = HibernateFactory.getHibernateSession(url, baseType);
		return factory;
	}
	
}
