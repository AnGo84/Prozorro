package ua.prozorro.entity.tenders;

import javax.persistence.*;

/*@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "Contracts_Organizations", joinColumns = { @JoinColumn(name = "contract_id") }, inverseJoinColumns = {
            @JoinColumn(name = "organization_id") })*/
@Entity
@Table(name = "Contracts_Suppliers")
public class ContractSupplierDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_id")
    //@Cascade({org.hibernate.annotations.CascadeType.ALL})
    private ContractDTO contract;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    //@Cascade({org.hibernate.annotations.CascadeType.ALL})
    private OrganizationDTO organization;

    public ContractSupplierDTO() {
    }

    public ContractSupplierDTO(ContractDTO contract, OrganizationDTO organization) {
        this.contract = contract;
        this.organization = organization;
        //this.id = 31 * contract.getId().hashCode() + organization.getId();
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

    public OrganizationDTO getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationDTO organization) {
        this.organization = organization;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof ContractSupplierDTO))
            return false;

        ContractSupplierDTO that = (ContractSupplierDTO) o;

        if (id != that.id)
            return false;
        if (contract != null ? !contract.equals(that.contract) : that.contract != null)
            return false;
        return organization != null ? organization.equals(that.organization) : that.organization == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (contract != null ? contract.hashCode() : 0);
        result = 31 * result + (organization != null ? organization.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ContractSupplierDTO{");
        sb.append("id=").append(id);
        sb.append(", contract=").append(contract);
        sb.append(", organization=").append(organization);
        sb.append('}');
        return sb.toString();
    }
}
