package ua.prozorro.entity.tenders;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Complaints")
public class ComplaintDTO {
    @Id
    //@GeneratedValue(strategy= GenerationType.AUTO)
    @Column
    private String id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private OrganizationDTO author;

    @Column(length = 4000)
    private String title;
    //@Column(length = 8000)
    @Lob
    @Column
    private String description;
    @Column
    private String date;
    @Column
    private String dateSubmitted;
    @Column
    private String dateAnswered;
    @Column
    private String dateEscalated;
    @Column
    private String dateDecision;
    @Column
    private String dateCanceled;
    @Column
    private String status;
    @Column
    private String type;
    //@Column(length = 4000)
    @Lob
    @Column
    private String resolution;
    @Column
    private String resolutionType;
    @Column(length = 1)
    private boolean satisfied;
    //@Column(length = 4000)
    @Lob
    @Column
    private String decision;
    //@Column(length = 4000)
    @Lob
    @Column
    private String cancellationReason;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "Complaints_Documents", joinColumns = {@JoinColumn(name = "complaint_id")},
            inverseJoinColumns = {@JoinColumn(name = "document_id")})
    private List<DocumentDTO> documents;
    @Column
    private String relatedLot;
    @Lob
    @Column
    private String tendererAction;
    @Column
    private String tendererActionDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public OrganizationDTO getAuthor() {
        return author;
    }

    public void setAuthor(OrganizationDTO author) {
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

    public List<DocumentDTO> getDocuments() {
        return documents;
    }

    public void setDocuments(List<DocumentDTO> documents) {
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
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        ComplaintDTO that = (ComplaintDTO) o;

        if (satisfied != that.satisfied)
            return false;
        if (id != null ? !id.equals(that.id) : that.id != null)
            return false;
        if (author != null ? !author.equals(that.author) : that.author != null)
            return false;
        if (title != null ? !title.equals(that.title) : that.title != null)
            return false;
        if (description != null ? !description.equals(that.description) : that.description != null)
            return false;
        if (date != null ? !date.equals(that.date) : that.date != null)
            return false;
        if (dateSubmitted != null ? !dateSubmitted.equals(that.dateSubmitted) : that.dateSubmitted != null)
            return false;
        if (dateAnswered != null ? !dateAnswered.equals(that.dateAnswered) : that.dateAnswered != null)
            return false;
        if (dateEscalated != null ? !dateEscalated.equals(that.dateEscalated) : that.dateEscalated != null)
            return false;
        if (dateDecision != null ? !dateDecision.equals(that.dateDecision) : that.dateDecision != null)
            return false;
        if (dateCanceled != null ? !dateCanceled.equals(that.dateCanceled) : that.dateCanceled != null)
            return false;
        if (status != null ? !status.equals(that.status) : that.status != null)
            return false;
        if (type != null ? !type.equals(that.type) : that.type != null)
            return false;
        if (resolution != null ? !resolution.equals(that.resolution) : that.resolution != null)
            return false;
        if (resolutionType != null ? !resolutionType.equals(that.resolutionType) : that.resolutionType != null)
            return false;
        if (decision != null ? !decision.equals(that.decision) : that.decision != null)
            return false;
        if (cancellationReason != null ? !cancellationReason.equals(that.cancellationReason) :
                that.cancellationReason != null)
            return false;
        if (documents != null ? !documents.equals(that.documents) : that.documents != null)
            return false;
        if (relatedLot != null ? !relatedLot.equals(that.relatedLot) : that.relatedLot != null)
            return false;
        if (tendererAction != null ? !tendererAction.equals(that.tendererAction) : that.tendererAction != null)
            return false;
        return tendererActionDate != null ? tendererActionDate.equals(that.tendererActionDate) :
                that.tendererActionDate == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (dateSubmitted != null ? dateSubmitted.hashCode() : 0);
        result = 31 * result + (dateAnswered != null ? dateAnswered.hashCode() : 0);
        result = 31 * result + (dateEscalated != null ? dateEscalated.hashCode() : 0);
        result = 31 * result + (dateDecision != null ? dateDecision.hashCode() : 0);
        result = 31 * result + (dateCanceled != null ? dateCanceled.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (resolution != null ? resolution.hashCode() : 0);
        result = 31 * result + (resolutionType != null ? resolutionType.hashCode() : 0);
        result = 31 * result + (satisfied ? 1 : 0);
        result = 31 * result + (decision != null ? decision.hashCode() : 0);
        result = 31 * result + (cancellationReason != null ? cancellationReason.hashCode() : 0);
        result = 31 * result + (documents != null ? documents.hashCode() : 0);
        result = 31 * result + (relatedLot != null ? relatedLot.hashCode() : 0);
        result = 31 * result + (tendererAction != null ? tendererAction.hashCode() : 0);
        result = 31 * result + (tendererActionDate != null ? tendererActionDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ComplaintDTO{");
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
