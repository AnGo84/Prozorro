package ua.prozorro.prozorro.model.plans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Budget {
	@SerializedName("id")
	@Expose
	private String id;
	
	@SerializedName("amountNet")
	@Expose
	private String amountNet;
	
	@SerializedName("description")
	@Expose
	private String description;
	
	@SerializedName("notes")
	@Expose
	private String notes;
	
	@SerializedName("project")
	@Expose
	private Project project;
	
	@SerializedName("currency")
	@Expose
	private String currency;
	
	@SerializedName("amount")
	@Expose
	private String amount;
	
	@SerializedName("year")
	@Expose
	private String year;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getAmountNet() {
		return amountNet;
	}
	
	public void setAmountNet(String amountNet) {
		this.amountNet = amountNet;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getNotes() {
		return notes;
	}
	
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	public Project getProject() {
		return project;
	}
	
	public void setProject(Project project) {
		this.project = project;
	}
	
	public String getCurrency() {
		return currency;
	}
	
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	public String getAmount() {
		return amount;
	}
	
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	public String getYear() {
		return year;
	}
	
	public void setYear(String year) {
		this.year = year;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		
		Budget budget = (Budget) o;
		
		if (id != null ? !id.equals(budget.id) : budget.id != null)
			return false;
		if (amountNet != null ? !amountNet.equals(budget.amountNet) : budget.amountNet != null)
			return false;
		if (description != null ? !description.equals(budget.description) : budget.description != null)
			return false;
		if (notes != null ? !notes.equals(budget.notes) : budget.notes != null)
			return false;
		if (project != null ? !project.equals(budget.project) : budget.project != null)
			return false;
		if (currency != null ? !currency.equals(budget.currency) : budget.currency != null)
			return false;
		if (amount != null ? !amount.equals(budget.amount) : budget.amount != null)
			return false;
		return year != null ? year.equals(budget.year) : budget.year == null;
	}
	
	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (amountNet != null ? amountNet.hashCode() : 0);
		result = 31 * result + (description != null ? description.hashCode() : 0);
		result = 31 * result + (notes != null ? notes.hashCode() : 0);
		result = 31 * result + (project != null ? project.hashCode() : 0);
		result = 31 * result + (currency != null ? currency.hashCode() : 0);
		result = 31 * result + (amount != null ? amount.hashCode() : 0);
		result = 31 * result + (year != null ? year.hashCode() : 0);
		return result;
	}
	
	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("Budget{");
		sb.append("id='").append(id).append('\'');
		sb.append(", amountNet=").append(amountNet);
		sb.append(", description='").append(description).append('\'');
		sb.append(", notes='").append(notes).append('\'');
		sb.append(", project=").append(project);
		sb.append(", currency='").append(currency).append('\'');
		sb.append(", amount=").append(amount);
		sb.append(", year='").append(year).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
