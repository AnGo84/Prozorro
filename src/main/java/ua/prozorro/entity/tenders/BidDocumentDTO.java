package ua.prozorro.entity.tenders;

/*@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "Bids_Documents", joinColumns = {@JoinColumn(name = "bid_id")},
            inverseJoinColumns = {@JoinColumn(name = "document_id")})*/

import javax.persistence.*;

@Entity
@Table(name = "Bids_Documents")
public class BidDocumentDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bid_id")
    //@Cascade({org.hibernate.annotations.CascadeType.ALL})
    private BidDTO bid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_id")
    //@Cascade({org.hibernate.annotations.CascadeType.ALL})
    private DocumentDTO document;

    public BidDocumentDTO() {
    }

    public BidDocumentDTO(BidDTO bid, DocumentDTO document) {
        this.bid = bid;
        this.document = document;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BidDTO getBid() {
        return bid;
    }

    public void setBid(BidDTO bid) {
        this.bid = bid;
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
        if (!(o instanceof BidDocumentDTO))
            return false;

        BidDocumentDTO that = (BidDocumentDTO) o;

        if (id != that.id)
            return false;
        if (bid != null ? !bid.equals(that.bid) : that.bid != null)
            return false;
        return document != null ? document.equals(that.document) : that.document == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (bid != null ? bid.hashCode() : 0);
        result = 31 * result + (document != null ? document.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BidDocumentDTO{");
        sb.append("id=").append(id);
        sb.append(", bid=").append(bid);
        sb.append(", document=").append(document);
        sb.append('}');
        return sb.toString();
    }
}
