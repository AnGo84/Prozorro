package ua.prozorro.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;
import ua.prozorro.entity.tenders.*;

public class ContractService {
    private static final Logger logger = LogManager.getRootLogger();

    private Session session;

    public ContractService(Session session) {
        this.session = session;
    }

    public void saveContract(ContractDTO contractDTO, Session session) throws Exception {
        if (session == null) {
            throw new Exception("Session did not set");
        }

        deleteAllContractDependentRecords(contractDTO, session);
        session.flush();
        session.clear();

        session.saveOrUpdate(contractDTO);
        session.flush();
        session.clear();

        saveContractItems(contractDTO, session);
        saveContractSuppliers(contractDTO, session);
        saveContractDocuments(contractDTO, session);

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

        q = session.createQuery("delete from ContractDocumentDTO where contract_id=:contractId");
        q.setParameter("contractId", contractDTO.getId());
        q.executeUpdate();

        session.flush();
        session.clear();
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

                session.saveOrUpdate(new ContractItemDTO(contractDTO.getId(), itemDTO.getId()));
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

                session.saveOrUpdate(new ContractSupplierDTO(contractDTO.getId(), organizationDTO.getId()));
                session.flush();
                session.clear();
            }
        }
    }

    public void saveContractDocuments(ContractDTO contractDTO, Session session) throws Exception {
        if (session == null) {
            throw new Exception("Session did not set");
        }
        if (contractDTO != null && contractDTO.getDocuments() != null) {
            for (DocumentDTO documentDTO : contractDTO.getDocuments()) {
                session.saveOrUpdate(documentDTO);
                session.flush();
                session.clear();

                session.saveOrUpdate(new ContractDocumentDTO(contractDTO.getId(), documentDTO.getId()));
                session.flush();
                session.clear();
            }
        }
    }
}
