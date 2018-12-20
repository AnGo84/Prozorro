package ua.prozorro.entity;

import ua.prozorro.entity.pages.PageDTO;
import ua.prozorro.entity.tenders.*;
import ua.prozorro.prozorro.model.pages.ProzorroPageElement;
import ua.prozorro.prozorro.model.tenders.*;

import java.util.ArrayList;
import java.util.List;

public class TenderDTOUtils {
	public static PageDTO getPageDTO(ProzorroPageElement pageElement) {
		if (pageElement == null) {
			return null;
		}
		PageDTO page = new PageDTO();

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
		}
		organizationDTO.setIdentifier(getIdentifierDTO(organization.getIdentifier()));
		organizationDTO.setId(organizationDTO.getIdentifier().getId());
		organizationDTO.setAddress(getAddressDTO(organization.getAddress()));

		return organizationDTO;
	}

	public static BidDTO getBidDTO(Bid bid) {

		if (bid == null) {
			return null;
		}
		BidDTO bidDTO = new BidDTO();

		bidDTO.setId(bid.getId());
		bidDTO.setStatus(bid.getStatus());
		bidDTO.setDate(bid.getDate());
		bidDTO.setParticipationUrl(bid.getParticipationUrl());
		if (bid.getValue() != null) {
			bidDTO.setCurrency(bid.getValue().getCurrency());
			bidDTO.setAmount(bid.getValue().getAmount());
			bidDTO.setValueAddedTaxIncluded(bid.getValue().getValueAddedTaxIncluded());
		}
		bidDTO.setSelfEligible(bid.isSelfEligible());
		bidDTO.setSelfQualified(bid.isSelfQualified());
		bidDTO.setDocuments(getDocumentDTOList(bid.getDocuments()));
		bidDTO.setTenderers(getOrganizationDTOList(bid.getTenderers()));


		return bidDTO;
	}

	public static CancellationDTO getCancellationDTO(Cancellation cancellation) {
		if (cancellation == null) {
			return null;
		}
		CancellationDTO cancellationDTO = new CancellationDTO();

		cancellationDTO.setId(cancellation.getId());
		cancellationDTO.setStatus(cancellation.getStatus());
		cancellationDTO.setDocuments(getDocumentDTOList(cancellation.getDocuments()));
		cancellationDTO.setReason(cancellation.getReason());
		cancellationDTO.setReasonType(cancellation.getReasonType());
		cancellationDTO.setDate(cancellation.getDate());
		cancellationDTO.setCancellationOf(cancellation.getCancellationOf());

		return cancellationDTO;
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

	public static ComplaintDTO getComplaintDTO(Complaint complaint) {
		if (complaint == null) {
			return null;
		}
		ComplaintDTO complaintDTO = new ComplaintDTO();


		complaintDTO.setId(complaint.getId());
		complaintDTO.setAuthor(getOrganizationDTO(complaint.getAuthor()));
		complaintDTO.setTitle(complaint.getTitle());
		complaintDTO.setDescription(complaint.getDescription());
		complaintDTO.setDate(complaint.getDate());
		complaintDTO.setDateSubmitted(complaint.getDateSubmitted());
		complaintDTO.setDateAnswered(complaint.getDateAnswered());
		complaintDTO.setDateEscalated(complaint.getDateEscalated());
		complaintDTO.setDateDecision(complaint.getDateDecision());
		complaintDTO.setDateCanceled(complaint.getDateCanceled());
		complaintDTO.setStatus(complaint.getStatus());
		complaintDTO.setType(complaint.getType());
		complaintDTO.setResolution(complaint.getResolution());
		complaintDTO.setResolutionType(complaint.getResolutionType());
		complaintDTO.setSatisfied(complaint.isSatisfied());
		complaintDTO.setDecision(complaint.getDecision());
		complaintDTO.setCancellationReason(complaint.getCancellationReason());
		complaintDTO.setDocuments(getDocumentDTOList(complaint.getDocuments()));
		complaintDTO.setRelatedLot(complaint.getRelatedLot());
		complaintDTO.setTendererAction(complaint.getTendererAction());
		complaintDTO.setTendererActionDate(complaint.getTendererActionDate());

		return complaintDTO;
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


		return contractDTO;
	}

	public static AwardDTO getAwardDTO(Award award) {
		if (award == null) {
			return null;
		}

		AwardDTO awardDTO = new AwardDTO();

		awardDTO.setId(award.getId());
		awardDTO.setStatus(award.getStatus());

		awardDTO.setDocuments(getDocumentDTOList(award.getDocuments()));

		awardDTO.setDate(award.getDate());
		awardDTO.setBidId(award.getBidId());
		awardDTO.setCurrency(award.getBidId());
		if (award.getValue() != null) {
			awardDTO.setAmount(award.getValue().getAmount());
			awardDTO.setCurrency(award.getValue().getCurrency());
			awardDTO.setValueAddedTaxIncluded(award.getValue().getValueAddedTaxIncluded());
		}
		if (award.getComplaintPeriod() != null) {
			awardDTO.setComplaintPeriodStartDate(award.getComplaintPeriod().getStartDate());
			awardDTO.setComplaintPeriodClarificationsUntil(award.getComplaintPeriod().getClarificationsUntil());
			awardDTO.setComplaintPeriodEndDate(award.getComplaintPeriod().getEndDate());
			awardDTO.setComplaintPeriodInvalidationDate(award.getComplaintPeriod().getInvalidationDate());
		}
		awardDTO.setSuppliers(getOrganizationDTOList(award.getSuppliers()));

		awardDTO.setEligible(award.isEligible());
		awardDTO.setQualified(award.isQualified());


		return awardDTO;
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

	public static LotDTO getLotDTO(Lot lot) {
		if (lot == null) {
			return null;
		}
		LotDTO lotDTO = new LotDTO();

		lotDTO.setId(lot.getId());
		lotDTO.setTitle(lot.getTitle());
		lotDTO.setDescription(lot.getDescription());
		if (lot.getValue() != null) {
			lotDTO.setCurrency(lot.getValue().getCurrency());
			lotDTO.setAmount(lot.getValue().getAmount());
			lotDTO.setAddedTaxIncluded(lot.getValue().getValueAddedTaxIncluded());
		}
		if (lot.getGuarantee() != null) {
			lotDTO.setGuaranteeCurrency(lot.getGuarantee().getCurrency());
			lotDTO.setGuaranteeAmount(lot.getGuarantee().getAmount());
		}
		lotDTO.setDate(lot.getDate());
		if (lot.getMinimalStep() != null) {
			lotDTO.setMinimalStepAmount(lot.getMinimalStep().getAmount());
			lotDTO.setMinimalStepCurrency(lot.getMinimalStep().getCurrency());
			lotDTO.setMinimalStepValueAddedTaxIncluded(lot.getMinimalStep().getValueAddedTaxIncluded());
		}
		if (lot.getAuctionPeriod() != null) {
			lotDTO.setAuctionPeriodStartDate(lot.getAuctionPeriod().getStartDate());
			lotDTO.setAuctionPeriodClarificationsUntil(lot.getAuctionPeriod().getClarificationsUntil());
			lotDTO.setAuctionPeriodEndDate(lot.getAuctionPeriod().getEndDate());
			lotDTO.setAuctionPeriodInvalidationDate(lot.getAuctionPeriod().getInvalidationDate());
		}
		lotDTO.setAuctionUrl(lot.getAuctionUrl());
		lotDTO.setStatus(lot.getStatus());


		return lotDTO;
	}

	public static QuestionDTO getQuestionDTO(Question question) {
		if (question == null) {
			return null;
		}
		QuestionDTO questionDTO = new QuestionDTO();

		questionDTO.setId(question.getId());
		questionDTO.setAuthor(getOrganizationDTO(question.getAuthor()));
		questionDTO.setTitle(question.getTitle());
		questionDTO.setDescription(question.getDescription());
		questionDTO.setDate(question.getDate());
		questionDTO.setDateAnswered(question.getDateAnswered());
		questionDTO.setAnswer(question.getAnswer());
		questionDTO.setQuestionOf(question.getQuestionOf());
		questionDTO.setRelatedItem(question.getRelatedItem());

		return questionDTO;
	}

	public static FeatureEnumDTO getFeatureEnumDTO(FeatureEnum featureEnum) {
		if (featureEnum != null) {
			return null;
		}
		FeatureEnumDTO featureEnumDTO = new FeatureEnumDTO();

		featureEnumDTO.setId(featureEnum.hashCode());
		featureEnumDTO.setTitle(featureEnum.getTitle());
		featureEnumDTO.setDescription(featureEnum.getDescription());
		featureEnumDTO.setValue(featureEnum.getValue());

		return featureEnumDTO;
	}

	public static FeatureDTO getFeatureDTO(Feature feature) {
		if (feature != null) {
			return null;
		}
		FeatureDTO featureDTO = new FeatureDTO();

		featureDTO.setCode(feature.getCode());
		featureDTO.setFeatureOf(feature.getFeatureOf());
		featureDTO.setRelatedItem(feature.getRelatedItem());
		featureDTO.setTitle(feature.getTitle());
		featureDTO.setDescription(feature.getDescription());

		featureDTO.setFeatureEnums(getFeatureEnumList(feature.getFeatureEnums()));

		return featureDTO;
	}

	public static UnitDTO getUnitDTO(Unit unit) {
		if (unit != null) {
			return null;
		}
		UnitDTO unitDTO = new UnitDTO();

		unitDTO.setCode(unit.getCode());
		unitDTO.setName(unit.getName());

		return unitDTO;
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

	public static List<FeatureEnumDTO> getFeatureEnumList(List<FeatureEnum> featureEnumList) {
		if (featureEnumList == null || featureEnumList.isEmpty()) {
			return null;
		}

		List<FeatureEnumDTO> featureEnumDTOList = new ArrayList<>();

		for (FeatureEnum featureEnum : featureEnumList) {
			FeatureEnumDTO featureEnumDTO = getFeatureEnumDTO(featureEnum);
			featureEnumDTOList.add(featureEnumDTO);
		}

		return featureEnumDTOList;
	}

	public static List<ContractDTO> getContractDTOList(List<Contract> contractList) {
		if (contractList == null || contractList.isEmpty()) {
			return null;
		}

		List<ContractDTO> contractDTOList = new ArrayList<>();

		for (Contract contract : contractList) {
			ContractDTO contractDTO = getContractDTO(contract);
			contractDTOList.add(contractDTO);
		}

		return contractDTOList;
	}

	public static List<AwardDTO> getAwardDTOList(List<Award> awardList) {
		if (awardList == null || awardList.isEmpty()) {
			return null;
		}
		List<AwardDTO> awardDTOList = new ArrayList<>();

		for (Award award : awardList) {
			AwardDTO contractDTO = getAwardDTO(award);
			awardDTOList.add(contractDTO);
		}

		return awardDTOList;
	}

	public static List<BidDTO> getBidDTOList(List<Bid> bidList) {
		if (bidList == null || bidList.isEmpty()) {
			return null;
		}
		List<BidDTO> bidDTOList = new ArrayList<>();

		for (Bid bid : bidList) {
			BidDTO contractDTO = getBidDTO(bid);
			bidDTOList.add(contractDTO);
		}

		return bidDTOList;
	}

	public static List<CancellationDTO> getCancellationDTOList(List<Cancellation> cancellationList) {
		if (cancellationList == null || cancellationList.isEmpty()) {
			return null;
		}
		List<CancellationDTO> cancellationDTOList = new ArrayList<>();

		for (Cancellation cancellation : cancellationList) {
			CancellationDTO contractDTO = getCancellationDTO(cancellation);
			cancellationDTOList.add(contractDTO);
		}

		return cancellationDTOList;
	}

	public static List<LotDTO> getLotDTOList(List<Lot> lotList) {
		if (lotList == null || lotList.isEmpty()) {
			return null;
		}
		List<LotDTO> lotDTOList = new ArrayList<>();

		for (Lot lot : lotList) {
			LotDTO lotDTO = getLotDTO(lot);
			lotDTOList.add(lotDTO);
		}

		return lotDTOList;
	}

	public static List<FeatureDTO> getFeatureDTOList(List<Feature> featureList) {
		if (featureList == null || featureList.isEmpty()) {
			return null;
		}
		List<FeatureDTO> featureDTOList = new ArrayList<>();

		for (Feature feature : featureList) {
			FeatureDTO featureDTO = getFeatureDTO(feature);
			featureDTOList.add(featureDTO);
		}

		return featureDTOList;
	}

	public static List<QuestionDTO> getQuestionDTOList(List<Question> questionList) {
		if (questionList == null || questionList.isEmpty()) {
			return null;
		}
		List<QuestionDTO> questionDTOList = new ArrayList<>();

		for (Question question : questionList) {
			QuestionDTO questionDTO = getQuestionDTO(question);
			questionDTOList.add(questionDTO);
		}

		return questionDTOList;
	}

	public static List<ComplaintDTO> getComplaintDTOList(List<Complaint> complaintList) {
		if (complaintList == null || complaintList.isEmpty()) {
			return null;
		}
		List<ComplaintDTO> complaintDTOList = new ArrayList<>();

		for (Complaint complaint : complaintList) {
			ComplaintDTO complaintDTO = getComplaintDTO(complaint);
			complaintDTOList.add(complaintDTO);
		}

		return complaintDTOList;
	}

	public static TenderDTO getTenderDTO(Tender tender) {
		if (tender == null) {
			return null;
		}

		TenderDTO tenderDTO = new TenderDTO();

		tenderDTO.setId(tender.getId());
		if (tender.getGuarantee() != null) {
			tenderDTO.setGuaranteeCurrency(tender.getGuarantee().getCurrency());
			tenderDTO.setGuaranteeAmount(tender.getGuarantee().getCurrency());
		}

		tenderDTO.setDate(tender.getDate());
		tenderDTO.setProcurementMethod(tender.getProcurementMethod());
		tenderDTO.setProcurementMethodType(tender.getProcurementMethodType());
		tenderDTO.setNumberOfBids(tender.getNumberOfBids());

		if (tender.getAwardPeriod() != null) {
			tenderDTO.setAwardPeriodStartDate(tender.getAwardPeriod().getStartDate());
			tenderDTO.setAwardPeriodClarificationsUntil(tender.getAwardPeriod().getClarificationsUntil());
			tenderDTO.setAwardPeriodEndDate(tender.getAwardPeriod().getEndDate());
			tenderDTO.setAwardPeriodInvalidationDate(tender.getAwardPeriod().getInvalidationDate());
		}

		if (tender.getComplaintPeriod() != null) {
			tenderDTO.setComplaintPeriodStartDate(tender.getComplaintPeriod().getStartDate());
			tenderDTO.setComplaintPeriodClarificationsUntil(tender.getComplaintPeriod().getClarificationsUntil());
			tenderDTO.setComplaintPeriodEndDate(tender.getComplaintPeriod().getEndDate());
			tenderDTO.setComplaintPeriodInvalidationDate(tender.getComplaintPeriod().getInvalidationDate());
		}
		tenderDTO.setAuctionUrl(tender.getAuctionUrl());
		if (tender.getEnquiryPeriod() != null) {
			tenderDTO.setEnquiryPeriodStartDate(tender.getEnquiryPeriod().getStartDate());
			tenderDTO.setEnquiryPeriodClarificationsUntil(tender.getEnquiryPeriod().getClarificationsUntil());
			tenderDTO.setEnquiryPeriodEndDate(tender.getEnquiryPeriod().getEndDate());
			tenderDTO.setEnquiryPeriodInvalidationDate(tender.getEnquiryPeriod().getInvalidationDate());
		}

		tenderDTO.setSubmissionMethod(tender.getSubmissionMethod());

		tenderDTO.setProcuringEntity(getOrganizationDTO(tender.getProcuringEntity()));
		tenderDTO.setFunders(getOrganizationDTOList(tender.getFunders()));

		tenderDTO.setOwner(tender.getOwner());

		tenderDTO.setDocuments(getDocumentDTOList(tender.getDocuments()));

		tenderDTO.setTitle(tender.getTitle());
		tenderDTO.setTenderID(tender.getTenderID());
		tenderDTO.setDateModified(tender.getDateModified());
		tenderDTO.setStatus(tender.getStatus());
		if (tender.getTenderPeriod() != null) {
			tenderDTO.setTenderPeriodStartDate(tender.getTenderPeriod().getStartDate());
			tenderDTO.setTenderPeriodClarificationsUntil(tender.getTenderPeriod().getClarificationsUntil());
			tenderDTO.setTenderPeriodEndDate(tender.getTenderPeriod().getEndDate());
			tenderDTO.setTenderPeriodInvalidationDate(tender.getTenderPeriod().getInvalidationDate());
		}
		tenderDTO.setContracts(getContractDTOList(tender.getContracts()));
		if (tender.getAuctionPeriod() != null) {
			tenderDTO.setAuctionPeriodStartDate(tender.getAuctionPeriod().getStartDate());
			tenderDTO.setAuctionPeriodClarificationsUntil(tender.getAuctionPeriod().getClarificationsUntil());
			tenderDTO.setAuctionPeriodEndDate(tender.getAuctionPeriod().getEndDate());
			tenderDTO.setAuctionPeriodInvalidationDate(tender.getAuctionPeriod().getInvalidationDate());
		}
		tenderDTO.setAwards(getAwardDTOList(tender.getAwards()));
		if (tender.getMinimalStep() != null) {
			tenderDTO.setMinimalStepCurrency(tender.getMinimalStep().getCurrency());
			tenderDTO.setMinimalStepAmount(tender.getMinimalStep().getAmount());
			tenderDTO.setMinimalStepValueAddedTaxIncluded(tender.getMinimalStep().getValueAddedTaxIncluded());
		}

		tenderDTO.setItems(getItemDTOList(tender.getItems()));
		tenderDTO.setBids(getBidDTOList(tender.getBids()));
		tenderDTO.setCancellations(getCancellationDTOList(tender.getCancellations()));

		if (tender.getValue() != null) {
			tenderDTO.setCurrency(tender.getValue().getCurrency());
			tenderDTO.setAmount(tender.getValue().getAmount());
			tenderDTO.setAddedTaxIncluded(tender.getValue().getValueAddedTaxIncluded());
		}
		tenderDTO.setAwardCriteria(tender.getAwardCriteria());
		tenderDTO.setDescription(tender.getDescription());

		tenderDTO.setLots(getLotDTOList(tender.getLots()));
		tenderDTO.setFeatures(getFeatureDTOList(tender.getFeatures()));
		tenderDTO.setQuestions(getQuestionDTOList(tender.getQuestions()));
		tenderDTO.setComplaints(getComplaintDTOList(tender.getComplaints()));


		return tenderDTO;
	}
}
