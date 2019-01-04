package ua.prozorro.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import ua.prozorro.entity.pages.TenderPageDTO;

import java.util.List;

public class PageService {
    private static final Logger logger = LogManager.getRootLogger();

    private Session session;


    public PageService(Session session) {
        this.session = session;
    }

    public void savePageList(List<TenderPageDTO> pageList) throws Exception {
        if (session == null) {
            throw new Exception("Session did not set");
        }
        for (TenderPageDTO page : pageList) {
            try {
                session.beginTransaction();
                TenderPageDTO oldPage = session.get(TenderPageDTO.class, page.getId());
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

    public boolean savePage(TenderPageDTO page) throws Exception {
        session = session.getSessionFactory().getCurrentSession();

        if (session.getSessionFactory() == null || !session.isOpen()) {
            throw new Exception("Session did not set");
        }

        TenderPageDTO oldPage = session.get(TenderPageDTO.class, page.getId());
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

    public boolean savePage(TenderPageDTO page, Session session) throws Exception {

        if (session == null || !session.isOpen()) {
            throw new Exception("Session did not set");
        }

        TenderPageDTO oldPage = session.get(TenderPageDTO.class, page.getId());
        session.flush();
        if (oldPage == null) {
            session.save(page);
            session.flush();

            logger.info("Save TenderPage: " + page + "\n");
        } else if (!oldPage.equals(page)) {
            session.update(page);
            session.flush();

            logger.info("Update TenderPage: " + page + "\n");
        } else {
            logger.info("Ignore TenderPage: " + page + "\n");
            return false;
        }
        return true;
    }

}
