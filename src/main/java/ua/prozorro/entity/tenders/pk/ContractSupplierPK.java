package ua.prozorro.entity.tenders.pk;

import java.io.Serializable;

public class ContractSupplierPK implements Serializable {
	protected String contractId;
	protected int supplierId;

	public ContractSupplierPK() {
	}

	public ContractSupplierPK(String contractId, int supplierId) {
		this.contractId = contractId;
		this.supplierId = supplierId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		ContractSupplierPK that = (ContractSupplierPK) o;

		if (supplierId != that.supplierId)
			return false;
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
		final StringBuffer sb = new StringBuffer("ContractSupplierPK{");
		sb.append("contractId='").append(contractId).append('\'');
		sb.append(", supplierId=").append(supplierId);
		sb.append('}');
		return sb.toString();
	}
}
