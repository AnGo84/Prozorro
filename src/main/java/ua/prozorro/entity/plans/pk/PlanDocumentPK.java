package ua.prozorro.entity.plans.pk;

import java.io.Serializable;

public class PlanDocumentPK implements Serializable {

	protected String planId;
	protected String documentId;


	public PlanDocumentPK() {
	}

	public PlanDocumentPK(String planId, String documentId) {
		this.planId = planId;
		this.documentId = documentId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		PlanDocumentPK that = (PlanDocumentPK) o;

		if (planId != null ? !planId.equals(that.planId) : that.planId != null)
			return false;
		return documentId != null ? documentId.equals(that.documentId) : that.documentId == null;
	}

	@Override
	public int hashCode() {
		int result = planId != null ? planId.hashCode() : 0;
		result = 31 * result + (documentId != null ? documentId.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("PlanDocumentPK{");
		sb.append("planId='").append(planId).append('\'');
		sb.append(", documentId='").append(documentId).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
