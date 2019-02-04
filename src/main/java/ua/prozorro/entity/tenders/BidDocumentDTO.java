package ua.prozorro.entity.tenders;

import ua.prozorro.entity.tenders.pk.BidDocumentPK;

import javax.persistence.*;

@Entity
@Table(name = "Bids_Documents")
@IdClass(BidDocumentPK.class)
public class BidDocumentDTO {
	@Id
	@Column(name = "bid_id")
	private String bidId;
	
	@Id
	@Column(name = "document_id")
	private String documentId;
	
	public BidDocumentDTO() {
	}
	
	public BidDocumentDTO(String bidId, String documentId) {
		this.bidId = bidId;
		this.documentId = documentId;
	}
	
	public String getBidId() {
		return bidId;
	}
	
	public void setBidId(String bidId) {
		this.bidId = bidId;
	}
	
	public String getDocumentId() {
		return documentId;
	}
	
	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		
		BidDocumentDTO that = (BidDocumentDTO) o;
		
		if (bidId != null ? !bidId.equals(that.bidId) : that.bidId != null)
			return false;
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
		final StringBuffer sb = new StringBuffer("BidDocumentDTO{");
		sb.append("bidId='").append(bidId).append('\'');
		sb.append(", documentId='").append(documentId).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
