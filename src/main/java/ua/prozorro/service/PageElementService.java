package ua.prozorro.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import ua.prozorro.model.pages.PageElement;

import java.util.List;

public class PageElementService {
	private static final Logger logger = LogManager.getRootLogger();

	private Session session = null;


	public PageElementService(Session session) {
		this.session = session;
	}

	public void savePageElementList(List<PageElement> pageElementList) throws Exception {
		if (session==null){
			throw  new Exception("Session не установлена");
		}
		for (PageElement pageElement : pageElementList) {
			try {
				session.beginTransaction();
				PageElement oldPage = (PageElement) session.get(PageElement.class, pageElement.getId());
				if (oldPage==null){
					session.save(pageElement);
					logger.info("Save pageElement" + pageElement + "\n");
				}else if (!oldPage.equals(pageElement)) {
					session.update(pageElement);
					logger.info("Update pageElement" + pageElement + "\n");
				}else {
					logger.info("Ignore pageElement" + pageElement + "\n");
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
}
