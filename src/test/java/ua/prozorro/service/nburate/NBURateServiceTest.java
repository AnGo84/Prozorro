package ua.prozorro.service.nburate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.prozorro.entity.nburate.NBURate;
import ua.prozorro.repositories.TestHibernateUtils;
import ua.prozorro.service.ResultType;
import ua.prozorro.source.ContentData;
import ua.prozorro.source.ReadResult;
import ua.prozorro.urlreader.ActionResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NBURateServiceTest {
	private static SessionFactory sessionFactory;
	private static Session session = null;
	
	private static NBURateService nbuRateService;
	
	@BeforeAll
	public static void before() {
		sessionFactory = TestHibernateUtils.getTestSessionFactory();
		session = sessionFactory.openSession();
		nbuRateService = new NBURateService(sessionFactory);
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
	public void whenSaveListNBURate() {
		try {
			nbuRateService.saveOrUpdate(getTestContentDataList());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		List<NBURate> nbuRates = TestHibernateUtils.getAllNBURates(session);
		assertNotNull(nbuRates);
		assertEquals(3, nbuRates.size());
	}
	
	@Test
	public void whenSaveEmptyListNBURate() throws IOException {
		List<ActionResult> actionResults = nbuRateService.saveOrUpdate(null);
		assertNotNull(actionResults);
		assertFalse(actionResults.isEmpty());
		assertTrue(actionResults.size() == 1);
		assertEquals(ResultType.ERROR, actionResults.get(0).getResultType());
		
		actionResults = nbuRateService.saveOrUpdate(new ArrayList<>());
		assertNotNull(actionResults);
		assertFalse(actionResults.isEmpty());
		assertTrue(actionResults.size() == 1);
		assertEquals(ResultType.ERROR, actionResults.get(0).getResultType());
		
		actionResults = nbuRateService.saveOrUpdate(Arrays.asList(new ContentData()));
		assertNotNull(actionResults);
		assertFalse(actionResults.isEmpty());
		assertTrue(actionResults.size() == 1);
		assertEquals(ResultType.ERROR, actionResults.get(0).getResultType());
		
		ContentData contentData = new ContentData();
		contentData.setReadResult(new ReadResult(ResultType.SUCCESS));
		actionResults = nbuRateService.saveOrUpdate(Arrays.asList(contentData));
		assertNotNull(actionResults);
		assertFalse(actionResults.isEmpty());
		assertTrue(actionResults.size() == 1);
		assertEquals(ResultType.ERROR, actionResults.get(0).getResultType());
	}
	
	private List<ContentData> getTestContentDataList() {
		
		ContentData contentData = new ContentData();
		contentData.setDataJSON("[\n" + "{ \n" +
								"\"StartDate\":\"20.01.2020\",\"TimeSign\":\"0000\",\"CurrencyCode\":\"036\",\"CurrencyCodeL\":\"AUD\",\"Units\":1,\"Amount\":16.7077\n" +
								" }\n" + ",{ \n" +
								"\"StartDate\":\"20.01.2020\",\"TimeSign\":\"0000\",\"CurrencyCode\":\"944\",\"CurrencyCodeL\":\"AZN\",\"Units\":1,\"Amount\":14.2831\n" +
								" }\n" + ",{ \n" +
								"\"StartDate\":\"20.01.2020\",\"TimeSign\":\"0000\",\"CurrencyCode\":\"933\",\"CurrencyCodeL\":\"BYN\",\"Units\":1,\"Amount\":11.4367\n" +
								" } \n]");
		contentData.setReadResult(new ReadResult(ResultType.SUCCESS));
		return Arrays.asList(contentData);
	}
}