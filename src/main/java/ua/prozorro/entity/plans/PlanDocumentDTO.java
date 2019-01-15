package ua.prozorro.entity.plans;

import ua.prozorro.entity.tenders.AwardDTO;
import ua.prozorro.entity.tenders.DocumentDTO;

import javax.persistence.*;

/*@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinTable(name = "Awards_Documents", joinColumns = {@JoinColumn(name = "award_id")}, inverseJoinColumns = {
			@JoinColumn(name = "document_id")})*/
@Entity
@Table(name = "Plans_Documents")
public class PlanDocumentDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id")
    //@Cascade({org.hibernate.annotations.CascadeType.ALL})
    private PlanDTO plan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_id")
    //@Cascade({org.hibernate.annotations.CascadeType.ALL})
    private DocumentDTO document;

    public PlanDocumentDTO() {
    }

    public PlanDocumentDTO(PlanDTO plan, DocumentDTO document) {
        this.plan = plan;
        this.document = document;
        //this.id = 31 * award.getId().hashCode() + document.getId().hashCode();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PlanDTO getPlan() {
        return plan;
    }

    public void setPlan(PlanDTO plan) {
        this.plan = plan;
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
        if (!(o instanceof PlanDocumentDTO))
            return false;

        PlanDocumentDTO that = (PlanDocumentDTO) o;

        if (id != that.id)
            return false;
        if (plan != null ? !plan.equals(that.plan) : that.plan != null)
            return false;
        return document != null ? document.equals(that.document) : that.document == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (plan != null ? plan.hashCode() : 0);
        result = 31 * result + (document != null ? document.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PlanDocumentDTO{");
        sb.append("id=").append(id);
        sb.append(", plan=").append(plan);
        sb.append(", document=").append(document);
        sb.append('}');
        return sb.toString();
    }
}
