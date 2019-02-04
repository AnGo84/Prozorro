package ua.prozorro.entity.plans.pk;

import java.io.Serializable;

public class PlanAdditionalClassificationPK implements Serializable {
	
	protected String planId;
	protected String classificationId;
	
	public PlanAdditionalClassificationPK() {
	}
	
	public PlanAdditionalClassificationPK(String planId, String classificationId) {
		this.planId = planId;
		this.classificationId = classificationId;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		
		PlanAdditionalClassificationPK that = (PlanAdditionalClassificationPK) o;
		
		if (planId != null ? !planId.equals(that.planId) : that.planId != null)
			return false;
		return classificationId != null ? classificationId.equals(that.classificationId) :
				that.classificationId == null;
	}
	
	@Override
	public int hashCode() {
		int result = planId != null ? planId.hashCode() : 0;
		result = 31 * result + (classificationId != null ? classificationId.hashCode() : 0);
		return result;
	}
	
	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("PlanAdditionalClassificationPK{");
		sb.append("planId='").append(planId).append('\'');
		sb.append(", classificationId='").append(classificationId).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
