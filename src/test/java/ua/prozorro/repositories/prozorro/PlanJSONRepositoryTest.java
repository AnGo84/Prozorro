package ua.prozorro.repositories.prozorro;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.prozorro.entity.prozorro.PlanJSON;
import ua.prozorro.repositories.TestHibernateUtils;
import ua.prozorro.source.SourceType;
import ua.prozorro.urlreader.ActionResult;

import javax.persistence.PersistenceException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class PlanJSONRepositoryTest {
	private static SessionFactory sessionFactory;
	private static Session session = null;
	
	private static PlanJSONRepository planJSONRepository;
	
	@BeforeAll
	public static void before() {
		sessionFactory = TestHibernateUtils.getTestSessionFactory();
		session = sessionFactory.openSession();
		planJSONRepository = new PlanJSONRepository(sessionFactory);
	}
	
	@AfterAll
	public static void after() {
		session.close();
		sessionFactory.close();
	}
	
	@BeforeEach
	public void beforeEach() {
		session.beginTransaction();
		TestHibernateUtils.deleteAllPlanJSONs(session);
		session.getTransaction().commit();
		
	}
	
	@Test
	public void whenSavePlanJSON() {
		PlanJSON PlanJSON = getTestPlanJSON();
		try {
			planJSONRepository.saveOrUpdate(PlanJSON);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		List<PlanJSON> PlanJSONList = TestHibernateUtils.getAllPlanJSONs(session);
		assertNotNull(PlanJSONList);
		assertEquals(1, PlanJSONList.size());
	}
	
	@Test
	public void whenSave_wrongPlanJSON() {
		// create the objects needed for testing
		final PlanJSON PlanJSON = new PlanJSON();
		
		assertThrows(PersistenceException.class, () -> {
			try {
				planJSONRepository.saveOrUpdate(PlanJSON);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}, "attempt to create saveOrUpdate event with null entity");
		
		
		PlanJSON.setId("id");
		assertThrows(PersistenceException.class, () -> {
			try {
				planJSONRepository.saveOrUpdate(PlanJSON);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}, "attempt to create saveOrUpdate event with null entity");
		
		PlanJSON.setId(null);
		PlanJSON.setDateModified("data");
		
		assertThrows(PersistenceException.class, () -> {
			try {
				planJSONRepository.saveOrUpdate(PlanJSON);
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
				planJSONRepository.saveOrUpdate(null);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}, "attempt to create saveOrUpdate event with null entity");
		
	}
	
	@Test
	void whenGetAddActionResult() {
		ActionResult actionResult = planJSONRepository.getAddActionResult(getTestPlanJSON());
		assertNotNull(actionResult);
		assertNotNull(actionResult.getObject());
		assertTrue(actionResult.getObject() instanceof PlanJSON);
		assertEquals(SourceType.PROZORRO_PLAN, actionResult.getSourceType());
		assertEquals("2019-04-21T00:27:43.001426+03:00", actionResult.getDate());
	}
	
	@Test
	void whenAddActionResult_from_null() {
		ActionResult actionResult = planJSONRepository.getAddActionResult(null);
		assertNotNull(actionResult);
		assertNull(actionResult.getObject());
		assertEquals(SourceType.PROZORRO_PLAN, actionResult.getSourceType());
		assertNull(actionResult.getDate());
	}
	
	@Test
	public void whenSaveListPlanJSON() {
		List<PlanJSON> testPlanJSONList = getTestPlanJSONList();
		try {
			planJSONRepository.saveOrUpdateAll(testPlanJSONList);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		List<PlanJSON> planJSONs = TestHibernateUtils.getAllPlanJSONs(session);
		assertNotNull(planJSONs);
		assertEquals(2, planJSONs.size());
	}
	
	@Test
	public void whenSaveEmptyListPlanJSON() {
		
		try {
			assertNull(planJSONRepository.saveOrUpdateAll(null));
			assertTrue(planJSONRepository.saveOrUpdateAll(new ArrayList<>()).isEmpty());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	private PlanJSON getTestPlanJSON() {
		PlanJSON PlanJSON = new PlanJSON();
		PlanJSON.setId("79ac30cf8fe349a59160c1bbd82dc1fe");
		PlanJSON.setDateModified("2019-04-21T00:27:43.001426+03:00");
		PlanJSON.setModel(
				"{\"id\": \"79ac30cf8fe349a59160c1bbd82dc1fe\", \"dateModified\": \"2019-04-21T00:27:43.001426+03:00\"}");
		return PlanJSON;
	}
	
	private List<PlanJSON> getTestPlanJSONList() {
		PlanJSON planJSON1 = new PlanJSON();
		planJSON1.setId("79ac30cf8fe349a59160c1bbd82dc1fe");
		planJSON1.setDateModified("2019-04-21T00:27:43.001426+03:00");
		planJSON1.setModel(
				"{\"id\": \"79ac30cf8fe349a59160c1bbd82dc1fe\", \"dateModified\": \"2019-04-21T00:27:43.001426+03:00\"}");
		
		PlanJSON planJSON2 = new PlanJSON();
		planJSON2.setId("083e1c2c8103422ab29c125be4daa60f");
		planJSON2.setDateModified("2019-04-20T21:35:35.633006+03:00");
		planJSON2.setModel(
				"{\"id\": \"083e1c2c8103422ab29c125be4daa60f\", \"dateModified\": \"2019-04-20T21:35:35.633006+03:00\"}");
		return Arrays.asList(planJSON1, planJSON2);
	}
	
}