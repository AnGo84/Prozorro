package ua.prozorro.entity.tenders.pk;

import java.io.Serializable;

public class AwardDocumentPK implements Serializable {

	protected String awardId;
	protected String documentId;


	public AwardDocumentPK() {
	}

	public AwardDocumentPK(String awardId, String documentId) {
		this.awardId = awardId;
		this.documentId = documentId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		AwardDocumentPK that = (AwardDocumentPK) o;

		if (awardId != null ? !awardId.equals(that.awardId) : that.awardId != null) return false;
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
		final StringBuffer sb = new StringBuffer("AwardDocumentPK{");
		sb.append("awardId='").append(awardId).append('\'');
		sb.append(", documentId='").append(documentId).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
