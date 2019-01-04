package ua.prozorro.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;
import ua.prozorro.entity.tenders.*;

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

        session.flush();

    }

    public void saveTender(TenderDTO tender, Session session) throws Exception {
        if (session == null) {
            throw new Exception("Session did not set");
        }
/*
		TenderDTO oldTenderDTO = session.get(TenderDTO.class, tender.getId());
		session.flush();
		if (oldTenderDTO == null) {
			session.save(tender);
			logger.info("Save Tender " + tender + "\n");
		} else if (!oldTenderDTO.equals(tender)) {
			session.update(tender);
			logger.info("Update Tender " + tender + "\n");
		} else {
			logger.info("Ignore Tender: " + tender + "\n");
		}*/


        session.flush();
        session.clear();
        deleteAllTenderDependentRecords(tender, session);

        session.saveOrUpdate(tender);
        session.flush();
        session.clear();

        saveTenderFunders(tender, session);
        saveTenderFeatures(tender, session);
        saveTenderDocuments(tender, session);
        saveTenderContracts(tender, session);
        saveTenderAwards(tender, session);
        saveTenderItems(tender, session);
        saveTenderBids(tender, session);
        saveTenderQuestions(tender, session);
        saveTenderComplaints(tender, session);

    }

    public void saveTenderFunders(TenderDTO tender, Session session) throws Exception {
        if (session == null) {
            throw new Exception("Session did not set");
        }

        if (tender != null && tender.getFunders() != null) {
            for (OrganizationDTO organizationDTO : tender.getFunders()) {
                session.saveOrUpdate(organizationDTO);
                session.saveOrUpdate(new TenderFunderDTO(tender, organizationDTO));
                session.flush();
                session.clear();

				/*Query query=session.createQuery("insert into TenderContractDTO value( :tenderId, :organizationId)");
				query.setParameter("tenderId", tender.getId());
				query.setParameter("organizationId", organizationDTO.getId());
				query.executeUpdate();*/

            }
        }

    }

    public void saveTenderFeatures(TenderDTO tender, Session session) throws Exception {
        if (session == null) {
            throw new Exception("Session did not set");
        }

        if (tender != null && tender.getFeatures() != null) {
            for (FeatureDTO featureDTO : tender.getFeatures()) {
                session.saveOrUpdate(featureDTO);
                session.saveOrUpdate(new TenderFeatureDTO(tender, featureDTO));
                session.flush();
                session.clear();

				/*Query query=session.createQuery("insert into TenderContractDTO value( :tenderId, :organizationId)");
				query.setParameter("tenderId", tender.getId());
				query.setParameter("organizationId", organizationDTO.getId());
				query.executeUpdate();*/

            }
        }

    }

    private void deleteAllTenderDependentRecords(TenderDTO tender, Session session) {
        Query q = session.createQuery("delete from TenderFunderDTO where tender_id=:tenderId");
        q.setParameter("tenderId", tender.getId());
        q.executeUpdate();

        q = session.createQuery("delete from TenderFeatureDTO where tender_id=:tenderId");
        q.setParameter("tenderId", tender.getId());
        q.executeUpdate();

        q = session.createQuery("delete from TenderDocumentDTO where tender_id=:tenderId");
        q.setParameter("tenderId", tender.getId());
        q.executeUpdate();

        q = session.createQuery("delete from TenderContractDTO where tender_id=:tenderId");
        q.setParameter("tenderId", tender.getId());
        q.executeUpdate();

        q = session.createQuery("delete from TenderAwardDTO where tender_id=:tenderId");
        q.setParameter("tenderId", tender.getId());
        q.executeUpdate();

        q = session.createQuery("delete from TenderItemDTO where tender_id=:tenderId");
        q.setParameter("tenderId", tender.getId());
        q.executeUpdate();

        q = session.createQuery("delete from TenderBidDTO where tender_id=:tenderId");
        q.setParameter("tenderId", tender.getId());
        q.executeUpdate();

        q = session.createQuery("delete from TenderQuestionDTO where tender_id=:tenderId");
        q.setParameter("tenderId", tender.getId());
        q.executeUpdate();

        q = session.createQuery("delete from TenderComplaintDTO where tender_id=:tenderId");
        q.setParameter("tenderId", tender.getId());
        q.executeUpdate();

        session.flush();
        session.clear();
    }

    private void deleteAllBidDependentRecords(BidDTO bid, Session session) {
        Query q = session.createQuery("delete from BidTendererDTO where bid_id=:bidId");
        q.setParameter("bidId", bid.getId());
        q.executeUpdate();

        q = session.createQuery("delete from BidDocumentDTO where bid_id=:bidId");
        q.setParameter("bidId", bid.getId());
        q.executeUpdate();

        session.flush();
        session.clear();
    }

    private void deleteAllAwardDependentRecords(AwardDTO awardDTO, Session session) {
        Query q = session.createQuery("delete from AwardSupplierDTO where award_id=:awardId");
        q.setParameter("awardId", awardDTO.getId());
        q.executeUpdate();

        q = session.createQuery("delete from AwardDocumentDTO where award_id=:awardId");
        q.setParameter("awardId", awardDTO.getId());
        q.executeUpdate();

        session.flush();
        session.clear();
    }

    private void deleteAllContractDependentRecords(ContractDTO contractDTO, Session session) {
        Query q = session.createQuery("delete from ContractSupplierDTO where contract_id=:contractId");
        q.setParameter("contractId", contractDTO.getId());
        q.executeUpdate();

        q = session.createQuery("delete from ContractItemDTO where contract_id=:contractId");
        q.setParameter("contractId", contractDTO.getId());
        q.executeUpdate();

        session.flush();
        session.clear();
    }

    public void saveTenderDocuments(TenderDTO tender, Session session) throws Exception {
        if (session == null) {
            throw new Exception("Session did not set");
        }
        if (tender != null && tender.getDocuments() != null) {
            for (DocumentDTO documentDTO : tender.getDocuments()) {
                session.saveOrUpdate(documentDTO);
                session.flush();
                session.clear();

                session.saveOrUpdate(new TenderDocumentDTO(tender, documentDTO));
                session.flush();
                session.clear();
            }
        }
    }

    public void saveBidDocuments(BidDTO bidDTO, Session session) throws Exception {
        if (session == null) {
            throw new Exception("Session did not set");
        }
        if (bidDTO != null && bidDTO.getDocuments() != null) {
            for (DocumentDTO documentDTO : bidDTO.getDocuments()) {
                session.saveOrUpdate(documentDTO);
                session.flush();
                session.clear();

                session.saveOrUpdate(new BidDocumentDTO(bidDTO, documentDTO));
                session.flush();
                session.clear();
            }
        }
    }

    public void saveBidTenderers(BidDTO bidDTO, Session session) throws Exception {
        if (session == null) {
            throw new Exception("Session did not set");
        }
        if (bidDTO != null && bidDTO.getDocuments() != null) {
            for (OrganizationDTO organizationDTO : bidDTO.getTenderers()) {
                session.saveOrUpdate(organizationDTO);
                session.flush();
                session.clear();

                session.saveOrUpdate(new BidTendererDTO(bidDTO, organizationDTO));
                session.flush();
                session.clear();
            }
        }
    }

    public void saveTenderContracts(TenderDTO tender, Session session) throws Exception {
        if (session == null) {
            throw new Exception("Session did not set");
        }

        String tenderId = tender.getId();
        if (tender != null && tender.getContracts() != null) {
            for (ContractDTO contractDTO : tender.getContracts()) {
                deleteAllContractDependentRecords(contractDTO, session);

                session.saveOrUpdate(contractDTO);

                saveContractItems(contractDTO, session);
                saveContractSuppliers(contractDTO, session);

                session.flush();
                session.clear();

                session.saveOrUpdate(new TenderContractDTO(tenderId, contractDTO.getId()));
                session.flush();
                session.clear();
            }
        }
    }


    public void saveTenderAwards(TenderDTO tender, Session session) throws Exception {
        if (session == null) {
            throw new Exception("Session did not set");
        }

        if (tender != null && tender.getAwards() != null) {
            for (AwardDTO awardDTO : tender.getAwards()) {

                deleteAllAwardDependentRecords(awardDTO, session);

                session.saveOrUpdate(awardDTO);

                saveAwardSuppliers(awardDTO, session);
                saveAwardDocuments(awardDTO, session);

                session.flush();
                session.clear();

                session.saveOrUpdate(new TenderAwardDTO(tender, awardDTO));
                session.flush();
                session.clear();
            }
        }
    }


    public void saveAwardDocuments(AwardDTO awardDTO, Session session) throws Exception {
        if (session == null) {
            throw new Exception("Session did not set");
        }
        if (awardDTO != null && awardDTO.getDocuments() != null) {
            for (DocumentDTO documentDTO : awardDTO.getDocuments()) {
                session.saveOrUpdate(documentDTO);
                session.flush();
                session.clear();

                session.saveOrUpdate(new AwardDocumentDTO(awardDTO, documentDTO));
                session.flush();
                session.clear();
            }
        }
    }

    public void saveAwardSuppliers(AwardDTO awardDTO, Session session) throws Exception {
        if (session == null) {
            throw new Exception("Session did not set");
        }
        if (awardDTO != null && awardDTO.getDocuments() != null) {
            for (OrganizationDTO organizationDTO : awardDTO.getSuppliers()) {
                session.saveOrUpdate(organizationDTO);
                session.flush();
                session.clear();

                session.saveOrUpdate(new AwardSupplierDTO(awardDTO, organizationDTO));
                session.flush();
                session.clear();
            }
        }
    }


    public void saveContractItems(ContractDTO contractDTO, Session session) throws Exception {
        if (session == null) {
            throw new Exception("Session did not set");
        }
        if (contractDTO != null && contractDTO.getItems() != null) {
            for (ItemDTO itemDTO : contractDTO.getItems()) {
                session.saveOrUpdate(itemDTO);
                session.flush();
                session.clear();

                session.saveOrUpdate(new ContractItemDTO(contractDTO, itemDTO));
                session.flush();
                session.clear();
            }
        }
    }

    public void saveContractSuppliers(ContractDTO contractDTO, Session session) throws Exception {
        if (session == null) {
            throw new Exception("Session did not set");
        }
        if (contractDTO != null && contractDTO.getSuppliers() != null) {
            for (OrganizationDTO organizationDTO : contractDTO.getSuppliers()) {
                session.saveOrUpdate(organizationDTO);
                session.flush();
                session.clear();

                session.saveOrUpdate(new ContractSupplierDTO(contractDTO, organizationDTO));
                session.flush();
                session.clear();
            }
        }
    }


    public void saveTenderItems(TenderDTO tender, Session session) throws Exception {
        if (session == null) {
            throw new Exception("Session did not set");
        }

        if (tender != null && tender.getItems() != null) {
            for (ItemDTO itemDTO : tender.getItems()) {
                session.saveOrUpdate(itemDTO);
                session.flush();
                session.clear();

                session.saveOrUpdate(new TenderItemDTO(tender, itemDTO));
                session.flush();
                session.clear();
            }
        }
    }

    public void saveTenderBids(TenderDTO tender, Session session) throws Exception {
        if (session == null) {
            throw new Exception("Session did not set");
        }

        if (tender != null && tender.getBids() != null) {
            for (BidDTO bidDTO : tender.getBids()) {
                deleteAllBidDependentRecords(bidDTO, session);
                session.saveOrUpdate(bidDTO);

                saveBidDocuments(bidDTO, session);
                saveBidTenderers(bidDTO, session);

                session.flush();
                session.clear();

                session.saveOrUpdate(new TenderBidDTO(tender, bidDTO));
                session.flush();
                session.clear();
            }
        }
    }

    public void saveTenderQuestions(TenderDTO tender, Session session) throws Exception {
        if (session == null) {
            throw new Exception("Session did not set");
        }

        if (tender != null && tender.getQuestions() != null) {
            for (QuestionDTO questionDTO : tender.getQuestions()) {
                session.saveOrUpdate(questionDTO);
                session.flush();
                session.clear();

                session.saveOrUpdate(new TenderQuestionDTO(tender, questionDTO));
                session.flush();
                session.clear();
            }
        }
    }

    public void saveTenderComplaints(TenderDTO tender, Session session) throws Exception {
        if (session == null) {
            throw new Exception("Session did not set");
        }

        if (tender != null && tender.getComplaints() != null) {
            for (ComplaintDTO complaintDTO : tender.getComplaints()) {
                session.saveOrUpdate(complaintDTO);
                session.flush();
                session.clear();

                session.saveOrUpdate(new TenderComplaintDTO(tender, complaintDTO));
                session.flush();
                session.clear();
            }
        }
    }


}
