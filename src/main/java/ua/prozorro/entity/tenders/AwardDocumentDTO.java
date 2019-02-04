package ua.prozorro.entity.tenders;

import ua.prozorro.entity.tenders.pk.AwardDocumentPK;

import javax.persistence.*;

@Entity
@Table(name = "Awards_Documents")
@IdClass(AwardDocumentPK.class)
public class AwardDocumentDTO {
	@Id
	@Column(name = "award_id")
	private String awardId;
	
	@Id
	@Column(name = "document_id")
	//@Cascade({org.hibernate.annotations.CascadeType.ALL})
	private String documentId;
	
	public AwardDocumentDTO() {
	}
	
	public AwardDocumentDTO(String awardId, String documentId) {
		this.awardId = awardId;
		this.documentId = documentId;
	}
	
	public String getAwardId() {
		return awardId;
	}
	
	public void setAwardId(String awardId) {
		this.awardId = awardId;
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
		
		AwardDocumentDTO that = (AwardDocumentDTO) o;
		
		if (awardId != null ? !awardId.equals(that.awardId) : that.awardId != null)
			return false;
		return documentId != null ? documentId.equals(that.documentId) : that.documentId == null;
	}
	
	@Override
	public int hashCode() {
		int result = awardId != null ? awardId.hashCode() : 0;
		result = 31 * result + (documentId != null ? documentId.hashCode() : 0);
		return result;
	}
	
	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("AwardDocumentDTO{");
		sb.append("awardId='").append(awardId).append('\'');
		sb.append(", documentId='").append(documentId).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
