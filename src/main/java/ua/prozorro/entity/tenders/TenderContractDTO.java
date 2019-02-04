package ua.prozorro.entity.tenders;

import ua.prozorro.entity.tenders.pk.TenderContractPK;

import javax.persistence.*;

@Entity
@Table(name = "Tenders_Contracts")
@IdClass(TenderContractPK.class)
public class TenderContractDTO {
	
	@Id
	@Column(name = "tender_id")
	private String tenderId;
	@Column(name = "contract_id")
	private String contractId;
	
	public TenderContractDTO() {
	}
	
	public TenderContractDTO(String tenderId, String contractId) {
		this.tenderId = tenderId;
		this.contractId = contractId;
		//this.id = 31 * tenderId.hashCode() + contractId.hashCode();
	}
	
	public String getTenderId() {
		return tenderId;
	}
	
	public void setTenderId(String tenderId) {
		this.tenderId = tenderId;
	}
	
	public String getContractId() {
		return contractId;
	}
	
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		
		TenderContractDTO that = (TenderContractDTO) o;
		
		if (tenderId != null ? !tenderId.equals(that.tenderId) : that.tenderId != null)
			return false;
		return contractId != null ? contractId.equals(that.contractId) : that.contractId == null;
	}
	
	@Override
	public int hashCode() {
		int result = tenderId != null ? tenderId.hashCode() : 0;
		result = 31 * result + (contractId != null ? contractId.hashCode() : 0);
		return result;
	}
	
	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("TenderContractDTO{");
		sb.append("tenderId='").append(tenderId).append('\'');
		sb.append(", contractId='").append(contractId).append('\'');
		sb.append('}');
		return sb.toString();
	}
}

