package ua.prozorro.prozorro.model.tenders;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;
//http://api-docs.openprocurement.org/uk_UA/latest/standard/tender.html
//https://docs.google.com/document/d/1cUKO4mTrKgn0TUW2yjRU17RVsAyyBSMykRgVnTXDSVQ/edit#heading=h.nnj75vv795k5

public class Tender implements Serializable {
	
	@SerializedName("guarantee")
	public Guarantee guarantee;
	
	@SerializedName("date")
	public String date;
	
	@SerializedName("procurementMethod")
	private String procurementMethod;
	
	@SerializedName("numberOfBids")
	private int numberOfBids;
	
	@SerializedName("awardPeriod")
	private Period awardPeriod;
	
	@SerializedName("complaintPeriod")
	private Period complaintPeriod;
	
	@SerializedName("auctionUrl")
	//@Expose
	private String auctionUrl;
	
	@SerializedName("enquiryPeriod")
	//@Expose
	private Period enquiryPeriod;
	
	@SerializedName("submissionMethod")
	//@Expose
	private String submissionMethod;
	
	@SerializedName("procuringEntity")
	//@Expose
	private Organization procuringEntity;
	
	@SerializedName("funders")
	//@Expose
	private List<Organization> funders;
	
	@SerializedName("owner")
	//@Expose
	private String owner;
	@SerializedName("id")
	//@Expose
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
		this.guarantee = guarantee;
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
		final StringBuffer sb = new StringBuffer("Tender{");
		sb.append("guarantee=").append(guarantee);
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
