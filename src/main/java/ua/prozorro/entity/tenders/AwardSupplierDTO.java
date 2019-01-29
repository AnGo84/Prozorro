package ua.prozorro.entity.tenders;

import ua.prozorro.entity.tenders.pk.AwardSupplierPK;

import javax.persistence.*;

@Entity
@Table(name = "Awards_Suppliers")
@IdClass(AwardSupplierPK.class)
public class AwardSupplierDTO {

    @Id
    @Column(name = "award_id")
    private String awardId;

    @Id
    @Column(name = "supplier_id")
    private int supplierId;

    public AwardSupplierDTO() {
    }

    public AwardSupplierDTO(String awardId, int supplierId) {
        this.awardId = awardId;
        this.supplierId = supplierId;
    }

    public String getAwardId() {
        return awardId;
    }

    public void setAwardId(String awardId) {
        this.awardId = awardId;
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

        AwardSupplierDTO that = (AwardSupplierDTO) o;

        if (supplierId != that.supplierId) return false;
        return awardId != null ? awardId.equals(that.awardId) : that.awardId == null;
    }

    @Override
    public int hashCode() {
        int result = awardId != null ? awardId.hashCode() : 0;
        result = 31 * result + supplierId;
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("AwardSupplierDTO{");
        sb.append("awardId='").append(awardId).append('\'');
        sb.append(", supplierId=").append(supplierId);
        sb.append('}');
        return sb.toString();
    }
}
