package ua.prozorro.entity.tenders;

import ua.prozorro.entity.tenders.pk.TenderComplaintPK;

import javax.persistence.*;

@Entity
@Table(name = "Tenders_Complaints")
@IdClass(TenderComplaintPK.class)
public class TenderComplaintDTO {
	@Id
	@Column(name = "tender_id")
	private String tenderId;
	
	@Id
	@Column(name = "complaint_id")
	private String complaintId;
	
	public TenderComplaintDTO() {
	}
	
	public TenderComplaintDTO(String tenderId, String complaintId) {
		this.tenderId = tenderId;
		this.complaintId = complaintId;
	}
	
	public String getTenderId() {
		return tenderId;
	}
	
	public void setTenderId(String tenderId) {
		this.tenderId = tenderId;
	}
	
	public String getComplaintId() {
		return complaintId;
	}
	
	public void setComplaintId(String complaintId) {
		this.complaintId = complaintId;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		
		TenderComplaintDTO that = (TenderComplaintDTO) o;
		
		if (tenderId != null ? !tenderId.equals(that.tenderId) : that.tenderId != null)
			return false;
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
		final StringBuffer sb = new StringBuffer("TenderComplaintDTO{");
		sb.append("tenderId='").append(tenderId).append('\'');
		sb.append(", complaintId='").append(complaintId).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
