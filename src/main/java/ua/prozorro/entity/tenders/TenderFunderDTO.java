package ua.prozorro.entity.tenders;

import ua.prozorro.entity.tenders.pk.TenderFunderPK;

import javax.persistence.*;

@Entity
@Table(name = "Tenders_Funders")
@IdClass(TenderFunderPK.class)
public class TenderFunderDTO {
	
	@Id
	@Column(name = "tender_id")
	private String tenderId;
	
	@Id
	@Column(name = "organization_id")
	private int funderId;
	
	public TenderFunderDTO() {
	}
	
	public TenderFunderDTO(String tenderId, int funderId) {
		this.tenderId = tenderId;
		this.funderId = funderId;
	}
	
	public String getTenderId() {
		return tenderId;
	}
	
	public void setTenderId(String tenderId) {
		this.tenderId = tenderId;
	}
	
	public int getFunderId() {
		return funderId;
	}
	
	public void setFunderId(int funderId) {
		this.funderId = funderId;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		
		TenderFunderDTO that = (TenderFunderDTO) o;
		
		if (funderId != that.funderId)
			return false;
		return tenderId != null ? tenderId.equals(that.tenderId) : that.tenderId == null;
	}
	
	@Override
	public int hashCode() {
		int result = tenderId != null ? tenderId.hashCode() : 0;
		result = 31 * result + funderId;
		return result;
	}
	
	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("TenderFunderDTO{");
		sb.append("tenderId='").append(tenderId).append('\'');
		sb.append(", funderId=").append(funderId);
		sb.append('}');
		return sb.toString();
	}
}
