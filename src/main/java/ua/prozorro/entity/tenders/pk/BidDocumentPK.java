package ua.prozorro.entity.tenders.pk;

import java.io.Serializable;

public class BidDocumentPK implements Serializable {

	protected String bidId;
	protected String documentId;


	public BidDocumentPK() {
	}

	public BidDocumentPK(String bidId, String documentId) {
		this.bidId = bidId;
		this.documentId = documentId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		BidDocumentPK that = (BidDocumentPK) o;

		if (bidId != null ? !bidId.equals(that.bidId) : that.bidId != null) return false;
		return documentId != null ? documentId.equals(that.documentId) : that.documentId == null;
	}

	@Override
	public int hashCode() {
		int result = bidId != null ? bidId.hashCode() : 0;
		result = 31 * result + (documentId != null ? documentId.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("BidDocumentPK{");
		sb.append("bidId='").append(bidId).append('\'');
		sb.append(", documentId='").append(documentId).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
