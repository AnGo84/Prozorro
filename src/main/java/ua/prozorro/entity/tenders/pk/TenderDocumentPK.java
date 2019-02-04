package ua.prozorro.entity.tenders.pk;

import java.io.Serializable;

public class TenderDocumentPK implements Serializable {

	protected String tenderId;
	protected String documentId;


	public TenderDocumentPK() {
	}

	public TenderDocumentPK(String tenderId, String documentId) {
		this.tenderId = tenderId;
		this.documentId = documentId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		TenderDocumentPK that = (TenderDocumentPK) o;

		if (tenderId != null ? !tenderId.equals(that.tenderId) : that.tenderId != null)
			return false;
		return documentId != null ? documentId.equals(that.documentId) : that.documentId == null;
	}

	@Override
	public int hashCode() {
		int result = tenderId != null ? tenderId.hashCode() : 0;
		result = 31 * result + (documentId != null ? documentId.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("TenderDocumentPK{");
		sb.append("tenderId='").append(tenderId).append('\'');
		sb.append(", documentId='").append(documentId).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
