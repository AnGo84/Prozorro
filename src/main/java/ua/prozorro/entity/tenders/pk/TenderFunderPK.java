package ua.prozorro.entity.tenders.pk;

import java.io.Serializable;

public class TenderFunderPK implements Serializable {

	protected String tenderId;
	protected int funderId;


	public TenderFunderPK() {
	}

	public TenderFunderPK(String tenderId, int funderId) {
		this.tenderId = tenderId;
		this.funderId = funderId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		TenderFunderPK that = (TenderFunderPK) o;

		if (funderId != that.funderId) return false;
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
		final StringBuffer sb = new StringBuffer("TenderFunderPK{");
		sb.append("tenderId='").append(tenderId).append('\'');
		sb.append(", funderId=").append(funderId);
		sb.append('}');
		return sb.toString();
	}
}
