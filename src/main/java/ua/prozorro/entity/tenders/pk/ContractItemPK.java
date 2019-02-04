package ua.prozorro.entity.tenders.pk;

import java.io.Serializable;

public class ContractItemPK implements Serializable {
	protected String contractId;
	protected String itemId;

	public ContractItemPK() {
	}

	public ContractItemPK(String contractId, String itemId) {
		this.contractId = contractId;
		this.itemId = itemId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		ContractItemPK that = (ContractItemPK) o;

		if (contractId != null ? !contractId.equals(that.contractId) : that.contractId != null)
			return false;
		return itemId != null ? itemId.equals(that.itemId) : that.itemId == null;
	}

	@Override
	public int hashCode() {
		int result = contractId != null ? contractId.hashCode() : 0;
		result = 31 * result + (itemId != null ? itemId.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("ContractItemPK{");
		sb.append("contractId='").append(contractId).append('\'');
		sb.append(", itemId='").append(itemId).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
