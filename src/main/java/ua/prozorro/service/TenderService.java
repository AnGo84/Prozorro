package ua.prozorro.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import ua.prozorro.model.pages.PageElement;
import ua.prozorro.model.tenders.Tender;

import java.util.List;

public class TenderService {
	private static final Logger logger = LogManager.getRootLogger();

	private Session session;


	public TenderService(Session session) {
		this.session = session;
	}

	public void saveTender(Tender tender) throws Exception {
		if (session == null) {
			throw new Exception("Session did not set");
		}
		session.saveOrUpdate(tender);

	}

}
