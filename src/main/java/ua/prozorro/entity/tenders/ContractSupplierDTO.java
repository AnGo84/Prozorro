package ua.prozorro.entity.tenders;

import ua.prozorro.entity.tenders.pk.ContractSupplierPK;

import javax.persistence.*;

@Entity
@Table(name = "Contracts_Suppliers")
@IdClass(ContractSupplierPK.class)
public class ContractSupplierDTO {
    @Id
    @Column(name = "contract_id")
    private String contractId;

    @Id
    @Column(name = "supplier_id")
    private int supplierId;

    public ContractSupplierDTO() {
    }

    public ContractSupplierDTO(String contractId, int supplierId) {
        this.contractId = contractId;
        this.supplierId = supplierId;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContractSupplierDTO that = (ContractSupplierDTO) o;

        if (supplierId != that.supplierId) return false;
        return contractId != null ? contractId.equals(that.contractId) : that.contractId == null;
    }

    @Override
    public int hashCode() {
        int result = contractId != null ? contractId.hashCode() : 0;
        result = 31 * result + supplierId;
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ContractSupplierDTO{");
        sb.append("contractId='").append(contractId).append('\'');
        sb.append(", supplierId=").append(supplierId);
        sb.append('}');
        return sb.toString();
    }
}
