package ua.prozorro.entity.tenders;


import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;
//http://api-docs.openprocurement.org/uk_UA/latest/standard/tender.html
//https://docs.google.com/document/d/1cUKO4mTrKgn0TUW2yjRU17RVsAyyBSMykRgVnTXDSVQ/edit#heading=h.nnj75vv795k5

@Entity
@Table(name = "Tenders")
public class TenderDTO {
    @Id
    //@GeneratedValue(strategy= GenerationType.AUTO)
    @Column(nullable = false)
    private String id;
    @Column
    public String guaranteeCurrency;
    @Column
    public String guaranteeAmount;
    @Column(length = 36)
    public String date;
    @Column
    private String procurementMethod;
    @Column
    private int numberOfBids;

    @Column
    private String awardPeriodStartDate;
    @Column
    private String awardPeriodClarificationsUntil;
    @Column
    private String awardPeriodEndDate;
    @Column
    private String awardPeriodInvalidationDate;

    @Column
    private String complaintPeriodStartDate;
    @Column
    private String complaintPeriodClarificationsUntil;
    @Column
    private String complaintPeriodEndDate;
    @Column
    private String complaintPeriodInvalidationDate;

    @Column(length = 2000)
    private String auctionUrl;

    @Column
    private String enquiryPeriodStartDate;
    @Column
    private String enquiryPeriodClarificationsUntil;
    @Column
    private String enquiryPeriodEndDate;
    @Column
    private String enquiryPeriodInvalidationDate;

    @Column
    private String submissionMethod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "procuringEntity_id")
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private OrganizationDTO procuringEntity;

    //	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    //	@JoinTable(name = "Tenders_Funders", joinColumns = {@JoinColumn(name = "tender_id")}, inverseJoinColumns = {
    //			@JoinColumn(name = "organization_id")})
    @Transient
    private List<OrganizationDTO> funders;

    @Column
    private String owner;

    /*@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "Tenders_Documents", joinColumns = {@JoinColumn(name = "tender_id")}, inverseJoinColumns = {
            @JoinColumn(name = "document_id")})*/
    @Transient
    private List<DocumentDTO> documents = null;

    //@Column(length = 4000)
    @Lob
    @Column
    private String title;

    @Column(name = "tender_id")
    private String tenderID;

    @Column
    private String dateModified;

    @Column
    private String status;

    @Column
    private String tenderPeriodStartDate;
    @Column
    private String tenderPeriodClarificationsUntil;
    @Column
    private String tenderPeriodEndDate;
    @Column
    private String tenderPeriodInvalidationDate;

    /*@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "Tenders_Contracts", joinColumns = {@JoinColumn(name = "tender_id")}, inverseJoinColumns = {
            @JoinColumn(name = "contract_id")})*/
    @Transient
    private List<ContractDTO> contracts = null;

    @Column
    private String auctionPeriodStartDate;
    @Column
    private String auctionPeriodClarificationsUntil;
    @Column
    private String auctionPeriodEndDate;
    @Column
    private String auctionPeriodInvalidationDate;

    @Column
    private String procurementMethodType;

    /*@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "Tenders_Awards", joinColumns = {@JoinColumn(name = "tender_id")}, inverseJoinColumns = {
            @JoinColumn(name = "award_id")})*/
    @Transient
    private List<AwardDTO> awards = null;

    @Column
    private String minimalStepCurrency;
    @Column
    private String minimalStepAmount;
    @Column
    private String minimalStepValueAddedTaxIncluded;

    /*@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "Tenders_Items", joinColumns = {@JoinColumn(name = "tender_id")}, inverseJoinColumns = {
            @JoinColumn(name = "item_id")})*/
    @Transient
    private List<ItemDTO> items = null;

    /*@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "Tenders_Bids", joinColumns = {@JoinColumn(name = "tender_id")}, inverseJoinColumns = {
            @JoinColumn(name = "bid_id")})*/
    @Transient
    private List<BidDTO> bids = null;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "Tenders_Cancellations", joinColumns = {@JoinColumn(name = "tender_id")},
            inverseJoinColumns = {@JoinColumn(name = "cancellation_id")})
    private List<CancellationDTO> cancellations = null;

    @Column
    private String currency;
    @Column
    private String amount;
    @Column
    private String addedTaxIncluded;

    @Column
    private String awardCriteria;

    //@Column(length = 8000)
    @Lob
    @Column
    private String description;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "Tenders_Lots", joinColumns = {@JoinColumn(name = "tender_id")},
            inverseJoinColumns = {@JoinColumn(name = "lot_id")})
    private List<LotDTO> lots = null;

    /*@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "Tenders_Features", joinColumns = {@JoinColumn(name = "tender_id")},
            inverseJoinColumns = {@JoinColumn(name = "feature_id")})*/
    @Transient
    private List<FeatureDTO> features = null;

    /*@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "Tenders_Questions", joinColumns = {@JoinColumn(name = "tender_id")}, inverseJoinColumns = {
            @JoinColumn(name = "question_id")})*/
    @Transient
    private List<QuestionDTO> questions = null;

    /*@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "Tenders_Complaints", joinColumns = {@JoinColumn(name = "tender_id")}, inverseJoinColumns = {
            @JoinColumn(name = "complaint_id")})*/
    @Transient
    private List<ComplaintDTO> complaints = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGuaranteeCurrency() {
        return guaranteeCurrency;
    }

    public void setGuaranteeCurrency(String guaranteeCurrency) {
        this.guaranteeCurrency = guaranteeCurrency;
    }

    public String getGuaranteeAmount() {
        return guaranteeAmount;
    }

    public void setGuaranteeAmount(String guaranteeAmount) {
        this.guaranteeAmount = guaranteeAmount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProcurementMethod() {
        return procurementMethod;
    }

    public void setProcurementMethod(String procurementMethod) {
        this.procurementMethod = procurementMethod;
    }

    public int getNumberOfBids() {
        return numberOfBids;
    }

    public void setNumberOfBids(int numberOfBids) {
        this.numberOfBids = numberOfBids;
    }

    public String getAwardPeriodStartDate() {
        return awardPeriodStartDate;
    }

    public void setAwardPeriodStartDate(String awardPeriodStartDate) {
        this.awardPeriodStartDate = awardPeriodStartDate;
    }

    public String getAwardPeriodClarificationsUntil() {
        return awardPeriodClarificationsUntil;
    }

    public void setAwardPeriodClarificationsUntil(String awardPeriodClarificationsUntil) {
        this.awardPeriodClarificationsUntil = awardPeriodClarificationsUntil;
    }

    public String getAwardPeriodEndDate() {
        return awardPeriodEndDate;
    }

    public void setAwardPeriodEndDate(String awardPeriodEndDate) {
        this.awardPeriodEndDate = awardPeriodEndDate;
    }

    public String getAwardPeriodInvalidationDate() {
        return awardPeriodInvalidationDate;
    }

    public void setAwardPeriodInvalidationDate(String awardPeriodInvalidationDate) {
        this.awardPeriodInvalidationDate = awardPeriodInvalidationDate;
    }

    public String getComplaintPeriodStartDate() {
        return complaintPeriodStartDate;
    }

    public void setComplaintPeriodStartDate(String complaintPeriodStartDate) {
        this.complaintPeriodStartDate = complaintPeriodStartDate;
    }

    public String getComplaintPeriodClarificationsUntil() {
        return complaintPeriodClarificationsUntil;
    }

    public void setComplaintPeriodClarificationsUntil(String complaintPeriodClarificationsUntil) {
        this.complaintPeriodClarificationsUntil = complaintPeriodClarificationsUntil;
    }

    public String getComplaintPeriodEndDate() {
        return complaintPeriodEndDate;
    }

    public void setComplaintPeriodEndDate(String complaintPeriodEndDate) {
        this.complaintPeriodEndDate = complaintPeriodEndDate;
    }

    public String getComplaintPeriodInvalidationDate() {
        return complaintPeriodInvalidationDate;
    }

    public void setComplaintPeriodInvalidationDate(String complaintPeriodInvalidationDate) {
        this.complaintPeriodInvalidationDate = complaintPeriodInvalidationDate;
    }

    public String getAuctionUrl() {
        return auctionUrl;
    }

    public void setAuctionUrl(String auctionUrl) {
        this.auctionUrl = auctionUrl;
    }

    public String getEnquiryPeriodStartDate() {
        return enquiryPeriodStartDate;
    }

    public void setEnquiryPeriodStartDate(String enquiryPeriodStartDate) {
        this.enquiryPeriodStartDate = enquiryPeriodStartDate;
    }

    public String getEnquiryPeriodClarificationsUntil() {
        return enquiryPeriodClarificationsUntil;
    }

    public void setEnquiryPeriodClarificationsUntil(String enquiryPeriodClarificationsUntil) {
        this.enquiryPeriodClarificationsUntil = enquiryPeriodClarificationsUntil;
    }

    public String getEnquiryPeriodEndDate() {
        return enquiryPeriodEndDate;
    }

    public void setEnquiryPeriodEndDate(String enquiryPeriodEndDate) {
        this.enquiryPeriodEndDate = enquiryPeriodEndDate;
    }

    public String getEnquiryPeriodInvalidationDate() {
        return enquiryPeriodInvalidationDate;
    }

    public void setEnquiryPeriodInvalidationDate(String enquiryPeriodInvalidationDate) {
        this.enquiryPeriodInvalidationDate = enquiryPeriodInvalidationDate;
    }

    public String getSubmissionMethod() {
        return submissionMethod;
    }

    public void setSubmissionMethod(String submissionMethod) {
        this.submissionMethod = submissionMethod;
    }

    public OrganizationDTO getProcuringEntity() {
        return procuringEntity;
    }

    public void setProcuringEntity(OrganizationDTO procuringEntity) {
        this.procuringEntity = procuringEntity;
    }

    public List<OrganizationDTO> getFunders() {
        return funders;
    }

    public void setFunders(List<OrganizationDTO> funders) {
        this.funders = funders;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public List<DocumentDTO> getDocuments() {
        return documents;
    }

    public void setDocuments(List<DocumentDTO> documents) {
        this.documents = documents;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTenderID() {
        return tenderID;
    }

    public void setTenderID(String tenderID) {
        this.tenderID = tenderID;
    }

    public String getDateModified() {
        return dateModified;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTenderPeriodStartDate() {
        return tenderPeriodStartDate;
    }

    public void setTenderPeriodStartDate(String tenderPeriodStartDate) {
        this.tenderPeriodStartDate = tenderPeriodStartDate;
    }

    public String getTenderPeriodClarificationsUntil() {
        return tenderPeriodClarificationsUntil;
    }

    public void setTenderPeriodClarificationsUntil(String tenderPeriodClarificationsUntil) {
        this.tenderPeriodClarificationsUntil = tenderPeriodClarificationsUntil;
    }

    public String getTenderPeriodEndDate() {
        return tenderPeriodEndDate;
    }

    public void setTenderPeriodEndDate(String tenderPeriodEndDate) {
        this.tenderPeriodEndDate = tenderPeriodEndDate;
    }

    public String getTenderPeriodInvalidationDate() {
        return tenderPeriodInvalidationDate;
    }

    public void setTenderPeriodInvalidationDate(String tenderPeriodInvalidationDate) {
        this.tenderPeriodInvalidationDate = tenderPeriodInvalidationDate;
    }

    public List<ContractDTO> getContracts() {
        return contracts;
    }

    public void setContracts(List<ContractDTO> contracts) {
        this.contracts = contracts;
    }

    public String getAuctionPeriodStartDate() {
        return auctionPeriodStartDate;
    }

    public void setAuctionPeriodStartDate(String auctionPeriodStartDate) {
        this.auctionPeriodStartDate = auctionPeriodStartDate;
    }

    public String getAuctionPeriodClarificationsUntil() {
        return auctionPeriodClarificationsUntil;
    }

    public void setAuctionPeriodClarificationsUntil(String auctionPeriodClarificationsUntil) {
        this.auctionPeriodClarificationsUntil = auctionPeriodClarificationsUntil;
    }

    public String getAuctionPeriodEndDate() {
        return auctionPeriodEndDate;
    }

    public void setAuctionPeriodEndDate(String auctionPeriodEndDate) {
        this.auctionPeriodEndDate = auctionPeriodEndDate;
    }

    public String getAuctionPeriodInvalidationDate() {
        return auctionPeriodInvalidationDate;
    }

    public void setAuctionPeriodInvalidationDate(String auctionPeriodInvalidationDate) {
        this.auctionPeriodInvalidationDate = auctionPeriodInvalidationDate;
    }

    public String getProcurementMethodType() {
        return procurementMethodType;
    }

    public void setProcurementMethodType(String procurementMethodType) {
        this.procurementMethodType = procurementMethodType;
    }

    public List<AwardDTO> getAwards() {
        return awards;
    }

    public void setAwards(List<AwardDTO> awards) {
        this.awards = awards;
    }

    public String getMinimalStepCurrency() {
        return minimalStepCurrency;
    }

    public void setMinimalStepCurrency(String minimalStepCurrency) {
        this.minimalStepCurrency = minimalStepCurrency;
    }

    public String getMinimalStepAmount() {
        return minimalStepAmount;
    }

    public void setMinimalStepAmount(String minimalStepAmount) {
        this.minimalStepAmount = minimalStepAmount;
    }

    public String getMinimalStepValueAddedTaxIncluded() {
        return minimalStepValueAddedTaxIncluded;
    }

    public void setMinimalStepValueAddedTaxIncluded(String minimalStepValueAddedTaxIncluded) {
        this.minimalStepValueAddedTaxIncluded = minimalStepValueAddedTaxIncluded;
    }

    public List<ItemDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemDTO> items) {
        this.items = items;
    }

    public List<BidDTO> getBids() {
        return bids;
    }

    public void setBids(List<BidDTO> bids) {
        this.bids = bids;
    }

    public List<CancellationDTO> getCancellations() {
        return cancellations;
    }

    public void setCancellations(List<CancellationDTO> cancellations) {
        this.cancellations = cancellations;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAddedTaxIncluded() {
        return addedTaxIncluded;
    }

    public void setAddedTaxIncluded(String addedTaxIncluded) {
        this.addedTaxIncluded = addedTaxIncluded;
    }

    public String getAwardCriteria() {
        return awardCriteria;
    }

    public void setAwardCriteria(String awardCriteria) {
        this.awardCriteria = awardCriteria;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<LotDTO> getLots() {
        return lots;
    }

    public void setLots(List<LotDTO> lots) {
        this.lots = lots;
    }

    public List<FeatureDTO> getFeatures() {
        return features;
    }

    public void setFeatures(List<FeatureDTO> features) {
        this.features = features;
    }

    public List<QuestionDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDTO> questions) {
        this.questions = questions;
    }

    public List<ComplaintDTO> getComplaints() {
        return complaints;
    }

    public void setComplaints(List<ComplaintDTO> complaints) {
        this.complaints = complaints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        TenderDTO tenderDTO = (TenderDTO) o;

        if (numberOfBids != tenderDTO.numberOfBids)
            return false;
        if (id != null ? !id.equals(tenderDTO.id) : tenderDTO.id != null)
            return false;
        if (guaranteeCurrency != null ? !guaranteeCurrency.equals(tenderDTO.guaranteeCurrency) :
                tenderDTO.guaranteeCurrency != null)
            return false;
        if (guaranteeAmount != null ? !guaranteeAmount.equals(tenderDTO.guaranteeAmount) :
                tenderDTO.guaranteeAmount != null)
            return false;
        if (date != null ? !date.equals(tenderDTO.date) : tenderDTO.date != null)
            return false;
        if (procurementMethod != null ? !procurementMethod.equals(tenderDTO.procurementMethod) :
                tenderDTO.procurementMethod != null)
            return false;
        if (awardPeriodStartDate != null ? !awardPeriodStartDate.equals(tenderDTO.awardPeriodStartDate) :
                tenderDTO.awardPeriodStartDate != null)
            return false;
        if (awardPeriodClarificationsUntil != null ?
                !awardPeriodClarificationsUntil.equals(tenderDTO.awardPeriodClarificationsUntil) :
                tenderDTO.awardPeriodClarificationsUntil != null)
            return false;
        if (awardPeriodEndDate != null ? !awardPeriodEndDate.equals(tenderDTO.awardPeriodEndDate) :
                tenderDTO.awardPeriodEndDate != null)
            return false;
        if (awardPeriodInvalidationDate != null ?
                !awardPeriodInvalidationDate.equals(tenderDTO.awardPeriodInvalidationDate) :
                tenderDTO.awardPeriodInvalidationDate != null)
            return false;
        if (complaintPeriodStartDate != null ? !complaintPeriodStartDate.equals(tenderDTO.complaintPeriodStartDate) :
                tenderDTO.complaintPeriodStartDate != null)
            return false;
        if (complaintPeriodClarificationsUntil != null ?
                !complaintPeriodClarificationsUntil.equals(tenderDTO.complaintPeriodClarificationsUntil) :
                tenderDTO.complaintPeriodClarificationsUntil != null)
            return false;
        if (complaintPeriodEndDate != null ? !complaintPeriodEndDate.equals(tenderDTO.complaintPeriodEndDate) :
                tenderDTO.complaintPeriodEndDate != null)
            return false;
        if (complaintPeriodInvalidationDate != null ?
                !complaintPeriodInvalidationDate.equals(tenderDTO.complaintPeriodInvalidationDate) :
                tenderDTO.complaintPeriodInvalidationDate != null)
            return false;
        if (auctionUrl != null ? !auctionUrl.equals(tenderDTO.auctionUrl) : tenderDTO.auctionUrl != null)
            return false;
        if (enquiryPeriodStartDate != null ? !enquiryPeriodStartDate.equals(tenderDTO.enquiryPeriodStartDate) :
                tenderDTO.enquiryPeriodStartDate != null)
            return false;
        if (enquiryPeriodClarificationsUntil != null ?
                !enquiryPeriodClarificationsUntil.equals(tenderDTO.enquiryPeriodClarificationsUntil) :
                tenderDTO.enquiryPeriodClarificationsUntil != null)
            return false;
        if (enquiryPeriodEndDate != null ? !enquiryPeriodEndDate.equals(tenderDTO.enquiryPeriodEndDate) :
                tenderDTO.enquiryPeriodEndDate != null)
            return false;
        if (enquiryPeriodInvalidationDate != null ?
                !enquiryPeriodInvalidationDate.equals(tenderDTO.enquiryPeriodInvalidationDate) :
                tenderDTO.enquiryPeriodInvalidationDate != null)
            return false;
        if (submissionMethod != null ? !submissionMethod.equals(tenderDTO.submissionMethod) :
                tenderDTO.submissionMethod != null)
            return false;
        if (procuringEntity != null ? !procuringEntity.equals(tenderDTO.procuringEntity) :
                tenderDTO.procuringEntity != null)
            return false;
        if (funders != null ? !funders.equals(tenderDTO.funders) : tenderDTO.funders != null)
            return false;
        if (owner != null ? !owner.equals(tenderDTO.owner) : tenderDTO.owner != null)
            return false;
        if (documents != null ? !documents.equals(tenderDTO.documents) : tenderDTO.documents != null)
            return false;
        if (title != null ? !title.equals(tenderDTO.title) : tenderDTO.title != null)
            return false;
        if (tenderID != null ? !tenderID.equals(tenderDTO.tenderID) : tenderDTO.tenderID != null)
            return false;
        if (dateModified != null ? !dateModified.equals(tenderDTO.dateModified) : tenderDTO.dateModified != null)
            return false;
        if (status != null ? !status.equals(tenderDTO.status) : tenderDTO.status != null)
            return false;
        if (tenderPeriodStartDate != null ? !tenderPeriodStartDate.equals(tenderDTO.tenderPeriodStartDate) :
                tenderDTO.tenderPeriodStartDate != null)
            return false;
        if (tenderPeriodClarificationsUntil != null ?
                !tenderPeriodClarificationsUntil.equals(tenderDTO.tenderPeriodClarificationsUntil) :
                tenderDTO.tenderPeriodClarificationsUntil != null)
            return false;
        if (tenderPeriodEndDate != null ? !tenderPeriodEndDate.equals(tenderDTO.tenderPeriodEndDate) :
                tenderDTO.tenderPeriodEndDate != null)
            return false;
        if (tenderPeriodInvalidationDate != null ?
                !tenderPeriodInvalidationDate.equals(tenderDTO.tenderPeriodInvalidationDate) :
                tenderDTO.tenderPeriodInvalidationDate != null)
            return false;
        if (contracts != null ? !contracts.equals(tenderDTO.contracts) : tenderDTO.contracts != null)
            return false;
        if (auctionPeriodStartDate != null ? !auctionPeriodStartDate.equals(tenderDTO.auctionPeriodStartDate) :
                tenderDTO.auctionPeriodStartDate != null)
            return false;
        if (auctionPeriodClarificationsUntil != null ?
                !auctionPeriodClarificationsUntil.equals(tenderDTO.auctionPeriodClarificationsUntil) :
                tenderDTO.auctionPeriodClarificationsUntil != null)
            return false;
        if (auctionPeriodEndDate != null ? !auctionPeriodEndDate.equals(tenderDTO.auctionPeriodEndDate) :
                tenderDTO.auctionPeriodEndDate != null)
            return false;
        if (auctionPeriodInvalidationDate != null ?
                !auctionPeriodInvalidationDate.equals(tenderDTO.auctionPeriodInvalidationDate) :
                tenderDTO.auctionPeriodInvalidationDate != null)
            return false;
        if (procurementMethodType != null ? !procurementMethodType.equals(tenderDTO.procurementMethodType) :
                tenderDTO.procurementMethodType != null)
            return false;
        if (awards != null ? !awards.equals(tenderDTO.awards) : tenderDTO.awards != null)
            return false;
        if (minimalStepCurrency != null ? !minimalStepCurrency.equals(tenderDTO.minimalStepCurrency) :
                tenderDTO.minimalStepCurrency != null)
            return false;
        if (minimalStepAmount != null ? !minimalStepAmount.equals(tenderDTO.minimalStepAmount) :
                tenderDTO.minimalStepAmount != null)
            return false;
        if (minimalStepValueAddedTaxIncluded != null ?
                !minimalStepValueAddedTaxIncluded.equals(tenderDTO.minimalStepValueAddedTaxIncluded) :
                tenderDTO.minimalStepValueAddedTaxIncluded != null)
            return false;
        if (items != null ? !items.equals(tenderDTO.items) : tenderDTO.items != null)
            return false;
        if (bids != null ? !bids.equals(tenderDTO.bids) : tenderDTO.bids != null)
            return false;
        if (cancellations != null ? !cancellations.equals(tenderDTO.cancellations) : tenderDTO.cancellations != null)
            return false;
        if (currency != null ? !currency.equals(tenderDTO.currency) : tenderDTO.currency != null)
            return false;
        if (amount != null ? !amount.equals(tenderDTO.amount) : tenderDTO.amount != null)
            return false;
        if (addedTaxIncluded != null ? !addedTaxIncluded.equals(tenderDTO.addedTaxIncluded) :
                tenderDTO.addedTaxIncluded != null)
            return false;
        if (awardCriteria != null ? !awardCriteria.equals(tenderDTO.awardCriteria) : tenderDTO.awardCriteria != null)
            return false;
        if (description != null ? !description.equals(tenderDTO.description) : tenderDTO.description != null)
            return false;
        if (lots != null ? !lots.equals(tenderDTO.lots) : tenderDTO.lots != null)
            return false;
        if (features != null ? !features.equals(tenderDTO.features) : tenderDTO.features != null)
            return false;
        if (questions != null ? !questions.equals(tenderDTO.questions) : tenderDTO.questions != null)
            return false;
        return complaints != null ? complaints.equals(tenderDTO.complaints) : tenderDTO.complaints == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (guaranteeCurrency != null ? guaranteeCurrency.hashCode() : 0);
        result = 31 * result + (guaranteeAmount != null ? guaranteeAmount.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (procurementMethod != null ? procurementMethod.hashCode() : 0);
        result = 31 * result + numberOfBids;
        result = 31 * result + (awardPeriodStartDate != null ? awardPeriodStartDate.hashCode() : 0);
        result = 31 * result + (awardPeriodClarificationsUntil != null ? awardPeriodClarificationsUntil.hashCode() : 0);
        result = 31 * result + (awardPeriodEndDate != null ? awardPeriodEndDate.hashCode() : 0);
        result = 31 * result + (awardPeriodInvalidationDate != null ? awardPeriodInvalidationDate.hashCode() : 0);
        result = 31 * result + (complaintPeriodStartDate != null ? complaintPeriodStartDate.hashCode() : 0);
        result = 31 * result +
                 (complaintPeriodClarificationsUntil != null ? complaintPeriodClarificationsUntil.hashCode() : 0);
        result = 31 * result + (complaintPeriodEndDate != null ? complaintPeriodEndDate.hashCode() : 0);
        result = 31 * result +
                 (complaintPeriodInvalidationDate != null ? complaintPeriodInvalidationDate.hashCode() : 0);
        result = 31 * result + (auctionUrl != null ? auctionUrl.hashCode() : 0);
        result = 31 * result + (enquiryPeriodStartDate != null ? enquiryPeriodStartDate.hashCode() : 0);
        result = 31 * result +
                 (enquiryPeriodClarificationsUntil != null ? enquiryPeriodClarificationsUntil.hashCode() : 0);
        result = 31 * result + (enquiryPeriodEndDate != null ? enquiryPeriodEndDate.hashCode() : 0);
        result = 31 * result + (enquiryPeriodInvalidationDate != null ? enquiryPeriodInvalidationDate.hashCode() : 0);
        result = 31 * result + (submissionMethod != null ? submissionMethod.hashCode() : 0);
        result = 31 * result + (procuringEntity != null ? procuringEntity.hashCode() : 0);
        result = 31 * result + (funders != null ? funders.hashCode() : 0);
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        result = 31 * result + (documents != null ? documents.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (tenderID != null ? tenderID.hashCode() : 0);
        result = 31 * result + (dateModified != null ? dateModified.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (tenderPeriodStartDate != null ? tenderPeriodStartDate.hashCode() : 0);
        result = 31 * result +
                 (tenderPeriodClarificationsUntil != null ? tenderPeriodClarificationsUntil.hashCode() : 0);
        result = 31 * result + (tenderPeriodEndDate != null ? tenderPeriodEndDate.hashCode() : 0);
        result = 31 * result + (tenderPeriodInvalidationDate != null ? tenderPeriodInvalidationDate.hashCode() : 0);
        result = 31 * result + (contracts != null ? contracts.hashCode() : 0);
        result = 31 * result + (auctionPeriodStartDate != null ? auctionPeriodStartDate.hashCode() : 0);
        result = 31 * result +
                 (auctionPeriodClarificationsUntil != null ? auctionPeriodClarificationsUntil.hashCode() : 0);
        result = 31 * result + (auctionPeriodEndDate != null ? auctionPeriodEndDate.hashCode() : 0);
        result = 31 * result + (auctionPeriodInvalidationDate != null ? auctionPeriodInvalidationDate.hashCode() : 0);
        result = 31 * result + (procurementMethodType != null ? procurementMethodType.hashCode() : 0);
        result = 31 * result + (awards != null ? awards.hashCode() : 0);
        result = 31 * result + (minimalStepCurrency != null ? minimalStepCurrency.hashCode() : 0);
        result = 31 * result + (minimalStepAmount != null ? minimalStepAmount.hashCode() : 0);
        result = 31 * result +
                 (minimalStepValueAddedTaxIncluded != null ? minimalStepValueAddedTaxIncluded.hashCode() : 0);
        result = 31 * result + (items != null ? items.hashCode() : 0);
        result = 31 * result + (bids != null ? bids.hashCode() : 0);
        result = 31 * result + (cancellations != null ? cancellations.hashCode() : 0);
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (addedTaxIncluded != null ? addedTaxIncluded.hashCode() : 0);
        result = 31 * result + (awardCriteria != null ? awardCriteria.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (lots != null ? lots.hashCode() : 0);
        result = 31 * result + (features != null ? features.hashCode() : 0);
        result = 31 * result + (questions != null ? questions.hashCode() : 0);
        result = 31 * result + (complaints != null ? complaints.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("TenderDTO{");
        sb.append("id='").append(id).append('\'');
        sb.append(", guaranteeCurrency='").append(guaranteeCurrency).append('\'');
        sb.append(", guaranteeAmount='").append(guaranteeAmount).append('\'');
        sb.append(", date='").append(date).append('\'');
        sb.append(", procurementMethod='").append(procurementMethod).append('\'');
        sb.append(", numberOfBids=").append(numberOfBids);
        sb.append(", awardPeriodStartDate='").append(awardPeriodStartDate).append('\'');
        sb.append(", awardPeriodClarificationsUntil='").append(awardPeriodClarificationsUntil).append('\'');
        sb.append(", awardPeriodEndDate='").append(awardPeriodEndDate).append('\'');
        sb.append(", awardPeriodInvalidationDate='").append(awardPeriodInvalidationDate).append('\'');
        sb.append(", complaintPeriodStartDate='").append(complaintPeriodStartDate).append('\'');
        sb.append(", complaintPeriodClarificationsUntil='").append(complaintPeriodClarificationsUntil).append('\'');
        sb.append(", complaintPeriodEndDate='").append(complaintPeriodEndDate).append('\'');
        sb.append(", complaintPeriodInvalidationDate='").append(complaintPeriodInvalidationDate).append('\'');
        sb.append(", auctionUrl='").append(auctionUrl).append('\'');
        sb.append(", enquiryPeriodStartDate='").append(enquiryPeriodStartDate).append('\'');
        sb.append(", enquiryPeriodClarificationsUntil='").append(enquiryPeriodClarificationsUntil).append('\'');
        sb.append(", enquiryPeriodEndDate='").append(enquiryPeriodEndDate).append('\'');
        sb.append(", enquiryPeriodInvalidationDate='").append(enquiryPeriodInvalidationDate).append('\'');
        sb.append(", submissionMethod='").append(submissionMethod).append('\'');
        sb.append(", procuringEntity=").append(procuringEntity);
        sb.append(", funders=").append(funders);
        sb.append(", owner='").append(owner).append('\'');
        sb.append(", documents=").append(documents);
        sb.append(", title='").append(title).append('\'');
        sb.append(", tenderID='").append(tenderID).append('\'');
        sb.append(", dateModified='").append(dateModified).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append(", tenderPeriodStartDate='").append(tenderPeriodStartDate).append('\'');
        sb.append(", tenderPeriodClarificationsUntil='").append(tenderPeriodClarificationsUntil).append('\'');
        sb.append(", tenderPeriodEndDate='").append(tenderPeriodEndDate).append('\'');
        sb.append(", tenderPeriodInvalidationDate='").append(tenderPeriodInvalidationDate).append('\'');
        sb.append(", contracts=").append(contracts);
        sb.append(", auctionPeriodStartDate='").append(auctionPeriodStartDate).append('\'');
        sb.append(", auctionPeriodClarificationsUntil='").append(auctionPeriodClarificationsUntil).append('\'');
        sb.append(", auctionPeriodEndDate='").append(auctionPeriodEndDate).append('\'');
        sb.append(", auctionPeriodInvalidationDate='").append(auctionPeriodInvalidationDate).append('\'');
        sb.append(", procurementMethodType='").append(procurementMethodType).append('\'');
        sb.append(", awards=").append(awards);
        sb.append(", minimalStepCurrency='").append(minimalStepCurrency).append('\'');
        sb.append(", minimalStepAmount='").append(minimalStepAmount).append('\'');
        sb.append(", minimalStepValueAddedTaxIncluded='").append(minimalStepValueAddedTaxIncluded).append('\'');
        sb.append(", items=").append(items);
        sb.append(", bids=").append(bids);
        sb.append(", cancellations=").append(cancellations);
        sb.append(", currency='").append(currency).append('\'');
        sb.append(", amount='").append(amount).append('\'');
        sb.append(", addedTaxIncluded='").append(addedTaxIncluded).append('\'');
        sb.append(", awardCriteria='").append(awardCriteria).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", lots=").append(lots);
        sb.append(", features=").append(features);
        sb.append(", questions=").append(questions);
        sb.append(", complaints=").append(complaints);
        sb.append('}');
        return sb.toString();
    }
}
