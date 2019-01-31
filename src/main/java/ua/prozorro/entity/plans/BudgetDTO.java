package ua.prozorro.entity.plans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import ua.prozorro.prozorro.model.plans.Project;

import javax.persistence.*;

@Entity
@Table(name = "Budgets")
public class BudgetDTO {
	@Id
	@Column(nullable = false)
	private String id;

	@Column
	private String amountNet;

	@Lob
	@Column
	private String description;

	@Lob
	@Column
	private String notes;

	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "project_id")
	private ProjectDTO project;

	@Column
	private String currency;

	@Column
	private String amount;

	@Column
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

	public ProjectDTO getProject() {
		return project;
	}

	public void setProject(ProjectDTO project) {
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
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		BudgetDTO budgetDTO = (BudgetDTO) o;

		if (id != null ? !id.equals(budgetDTO.id) : budgetDTO.id != null) return false;
		if (amountNet != null ? !amountNet.equals(budgetDTO.amountNet) : budgetDTO.amountNet != null) return false;
		if (description != null ? !description.equals(budgetDTO.description) : budgetDTO.description != null)
			return false;
		if (notes != null ? !notes.equals(budgetDTO.notes) : budgetDTO.notes != null) return false;
		if (project != null ? !project.equals(budgetDTO.project) : budgetDTO.project != null) return false;
		if (currency != null ? !currency.equals(budgetDTO.currency) : budgetDTO.currency != null) return false;
		if (amount != null ? !amount.equals(budgetDTO.amount) : budgetDTO.amount != null) return false;
		return year != null ? year.equals(budgetDTO.year) : budgetDTO.year == null;
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
		final StringBuffer sb = new StringBuffer("BudgetDTO{");
		sb.append("id='").append(id).append('\'');
		sb.append(", amountNet='").append(amountNet).append('\'');
		sb.append(", description='").append(description).append('\'');
		sb.append(", notes='").append(notes).append('\'');
		sb.append(", project=").append(project);
		sb.append(", currency='").append(currency).append('\'');
		sb.append(", amount='").append(amount).append('\'');
		sb.append(", year='").append(year).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
