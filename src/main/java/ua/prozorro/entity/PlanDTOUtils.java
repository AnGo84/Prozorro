package ua.prozorro.entity;

import ua.prozorro.entity.pages.PlanPageDTO;
import ua.prozorro.entity.plans.*;
import ua.prozorro.entity.tenders.*;
import ua.prozorro.prozorro.model.pages.ProzorroPageElement;
import ua.prozorro.prozorro.model.plans.Budget;
import ua.prozorro.prozorro.model.plans.Plan;
import ua.prozorro.prozorro.model.plans.ProcuringEntity;
import ua.prozorro.prozorro.model.plans.Project;
import ua.prozorro.prozorro.model.tenders.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlanDTOUtils {

    public static PlanPageDTO getPageDTO(ProzorroPageElement pageElement) {
        if (pageElement == null) {
            return null;
        }
        PlanPageDTO page = new PlanPageDTO();

        page.setId(pageElement.getId());
        page.setDateModified(pageElement.getDateModified());

        return page;
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


    public static BudgetDTO getBudgetDTO(Budget budget) {
        if (budget == null) {
            return null;
        }
        BudgetDTO budgetDTO = new BudgetDTO();


        budgetDTO.setId(budget.getId());
        budgetDTO.setAmountNet(budget.getAmountNet());
        budgetDTO.setDescription(budget.getDescription());
        budgetDTO.setNotes(budget.getNotes());

        budgetDTO.setProject(getProjectDTO(budget.getProject()));

        budgetDTO.setCurrency(budget.getCurrency());
        budgetDTO.setAmount(budget.getAmount());
        budgetDTO.setYear(budget.getYear());

        return budgetDTO;
    }


    public static ProjectDTO getProjectDTO(Project project) {
        if (project == null || project.getId()==null || project.getId().equals("")) {
            return null;
        }

        ProjectDTO projectDTO = new ProjectDTO();

        projectDTO.setId(project.getId());
        projectDTO.setName(project.getName());

        return projectDTO;
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

    public static UnitDTO getUnitDTO(Unit unit) {
        if (unit == null || unit.getCode() == null || unit.getCode().equals("")) {
            return null;
        }
        UnitDTO unitDTO = new UnitDTO();

        unitDTO.setCode(unit.getCode());
        unitDTO.setName(unit.getName());

        return unitDTO;
    }


    public static List<DocumentDTO> getDocumentDTOList(List<Document> documentList) {
        if (documentList == null || documentList.isEmpty()) {
            return null;
        }
        Map<String, Document> documentsMap = new HashMap<>();
        for (Document document : documentList) {
            documentsMap.put(document.getId(), document);
        }

        List<DocumentDTO> documentDTOList = new ArrayList<>();

        for (Map.Entry<String,Document> entry: documentsMap.entrySet()){
            DocumentDTO documentDTO = getDocumentDTO(entry.getValue());
            documentDTOList.add(documentDTO);
        }

        /*
        List<DocumentDTO> documentDTOList = new ArrayList<>();
        for (Document document : documentList) {
            DocumentDTO documentDTO = getDocumentDTO(document);
            documentDTOList.add(documentDTO);
        }*/

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

    public static PlanTenderDTO getPlanTenderDTO(Tender tender) {
        if (tender == null) {
            return null;
        }

        PlanTenderDTO planTenderDTO = new PlanTenderDTO();

        planTenderDTO.setProcurementMethod(tender.getProcurementMethod());
        planTenderDTO.setProcurementMethodType(tender.getProcurementMethodType());

        if (tender.getTenderPeriod() != null) {
            planTenderDTO.setTenderPeriodStartDate(tender.getTenderPeriod().getStartDate());
            planTenderDTO.setTenderPeriodClarificationsUntil(tender.getTenderPeriod().getClarificationsUntil());
            planTenderDTO.setTenderPeriodEndDate(tender.getTenderPeriod().getEndDate());
            planTenderDTO.setTenderPeriodInvalidationDate(tender.getTenderPeriod().getInvalidationDate());
        }

        return planTenderDTO;
    }


    public static PlanDTO getPlanDTO(Plan plan) {
        if (plan == null) {
            return null;
        }

        PlanDTO planDTO = new PlanDTO();

        planDTO.setId(plan.getId());

        planDTO.setPlanID(plan.getPlanID());

        planDTO.setDocuments(getDocumentDTOList(plan.getDocuments()));

        planDTO.setClassification(getClassificationDTO(plan.getClassification()));

        planDTO.setItems(getItemDTOList(plan.getItems()));

        planDTO.setBudget(getBudgetDTO(plan.getBudget()));

        planDTO.setAdditionalClassifications(getClassificationDTOList(plan.getAdditionalClassifications()));

        planDTO.setProcuringEntity(getProcuringEntityDTO(plan.getProcuringEntity()));

        planDTO.setTender(getPlanTenderDTO(plan.getTender()));
        if (planDTO.getTender() != null) {
            planDTO.getTender().setId(planDTO.getId());
        }

        planDTO.setDatePublished(plan.getDatePublished());
        planDTO.setOwner(plan.getOwner());
        planDTO.setDateModified(plan.getDateModified());

        return planDTO;
    }
}
