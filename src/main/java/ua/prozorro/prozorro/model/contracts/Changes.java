package ua.prozorro.prozorro.model.contracts;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Changes {
	@SerializedName("status")
	@Expose
	private String status;
	@SerializedName("rationaleTypes")
	@Expose
	private List<String> rationaleTypes = null;
	@SerializedName("contractNumber")
	@Expose
	private String contractNumber;
	@SerializedName("dateSigned")
	@Expose
	private String dateSigned;
	@SerializedName("rationale")
	@Expose
	private String rationale;
	@SerializedName("date")
	@Expose
	private String date;
	@SerializedName("id")
	@Expose
	private String id;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<String> getRationaleTypes() {
		return rationaleTypes;
	}

	public void setRationaleTypes(List<String> rationaleTypes) {
		this.rationaleTypes = rationaleTypes;
	}

	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public String getDateSigned() {
		return dateSigned;
	}

	public void setDateSigned(String dateSigned) {
		this.dateSigned = dateSigned;
	}

	public String getRationale() {
		return rationale;
	}

	public void setRationale(String rationale) {
		this.rationale = rationale;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Changes changes = (Changes) o;

		if (status != null ? !status.equals(changes.status) : changes.status != null) return false;
		if (rationaleTypes != null ? !rationaleTypes.equals(changes.rationaleTypes) : changes.rationaleTypes != null)
			return false;
		if (contractNumber != null ? !contractNumber.equals(changes.contractNumber) : changes.contractNumber != null)
			return false;
		if (dateSigned != null ? !dateSigned.equals(changes.dateSigned) : changes.dateSigned != null) return false;
		if (rationale != null ? !rationale.equals(changes.rationale) : changes.rationale != null) return false;
		if (date != null ? !date.equals(changes.date) : changes.date != null) return false;
		return id != null ? id.equals(changes.id) : changes.id == null;
	}

	@Override
	public int hashCode() {
		int result = status != null ? status.hashCode() : 0;
		result = 31 * result + (rationaleTypes != null ? rationaleTypes.hashCode() : 0);
		result = 31 * result + (contractNumber != null ? contractNumber.hashCode() : 0);
		result = 31 * result + (dateSigned != null ? dateSigned.hashCode() : 0);
		result = 31 * result + (rationale != null ? rationale.hashCode() : 0);
		result = 31 * result + (date != null ? date.hashCode() : 0);
		result = 31 * result + (id != null ? id.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("Changes{");
		sb.append("status='").append(status).append('\'');
		sb.append(", rationaleTypes=").append(rationaleTypes);
		sb.append(", contractNumber='").append(contractNumber).append('\'');
		sb.append(", dateSigned='").append(dateSigned).append('\'');
		sb.append(", rationale='").append(rationale).append('\'');
		sb.append(", date='").append(date).append('\'');
		sb.append(", id='").append(id).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
