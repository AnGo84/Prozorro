package ua.prozorro.prozorro.model.plans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import ua.prozorro.prozorro.model.tenders.Classification;
import ua.prozorro.prozorro.model.tenders.Document;
import ua.prozorro.prozorro.model.tenders.Item;
import ua.prozorro.prozorro.model.tenders.Tender;

import java.util.List;

//http://planning.api-docs.openprocurement.org/uk/latest/standard/contract.html
public class Plan {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("planID")
    @Expose
    private String planID;

    @SerializedName("documents")
    @Expose
    private List<Document> documents = null;

    @SerializedName("classification")
    @Expose
    private Classification classification;

    @SerializedName("items")
    @Expose
    private List<Item> items = null;
    @SerializedName("budget")
    @Expose
    private Budget budget;
    @SerializedName("additionalClassifications")
    @Expose
    private List<Classification> additionalClassifications = null;
    @SerializedName("procuringEntity")
    @Expose
    private ProcuringEntity procuringEntity;
    @SerializedName("tender")
    @Expose
    private Tender tender;

    @SerializedName("datePublished")
    @Expose
    private String datePublished;

    @SerializedName("owner")
    @Expose
    private String owner;

    @SerializedName("dateModified")
    @Expose
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

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public Classification getClassification() {
        return classification;
    }

    public void setClassification(Classification classification) {
        this.classification = classification;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    public List<Classification> getAdditionalClassifications() {
        return additionalClassifications;
    }

    public void setAdditionalClassifications(List<Classification> additionalClassifications) {
        this.additionalClassifications = additionalClassifications;
    }

    public ProcuringEntity getProcuringEntity() {
        return procuringEntity;
    }

    public void setProcuringEntity(ProcuringEntity procuringEntity) {
        this.procuringEntity = procuringEntity;
    }

    public Tender getTender() {
        return tender;
    }

    public void setTender(Tender tender) {
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
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Plan plan = (Plan) o;

        if (id != null ? !id.equals(plan.id) : plan.id != null)
            return false;
        if (planID != null ? !planID.equals(plan.planID) : plan.planID != null)
            return false;
        if (documents != null ? !documents.equals(plan.documents) : plan.documents != null)
            return false;
        if (classification != null ? !classification.equals(plan.classification) : plan.classification != null)
            return false;
        if (items != null ? !items.equals(plan.items) : plan.items != null)
            return false;
        if (budget != null ? !budget.equals(plan.budget) : plan.budget != null)
            return false;
        if (additionalClassifications != null ? !additionalClassifications.equals(plan.additionalClassifications) :
                plan.additionalClassifications != null)
            return false;
        if (procuringEntity != null ? !procuringEntity.equals(plan.procuringEntity) : plan.procuringEntity != null)
            return false;
        if (tender != null ? !tender.equals(plan.tender) : plan.tender != null)
            return false;
        if (datePublished != null ? !datePublished.equals(plan.datePublished) : plan.datePublished != null)
            return false;
        if (owner != null ? !owner.equals(plan.owner) : plan.owner != null)
            return false;
        return dateModified != null ? dateModified.equals(plan.dateModified) : plan.dateModified == null;
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
        final StringBuffer sb = new StringBuffer("Plan{");
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
