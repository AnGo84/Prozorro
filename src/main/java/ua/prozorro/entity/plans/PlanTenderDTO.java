package ua.prozorro.entity.plans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Plan_Tenders")
public class PlanTenderDTO {
	@Id
	@Column(nullable = false)
	private String id;

	@Column
	private String procurementMethod;
	@Column
	private String procurementMethodType;

	@Column
	private String tenderPeriodStartDate;
	@Column
	private String tenderPeriodClarificationsUntil;
	@Column
	private String tenderPeriodEndDate;
	@Column
	private String tenderPeriodInvalidationDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProcurementMethod() {
		return procurementMethod;
	}

	public void setProcurementMethod(String procurementMethod) {
		this.procurementMethod = procurementMethod;
	}

	public String getProcurementMethodType() {
		return procurementMethodType;
	}

	public void setProcurementMethodType(String procurementMethodType) {
		this.procurementMethodType = procurementMethodType;
	}

	public String getTenderPeriodStartDate() {
		return tenderPeriodStartDate;
	}

	public void setTenderPeriodStartDate(String tenderPeriodStartDate) {
		this.tenderPeriodStartDate = tenderPeriodStartDate;
	}

	public String getTenderPeriodClarificationsUntil() {
		return tenderPeriodClarificationsUntil;
	}

	public void setTenderPeriodClarificationsUntil(String tenderPeriodClarificationsUntil) {
		this.tenderPeriodClarificationsUntil = tenderPeriodClarificationsUntil;
	}

	public String getTenderPeriodEndDate() {
		return tenderPeriodEndDate;
	}

	public void setTenderPeriodEndDate(String tenderPeriodEndDate) {
		this.tenderPeriodEndDate = tenderPeriodEndDate;
	}

	public String getTenderPeriodInvalidationDate() {
		return tenderPeriodInvalidationDate;
	}

	public void setTenderPeriodInvalidationDate(String tenderPeriodInvalidationDate) {
		this.tenderPeriodInvalidationDate = tenderPeriodInvalidationDate;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		PlanTenderDTO that = (PlanTenderDTO) o;

		if (id != null ? !id.equals(that.id) : that.id != null) return false;
		if (procurementMethod != null ? !procurementMethod.equals(that.procurementMethod) : that.procurementMethod != null)
			return false;
		if (procurementMethodType != null ? !procurementMethodType.equals(that.procurementMethodType) : that.procurementMethodType != null)
			return false;
		if (tenderPeriodStartDate != null ? !tenderPeriodStartDate.equals(that.tenderPeriodStartDate) : that.tenderPeriodStartDate != null)
			return false;
		if (tenderPeriodClarificationsUntil != null ? !tenderPeriodClarificationsUntil.equals(that.tenderPeriodClarificationsUntil) : that.tenderPeriodClarificationsUntil != null)
			return false;
		if (tenderPeriodEndDate != null ? !tenderPeriodEndDate.equals(that.tenderPeriodEndDate) : that.tenderPeriodEndDate != null)
			return false;
		return tenderPeriodInvalidationDate != null ? tenderPeriodInvalidationDate.equals(that.tenderPeriodInvalidationDate) : that.tenderPeriodInvalidationDate == null;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (procurementMethod != null ? procurementMethod.hashCode() : 0);
		result = 31 * result + (procurementMethodType != null ? procurementMethodType.hashCode() : 0);
		result = 31 * result + (tenderPeriodStartDate != null ? tenderPeriodStartDate.hashCode() : 0);
		result = 31 * result + (tenderPeriodClarificationsUntil != null ? tenderPeriodClarificationsUntil.hashCode() : 0);
		result = 31 * result + (tenderPeriodEndDate != null ? tenderPeriodEndDate.hashCode() : 0);
		result = 31 * result + (tenderPeriodInvalidationDate != null ? tenderPeriodInvalidationDate.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("PlanTenderDTO{");
		sb.append("id='").append(id).append('\'');
		sb.append(", procurementMethod='").append(procurementMethod).append('\'');
		sb.append(", procurementMethodType='").append(procurementMethodType).append('\'');
		sb.append(", tenderPeriodStartDate='").append(tenderPeriodStartDate).append('\'');
		sb.append(", tenderPeriodClarificationsUntil='").append(tenderPeriodClarificationsUntil).append('\'');
		sb.append(", tenderPeriodEndDate='").append(tenderPeriodEndDate).append('\'');
		sb.append(", tenderPeriodInvalidationDate='").append(tenderPeriodInvalidationDate).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
