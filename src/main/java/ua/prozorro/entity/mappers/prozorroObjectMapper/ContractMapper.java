package ua.prozorro.entity.mappers.prozorroObjectMapper;

import ua.prozorro.entity.mappers.AbstractMapper;
import ua.prozorro.entity.tenders.ContractDTO;
import ua.prozorro.prozorro.model.contracts.Contract;

public class ContractMapper extends AbstractMapper<Contract, ContractDTO> {
    private ItemsListMapper itemsListMapper = new ItemsListMapper();
    private OrganizationsListMapper organizationsListMapper = new OrganizationsListMapper();
    private DocumentsListMapper documentsListMapper = new DocumentsListMapper();
    private ProcuringEntityMapper procuringEntityMapper = new ProcuringEntityMapper();
    @Override
    public ContractDTO convertToEntity(Contract contract) {
        if(contract==null){
            return null;
        }
        ContractDTO contractDTO = new ContractDTO();
        contractDTO.setId(contract.getId());
        contractDTO.setStatus(contract.getStatus());
        contractDTO.setItems(itemsListMapper.convertToEntitiesList(contract.getItems()));
        contractDTO.setSuppliers(organizationsListMapper.convertToEntitiesList(contract.getSuppliers()));
        if (contract.getValue() != null) {
            contractDTO.setCurrency(contract.getValue().getCurrency());
            contractDTO.setAmount(contract.getValue().getAmount());
            contractDTO.setValueAddedTaxIncluded(contract.getValue().getValueAddedTaxIncluded());
        }
        contractDTO.setAwardID(contract.getAwardID());
        contractDTO.setContractID(contract.getContractID());

        contractDTO.setDocuments(documentsListMapper.convertToEntitiesList(contract.getDocuments()));

        contractDTO.setTenderId(contract.getTenderId());

        contractDTO.setDescription(contract.getDescription());
        contractDTO.setTitle(contract.getTitle());
        contractDTO.setContractNumber(contract.getContractNumber());

        if (contract.getPeriod() != null) {
            contractDTO.setContractPeriodStartDate(contract.getPeriod().getStartDate());
            contractDTO.setContractPeriodClarificationsUntil(contract.getPeriod().getClarificationsUntil());
            contractDTO.setContractPeriodEndDate(contract.getPeriod().getEndDate());
            contractDTO.setContractPeriodInvalidationDate(contract.getPeriod().getInvalidationDate());
        }
        contractDTO.setDateSigned(contract.getDateSigned());
        contractDTO.setDateModified(contract.getDateModified());
        contractDTO.setProcuringEntity(procuringEntityMapper.convertToEntity(contract.getProcuringEntity()));
        contractDTO.setOwner(contract.getOwner());

        return contractDTO;
    }

    @Override
    public Contract convertToObject(ContractDTO entity) {
        return null;
    }
}
