package ua.prozorro.entity.plans.pk;

import java.io.Serializable;

public class PlanItemPK implements Serializable {

	protected String planId;
	protected String itemId;


	public PlanItemPK() {
	}

	public PlanItemPK(String planId, String itemId) {
		this.planId = planId;
		this.itemId = itemId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		PlanItemPK that = (PlanItemPK) o;

		if (planId != null ? !planId.equals(that.planId) : that.planId != null) return false;
		return itemId != null ? itemId.equals(that.itemId) : that.itemId == null;
	}

	@Override
	public int hashCode() {
		int result = planId != null ? planId.hashCode() : 0;
		result = 31 * result + (itemId != null ? itemId.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("PlanItemPK{");
		sb.append("planId='").append(planId).append('\'');
		sb.append(", itemId='").append(itemId).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
