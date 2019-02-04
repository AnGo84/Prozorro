package ua.prozorro.entity.tenders;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Bids")
public class BidDTO {
	@Id
	//@GeneratedValue(strategy= GenerationType.AUTO)
	@Column
	private String id;
	@Column
	private String status;
	@Column(name = "arcdate")
	private String date;
	@Column(length = 2000)
	private String participationUrl;
	
	@Column
	private String currency;
	@Column
	private String amount;
	@Column
	private String valueAddedTaxIncluded;
	
	@Column(length = 1)
	private boolean selfEligible;
	@Column(length = 1)
	private boolean selfQualified;
	/*@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinTable(name = "Bids_Documents", joinColumns = {@JoinColumn(name = "bid_id")},
			inverseJoinColumns = {@JoinColumn(name = "document_id")})*/
	@Transient
	private List<DocumentDTO> documents = null;
	
	/*@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinTable(name = "Bids_Organizations", joinColumns = {@JoinColumn(name = "bid_id")},
			inverseJoinColumns = {@JoinColumn(name = "organization_id")})*/
	@Transient
	private List<OrganizationDTO> tenderers = null;
	
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
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getParticipationUrl() {
		return participationUrl;
	}
	
	public void setParticipationUrl(String participationUrl) {
		this.participationUrl = participationUrl;
	}
	
	public boolean isSelfEligible() {
		return selfEligible;
	}
	
	public void setSelfEligible(boolean selfEligible) {
		this.selfEligible = selfEligible;
	}
	
	public boolean isSelfQualified() {
		return selfQualified;
	}
	
	public void setSelfQualified(boolean selfQualified) {
		this.selfQualified = selfQualified;
	}
	
	public List<DocumentDTO> getDocuments() {
		return documents;
	}
	
	public void setDocuments(List<DocumentDTO> documents) {
		this.documents = documents;
	}
	
	public List<OrganizationDTO> getTenderers() {
		return tenderers;
	}
	
	public void setTenderers(List<OrganizationDTO> tenderers) {
		this.tenderers = tenderers;
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
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		
		BidDTO bidDTO = (BidDTO) o;
		
		if (selfEligible != bidDTO.selfEligible)
			return false;
		if (selfQualified != bidDTO.selfQualified)
			return false;
		if (id != null ? !id.equals(bidDTO.id) : bidDTO.id != null)
			return false;
		if (status != null ? !status.equals(bidDTO.status) : bidDTO.status != null)
			return false;
		if (date != null ? !date.equals(bidDTO.date) : bidDTO.date != null)
			return false;
		if (participationUrl != null ? !participationUrl.equals(bidDTO.participationUrl) :
				bidDTO.participationUrl != null)
			return false;
		if (currency != null ? !currency.equals(bidDTO.currency) : bidDTO.currency != null)
			return false;
		if (amount != null ? !amount.equals(bidDTO.amount) : bidDTO.amount != null)
			return false;
		if (valueAddedTaxIncluded != null ? !valueAddedTaxIncluded.equals(bidDTO.valueAddedTaxIncluded) :
				bidDTO.valueAddedTaxIncluded != null)
			return false;
		if (documents != null ? !documents.equals(bidDTO.documents) : bidDTO.documents != null)
			return false;
		return tenderers != null ? tenderers.equals(bidDTO.tenderers) : bidDTO.tenderers == null;
	}
	
	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (status != null ? status.hashCode() : 0);
		result = 31 * result + (date != null ? date.hashCode() : 0);
		result = 31 * result + (participationUrl != null ? participationUrl.hashCode() : 0);
		result = 31 * result + (currency != null ? currency.hashCode() : 0);
		result = 31 * result + (amount != null ? amount.hashCode() : 0);
		result = 31 * result + (valueAddedTaxIncluded != null ? valueAddedTaxIncluded.hashCode() : 0);
		result = 31 * result + (selfEligible ? 1 : 0);
		result = 31 * result + (selfQualified ? 1 : 0);
		result = 31 * result + (documents != null ? documents.hashCode() : 0);
		result = 31 * result + (tenderers != null ? tenderers.hashCode() : 0);
		return result;
	}
	
	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("BidDTO{");
		sb.append("id='").append(id).append('\'');
		sb.append(", status='").append(status).append('\'');
		sb.append(", date='").append(date).append('\'');
		sb.append(", participationUrl='").append(participationUrl).append('\'');
		sb.append(", selfEligible=").append(selfEligible);
		sb.append(", selfQualified=").append(selfQualified);
		sb.append(", documents=").append(documents);
		sb.append(", tenderers=").append(tenderers);
		sb.append('}');
		return sb.toString();
	}
}
