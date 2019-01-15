package ua.prozorro.entity.plans;

import ua.prozorro.entity.tenders.ClassificationDTO;

import javax.persistence.*;

/*@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinTable(name = "Awards_Documents", joinColumns = {@JoinColumn(name = "award_id")}, inverseJoinColumns = {
			@JoinColumn(name = "document_id")})*/
@Entity
@Table(name = "Plans_AdditionalClassifications")
public class PlanAdditionalClassificationDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id")
    //@Cascade({org.hibernate.annotations.CascadeType.ALL})
    private PlanDTO plan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "сlassification_id")
    //@Cascade({org.hibernate.annotations.CascadeType.ALL})
    private ClassificationDTO classification;

    public PlanAdditionalClassificationDTO() {
    }

    public PlanAdditionalClassificationDTO(PlanDTO plan, ClassificationDTO classificationDTO) {
        this.plan = plan;
        this.classification = classificationDTO;
        //this.id = 31 * award.getId().hashCode() + classificationDTO.getId().hashCode();
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

    public ClassificationDTO getClassification() {
        return classification;
    }

    public void setClassification(ClassificationDTO classification) {
        this.classification = classification;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof PlanAdditionalClassificationDTO))
            return false;

        PlanAdditionalClassificationDTO that = (PlanAdditionalClassificationDTO) o;

        if (id != that.id)
            return false;
        if (plan != null ? !plan.equals(that.plan) : that.plan != null)
            return false;
        return classification != null ? classification.equals(that.classification) : that.classification == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (plan != null ? plan.hashCode() : 0);
        result = 31 * result + (classification != null ? classification.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PlanAdditionalClassificationDTO{");
        sb.append("id=").append(id);
        sb.append(", plan=").append(plan);
        sb.append(", classification=").append(classification);
        sb.append('}');
        return sb.toString();
    }
}
