package ua.prozorro.entity.tenders;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Contracts")
public class ContractDTO {
    @Id
    //@GeneratedValue(strategy= GenerationType.AUTO)
    @Column
    private String id;

    @Column
    private String status;

    /*@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "Contracts_Items", joinColumns = { @JoinColumn(name = "contract_id") }, inverseJoinColumns = {
            @JoinColumn(name = "item_id") })*/
    @Transient
    private List<ItemDTO> items = null;

    /*@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "Contracts_Organizations", joinColumns = { @JoinColumn(name = "contract_id") }, inverseJoinColumns = {
            @JoinColumn(name = "organization_id") })*/
    @Transient
    private List<OrganizationDTO> suppliers = null;

    @Column
    private String currency;
    @Column
    private String amount;
    @Column
    private String valueAddedTaxIncluded;

    @Column(name = "award_id")
    private String awardID;

    @Column(name = "contract_id")
    private String contractID;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ItemDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemDTO> items) {
        this.items = items;
    }

    public List<OrganizationDTO> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<OrganizationDTO> suppliers) {
        this.suppliers = suppliers;
    }

    public String getAwardID() {
        return awardID;
    }

    public void setAwardID(String awardID) {
        this.awardID = awardID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContractID() {
        return contractID;
    }

    public void setContractID(String contractID) {
        this.contractID = contractID;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getValueAddedTaxIncluded() {
        return valueAddedTaxIncluded;
    }

    public void setValueAddedTaxIncluded(String valueAddedTaxIncluded) {
        this.valueAddedTaxIncluded = valueAddedTaxIncluded;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        ContractDTO that = (ContractDTO) o;

        if (id != null ? !id.equals(that.id) : that.id != null)
            return false;
        if (status != null ? !status.equals(that.status) : that.status != null)
            return false;
        if (items != null ? !items.equals(that.items) : that.items != null)
            return false;
        if (suppliers != null ? !suppliers.equals(that.suppliers) : that.suppliers != null)
            return false;
        if (currency != null ? !currency.equals(that.currency) : that.currency != null)
            return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null)
            return false;
        if (valueAddedTaxIncluded != null ? !valueAddedTaxIncluded.equals(that.valueAddedTaxIncluded) :
                that.valueAddedTaxIncluded != null)
            return false;
        if (awardID != null ? !awardID.equals(that.awardID) : that.awardID != null)
            return false;
        return contractID != null ? contractID.equals(that.contractID) : that.contractID == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (items != null ? items.hashCode() : 0);
        result = 31 * result + (suppliers != null ? suppliers.hashCode() : 0);
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (valueAddedTaxIncluded != null ? valueAddedTaxIncluded.hashCode() : 0);
        result = 31 * result + (awardID != null ? awardID.hashCode() : 0);
        result = 31 * result + (contractID != null ? contractID.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ContractDTO{");
        sb.append("id='").append(id).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append(", items=").append(items);
        sb.append(", suppliers=").append(suppliers);
        sb.append(", currency='").append(currency).append('\'');
        sb.append(", amount='").append(amount).append('\'');
        sb.append(", valueAddedTaxIncluded='").append(valueAddedTaxIncluded).append('\'');
        sb.append(", awardID='").append(awardID).append('\'');
        sb.append(", contractID='").append(contractID).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
