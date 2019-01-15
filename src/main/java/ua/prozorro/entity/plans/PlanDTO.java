package ua.prozorro.entity.plans;

import ua.prozorro.entity.tenders.ClassificationDTO;
import ua.prozorro.entity.tenders.DocumentDTO;
import ua.prozorro.entity.tenders.ItemDTO;

import javax.persistence.*;
import java.util.List;

//http://planning.api-docs.openprocurement.org/uk/latest/tutorial.html

@Entity
@Table(name = "Plans")
public class PlanDTO {

	@Id
	@Column(nullable = false)
	private String id;

	@Column
	private String planID;

	/*@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinTable(name = "Plans_Documents", joinColumns = {@JoinColumn(name = "plan_id")}, inverseJoinColumns = {
			@JoinColumn(name = "document_id")})*/
	@Transient
	private List<DocumentDTO> documents = null;

	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "classification_id")
	//@Cascade({org.hibernate.annotations.CascadeType.ALL})
	private ClassificationDTO classification;

	/*@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinTable(name = "Plans_Items", joinColumns = {@JoinColumn(name = "plan_id")}, inverseJoinColumns = {
			@JoinColumn(name = "item_id")})*/
	@Transient
	private List<ItemDTO> items = null;

	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "budget_id")
	private BudgetDTO budget;


	/*@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinTable(name = "Plans_AdditionalClassifications", joinColumns = {@JoinColumn(name = "plan_id")},
			inverseJoinColumns = {@JoinColumn(name = "classification_id")})*/
	@Transient
	private List<ClassificationDTO> additionalClassifications = null;

	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "procuringEntity_id")
	private ProcuringEntityDTO procuringEntity;

	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "planTender_id")
	private PlanTenderDTO tender;

	@Column
	private String datePublished;

	@Column(length = 1000)
	private String owner;

	@Column
	private String dateModified;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPlanID() {
		return planID;
	}

	public void setPlanID(String planID) {
		this.planID = planID;
	}

	public List<DocumentDTO> getDocuments() {
		return documents;
	}

	public void setDocuments(List<DocumentDTO> documents) {
		this.documents = documents;
	}

	public ClassificationDTO getClassification() {
		return classification;
	}

	public void setClassification(ClassificationDTO classification) {
		this.classification = classification;
	}

	public List<ItemDTO> getItems() {
		return items;
	}

	public void setItems(List<ItemDTO> items) {
		this.items = items;
	}

	public BudgetDTO getBudget() {
		return budget;
	}

	public void setBudget(BudgetDTO budget) {
		this.budget = budget;
	}

	public List<ClassificationDTO> getAdditionalClassifications() {
		return additionalClassifications;
	}

	public void setAdditionalClassifications(List<ClassificationDTO> additionalClassifications) {
		this.additionalClassifications = additionalClassifications;
	}

	public ProcuringEntityDTO getProcuringEntity() {
		return procuringEntity;
	}

	public void setProcuringEntity(ProcuringEntityDTO procuringEntity) {
		this.procuringEntity = procuringEntity;
	}

	public PlanTenderDTO getTender() {
		return tender;
	}

	public void setTender(PlanTenderDTO tender) {
		this.tender = tender;
	}

	public String getDatePublished() {
		return datePublished;
	}

	public void setDatePublished(String datePublished) {
		this.datePublished = datePublished;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getDateModified() {
		return dateModified;
	}

	public void setDateModified(String dateModified) {
		this.dateModified = dateModified;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		PlanDTO planDTO = (PlanDTO) o;

		if (id != null ? !id.equals(planDTO.id) : planDTO.id != null) return false;
		if (planID != null ? !planID.equals(planDTO.planID) : planDTO.planID != null) return false;
		if (documents != null ? !documents.equals(planDTO.documents) : planDTO.documents != null) return false;
		if (classification != null ? !classification.equals(planDTO.classification) : planDTO.classification != null)
			return false;
		if (items != null ? !items.equals(planDTO.items) : planDTO.items != null) return false;
		if (budget != null ? !budget.equals(planDTO.budget) : planDTO.budget != null) return false;
		if (additionalClassifications != null ? !additionalClassifications.equals(planDTO.additionalClassifications) : planDTO.additionalClassifications != null)
			return false;
		if (procuringEntity != null ? !procuringEntity.equals(planDTO.procuringEntity) : planDTO.procuringEntity != null)
			return false;
		if (tender != null ? !tender.equals(planDTO.tender) : planDTO.tender != null) return false;
		if (datePublished != null ? !datePublished.equals(planDTO.datePublished) : planDTO.datePublished != null)
			return false;
		if (owner != null ? !owner.equals(planDTO.owner) : planDTO.owner != null) return false;
		return dateModified != null ? dateModified.equals(planDTO.dateModified) : planDTO.dateModified == null;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (planID != null ? planID.hashCode() : 0);
		result = 31 * result + (documents != null ? documents.hashCode() : 0);
		result = 31 * result + (classification != null ? classification.hashCode() : 0);
		result = 31 * result + (items != null ? items.hashCode() : 0);
		result = 31 * result + (budget != null ? budget.hashCode() : 0);
		result = 31 * result + (additionalClassifications != null ? additionalClassifications.hashCode() : 0);
		result = 31 * result + (procuringEntity != null ? procuringEntity.hashCode() : 0);
		result = 31 * result + (tender != null ? tender.hashCode() : 0);
		result = 31 * result + (datePublished != null ? datePublished.hashCode() : 0);
		result = 31 * result + (owner != null ? owner.hashCode() : 0);
		result = 31 * result + (dateModified != null ? dateModified.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("PlanDTO{");
		sb.append("id='").append(id).append('\'');
		sb.append(", planID='").append(planID).append('\'');
		sb.append(", documents=").append(documents);
		sb.append(", classification=").append(classification);
		sb.append(", items=").append(items);
		sb.append(", budget=").append(budget);
		sb.append(", additionalClassifications=").append(additionalClassifications);
		sb.append(", procuringEntity=").append(procuringEntity);
		sb.append(", tender=").append(tender);
		sb.append(", datePublished='").append(datePublished).append('\'');
		sb.append(", owner='").append(owner).append('\'');
		sb.append(", dateModified='").append(dateModified).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
