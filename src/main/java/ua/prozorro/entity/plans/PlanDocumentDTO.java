package ua.prozorro.entity.plans;

import ua.prozorro.entity.plans.pk.PlanDocumentPK;
import ua.prozorro.entity.tenders.AwardDTO;
import ua.prozorro.entity.tenders.DocumentDTO;

import javax.persistence.*;

@Entity
@Table(name = "Plans_Documents")
@IdClass(PlanDocumentPK.class)
public class PlanDocumentDTO {
    @Id
    @Column(name = "plan_id")
    private String planId;
    @Id
    @Column(name = "document_id")
    private String documentId;

    public PlanDocumentDTO() {
    }

    public PlanDocumentDTO(String planId, String documentId) {
        this.planId = planId;
        this.documentId = documentId;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
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

        PlanDocumentDTO that = (PlanDocumentDTO) o;

        if (planId != null ? !planId.equals(that.planId) : that.planId != null) return false;
        return documentId != null ? documentId.equals(that.documentId) : that.documentId == null;
    }

    @Override
    public int hashCode() {
        int result = planId != null ? planId.hashCode() : 0;
        result = 31 * result + (documentId != null ? documentId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PlanDocumentDTO{");
        sb.append("planId='").append(planId).append('\'');
        sb.append(", documentId='").append(documentId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
