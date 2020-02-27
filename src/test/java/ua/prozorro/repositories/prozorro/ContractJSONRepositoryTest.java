package ua.prozorro.repositories.prozorro;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.prozorro.entity.prozorro.ContractJSON;
import ua.prozorro.repositories.TestHibernateUtils;
import ua.prozorro.source.SourceType;
import ua.prozorro.urlreader.ActionResult;

import javax.persistence.PersistenceException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ContractJSONRepositoryTest {
	private static SessionFactory sessionFactory;
	private static Session session = null;
	
	private static ContractJSONRepository contractJSONRepository;
	
	@BeforeAll
	public static void before() {
		sessionFactory = TestHibernateUtils.getTestSessionFactory();
		session = sessionFactory.openSession();
		contractJSONRepository = new ContractJSONRepository(sessionFactory);
	}
	
	@AfterAll
	public static void after() {
		session.close();
		sessionFactory.close();
	}
	
	@BeforeEach
	public void beforeEach() {
		session.beginTransaction();
		TestHibernateUtils.deleteAllContractJSONs(session);
		session.getTransaction().commit();
		
	}
	
	@Test
	public void whenSaveContractJSON() {
		ContractJSON contractJSON = getTestContractJSON();
		try {
			contractJSONRepository.saveOrUpdate(contractJSON);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		List<ContractJSON> contractJSONList = TestHibernateUtils.getAllContractJSONs(session);
		assertNotNull(contractJSONList);
		assertEquals(1, contractJSONList.size());
	}
	
	@Test
	public void whenSave_wrongContractJSON() {
		// create the objects needed for testing
		final ContractJSON contractJSON = new ContractJSON();
		
		assertThrows(PersistenceException.class, () -> {
			try {
				contractJSONRepository.saveOrUpdate(contractJSON);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}, "attempt to create saveOrUpdate event with null entity");
		
		
		contractJSON.setId("id");
		assertThrows(PersistenceException.class, () -> {
			try {
				contractJSONRepository.saveOrUpdate(contractJSON);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}, "attempt to create saveOrUpdate event with null entity");
		
		contractJSON.setId(null);
		contractJSON.setDateModified("data");
		
		assertThrows(PersistenceException.class, () -> {
			try {
				contractJSONRepository.saveOrUpdate(contractJSON);
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
				contractJSONRepository.saveOrUpdate(null);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}, "attempt to create saveOrUpdate event with null entity");
		
	}
	
	@Test
	void whenGetAddActionResult() {
		ActionResult actionResult = contractJSONRepository.getAddActionResult(getTestContractJSON());
		assertNotNull(actionResult);
		assertNotNull(actionResult.getObject());
		assertTrue(actionResult.getObject() instanceof ContractJSON);
		assertEquals(SourceType.PROZORRO_CONTRACT, actionResult.getSourceType());
		assertEquals("2019-04-21T00:27:43.001426+03:00", actionResult.getDate());
	}
	
	@Test
	void whenAddActionResult_from_null() {
		ActionResult actionResult = contractJSONRepository.getAddActionResult(null);
		assertNotNull(actionResult);
		assertNull(actionResult.getObject());
		assertEquals(SourceType.PROZORRO_CONTRACT, actionResult.getSourceType());
		assertNull(actionResult.getDate());
	}
	
	
	@Test
	public void whenSaveListContractJSON() {
		List<ContractJSON> testContractJSONList = getTestContractJSONList();
		try {
			contractJSONRepository.saveOrUpdateAll(testContractJSONList);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		List<ContractJSON> contractJSONs = TestHibernateUtils.getAllContractJSONs(session);
		assertNotNull(contractJSONs);
		assertEquals(2, contractJSONs.size());
	}
	
	@Test
	public void whenSaveEmptyListContractJSON() {
		
		try {
			assertNull(contractJSONRepository.saveOrUpdateAll(null));
			assertTrue(contractJSONRepository.saveOrUpdateAll(new ArrayList<>()).isEmpty());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	private ContractJSON getTestContractJSON() {
		ContractJSON contractJSON = new ContractJSON();
		contractJSON.setId("79ac30cf8fe349a59160c1bbd82dc1fe");
		contractJSON.setDateModified("2019-04-21T00:27:43.001426+03:00");
		contractJSON.setModel(
				"{\"id\": \"79ac30cf8fe349a59160c1bbd82dc1fe\", \"dateModified\": \"2019-04-21T00:27:43.001426+03:00\"}");
		return contractJSON;
	}
	
	private List<ContractJSON> getTestContractJSONList() {
		ContractJSON contractJSON1 = new ContractJSON();
		contractJSON1.setId("79ac30cf8fe349a59160c1bbd82dc1fe");
		contractJSON1.setDateModified("2019-04-21T00:27:43.001426+03:00");
		contractJSON1.setModel(
				"{\"id\": \"79ac30cf8fe349a59160c1bbd82dc1fe\", \"dateModified\": \"2019-04-21T00:27:43.001426+03:00\"}");
		
		ContractJSON contractJSON2 = new ContractJSON();
		contractJSON2.setId("7138ec048d0a4710a248a686be4a9795");
		contractJSON2.setDateModified("2019-04-21T00:53:05.614257+03:00");
		contractJSON2.setModel(
				"{\"id\": \"7138ec048d0a4710a248a686be4a9795\", \"dateModified\": \"2019-04-21T00:53:05.614257+03:00\"}");
		return Arrays.asList(contractJSON1, contractJSON2);
	}
}