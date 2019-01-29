package ua.prozorro.entity.plans;

import ua.prozorro.entity.plans.pk.PlanAdditionalClassificationPK;
import ua.prozorro.entity.tenders.ClassificationDTO;

import javax.persistence.*;

@Entity
@Table(name = "Plans_AddClassifications")
@IdClass(PlanAdditionalClassificationPK.class)
public class PlanAdditionalClassificationDTO {
    @Id
    @Column(name = "plan_id")
    private String planId;
    @Id
    @Column(name = "classification_id")
    private String classificationId;

    public PlanAdditionalClassificationDTO(String planId, String classificationId) {
        this.planId = planId;
        this.classificationId = classificationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlanAdditionalClassificationDTO that = (PlanAdditionalClassificationDTO) o;

        if (planId != null ? !planId.equals(that.planId) : that.planId != null) return false;
        return classificationId != null ? classificationId.equals(that.classificationId) : that.classificationId == null;
    }

    @Override
    public int hashCode() {
        int result = planId != null ? planId.hashCode() : 0;
        result = 31 * result + (classificationId != null ? classificationId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PlanAdditionalClassificationDTO{");
        sb.append("planId='").append(planId).append('\'');
        sb.append(", classificationId='").append(classificationId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
