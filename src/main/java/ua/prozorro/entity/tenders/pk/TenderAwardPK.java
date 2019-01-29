package ua.prozorro.entity.tenders.pk;

import java.io.Serializable;

public class TenderAwardPK implements Serializable {

	protected String tenderId;
	protected String awardId;


	public TenderAwardPK() {
	}

	public TenderAwardPK(String tenderId, String awardId) {
		this.tenderId = tenderId;
		this.awardId = awardId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		TenderAwardPK that = (TenderAwardPK) o;

		if (tenderId != null ? !tenderId.equals(that.tenderId) : that.tenderId != null) return false;
		return awardId != null ? awardId.equals(that.awardId) : that.awardId == null;
	}

	@Override
	public int hashCode() {
		int result = tenderId != null ? tenderId.hashCode() : 0;
		result = 31 * result + (awardId != null ? awardId.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("TenderAwardPK{");
		sb.append("tenderId='").append(tenderId).append('\'');
		sb.append(", awardId='").append(awardId).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
