package ua.prozorro.model.tenders;

import java.util.List;

public class Complaint {
    private String id;
    private Organization author;
    private String title;
    private String description;
    private String date;
    private String dateSubmitted;
    private String dateAnswered;
    private String dateEscalated;
    private String dateDecision;
    private String dateCanceled;
    private String status;
    private String type;
    private String resolution;
    private String resolutionType;
    private boolean satisfied;
    private String decision;
    private String cancellationReason;
    private List<Document> documents;
    private String relatedLot;
    private String tendererAction;
    private String tendererActionDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Organization getAuthor() {
        return author;
    }

    public void setAuthor(Organization author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(String dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }

    public String getDateAnswered() {
        return dateAnswered;
    }

    public void setDateAnswered(String dateAnswered) {
        this.dateAnswered = dateAnswered;
    }

    public String getDateEscalated() {
        return dateEscalated;
    }

    public void setDateEscalated(String dateEscalated) {
        this.dateEscalated = dateEscalated;
    }

    public String getDateDecision() {
        return dateDecision;
    }

    public void setDateDecision(String dateDecision) {
        this.dateDecision = dateDecision;
    }

    public String getDateCanceled() {
        return dateCanceled;
    }

    public void setDateCanceled(String dateCanceled) {
        this.dateCanceled = dateCanceled;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getResolutionType() {
        return resolutionType;
    }

    public void setResolutionType(String resolutionType) {
        this.resolutionType = resolutionType;
    }

    public boolean isSatisfied() {
        return satisfied;
    }

    public void setSatisfied(boolean satisfied) {
        this.satisfied = satisfied;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public String getCancellationReason() {
        return cancellationReason;
    }

    public void setCancellationReason(String cancellationReason) {
        this.cancellationReason = cancellationReason;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public String getRelatedLot() {
        return relatedLot;
    }

    public void setRelatedLot(String relatedLot) {
        this.relatedLot = relatedLot;
    }

    public String getTendererAction() {
        return tendererAction;
    }

    public void setTendererAction(String tendererAction) {
        this.tendererAction = tendererAction;
    }

    public String getTendererActionDate() {
        return tendererActionDate;
    }

    public void setTendererActionDate(String tendererActionDate) {
        this.tendererActionDate = tendererActionDate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Complaint{");
        sb.append("id='").append(id).append('\'');
        sb.append(", author=").append(author);
        sb.append(", title='").append(title).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", date='").append(date).append('\'');
        sb.append(", dateSubmitted='").append(dateSubmitted).append('\'');
        sb.append(", dateAnswered='").append(dateAnswered).append('\'');
        sb.append(", dateEscalated='").append(dateEscalated).append('\'');
        sb.append(", dateDecision='").append(dateDecision).append('\'');
        sb.append(", dateCanceled='").append(dateCanceled).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append(", resolution='").append(resolution).append('\'');
        sb.append(", resolutionType='").append(resolutionType).append('\'');
        sb.append(", satisfied=").append(satisfied);
        sb.append(", decision='").append(decision).append('\'');
        sb.append(", cancellationReason='").append(cancellationReason).append('\'');
        sb.append(", documents=").append(documents);
        sb.append(", relatedLot=").append(relatedLot);
        sb.append(", tendererAction='").append(tendererAction).append('\'');
        sb.append(", tendererActionDate='").append(tendererActionDate).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
