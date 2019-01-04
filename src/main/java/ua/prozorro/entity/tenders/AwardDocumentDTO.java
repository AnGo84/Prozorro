package ua.prozorro.entity.tenders;

import javax.persistence.*;

/*@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinTable(name = "Awards_Documents", joinColumns = {@JoinColumn(name = "award_id")}, inverseJoinColumns = {
			@JoinColumn(name = "document_id")})*/
@Entity
@Table(name = "Awards_Documents")
public class AwardDocumentDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "award_id")
    //@Cascade({org.hibernate.annotations.CascadeType.ALL})
    private AwardDTO award;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_id")
    //@Cascade({org.hibernate.annotations.CascadeType.ALL})
    private DocumentDTO document;

    public AwardDocumentDTO() {
    }

    public AwardDocumentDTO(AwardDTO award, DocumentDTO document) {
        this.award = award;
        this.document = document;
        //this.id = 31 * award.getId().hashCode() + document.getId().hashCode();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public AwardDTO getAward() {
        return award;
    }

    public void setAward(AwardDTO award) {
        this.award = award;
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
        if (!(o instanceof AwardDocumentDTO))
            return false;

        AwardDocumentDTO that = (AwardDocumentDTO) o;

        if (id != that.id)
            return false;
        if (award != null ? !award.equals(that.award) : that.award != null)
            return false;
        return document != null ? document.equals(that.document) : that.document == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (award != null ? award.hashCode() : 0);
        result = 31 * result + (document != null ? document.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AwardDocumentDTO{");
        sb.append("id=").append(id);
        sb.append(", award=").append(award);
        sb.append(", document=").append(document);
        sb.append('}');
        return sb.toString();
    }
}
