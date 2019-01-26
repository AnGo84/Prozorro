package ua.prozorro.entity.tenders;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cancellations")
public class CancellationDTO {
    @Id
    //@GeneratedValue(strategy= GenerationType.AUTO)
    @Column
    private String id;

    @Column
    private String status;
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "Cancellations_Documents", joinColumns = {@JoinColumn(name = "cancellation_id")},
            inverseJoinColumns = {@JoinColumn(name = "document_id")})
    private List<DocumentDTO> documents = null;
    //@Column(length = 4000)
    @Lob
    @Column
    private String reason;
    @Column
    private String reasonType;
    @Column(name = "arcdate")
    private String date;
    @Column
    private String cancellationOf;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DocumentDTO> getDocuments() {
        return documents;
    }

    public void setDocuments(List<DocumentDTO> documents) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        CancellationDTO that = (CancellationDTO) o;

        if (id != null ? !id.equals(that.id) : that.id != null)
            return false;
        if (status != null ? !status.equals(that.status) : that.status != null)
            return false;
        if (documents != null ? !documents.equals(that.documents) : that.documents != null)
            return false;
        if (reason != null ? !reason.equals(that.reason) : that.reason != null)
            return false;
        if (reasonType != null ? !reasonType.equals(that.reasonType) : that.reasonType != null)
            return false;
        if (date != null ? !date.equals(that.date) : that.date != null)
            return false;
        return cancellationOf != null ? cancellationOf.equals(that.cancellationOf) : that.cancellationOf == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (documents != null ? documents.hashCode() : 0);
        result = 31 * result + (reason != null ? reason.hashCode() : 0);
        result = 31 * result + (reasonType != null ? reasonType.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (cancellationOf != null ? cancellationOf.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CancellationDTO{");
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
