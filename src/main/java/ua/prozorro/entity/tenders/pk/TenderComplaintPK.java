package ua.prozorro.entity.tenders.pk;

import java.io.Serializable;

public class TenderComplaintPK implements Serializable {

	protected String tenderId;
	protected String complaintId;


	public TenderComplaintPK() {
	}

	public TenderComplaintPK(String tenderId, String complaintId) {
		this.tenderId = tenderId;
		this.complaintId = complaintId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		TenderComplaintPK that = (TenderComplaintPK) o;

		if (tenderId != null ? !tenderId.equals(that.tenderId) : that.tenderId != null) return false;
		return complaintId != null ? complaintId.equals(that.complaintId) : that.complaintId == null;
	}

	@Override
	public int hashCode() {
		int result = tenderId != null ? tenderId.hashCode() : 0;
		result = 31 * result + (complaintId != null ? complaintId.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("TenderComplaintPK{");
		sb.append("tenderId='").append(tenderId).append('\'');
		sb.append(", complaintId='").append(complaintId).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
