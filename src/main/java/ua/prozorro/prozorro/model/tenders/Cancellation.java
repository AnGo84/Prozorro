package ua.prozorro.prozorro.model.tenders;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

public class Cancellation implements Serializable {
	private final static long serialVersionUID = 567810043027589757L;

	private String status;

	private List<Document> documents = null;

	private String reason;

	private String reasonType;

	private String date;

	private String cancellationOf;

	private String id;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getReasonType() {
		return reasonType;
	}

	public void setReasonType(String reasonType) {
		this.reasonType = reasonType;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCancellationOf() {
		return cancellationOf;
	}

	public void setCancellationOf(String cancellationOf) {
		this.cancellationOf = cancellationOf;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("Cancellation{");
		sb.append("status='").append(status).append('\'');
		sb.append(", documents=").append(documents);
		sb.append(", reason='").append(reason).append('\'');
		sb.append(", reasonType='").append(reasonType).append('\'');
		sb.append(", date='").append(date).append('\'');
		sb.append(", cancellationOf='").append(cancellationOf).append('\'');
		sb.append(", id='").append(id).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
