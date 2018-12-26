package ua.prozorro.entity.tenders;

/*@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
@JoinTable(name = "Tenders_Documents", joinColumns = {@JoinColumn(name = "tender_id")}, inverseJoinColumns = {
        @JoinColumn(name = "document_id")})*/

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Tenders_Documents")
public class TenderDocumentDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tender_id")
    //@Cascade({org.hibernate.annotations.CascadeType.ALL})
    private TenderDTO tender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_id")
    //@Cascade({org.hibernate.annotations.CascadeType.ALL})
    private DocumentDTO document;

    public TenderDocumentDTO() {
    }

    public TenderDocumentDTO(TenderDTO tender, DocumentDTO document) {
        this.tender = tender;
        this.document = document;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TenderDTO getTender() {
        return tender;
    }

    public void setTender(TenderDTO tender) {
        this.tender = tender;
    }

    public DocumentDTO getDocument() {
        return document;
    }

    public void setDocument(DocumentDTO document) {
        this.document = document;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof TenderDocumentDTO))
            return false;
        TenderDocumentDTO that = (TenderDocumentDTO) o;
        return id == that.id && Objects.equals(tender, that.tender) && Objects.equals(document, that.document);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tender, document);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TenderDocumentDTO{");
        sb.append("id=").append(id);
        sb.append(", tender=").append(tender);
        sb.append(", document=").append(document);
        sb.append('}');
        return sb.toString();
    }
}
