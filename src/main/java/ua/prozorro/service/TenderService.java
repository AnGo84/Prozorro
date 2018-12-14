package ua.prozorro.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import ua.prozorro.entity.tenders.TenderDTO;

public class TenderService {
	private static final Logger logger = LogManager.getRootLogger();

	private Session session;


	public TenderService(Session session) {
		this.session = session;
	}

	public void saveTender(TenderDTO tender) throws Exception {
		if (session == null) {
			throw new Exception("Session did not set");
		}

		TenderDTO oldTenderDTO = session.get(TenderDTO.class, tender.getId());
		if (oldTenderDTO == null) {
			session.save(tender);
			logger.info("Save Tender " + tender + "\n");
		} else if (!oldTenderDTO.equals(tender)) {
			session.delete(oldTenderDTO);
			session.save(tender);
			logger.info("Update Tender " + tender + "\n");
		} else {
			logger.info("Ignore Tender: " + tender + "\n");
		}


	}
	public void saveTender(TenderDTO tender, Session session) throws Exception {
		if (session == null) {
			throw new Exception("Session did not set");
		}

		TenderDTO oldTenderDTO = session.get(TenderDTO.class, tender.getId());
		if (oldTenderDTO == null) {
			session.save(tender);
			logger.info("Save Tender " + tender + "\n");
		} else if (!oldTenderDTO.equals(tender)) {
			session.delete(oldTenderDTO);
			session.save(tender);
			logger.info("Update Tender " + tender + "\n");
		} else {
			logger.info("Ignore Tender: " + tender + "\n");
		}

	}

}
