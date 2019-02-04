package ua.prozorro.entity.tenders.pk;

import java.io.Serializable;

public class ContractDocumentPK implements Serializable {
	protected String contractId;
	protected String documentId;

	public ContractDocumentPK() {
	}

	public ContractDocumentPK(String contractId, String documentId) {
		this.contractId = contractId;
		this.documentId = documentId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		ContractDocumentPK that = (ContractDocumentPK) o;

		if (contractId != null ? !contractId.equals(that.contractId) : that.contractId != null)
			return false;
		return documentId != null ? documentId.equals(that.documentId) : that.documentId == null;
	}

	@Override
	public int hashCode() {
		int result = contractId != null ? contractId.hashCode() : 0;
		result = 31 * result + (documentId != null ? documentId.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("ContractDocumentPK{");
		sb.append("contractId='").append(contractId).append('\'');
		sb.append(", documentId='").append(documentId).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
