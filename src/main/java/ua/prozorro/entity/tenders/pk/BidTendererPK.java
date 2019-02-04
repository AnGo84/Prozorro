package ua.prozorro.entity.tenders.pk;

import java.io.Serializable;

public class BidTendererPK implements Serializable {

	protected String bidId;
	protected int tendererId;


	public BidTendererPK() {
	}

	public BidTendererPK(String bidId, int tendererId) {
		this.bidId = bidId;
		this.tendererId = tendererId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		BidTendererPK that = (BidTendererPK) o;

		if (tendererId != that.tendererId)
			return false;
		return bidId != null ? bidId.equals(that.bidId) : that.bidId == null;
	}

	@Override
	public int hashCode() {
		int result = bidId != null ? bidId.hashCode() : 0;
		result = 31 * result + tendererId;
		return result;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("BidTendererPK{");
		sb.append("bidId='").append(bidId).append('\'');
		sb.append(", tendererId=").append(tendererId);
		sb.append('}');
		return sb.toString();
	}
}
