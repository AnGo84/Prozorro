package ua.prozorro.entity.tenders.pk;

import java.io.Serializable;

public class AwardSupplierPK implements Serializable {

	protected String awardId;
	protected int supplierId;


	public AwardSupplierPK() {
	}

	public AwardSupplierPK(String awardId, int supplierId) {
		this.awardId = awardId;
		this.supplierId = supplierId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		AwardSupplierPK that = (AwardSupplierPK) o;

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
		final StringBuffer sb = new StringBuffer("AwardSupplierPK{");
		sb.append("awardId='").append(awardId).append('\'');
		sb.append(", supplierId=").append(supplierId);
		sb.append('}');
		return sb.toString();
	}
}
