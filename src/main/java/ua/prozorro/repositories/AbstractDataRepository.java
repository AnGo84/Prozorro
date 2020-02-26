package ua.prozorro.repositories;

import lombok.extern.log4j.Log4j2;
import org.hibernate.*;
import ua.prozorro.urlreader.ActionResult;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public abstract class AbstractDataRepository<T> implements DataRepository<T> {
	protected final SessionFactory sessionFactory;
	
	public AbstractDataRepository(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	protected ActionResult saveAndLog(Session session, T data) throws HibernateException {
		try {
			session.saveOrUpdate(data);
			log.info("Save '{}' object: {}", data.getClass().getName(), data);
			return getAddActionResult(data);
		} catch (HibernateException e) {
			log.error("Error on saving '{}' object: {}", data.getClass().getName(), data);
			log.error("Error message", e.getMessage());
			throw e;
		}
	}
	
	public ActionResult saveOrUpdate(T data) throws IOException {
		if (sessionFactory == null) {
			throw new SessionException("SessionFactory did not initiated");
		}
		ActionResult actionResult;
		Transaction transaction = null;
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();
			actionResult = saveAndLog(session, data);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			throw new IOException("Error on saving object: " + data, e);
		}
		finally {
			session.close();
		}
		return actionResult;
	}
	
	public List<ActionResult> saveOrUpdateAll(List<T> listOfData) throws IOException {
		if (sessionFactory == null) {
			throw new SessionException("SessionFactory did not initiated");
		}
		if (listOfData == null) {
			return null;
		}
		List<ActionResult> actionResults;
		Transaction transaction = null;
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();
			actionResults = listOfData.stream().map(data -> saveAndLog(session, data)).collect(Collectors.toList());
			transaction.commit();
		}
		catch (HibernateException e) {
			transaction.rollback();
			throw new IOException("Error on saving list: " + e.getMessage(), e);
		}
		finally {
			session.close();
		}
		return actionResults;
	}
}
