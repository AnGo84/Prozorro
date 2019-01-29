package ua.prozorro.entity.tenders.pk;

import java.io.Serializable;

public class TenderContractPK implements Serializable {

	protected String tenderId;
	protected String contractId;


	public TenderContractPK() {
	}

	public TenderContractPK(String tenderId, String contractId) {
		this.tenderId = tenderId;
		this.contractId = contractId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		TenderContractPK that = (TenderContractPK) o;

		if (tenderId != null ? !tenderId.equals(that.tenderId) : that.tenderId != null) return false;
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
		final StringBuffer sb = new StringBuffer("TenderContractPK{");
		sb.append("tenderId='").append(tenderId).append('\'');
		sb.append(", contractId='").append(contractId).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
