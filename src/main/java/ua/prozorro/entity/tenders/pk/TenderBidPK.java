package ua.prozorro.entity.tenders.pk;

import java.io.Serializable;

public class TenderBidPK implements Serializable {

	protected String tenderId;
	protected String bidId;


	public TenderBidPK() {
	}

	public TenderBidPK(String tenderId, String bidId) {
		this.tenderId = tenderId;
		this.bidId = bidId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		TenderBidPK that = (TenderBidPK) o;

		if (tenderId != null ? !tenderId.equals(that.tenderId) : that.tenderId != null)
			return false;
		return bidId != null ? bidId.equals(that.bidId) : that.bidId == null;
	}

	@Override
	public int hashCode() {
		int result = tenderId != null ? tenderId.hashCode() : 0;
		result = 31 * result + (bidId != null ? bidId.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("TenderBidPK{");
		sb.append("tenderId='").append(tenderId).append('\'');
		sb.append(", bidId='").append(bidId).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
