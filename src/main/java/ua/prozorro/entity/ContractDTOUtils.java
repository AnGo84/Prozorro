package ua.prozorro.entity;

import ua.prozorro.entity.pages.ContractPageDTO;
import ua.prozorro.entity.plans.ProcuringEntityDTO;
import ua.prozorro.entity.tenders.*;
import ua.prozorro.prozorro.model.contracts.Contract;
import ua.prozorro.prozorro.model.pages.ProzorroPageElement;
import ua.prozorro.prozorro.model.plans.ProcuringEntity;
import ua.prozorro.prozorro.model.tenders.*;

import java.util.ArrayList;
import java.util.List;

public class ContractDTOUtils {

    public static ContractPageDTO getPageDTO(ProzorroPageElement pageElement) {
        if (pageElement == null) {
            return null;
        }
        ContractPageDTO page = new ContractPageDTO();

        page.setId(pageElement.getId());
        page.setDateModified(pageElement.getDateModified());

        return page;
    }

    public static ContractDTO getContractDTO(Contract contract) {
        if (contract == null) {
            return null;
        }

        ContractDTO contractDTO = new ContractDTO();

        contractDTO.setId(contract.getId());
        contractDTO.setStatus(contract.getStatus());
        contractDTO.setItems(getItemDTOList(contract.getItems()));
        contractDTO.setSuppliers(getOrganizationDTOList(contract.getSuppliers()));
        if (contract.getValue() != null) {
            contractDTO.setCurrency(contract.getValue().getCurrency());
            contractDTO.setAmount(contract.getValue().getAmount());
            contractDTO.setValueAddedTaxIncluded(contract.getValue().getValueAddedTaxIncluded());
        }
        contractDTO.setAwardID(contract.getAwardID());
        contractDTO.setContractID(contract.getContractID());

        contractDTO.setDocuments(getDocumentDTOList(contract.getDocuments()));

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
        contractDTO.setProcuringEntity(getProcuringEntityDTO(contract.getProcuringEntity()));
        contractDTO.setOwner(contract.getOwner());

        return contractDTO;
    }

    public static OrganizationDTO getOrganizationDTO(Organization organization) {
        if (organization == null) {
            return null;
        }
        OrganizationDTO organizationDTO = new OrganizationDTO();

        organizationDTO.setName(organization.getName());
        if (organization.getContactPoint() != null) {
            organizationDTO.setTelephone(organization.getContactPoint().getTelephone());
            organizationDTO.setContactPointName(organization.getContactPoint().getName());
            organizationDTO.setEmail(organization.getContactPoint().getEmail());
            organizationDTO.setUrl(organization.getContactPoint().getUrl());
        }
        organizationDTO.setIdentifier(getIdentifierDTO(organization.getIdentifier()));
        organizationDTO.setId(organizationDTO.getIdentifier().getId());
        organizationDTO.setAddress(getAddressDTO(organization.getAddress()));

        return organizationDTO;
    }

    public static List<OrganizationDTO> getOrganizationDTOList(List<Organization> organizationList) {
        if (organizationList == null || organizationList.isEmpty()) {
            return null;
        }
        List<OrganizationDTO> organizationDTOList = new ArrayList<>();

        for (Organization organization : organizationList) {
            OrganizationDTO organizationDTO = getOrganizationDTO(organization);
            organizationDTOList.add(organizationDTO);
        }

        return organizationDTOList;
    }

    public static AddressDTO getAddressDTO(Address address) {
        if (address == null) {
            return null;
        }
        AddressDTO addressDTO = new AddressDTO();

        addressDTO.setId(address.hashCode());
        addressDTO.setPostalCode(address.getPostalCode());
        addressDTO.setCountryName(address.getCountryName());
        addressDTO.setStreetAddress(address.getStreetAddress());
        addressDTO.setRegion(address.getRegion());
        addressDTO.setLocality(address.getLocality());

        return addressDTO;
    }

    public static IdentifierDTO getIdentifierDTO(Identifier identifier) {
        if (identifier == null) {
            return null;
        }
        IdentifierDTO identifierDTO = new IdentifierDTO();

        identifierDTO.setId(identifier.hashCode());
        identifierDTO.setScheme(identifier.getScheme());
        identifierDTO.setSchemeId(identifier.getId());
        identifierDTO.setUri(identifier.getUri());
        identifierDTO.setLegalName(identifier.getLegalName());
        identifierDTO.setLegalNameEn(identifier.getLegalNameEn());

        return identifierDTO;
    }

    public static DocumentDTO getDocumentDTO(Document document) {
        if (document == null) {
            return null;
        }
        DocumentDTO documentDTO = new DocumentDTO();

        documentDTO.setId(document.getId());
        documentDTO.setHash(document.getHash());
        documentDTO.setDescription(document.getDescription());
        documentDTO.setTitle(document.getTitle());
        documentDTO.setTitleRu(document.getTitleRu());
        documentDTO.setTitleEn(document.getTitleEn());
        documentDTO.setUrl(document.getUrl());
        documentDTO.setFormat(document.getFormat());
        documentDTO.setDocumentOf(document.getDocumentOf());
        documentDTO.setDatePublished(document.getDatePublished());
        documentDTO.setAuthor(document.getAuthor());
        documentDTO.setDocumentType(document.getDocumentType());
        documentDTO.setDateModified(document.getDateModified());

        return documentDTO;
    }


    public static ClassificationDTO getClassificationDTO(Classification classification) {
        if (classification == null) {
            return null;
        }
        ClassificationDTO classificationDTO = new ClassificationDTO();


        classificationDTO.setId(classification.getId());
        classificationDTO.setScheme(classification.getScheme());
        classificationDTO.setDescription(classification.getDescription());
        return classificationDTO;
    }

    public static List<ClassificationDTO> getClassificationDTOList(List<Classification> classificationList) {
        if (classificationList == null || classificationList.isEmpty()) {
            return null;
        }
        List<ClassificationDTO> classificationsDTO = new ArrayList<>();

        for (Classification classification : classificationList) {
            ClassificationDTO classificationDTO = getClassificationDTO(classification);
            classificationsDTO.add(classificationDTO);
        }

        return classificationsDTO;
    }


    public static ItemDTO getItemDTO(Item item) {
        if (item == null) {
            return null;
        }
        ItemDTO itemDTO = new ItemDTO();

        itemDTO.setId(item.getId());
        itemDTO.setDescription(item.getDescription());
        itemDTO.setClassification(getClassificationDTO(item.getClassification()));
        itemDTO.setDeliveryAddress(getAddressDTO(item.getDeliveryAddress()));
        if (item.getDeliveryDate() != null) {
            itemDTO.setDeliveryStartDate(item.getDeliveryDate().getStartDate());
            itemDTO.setDeliveryClarificationsUntil(item.getDeliveryDate().getClarificationsUntil());
            itemDTO.setDeliveryEndDate(item.getDeliveryDate().getEndDate());
            itemDTO.setDeliveryInvalidationDate(item.getDeliveryDate().getInvalidationDate());
        }
        itemDTO.setUnit(getUnitDTO(item.getUnit()));
        itemDTO.setQuantity(item.getQuantity());

        return itemDTO;
    }


    public static UnitDTO getUnitDTO(Unit unit) {
        if (unit == null || unit.getCode() == null) {
            return null;
        }
        UnitDTO unitDTO = new UnitDTO();

        unitDTO.setCode(unit.getCode());
        unitDTO.setName(unit.getName());

        return unitDTO;
    }

    public static ProcuringEntityDTO getProcuringEntityDTO(ProcuringEntity procuringEntity) {
        if (procuringEntity == null) {
            return null;
        }
        ProcuringEntityDTO procuringEntityDTO = new ProcuringEntityDTO();

        procuringEntityDTO.setId(procuringEntity.getIdentifier().hashCode());

        procuringEntityDTO.setIdentifier(getIdentifierDTO(procuringEntity.getIdentifier()));
        procuringEntityDTO.setName(procuringEntity.getName());

        if (procuringEntity.getContactPoint() != null) {
            procuringEntityDTO.setTelephone(procuringEntity.getContactPoint().getTelephone());
            procuringEntityDTO.setContactPointName(procuringEntity.getContactPoint().getName());
            procuringEntityDTO.setEmail(procuringEntity.getContactPoint().getEmail());
            procuringEntityDTO.setContactUrl(procuringEntity.getContactPoint().getUrl());
        }
        procuringEntityDTO.setKind(procuringEntity.getKind());
        procuringEntityDTO.setAddress(getAddressDTO(procuringEntity.getAddress()));

        return procuringEntityDTO;
    }


    public static List<DocumentDTO> getDocumentDTOList(List<Document> documentList) {
        if (documentList == null || documentList.isEmpty()) {
            return null;
        }
        List<DocumentDTO> documentDTOList = new ArrayList<>();

        for (Document document : documentList) {
            DocumentDTO documentDTO = getDocumentDTO(document);
            documentDTOList.add(documentDTO);
        }

        return documentDTOList;
    }

    public static List<ItemDTO> getItemDTOList(List<Item> itemList) {
        if (itemList == null || itemList.isEmpty()) {
            return null;
        }
        List<ItemDTO> itemDTOList = new ArrayList<>();

        for (Item item : itemList) {
            ItemDTO itemDTO = getItemDTO(item);
            itemDTOList.add(itemDTO);
        }

        return itemDTOList;
    }

}
