package ua.prozorro.entity.tenders.pk;

import java.io.Serializable;

public class TenderItemPK implements Serializable {

	protected String tenderId;
	protected String itemId;


	public TenderItemPK() {
	}

	public TenderItemPK(String tenderId, String itemId) {
		this.tenderId = tenderId;
		this.itemId = itemId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		TenderItemPK that = (TenderItemPK) o;

		if (tenderId != null ? !tenderId.equals(that.tenderId) : that.tenderId != null)
			return false;
		return itemId != null ? itemId.equals(that.itemId) : that.itemId == null;
	}

	@Override
	public int hashCode() {
		int result = tenderId != null ? tenderId.hashCode() : 0;
		result = 31 * result + (itemId != null ? itemId.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("TenderItemPK{");
		sb.append("tenderId='").append(tenderId).append('\'');
		sb.append(", itemId='").append(itemId).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
