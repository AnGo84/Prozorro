package ua.prozorro.model.tenders;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;
//http://api-docs.openprocurement.org/uk_UA/latest/standard/tender.html
//https://docs.google.com/document/d/1cUKO4mTrKgn0TUW2yjRU17RVsAyyBSMykRgVnTXDSVQ/edit#heading=h.nnj75vv795k5
public class Tender implements Serializable {

	@SerializedName("guarantee")
	//@Expose
	public Guarantee guarantee;
	@SerializedName("date")
	//@Expose
	public String date;
	@SerializedName("procurementMethod")
	//@Expose
	private String procurementMethod;
	@SerializedName("numberOfBids")
	private int numberOfBids;
	@SerializedName("awardPeriod")
	//@Expose
	private Period awardPeriod;
	@SerializedName("complaintPeriod")
	//@Expose
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


	public Tender() {
	}

	public Tender(String id) {
		this.id = id;
	}



}
