package ua.prozorro.entity.tenders;

import ua.prozorro.entity.tenders.pk.TenderDocumentPK;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Tenders_Documents")
@IdClass(TenderDocumentPK.class)
public class TenderDocumentDTO {

    @Id
    @Column(name = "tender_id")
    private String tenderId;

    @Id
    @Column(name = "document_id")
    private String documentId;

    public TenderDocumentDTO() {
    }

    public TenderDocumentDTO(String tenderId, String documentId) {
        this.tenderId = tenderId;
        this.documentId = documentId;
    }

    public String getTenderId() {
        return tenderId;
    }

    public void setTenderId(String tenderId) {
        this.tenderId = tenderId;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TenderDocumentDTO that = (TenderDocumentDTO) o;

        if (tenderId != null ? !tenderId.equals(that.tenderId) : that.tenderId != null) return false;
        return documentId != null ? documentId.equals(that.documentId) : that.documentId == null;
    }

    @Override
    public int hashCode() {
        int result = tenderId != null ? tenderId.hashCode() : 0;
        result = 31 * result + (documentId != null ? documentId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("TenderDocumentDTO{");
        sb.append("tenderId='").append(tenderId).append('\'');
        sb.append(", documentId='").append(documentId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
