package ua.prozorro.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;
import ua.prozorro.entity.plans.PlanAdditionalClassificationDTO;
import ua.prozorro.entity.plans.PlanDTO;
import ua.prozorro.entity.plans.PlanDocumentDTO;
import ua.prozorro.entity.plans.PlanItemDTO;
import ua.prozorro.entity.tenders.ClassificationDTO;
import ua.prozorro.entity.tenders.DocumentDTO;
import ua.prozorro.entity.tenders.ItemDTO;

public class PlanService {
    private static final Logger logger = LogManager.getRootLogger();

    private Session session;
    public PlanService(Session session) {
        this.session = session;
    }

    public boolean savePlan(PlanDTO plan, Session session) throws Exception {

        if (session == null || !session.isOpen()) {
            throw new Exception("Session did not set");
        }

        session.flush();
        session.clear();
        deleteAllPlanDependentRecords(plan, session);

        session.flush();
        session.clear();
        session.saveOrUpdate(plan);
        session.flush();
        session.clear();

        savePlanDocuments(plan, session);
        savePlanItems(plan, session);
        savePlanAdditionalClassifications(plan, session);

        //logger.info("Save plan id= " + plan.getId());
        return true;
    }
    private void deleteAllPlanDependentRecords(PlanDTO plan, Session session) {
        Query q = session.createQuery("delete from PlanDocumentDTO where plan_id=:planId");
        q.setParameter("planId", plan.getId());
        q.executeUpdate();

        q = session.createQuery("delete from PlanItemDTO where plan_id=:planId");
        q.setParameter("planId", plan.getId());
        q.executeUpdate();

        q = session.createQuery("delete from PlanAdditionalClassificationDTO where plan_id=:planId");
        q.setParameter("planId", plan.getId());
        q.executeUpdate();

        session.flush();
        session.clear();
    }

    public void savePlanDocuments(PlanDTO planDTO, Session session) throws Exception {
        if (session == null) {
            throw new Exception("Session did not set");
        }
        if (planDTO != null && planDTO.getDocuments() != null) {
            for (DocumentDTO documentDTO : planDTO.getDocuments()) {
                session.saveOrUpdate(documentDTO);
                session.flush();
                session.clear();

                session.saveOrUpdate(new PlanDocumentDTO(planDTO, documentDTO));
                session.flush();
                session.clear();
            }
        }
    }
    public void savePlanItems(PlanDTO planDTO, Session session) throws Exception {
        if (session == null) {
            throw new Exception("Session did not set");
        }
        if (planDTO != null && planDTO.getItems() != null) {
            for (ItemDTO itemsDTO : planDTO.getItems()) {
                session.saveOrUpdate(itemsDTO);
                session.flush();
                session.clear();

                session.saveOrUpdate(new PlanItemDTO(planDTO, itemsDTO));
                session.flush();
                session.clear();
            }
        }
    }
    public void savePlanAdditionalClassifications(PlanDTO planDTO, Session session) throws Exception {
        if (session == null) {
            throw new Exception("Session did not set");
        }
        if (planDTO != null && planDTO.getAdditionalClassifications() != null) {
            for (ClassificationDTO classificationDTO : planDTO.getAdditionalClassifications()) {
                session.saveOrUpdate(classificationDTO);
                session.flush();
                session.clear();

                session.saveOrUpdate(new PlanAdditionalClassificationDTO(planDTO, classificationDTO));
                session.flush();
                session.clear();
            }
        }
    }
}
