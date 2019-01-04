package ua.prozorro.entity.tenders;

import javax.persistence.*;

/*@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "Contracts_Items", joinColumns = { @JoinColumn(name = "contract_id") }, inverseJoinColumns = {
            @JoinColumn(name = "item_id") })*/
@Entity
@Table(name = "Contracts_Items")
public class ContractItemDTO {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_id")
    //@Cascade({org.hibernate.annotations.CascadeType.ALL})
    private ContractDTO contract;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    //@Cascade({org.hibernate.annotations.CascadeType.ALL})
    private ItemDTO item;

    public ContractItemDTO() {
    }

    public ContractItemDTO(ContractDTO contract, ItemDTO item) {
        this.contract = contract;
        this.item = item;
        //this.id = 31 * contract.getId().hashCode() + item.getId().hashCode();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ContractDTO getContract() {
        return contract;
    }

    public void setContract(ContractDTO contract) {
        this.contract = contract;
    }

    public ItemDTO getItem() {
        return item;
    }

    public void setItem(ItemDTO item) {
        this.item = item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof ContractItemDTO))
            return false;

        ContractItemDTO that = (ContractItemDTO) o;

        if (id != that.id)
            return false;
        if (contract != null ? !contract.equals(that.contract) : that.contract != null)
            return false;
        return item != null ? item.equals(that.item) : that.item == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (contract != null ? contract.hashCode() : 0);
        result = 31 * result + (item != null ? item.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ContractItemDTO{");
        sb.append("id=").append(id);
        sb.append(", contract=").append(contract);
        sb.append(", item=").append(item);
        sb.append('}');
        return sb.toString();
    }
}
