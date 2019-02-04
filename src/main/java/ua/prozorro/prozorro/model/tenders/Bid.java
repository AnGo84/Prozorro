package ua.prozorro.prozorro.model.tenders;

import java.util.List;

public class Bid {
	private String id;
	private String status;
	private String date;
	private String participationUrl;
	private Value value;
	
	private boolean selfEligible;
	private boolean selfQualified;
	private List<Document> documents = null;
	private List<Organization> tenderers = null;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getParticipationUrl() {
		return participationUrl;
	}
	
	public void setParticipationUrl(String participationUrl) {
		this.participationUrl = participationUrl;
	}
	
	public Value getValue() {
		return value;
	}
	
	public void setValue(Value value) {
		this.value = value;
	}
	
	public boolean isSelfEligible() {
		return selfEligible;
	}
	
	public void setSelfEligible(boolean selfEligible) {
		this.selfEligible = selfEligible;
	}
	
	public boolean isSelfQualified() {
		return selfQualified;
	}
	
	public void setSelfQualified(boolean selfQualified) {
		this.selfQualified = selfQualified;
	}
	
	public List<Document> getDocuments() {
		return documents;
	}
	
	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}
	
	public List<Organization> getTenderers() {
		return tenderers;
	}
	
	public void setTenderers(List<Organization> tenderers) {
		this.tenderers = tenderers;
	}
	
	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("Bid{");
		sb.append("id='").append(id).append('\'');
		sb.append(", status='").append(status).append('\'');
		sb.append(", date='").append(date).append('\'');
		sb.append(", participationUrl='").append(participationUrl).append('\'');
		sb.append(", value=").append(value);
		sb.append(", selfEligible=").append(selfEligible);
		sb.append(", selfQualified=").append(selfQualified);
		sb.append(", documents=").append(documents);
		sb.append(", tenderers=").append(tenderers);
		sb.append('}');
		return sb.toString();
	}
}
