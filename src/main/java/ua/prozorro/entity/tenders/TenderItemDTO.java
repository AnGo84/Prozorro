package ua.prozorro.entity.tenders;

/*@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinTable(name = "Tenders_Items", joinColumns = {@JoinColumn(name = "tender_id")}, inverseJoinColumns = {
			@JoinColumn(name = "item_id")})*/

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Tenders_Items")
public class TenderItemDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tender_id")
    //@Cascade({org.hibernate.annotations.CascadeType.ALL})
    private TenderDTO tender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    //@Cascade({org.hibernate.annotations.CascadeType.ALL})
    private ItemDTO itemDTO;

    public TenderItemDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TenderItemDTO(TenderDTO tender, ItemDTO itemDTO) {
        this.tender = tender;
        this.itemDTO = itemDTO;
        //this.id = 31 * tender.getId().hashCode() + itemDTO.getId().hashCode();
    }

    public TenderDTO getTender() {
        return tender;
    }

    public void setTender(TenderDTO tender) {
        this.tender = tender;
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
        if (!(o instanceof TenderItemDTO))
            return false;
        TenderItemDTO that = (TenderItemDTO) o;
        return id == that.id && Objects.equals(tender, that.tender) && Objects.equals(itemDTO, that.itemDTO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tender, itemDTO);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TenderItemDTO{");
        sb.append("id=").append(id);
        sb.append(", tender=").append(tender);
        sb.append(", itemDTO=").append(itemDTO);
        sb.append('}');
        return sb.toString();
    }
}

