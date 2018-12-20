package ua.prozorro.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import ua.prozorro.entity.pages.PageDTO;

import java.util.List;

public class PageService {
	private static final Logger logger = LogManager.getRootLogger();

	private Session session;


	public PageService(Session session) {
		this.session = session;
	}

	public void savePageList(List<PageDTO> pageList) throws Exception {
		if (session == null) {
			throw new Exception("Session did not set");
		}
		for (PageDTO page : pageList) {
			try {
				session.beginTransaction();
				PageDTO oldPage = session.get(PageDTO.class, page.getId());
				if (oldPage == null) {
					session.save(page);
					logger.info("Save page" + page + "\n");
				} else if (!oldPage.equals(page)) {
					session.update(page);
					logger.info("Update page" + page + "\n");
				} else {
					logger.info("Ignore page" + page + "\n");
				}

				session.getTransaction().commit();
			} catch (Exception sqlException) {
				if (null != session.getTransaction()) {

					logger.info("SQL exception: " + sqlException.getMessage() + "\n");
					logger.info(".......Transaction Is Being Rolled Back......." + "\n");
					session.getTransaction().rollback();

				}
			}
		}
	}

	public boolean savePage(PageDTO page) throws Exception {
		session = session.getSessionFactory().getCurrentSession();

		if (session.getSessionFactory() == null || !session.isOpen()) {
			throw new Exception("Session did not set");
		}

		PageDTO oldPage = session.get(PageDTO.class, page.getId());
		if (oldPage == null) {
			session.save(page);

			logger.info("Save page: " + page + "\n");
		} else if (!oldPage.equals(page)) {
			session.update(page);

			logger.info("Update page: " + page + "\n");
		} else {
			logger.info("Ignore page: " + page + "\n");
			return false;
		}
		session.flush();
		return true;
	}

	public boolean savePage(PageDTO page, Session session) throws Exception {

		if (session == null || !session.isOpen()) {
			throw new Exception("Session did not set");
		}

		PageDTO oldPage = session.get(PageDTO.class, page.getId());
		session.flush();
		if (oldPage == null) {
			session.save(page);
			session.flush();

			logger.info("Save page: " + page + "\n");
		} else if (!oldPage.equals(page)) {
			session.update(page);
			session.flush();

			logger.info("Update page: " + page + "\n");
		} else {
			logger.info("Ignore page: " + page + "\n");
			return false;
		}
		return true;
	}

}
