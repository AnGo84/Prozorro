package ua.prozorro.model.tenders;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
//http://api-docs.openprocurement.org/uk_UA/latest/standard/tender.html
//https://docs.google.com/document/d/1cUKO4mTrKgn0TUW2yjRU17RVsAyyBSMykRgVnTXDSVQ/edit#heading=h.nnj75vv795k5

@Entity
@Table(name = "tenders")
public class Tender implements Serializable {

	@SerializedName("guarantee")
	//@Expose
	public Guarantee guarantee;

	@Column
	@Expose(deserialize = false)
	public String currency;
	@Column
	@Expose(deserialize = false)
	public double amount;

	@SerializedName("date")
	//@Expose
	@Column
	public String date;

	@SerializedName("procurementMethod")
	//@Expose
	@Column
	private String procurementMethod;

	@SerializedName("numberOfBids")
	@Column
	private int numberOfBids;

	@SerializedName("awardPeriod")
	//@Expose
	private Period awardPeriod;
	@Column
	private String awardPeriodStartDate;
	@Column
	private String awardPeriodClarificationsUntil;
	@Column
	private String awardPeriodEndDate;
	@Column
	private String awardPeriodInvalidationDate;


	@SerializedName("complaintPeriod")
	//@Expose
	private Period complaintPeriod;
	@Column
	private String complaintPeriodStartDate;
	@Column
	private String complaintPeriodClarificationsUntil;
	@Column
	private String complaintPeriodEndDate;
	@Column
	private String complaintPeriodInvalidationDate;



	@SerializedName("auctionUrl")
	//@Expose
	@Column
	private String auctionUrl;

	@SerializedName("enquiryPeriod")
	//@Expose
	private Period enquiryPeriod;
	@Column
	private String enquiryPeriodStartDate;
	@Column
	private String enquiryPeriodClarificationsUntil;
	@Column
	private String enquiryPeriodEndDate;
	@Column
	private String enquiryPeriodInvalidationDate;

	@SerializedName("submissionMethod")
	//@Expose
	@Column
	private String submissionMethod;

	@SerializedName("procuringEntity")
	//@Expose

    //@ManyToOne(optional = false)
    @ManyToOne
    @JoinColumn(name = "procuringEntity")
	private Organization procuringEntity;

	@SerializedName("funders")
	//@Expose
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USER_ROLE", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = {
            @JoinColumn(name = "ROLE_ID") })
	private List<Organization> funders;
	@SerializedName("owner")
	//@Expose
	@Column
	private String owner;
	@SerializedName("id")
	//@Expose
	@Column
	private String id;
	@SerializedName("documents")
	//@Expose
	private List<Document> documents = null;
	@SerializedName("title")
	//@Expose
	private String title;
	@SerializedName("tenderID")
	//@Expose
	private String tenderID;
	@SerializedName("dateModified")
	//@Expose
	private String dateModified;
	@SerializedName("status")
	//@Expose
	private String status;
	@SerializedName("tenderPeriod")
	//@Expose
	private Period tenderPeriod;
	@SerializedName("contracts")
	//@Expose
	private List<Contract> contracts = null;
	@SerializedName("auctionPeriod")
	//@Expose
	private Period auctionPeriod;
	@SerializedName("procurementMethodType")
	//@Expose
	private String procurementMethodType;
	@SerializedName("awards")
	//@Expose
	private List<Award> awards = null;
	@SerializedName("minimalStep")
	//@Expose
	private Value minimalStep;

	@SerializedName("items")
	//@Expose
	private List<Item> items = null;

	@SerializedName("bids")
	//@Expose
	private List<Bid> bids = null;

	@SerializedName("cancellations")
	//@Expose
	private List<Cancellation> cancellations = null;

	@SerializedName("value")
	//@Expose
	private Value value;

	@SerializedName("awardCriteria")
	//@Expose
	private String awardCriteria;

	@SerializedName("description")
	//@Expose
	private String description;

	@SerializedName("lots")
	//@Expose
	private List<Lot> lots = null;

	@SerializedName("features")
	//@Expose
	private List<Feature> features = null;

	@SerializedName("questions")
	//@Expose
	private List<Question> questions = null;

	@SerializedName("complaints")
	//@Expose
	private List<Complaint> complaints = null;

	public Tender() {
	}

	public Tender(String id) {
		this.id = id;
	}

	public Guarantee getGuarantee() {
		return guarantee;
	}

	public void setGuarantee(Guarantee guarantee) {
		this.amount = guarantee.getAmount();
		this.currency = guarantee.getCurrency();
		this.guarantee = guarantee;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
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

	public Period getAwardPeriod() {
		return awardPeriod;
	}

	public void setAwardPeriod(Period awardPeriod) {
		this.awardPeriod = awardPeriod;
	}

	public Period getComplaintPeriod() {
		return complaintPeriod;
	}

	public void setComplaintPeriod(Period complaintPeriod) {
		this.complaintPeriod = complaintPeriod;
	}

	public String getAuctionUrl() {
		return auctionUrl;
	}

	public void setAuctionUrl(String auctionUrl) {
		this.auctionUrl = auctionUrl;
	}

	public Period getEnquiryPeriod() {
		return enquiryPeriod;
	}

	public void setEnquiryPeriod(Period enquiryPeriod) {
		this.enquiryPeriod = enquiryPeriod;
	}

	public String getSubmissionMethod() {
		return submissionMethod;
	}

	public void setSubmissionMethod(String submissionMethod) {
		this.submissionMethod = submissionMethod;
	}

	public Organization getProcuringEntity() {
		return procuringEntity;
	}

	public void setProcuringEntity(Organization procuringEntity) {
		this.procuringEntity = procuringEntity;
	}

	public List<Organization> getFunders() {
		return funders;
	}

	public void setFunders(List<Organization> funders) {
		this.funders = funders;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
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

	public Period getTenderPeriod() {
		return tenderPeriod;
	}

	public void setTenderPeriod(Period tenderPeriod) {
		this.tenderPeriod = tenderPeriod;
	}

	public List<Contract> getContracts() {
		return contracts;
	}

	public void setContracts(List<Contract> contracts) {
		this.contracts = contracts;
	}

	public Period getAuctionPeriod() {
		return auctionPeriod;
	}

	public void setAuctionPeriod(Period auctionPeriod) {
		this.auctionPeriod = auctionPeriod;
	}

	public String getProcurementMethodType() {
		return procurementMethodType;
	}

	public void setProcurementMethodType(String procurementMethodType) {
		this.procurementMethodType = procurementMethodType;
	}

	public List<Award> getAwards() {
		return awards;
	}

	public void setAwards(List<Award> awards) {
		this.awards = awards;
	}

	public Value getMinimalStep() {
		return minimalStep;
	}

	public void setMinimalStep(Value minimalStep) {
		this.minimalStep = minimalStep;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public List<Bid> getBids() {
		return bids;
	}

	public void setBids(List<Bid> bids) {
		this.bids = bids;
	}

	public List<Cancellation> getCancellations() {
		return cancellations;
	}

	public void setCancellations(List<Cancellation> cancellations) {
		this.cancellations = cancellations;
	}

	public Value getValue() {
		return value;
	}

	public void setValue(Value value) {
		this.value = value;
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

	public List<Lot> getLots() {
		return lots;
	}

	public void setLots(List<Lot> lots) {
		this.lots = lots;
	}

	public List<Feature> getFeatures() {
		return features;
	}

	public void setFeatures(List<Feature> features) {
		this.features = features;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public List<Complaint> getComplaints() {
		return complaints;
	}

	public void setComplaints(List<Complaint> complaints) {
		this.complaints = complaints;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Tender{");
		sb.append("guarantee=").append(guarantee);
		sb.append(", currency='").append(currency).append('\'');
		sb.append(", amount=").append(amount);
		sb.append(", date='").append(date).append('\'');
		sb.append(", procurementMethod='").append(procurementMethod).append('\'');
		sb.append(", numberOfBids=").append(numberOfBids);
		sb.append(", awardPeriod=").append(awardPeriod);
		sb.append(", complaintPeriod=").append(complaintPeriod);
		sb.append(", auctionUrl='").append(auctionUrl).append('\'');
		sb.append(", enquiryPeriod=").append(enquiryPeriod);
		sb.append(", submissionMethod='").append(submissionMethod).append('\'');
		sb.append(", procuringEntity=").append(procuringEntity);
		sb.append(", funders=").append(funders);
		sb.append(", owner='").append(owner).append('\'');
		sb.append(", id='").append(id).append('\'');
		sb.append(", documents=").append(documents);
		sb.append(", title='").append(title).append('\'');
		sb.append(", tenderID='").append(tenderID).append('\'');
		sb.append(", dateModified='").append(dateModified).append('\'');
		sb.append(", status='").append(status).append('\'');
		sb.append(", tenderPeriod=").append(tenderPeriod);
		sb.append(", contracts=").append(contracts);
		sb.append(", auctionPeriod=").append(auctionPeriod);
		sb.append(", procurementMethodType='").append(procurementMethodType).append('\'');
		sb.append(", awards=").append(awards);
		sb.append(", minimalStep=").append(minimalStep);
		sb.append(", items=").append(items);
		sb.append(", bids=").append(bids);
		sb.append(", cancellations=").append(cancellations);
		sb.append(", value=").append(value);
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
