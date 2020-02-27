package ua.prozorro.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ua.prozorro.entity.nburate.NBURate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HibrnateTest {
	private static SessionFactory sessionFactory;
	private static Session session = null;
	
	@BeforeAll
	public static void before() {
		sessionFactory = TestHibernateUtils.getTestSessionFactory();
		session = sessionFactory.openSession();
	}
	
	@AfterAll
	public static void after() {
		session.close();
		sessionFactory.close();
	}
	
	@Test
	public void whenSaveNBURate() {
		// create the objects needed for testing
		NBURate nbuRate = getTestNbuRate();
		
		// storing the objects for the test in the database
		session.beginTransaction();
		session.saveOrUpdate(nbuRate);
		session.getTransaction().commit();
		
		List<NBURate> nbuRates = TestHibernateUtils.getAllNBURates(session);
		//nbuRates.forEach((x) -> System.out.printf("- %s%n", x));
		assertNotNull(nbuRates);
		assertEquals(1, nbuRates.size());
	}
	
	@Test
	public void whenSave_wrongNBURate() {
		// create the objects needed for testing
		NBURate nbuRate = new NBURate();
		
		assertThrows(IllegalStateException.class, () -> {
			session.beginTransaction();
			session.saveOrUpdate(nbuRate);
			session.getTransaction().commit();
		}, "attempt to create saveOrUpdate event with null entity");
		
		nbuRate.setDate("20.01.2020");
		assertThrows(IllegalStateException.class, () -> {
			session.beginTransaction();
			session.saveOrUpdate(nbuRate);
			session.getTransaction().commit();
		}, "attempt to create saveOrUpdate event with null entity");
		
		nbuRate.setTimeSign("0000");
		assertThrows(IllegalStateException.class, () -> {
			session.beginTransaction();
			session.saveOrUpdate(nbuRate);
			session.getTransaction().commit();
		}, "attempt to create saveOrUpdate event with null entity");
		
		nbuRate.setCurrencyCodeL("USD");
		nbuRate.setUnits(1);
		nbuRate.setAmount(24.2527);
		
		assertThrows(IllegalStateException.class, () -> {
			session.beginTransaction();
			session.saveOrUpdate(nbuRate);
			session.getTransaction().commit();
		}, "attempt to create saveOrUpdate event with null entity");
		
		
	}
	
	@Test
	public void whenSave_null_or_empty_object() {
		assertThrows(IllegalArgumentException.class, () -> {
			session.beginTransaction();
			session.saveOrUpdate(null);
			session.getTransaction().commit();
		}, "attempt to create saveOrUpdate event with null entity");
		assertThrows(IllegalStateException.class, () -> {
			session.beginTransaction();
			session.saveOrUpdate("");
			session.getTransaction().commit();
		}, "attempt to create saveOrUpdate event with null entity");
	}
	
	private NBURate getTestNbuRate() {
		NBURate nbuRate = new NBURate();
		nbuRate.setDate("20.01.2020");
		nbuRate.setTimeSign("0000");
		nbuRate.setCurrencyCode("840");
		nbuRate.setCurrencyCodeL("USD");
		nbuRate.setUnits(1);
		nbuRate.setAmount(24.2527);
		return nbuRate;
	}
}