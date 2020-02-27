package ua.prozorro.repositories.nburate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.prozorro.entity.nburate.NBURate;
import ua.prozorro.repositories.TestHibernateUtils;
import ua.prozorro.source.SourceType;
import ua.prozorro.urlreader.ActionResult;

import javax.persistence.PersistenceException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NBURateRepositoryTest {
	private static SessionFactory sessionFactory;
	private static Session session = null;
	
	private static NBURateRepository nbuRateRepository;
	
	@BeforeAll
	public static void before() {
		sessionFactory = TestHibernateUtils.getTestSessionFactory();
		session = sessionFactory.openSession();
		nbuRateRepository = new NBURateRepository(sessionFactory);
	}
	
	@AfterAll
	public static void after() {
		session.close();
		sessionFactory.close();
	}
	
	@BeforeEach
	public void beforeEach() {
		session.beginTransaction();
		TestHibernateUtils.deleteAllNBURateJSONs(session);
		session.getTransaction().commit();
		
	}
	
	@Test
	public void whenSaveNBURate() {
		NBURate nbuRate = getTestNbuRate();
		try {
			nbuRateRepository.saveOrUpdate(nbuRate);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		List<NBURate> nbuRates = TestHibernateUtils.getAllNBURates(session);
		assertNotNull(nbuRates);
		assertEquals(1, nbuRates.size());
	}
	
	@Test
	public void whenSave_wrongNBURate() {
		// create the objects needed for testing
		NBURate nbuRate = new NBURate();
		
		assertThrows(PersistenceException.class, () -> {
			try {
				nbuRateRepository.saveOrUpdate(nbuRate);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}, "attempt to create saveOrUpdate event with null entity");
		
		nbuRate.setDate("20.01.2020");
		assertThrows(PersistenceException.class, () -> {
			try {
				nbuRateRepository.saveOrUpdate(nbuRate);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}, "attempt to create saveOrUpdate event with null entity");
		
		nbuRate.setTimeSign("0000");
		assertThrows(PersistenceException.class, () -> {
			try {
				nbuRateRepository.saveOrUpdate(nbuRate);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}, "attempt to create saveOrUpdate event with null entity");
		
		nbuRate.setCurrencyCodeL("USD");
		nbuRate.setUnits(1);
		nbuRate.setAmount(24.2527);
		
		assertThrows(PersistenceException.class, () -> {
			try {
				nbuRateRepository.saveOrUpdate(nbuRate);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}, "attempt to create saveOrUpdate event with null entity");
		
		
	}
	
	
	@Test
	public void whenSaveListNBURate() {
		List<NBURate> nbuRateList = getTestNbuRateList();
		try {
			nbuRateRepository.saveOrUpdateAll(nbuRateList);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		List<NBURate> nbuRates = TestHibernateUtils.getAllNBURates(session);
		assertNotNull(nbuRates);
		assertEquals(2, nbuRates.size());
	}
	
	@Test
	public void whenSaveEmptyListNBURate() {
		
		try {
			assertNull(nbuRateRepository.saveOrUpdateAll(null));
			assertTrue(nbuRateRepository.saveOrUpdateAll(new ArrayList<>()).isEmpty());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void whenSave_null_or_empty_object() {
		assertThrows(IllegalArgumentException.class, () -> {
			try {
				nbuRateRepository.saveOrUpdate(null);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}, "attempt to create saveOrUpdate event with null entity");
		
	}
	
	@Test
	void whenGetAddActionResult() {
		ActionResult actionResult = nbuRateRepository.getAddActionResult(getTestNbuRate());
		assertNotNull(actionResult);
		assertNotNull(actionResult.getObject());
		assertTrue(actionResult.getObject() instanceof NBURate);
		assertEquals(SourceType.NBU_RATE, actionResult.getSourceType());
		assertEquals("20.01.2020", actionResult.getDate());
	}
	
	@Test
	void whenAddActionResult_from_null() {
		ActionResult actionResult = nbuRateRepository.getAddActionResult(null);
		assertNotNull(actionResult);
		assertNull(actionResult.getObject());
		assertEquals(SourceType.NBU_RATE, actionResult.getSourceType());
		assertNull(actionResult.getDate());
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
	
	private List<NBURate> getTestNbuRateList() {
		NBURate nbuRate1 = new NBURate();
		nbuRate1.setDate("20.01.2020");
		nbuRate1.setTimeSign("0000");
		nbuRate1.setCurrencyCode("840");
		nbuRate1.setCurrencyCodeL("USD");
		nbuRate1.setUnits(1);
		nbuRate1.setAmount(24.2527);
		
		NBURate nbuRate2 = new NBURate();
		nbuRate2.setDate("20.01.2020");
		nbuRate2.setTimeSign("0000");
		nbuRate2.setCurrencyCode("978");
		nbuRate2.setCurrencyCodeL("EUR");
		nbuRate2.setUnits(1);
		nbuRate2.setAmount(26.9363);
		return Arrays.asList(nbuRate1, nbuRate2);
	}
}