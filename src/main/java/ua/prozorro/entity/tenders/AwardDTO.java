package ua.prozorro.entity.tenders;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Awards")
public class AwardDTO {
	@Id
	@Column
	@GeneratedValue(strategy= GenerationType.AUTO)

	private String id;

	@Column
	private String status;

	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinTable(name = "Awards_Documents", joinColumns = {@JoinColumn(name = "award_id")}, inverseJoinColumns = {
			@JoinColumn(name = "document_id")})
	private List<DocumentDTO> documents = null;

	@Column
	private String date;

	@Column(name = "bid_id")
	private String bidId;

	@Column
	private String currency;
	@Column
	private String amount;
	@Column
	private String valueAddedTaxIncluded;

	@Column
	private String complaintPeriodStartDate;
	@Column
	private String complaintPeriodClarificationsUntil;
	@Column
	private String complaintPeriodEndDate;
	@Column
	private String complaintPeriodInvalidationDate;

	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinTable(name = "Awards_Suppliers", joinColumns = {@JoinColumn(name = "award_id")}, inverseJoinColumns = {
			@JoinColumn(name = "supplier_id")})
	private List<OrganizationDTO> suppliers = null;

	@Column(length = 1)
	private boolean eligible;

	@Column(length = 1)
	private boolean qualified;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<DocumentDTO> getDocuments() {
		return documents;
	}

	public void setDocuments(List<DocumentDTO> documents) {
		this.documents = documents;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getBidId() {
		return bidId;
	}

	public void setBidId(String bidId) {
		this.bidId = bidId;
	}

	public List<OrganizationDTO> getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(List<OrganizationDTO> suppliers) {
		this.suppliers = suppliers;
	}

	public boolean isEligible() {
		return eligible;
	}

	public void setEligible(boolean eligible) {
		this.eligible = eligible;
	}

	public boolean isQualified() {
		return qualified;
	}

	public void setQualified(boolean qualified) {
		this.qualified = qualified;
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

	public String getValueAddedTaxIncluded() {
		return valueAddedTaxIncluded;
	}

	public void setValueAddedTaxIncluded(String valueAddedTaxIncluded) {
		this.valueAddedTaxIncluded = valueAddedTaxIncluded;
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		AwardDTO awardDTO = (AwardDTO) o;

		if (eligible != awardDTO.eligible) return false;
		if (qualified != awardDTO.qualified) return false;
		if (id != null ? !id.equals(awardDTO.id) : awardDTO.id != null) return false;
		if (status != null ? !status.equals(awardDTO.status) : awardDTO.status != null) return false;
		if (documents != null ? !documents.equals(awardDTO.documents) : awardDTO.documents != null) return false;
		if (date != null ? !date.equals(awardDTO.date) : awardDTO.date != null) return false;
		if (bidId != null ? !bidId.equals(awardDTO.bidId) : awardDTO.bidId != null) return false;
		if (currency != null ? !currency.equals(awardDTO.currency) : awardDTO.currency != null) return false;
		if (amount != null ? !amount.equals(awardDTO.amount) : awardDTO.amount != null) return false;
		if (valueAddedTaxIncluded != null ? !valueAddedTaxIncluded.equals(awardDTO.valueAddedTaxIncluded) : awardDTO.valueAddedTaxIncluded != null)
			return false;
		if (complaintPeriodStartDate != null ? !complaintPeriodStartDate.equals(awardDTO.complaintPeriodStartDate) : awardDTO.complaintPeriodStartDate != null)
			return false;
		if (complaintPeriodClarificationsUntil != null ? !complaintPeriodClarificationsUntil.equals(awardDTO.complaintPeriodClarificationsUntil) : awardDTO.complaintPeriodClarificationsUntil != null)
			return false;
		if (complaintPeriodEndDate != null ? !complaintPeriodEndDate.equals(awardDTO.complaintPeriodEndDate) : awardDTO.complaintPeriodEndDate != null)
			return false;
		if (complaintPeriodInvalidationDate != null ? !complaintPeriodInvalidationDate.equals(awardDTO.complaintPeriodInvalidationDate) : awardDTO.complaintPeriodInvalidationDate != null)
			return false;
		return suppliers != null ? suppliers.equals(awardDTO.suppliers) : awardDTO.suppliers == null;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (status != null ? status.hashCode() : 0);
		result = 31 * result + (documents != null ? documents.hashCode() : 0);
		result = 31 * result + (date != null ? date.hashCode() : 0);
		result = 31 * result + (bidId != null ? bidId.hashCode() : 0);
		result = 31 * result + (currency != null ? currency.hashCode() : 0);
		result = 31 * result + (amount != null ? amount.hashCode() : 0);
		result = 31 * result + (valueAddedTaxIncluded != null ? valueAddedTaxIncluded.hashCode() : 0);
		result = 31 * result + (complaintPeriodStartDate != null ? complaintPeriodStartDate.hashCode() : 0);
		result = 31 * result + (complaintPeriodClarificationsUntil != null ? complaintPeriodClarificationsUntil.hashCode() : 0);
		result = 31 * result + (complaintPeriodEndDate != null ? complaintPeriodEndDate.hashCode() : 0);
		result = 31 * result + (complaintPeriodInvalidationDate != null ? complaintPeriodInvalidationDate.hashCode() : 0);
		result = 31 * result + (suppliers != null ? suppliers.hashCode() : 0);
		result = 31 * result + (eligible ? 1 : 0);
		result = 31 * result + (qualified ? 1 : 0);
		return result;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("AwardDTO{");
		sb.append("id='").append(id).append('\'');
		sb.append(", status='").append(status).append('\'');
		sb.append(", documents=").append(documents);
		sb.append(", date='").append(date).append('\'');
		sb.append(", bidId='").append(bidId).append('\'');
		sb.append(", currency='").append(currency).append('\'');
		sb.append(", amount='").append(amount).append('\'');
		sb.append(", valueAddedTaxIncluded='").append(valueAddedTaxIncluded).append('\'');
		sb.append(", complaintPeriodStartDate='").append(complaintPeriodStartDate).append('\'');
		sb.append(", complaintPeriodClarificationsUntil='").append(complaintPeriodClarificationsUntil).append('\'');
		sb.append(", complaintPeriodEndDate='").append(complaintPeriodEndDate).append('\'');
		sb.append(", complaintPeriodInvalidationDate='").append(complaintPeriodInvalidationDate).append('\'');
		sb.append(", suppliers=").append(suppliers);
		sb.append(", eligible=").append(eligible);
		sb.append(", qualified=").append(qualified);
		sb.append('}');
		return sb.toString();
	}
}
