package ua.prozorro.entity.mappers.prozorroObjectMapper;

import ua.prozorro.entity.mappers.AbstractMapper;
import ua.prozorro.entity.tenders.TenderDTO;
import ua.prozorro.prozorro.model.tenders.Tender;

public class TenderMapper extends AbstractMapper<Tender, TenderDTO> {
    private OrganizationMapper organizationMapper = new OrganizationMapper();
    private OrganizationsListMapper organizationsListMapper = new OrganizationsListMapper();
    private DocumentsListMapper documentsListMapper = new DocumentsListMapper();
    private TenderContractsListMapper tenderContractsListMapper = new TenderContractsListMapper();
    private AwardsListMapper awardsListMapper = new AwardsListMapper();
    private ItemsListMapper itemsListMapper = new ItemsListMapper();
    private BidsListMapper bidsListMapper = new BidsListMapper();
    private CancellationsListMapper cancellationsListMapper = new CancellationsListMapper();
    private LotsListMapper lotsListMapper = new LotsListMapper();
    private FeaturesListMapper featuresListMapper = new FeaturesListMapper();
    private QuestionsListMapper questionsListMapper = new QuestionsListMapper();
    private ComplaintsListMapper complaintsListMapper = new ComplaintsListMapper();

    @Override
    public TenderDTO convertToEntity(Tender tender) {
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

        tenderDTO.setProcuringEntity(organizationMapper.convertToEntity(tender.getProcuringEntity()));
        tenderDTO.setFunders(organizationsListMapper.convertToEntitiesList(tender.getFunders()));

        tenderDTO.setOwner(tender.getOwner());

        tenderDTO.setDocuments(documentsListMapper.convertToEntitiesList(tender.getDocuments()));

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
        tenderDTO.setContracts(tenderContractsListMapper.convertToEntitiesList(tender.getContracts()));
        if (tender.getAuctionPeriod() != null) {
            tenderDTO.setAuctionPeriodStartDate(tender.getAuctionPeriod().getStartDate());
            tenderDTO.setAuctionPeriodClarificationsUntil(tender.getAuctionPeriod().getClarificationsUntil());
            tenderDTO.setAuctionPeriodEndDate(tender.getAuctionPeriod().getEndDate());
            tenderDTO.setAuctionPeriodInvalidationDate(tender.getAuctionPeriod().getInvalidationDate());
        }
        tenderDTO.setAwards(awardsListMapper.convertToEntitiesList(tender.getAwards()));
        if (tender.getMinimalStep() != null) {
            tenderDTO.setMinimalStepCurrency(tender.getMinimalStep().getCurrency());
            tenderDTO.setMinimalStepAmount(tender.getMinimalStep().getAmount());
            tenderDTO.setMinimalStepValueAddedTaxIncluded(tender.getMinimalStep().getValueAddedTaxIncluded());
        }

        tenderDTO.setItems(itemsListMapper.convertToEntitiesList(tender.getItems()));
        tenderDTO.setBids(bidsListMapper.convertToEntitiesList(tender.getBids()));
        tenderDTO.setCancellations(cancellationsListMapper.convertToEntitiesList(tender.getCancellations()));

        if (tender.getValue() != null) {
            tenderDTO.setCurrency(tender.getValue().getCurrency());
            tenderDTO.setAmount(tender.getValue().getAmount());
            tenderDTO.setAddedTaxIncluded(tender.getValue().getValueAddedTaxIncluded());
        }
        tenderDTO.setAwardCriteria(tender.getAwardCriteria());
        tenderDTO.setDescription(tender.getDescription());

        tenderDTO.setLots(lotsListMapper.convertToEntitiesList(tender.getLots()));
        tenderDTO.setFeatures(featuresListMapper.convertToEntitiesList(tender.getFeatures()));
        tenderDTO.setQuestions(questionsListMapper.convertToEntitiesList(tender.getQuestions()));
        tenderDTO.setComplaints(complaintsListMapper.convertToEntitiesList(tender.getComplaints()));

        return tenderDTO;
    }

    @Override
    public Tender convertToObject(TenderDTO entity) {
        return null;
    }
}
