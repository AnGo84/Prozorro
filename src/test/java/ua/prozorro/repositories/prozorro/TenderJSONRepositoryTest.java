package ua.prozorro.repositories.prozorro;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.prozorro.entity.prozorro.TenderJSON;
import ua.prozorro.repositories.TestHibernateUtils;
import ua.prozorro.source.SourceType;
import ua.prozorro.urlreader.ActionResult;

import javax.persistence.PersistenceException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TenderJSONRepositoryTest {
	private static SessionFactory sessionFactory;
	private static Session session = null;
	
	private static TenderJSONRepository tenderJSONRepository;
	
	@BeforeAll
	public static void before() {
		sessionFactory = TestHibernateUtils.getTestSessionFactory();
		session = sessionFactory.openSession();
		tenderJSONRepository = new TenderJSONRepository(sessionFactory);
	}
	
	@AfterAll
	public static void after() {
		session.close();
		sessionFactory.close();
	}
	
	@BeforeEach
	public void beforeEach() {
		session.beginTransaction();
		TestHibernateUtils.deleteAllTenderJSONs(session);
		session.getTransaction().commit();
		
	}
	
	@Test
	public void whenSaveTenderJSON() {
		TenderJSON TenderJSON = getTestTenderJSON();
		try {
			tenderJSONRepository.saveOrUpdate(TenderJSON);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		List<TenderJSON> TenderJSONList = TestHibernateUtils.getAllTenderJSONs(session);
		assertNotNull(TenderJSONList);
		assertEquals(1, TenderJSONList.size());
	}
	
	@Test
	public void whenSave_wrongTenderJSON() {
		// create the objects needed for testing
		final TenderJSON TenderJSON = new TenderJSON();
		
		assertThrows(PersistenceException.class, () -> {
			try {
				tenderJSONRepository.saveOrUpdate(TenderJSON);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}, "attempt to create saveOrUpdate event with null entity");
		
		
		TenderJSON.setId("id");
		assertThrows(PersistenceException.class, () -> {
			try {
				tenderJSONRepository.saveOrUpdate(TenderJSON);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}, "attempt to create saveOrUpdate event with null entity");
		
		TenderJSON.setId(null);
		TenderJSON.setDateModified("data");
		
		assertThrows(PersistenceException.class, () -> {
			try {
				tenderJSONRepository.saveOrUpdate(TenderJSON);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}, "attempt to create saveOrUpdate event with null entity");
		
	}
	
	@Test
	public void whenSave_null_or_empty_object() {
		assertThrows(IllegalArgumentException.class, () -> {
			try {
				tenderJSONRepository.saveOrUpdate(null);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}, "attempt to create saveOrUpdate event with null entity");
		
	}
	
	@Test
	void whenGetAddActionResult() {
		ActionResult actionResult = tenderJSONRepository.getAddActionResult(getTestTenderJSON());
		assertNotNull(actionResult);
		assertNotNull(actionResult.getObject());
		assertTrue(actionResult.getObject() instanceof TenderJSON);
		assertEquals(SourceType.PROZORRO_TENDER, actionResult.getSourceType());
		assertEquals("2019-04-21T00:27:43.001426+03:00", actionResult.getDate());
	}
	
	@Test
	void whenAddActionResult_from_null() {
		ActionResult actionResult = tenderJSONRepository.getAddActionResult(null);
		assertNotNull(actionResult);
		assertNull(actionResult.getObject());
		assertEquals(SourceType.PROZORRO_TENDER, actionResult.getSourceType());
		assertNull(actionResult.getDate());
	}
	
	
	@Test
	public void whenSaveListTenderJSON() {
		List<TenderJSON> testTenderJSONList = getTestTenderJSONList();
		try {
			tenderJSONRepository.saveOrUpdateAll(testTenderJSONList);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		List<TenderJSON> tenderJSONs = TestHibernateUtils.getAllTenderJSONs(session);
		assertNotNull(tenderJSONs);
		assertEquals(2, tenderJSONs.size());
	}
	
	@Test
	public void whenSaveEmptyListTenderJSON() {
		
		try {
			assertNull(tenderJSONRepository.saveOrUpdateAll(null));
			assertTrue(tenderJSONRepository.saveOrUpdateAll(new ArrayList<>()).isEmpty());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	private TenderJSON getTestTenderJSON() {
		TenderJSON TenderJSON = new TenderJSON();
		TenderJSON.setId("79ac30cf8fe349a59160c1bbd82dc1fe");
		TenderJSON.setDateModified("2019-04-21T00:27:43.001426+03:00");
		TenderJSON.setModel(
				"{\"id\": \"79ac30cf8fe349a59160c1bbd82dc1fe\", \"dateModified\": \"2019-04-21T00:27:43.001426+03:00\"}");
		return TenderJSON;
	}
	
	private List<TenderJSON> getTestTenderJSONList() {
		TenderJSON TenderJSON1 = new TenderJSON();
		TenderJSON1.setId("79ac30cf8fe349a59160c1bbd82dc1fe");
		TenderJSON1.setDateModified("2019-04-21T00:27:43.001426+03:00");
		TenderJSON1.setModel(
				"{\"id\": \"79ac30cf8fe349a59160c1bbd82dc1fe\", \"dateModified\": \"2019-04-21T00:27:43.001426+03:00\"}");
		
		TenderJSON tenderJSON2 = new TenderJSON();
		tenderJSON2.setId("9ee46689213c48aaa914c59d12b56086");
		tenderJSON2.setDateModified("2019-06-19T23:12:11.778326+03:00");
		tenderJSON2.setModel(
				"{\"id\": \"9ee46689213c48aaa914c59d12b56086\", \"dateModified\": \"2019-06-19T23:12:11.778326+03:00\"}");
		return Arrays.asList(TenderJSON1, tenderJSON2);
	}
	
}