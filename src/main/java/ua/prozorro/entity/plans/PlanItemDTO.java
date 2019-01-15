package ua.prozorro.entity.plans;

/*@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinTable(name = "Tenders_Items", joinColumns = {@JoinColumn(name = "tender_id")}, inverseJoinColumns = {
			@JoinColumn(name = "item_id")})*/

import ua.prozorro.entity.tenders.ItemDTO;
import ua.prozorro.entity.tenders.TenderDTO;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Plans_Items")
public class PlanItemDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id")
    //@Cascade({org.hibernate.annotations.CascadeType.ALL})
    private PlanDTO plan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    //@Cascade({org.hibernate.annotations.CascadeType.ALL})
    private ItemDTO itemDTO;

    public PlanItemDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PlanItemDTO(PlanDTO planDTO, ItemDTO itemDTO) {
        this.plan = planDTO;
        this.itemDTO = itemDTO;
        //this.id = 31 * tender.getId().hashCode() + itemDTO.getId().hashCode();
    }

    public PlanDTO getPlan() {
        return plan;
    }

    public void setPlan(PlanDTO plan) {
        this.plan = plan;
    }

    public ItemDTO getItemDTO() {
        return itemDTO;
    }

    public void setItemDTO(ItemDTO itemDTO) {
        this.itemDTO = itemDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof PlanItemDTO))
            return false;

        PlanItemDTO that = (PlanItemDTO) o;

        if (id != that.id)
            return false;
        if (plan != null ? !plan.equals(that.plan) : that.plan != null)
            return false;
        return itemDTO != null ? itemDTO.equals(that.itemDTO) : that.itemDTO == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (plan != null ? plan.hashCode() : 0);
        result = 31 * result + (itemDTO != null ? itemDTO.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PlanItemDTO{");
        sb.append("id=").append(id);
        sb.append(", plan=").append(plan);
        sb.append(", itemDTO=").append(itemDTO);
        sb.append('}');
        return sb.toString();
    }
}

