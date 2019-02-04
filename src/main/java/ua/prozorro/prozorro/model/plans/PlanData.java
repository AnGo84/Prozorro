package ua.prozorro.prozorro.model.plans;

import com.google.gson.annotations.SerializedName;

public class PlanData {
	@SerializedName("data")
	private Plan plan;
	
	public Plan getPlan() {
		return plan;
	}
	
	public void setPlan(Plan plan) {
		this.plan = plan;
	}
	
	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("PlanData{");
		sb.append("plan=").append(plan);
		sb.append('}');
		return sb.toString();
	}
}
