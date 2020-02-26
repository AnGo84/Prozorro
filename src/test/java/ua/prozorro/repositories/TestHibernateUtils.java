package ua.prozorro.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.prozorro.entity.nburate.NBURate;
import ua.prozorro.entity.prozorro.ContractJSON;
import ua.prozorro.entity.prozorro.PlanJSON;
import ua.prozorro.entity.prozorro.TenderJSON;

import java.util.List;

public class TestHibernateUtils {
	public static SessionFactory getTestSessionFactory() {
		return new Configuration().configure("hibernate_h2.cfg.xml").buildSessionFactory();
	}
	
	public static List<NBURate> getAllNBURates(Session session) {
		return session.createQuery("FROM NBURate").list();
	}
	
	public static int deleteAllNBURateJSONs(Session session) {
		return session.createQuery("DELETE FROM NBURate").executeUpdate();
	}
	
	public static List<TenderJSON> getAllTenderJSONs(Session session) {
		return session.createQuery("FROM TenderJSON").list();
	}
	
	public static int deleteAllTenderJSONs(Session session) {
		return session.createQuery("DELETE FROM TenderJSON").executeUpdate();
	}
	
	public static List<PlanJSON> getAllPlanJSONs(Session session) {
		return session.createQuery("FROM PlanJSON").list();
	}
	
	public static int deleteAllPlanJSONs(Session session) {
		return session.createQuery("DELETE FROM PlanJSON").executeUpdate();
	}
	
	public static List<ContractJSON> getAllContractJSONs(Session session) {
		return session.createQuery("FROM ContractJSON").list();
	}
	
	public static int deleteAllContractJSONs(Session session) {
		return session.createQuery("DELETE FROM ContractJSON").executeUpdate();
	}
}
