package ua.prozorro.service.prozorro;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.prozorro.entity.prozorro.PlanJSON;
import ua.prozorro.repositories.TestHibernateUtils;
import ua.prozorro.service.ResultType;
import ua.prozorro.source.ContentData;
import ua.prozorro.source.DataURL;
import ua.prozorro.source.ReadResult;
import ua.prozorro.urlreader.ActionResult;

import javax.persistence.PersistenceException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class PlanServiceTest {
	private static SessionFactory sessionFactory;
	private static Session session = null;
	
	private static PlanService planService;
	
	@BeforeAll
	public static void before() {
		sessionFactory = TestHibernateUtils.getTestSessionFactory();
		session = sessionFactory.openSession();
		planService = new PlanService(sessionFactory);
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
	public void whenSaveListPlan() throws IOException {
		
		planService.saveOrUpdate(getTestContentDataList());
		
		List<PlanJSON> Plans = TestHibernateUtils.getAllPlanJSONs(session);
		assertNotNull(Plans);
		assertEquals(3, Plans.size());
	}
	
	@Test
	public void whenSaveEmptyListPlan() throws IOException {
		List<ActionResult> actionResults = planService.saveOrUpdate(null);
		assertNotNull(actionResults);
		assertFalse(actionResults.isEmpty());
		assertTrue(actionResults.size() == 1);
		assertEquals(ResultType.ERROR, actionResults.get(0).getResultType());
		
		actionResults = planService.saveOrUpdate(new ArrayList<>());
		assertNotNull(actionResults);
		assertFalse(actionResults.isEmpty());
		assertTrue(actionResults.size() == 1);
		assertEquals(ResultType.ERROR, actionResults.get(0).getResultType());
		
	}
	
	@Test
	public void whenSaveListPlanThrowsException() {
		assertThrows(NullPointerException.class, () -> {
			planService.saveOrUpdate(Arrays.asList(new ContentData()));
		});
		
		ContentData contentData = new ContentData();
		contentData.setDataURL(DataURL.builder().build());
		contentData.setReadResult(new ReadResult(ResultType.SUCCESS));
		
		assertThrows(PersistenceException.class, () -> {
			planService.saveOrUpdate(Arrays.asList(contentData));
		});
	}
	
	private List<ContentData> getTestContentDataList() {
		
		ContentData contentData1 = new ContentData();
		contentData1.setId("7138ec048d0a4710a248a686be4a9795");
		contentData1.setDataURL(DataURL.builder().date("2019-04-21T00:53:05.614257+03:00").build());
		contentData1.setDataJSON(
				"\"id\": \"7138ec048d0a4710a248a686be4a9795\", \"dateModified\": \"2019-04-21T00:53:05.614257+03:00\"");
		contentData1.setReadResult(new ReadResult(ResultType.SUCCESS));
		
		ContentData contentData2 = new ContentData();
		contentData2.setId("02bd040cea08414bacd99a185f08c9ee");
		contentData2.setDataURL(DataURL.builder().date("2019-04-21T08:39:42.105502+03:00").build());
		contentData2.setDataJSON(
				"\"id\": \"02bd040cea08414bacd99a185f08c9ee\", \"dateModified\": \"2019-04-21T08:39:42.105502+03:00\"");
		contentData2.setReadResult(new ReadResult(ResultType.SUCCESS));
		
		ContentData contentData3 = new ContentData();
		contentData3.setId("2a3e46f0df3a4fbda50692a0403bbc3f");
		contentData3.setDataURL(DataURL.builder().date("2019-04-21T09:47:53.173708+03:00").build());
		contentData3.setDataJSON(
				"\"id\": \"2a3e46f0df3a4fbda50692a0403bbc3f\", \"dateModified\": \"2019-04-21T09:47:53.173708+03:00\"");
		contentData3.setReadResult(new ReadResult(ResultType.SUCCESS));
		
		return Arrays.asList(contentData1, contentData2, contentData3);
	}
}