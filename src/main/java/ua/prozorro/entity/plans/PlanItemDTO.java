package ua.prozorro.entity.plans;

/*@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinTable(name = "Tenders_Items", joinColumns = {@JoinColumn(name = "tender_id")}, inverseJoinColumns = {
			@JoinColumn(name = "item_id")})*/

import ua.prozorro.entity.plans.pk.PlanItemPK;
import ua.prozorro.entity.tenders.ItemDTO;
import ua.prozorro.entity.tenders.TenderDTO;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Plans_Items")
@IdClass(PlanItemPK.class)
public class PlanItemDTO {
    @Id
    @Column(name = "plan_id")
    private String planId;
    @Id
    @Column(name = "item_id")
    private String itemId;

    public PlanItemDTO(String planId, String itemId) {
        this.planId = planId;
        this.itemId = itemId;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlanItemDTO that = (PlanItemDTO) o;

        if (planId != null ? !planId.equals(that.planId) : that.planId != null) return false;
        return itemId != null ? itemId.equals(that.itemId) : that.itemId == null;
    }

    @Override
    public int hashCode() {
        int result = planId != null ? planId.hashCode() : 0;
        result = 31 * result + (itemId != null ? itemId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PlanItemDTO{");
        sb.append("planId='").append(planId).append('\'');
        sb.append(", itemId='").append(itemId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

