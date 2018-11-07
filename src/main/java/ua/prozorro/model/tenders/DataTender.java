package ua.prozorro.model.tenders;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DATA_TENDER")
public class DataTender {
	@Id
	@Column(name = "id")
	private String id;

	@Column(name = "procurementMethod")
	private String procurementMethod;

	@Column(name = "numberOfBids")
	private String numberOfBids;

	@Column(name = "auctionUrl")
	private String auctionUrl;

	@Column(name = "submissionMethod")
	private String submissionMethod;

	@Column(name = "awardCriteria")
	private String awardCriteria;

	@Column(name = "owner")
	private String owner;

	@Column(name = "description")
	private String description;

	@Column(name = "title")
	private String title;

	@Column(name = "titleID")
	private String titleID;

	@Column(name = "title_date")
	private String titleDate;

	@Column(name = "tender_date")
	private String tenderDate;

	@Column(name = "dateModified")
	private String dateModified;

	@Column(name = "status")
	private String status;

	@Column(name = "procurementMethodType")
	private String procurementMethodType;

	@Column(name = "title_en")
	private String titleEn;

	@Column(name = "description_en")
	private String descriptionEn;

	@Column(name = "description_en")
	private String dates;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProcurementMethod() {
		return procurementMethod;
	}

	public void setProcurementMethod(String procurementMethod) {
		this.procurementMethod = procurementMethod;
	}

	public String getNumberOfBids() {
		return numberOfBids;
	}

	public void setNumberOfBids(String numberOfBids) {
		this.numberOfBids = numberOfBids;
	}

	public String getAuctionUrl() {
		return auctionUrl;
	}

	public void setAuctionUrl(String auctionUrl) {
		this.auctionUrl = auctionUrl;
	}

	public String getSubmissionMethod() {
		return submissionMethod;
	}

	public void setSubmissionMethod(String submissionMethod) {
		this.submissionMethod = submissionMethod;
	}

	public String getAwardCriteria() {
		return awardCriteria;
	}

	public void setAwardCriteria(String awardCriteria) {
		this.awardCriteria = awardCriteria;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitleID() {
		return titleID;
	}

	public void setTitleID(String titleID) {
		this.titleID = titleID;
	}

	public String getTitleDate() {
		return titleDate;
	}

	public void setTitleDate(String titleDate) {
		this.titleDate = titleDate;
	}

	public String getTenderDate() {
		return tenderDate;
	}

	public void setTenderDate(String tenderDate) {
		this.tenderDate = tenderDate;
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

	public String getProcurementMethodType() {
		return procurementMethodType;
	}

	public void setProcurementMethodType(String procurementMethodType) {
		this.procurementMethodType = procurementMethodType;
	}

	public String getTitleEn() {
		return titleEn;
	}

	public void setTitleEn(String titleEn) {
		this.titleEn = titleEn;
	}

	public String getDescriptionEn() {
		return descriptionEn;
	}

	public void setDescriptionEn(String descriptionEn) {
		this.descriptionEn = descriptionEn;
	}

	public String getDates() {
		return dates;
	}

	public void setDates(String dates) {
		this.dates = dates;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("DataTender{");
		sb.append("id='").append(id).append('\'');
		sb.append(", procurementMethod='").append(procurementMethod).append('\'');
		sb.append(", numberOfBids='").append(numberOfBids).append('\'');
		sb.append(", auctionUrl='").append(auctionUrl).append('\'');
		sb.append(", submissionMethod='").append(submissionMethod).append('\'');
		sb.append(", awardCriteria='").append(awardCriteria).append('\'');
		sb.append(", owner='").append(owner).append('\'');
		sb.append(", description='").append(description).append('\'');
		sb.append(", title='").append(title).append('\'');
		sb.append(", titleID='").append(titleID).append('\'');
		sb.append(", titleDate='").append(titleDate).append('\'');
		sb.append(", tenderDate='").append(tenderDate).append('\'');
		sb.append(", dateModified='").append(dateModified).append('\'');
		sb.append(", status='").append(status).append('\'');
		sb.append(", procurementMethodType='").append(procurementMethodType).append('\'');
		sb.append(", titleEn='").append(titleEn).append('\'');
		sb.append(", descriptionEn='").append(descriptionEn).append('\'');
		sb.append(", dates='").append(dates).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
