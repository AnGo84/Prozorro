package ua.prozorro.prozorro.model.contracts;

import com.google.gson.annotations.SerializedName;
import ua.prozorro.prozorro.model.plans.ProcuringEntity;
import ua.prozorro.prozorro.model.tenders.*;

import java.util.List;

//http://contracting.api-docs.openprocurement.org/uk/latest/tutorial.html
public class Contract {

    @SerializedName("id")
    private String id;

    @SerializedName("status")
    //@Expose
    private String status;

    @SerializedName("items")
    private List<Item> items = null;

    @SerializedName("suppliers")
    private List<Organization> suppliers = null;

    @SerializedName("value")
    private Value value;

    @SerializedName("awardID")
    private String awardID;

    @SerializedName("contractID")
    private String contractID;

    @SerializedName("documents")
    private List<Document> documents = null;

    @SerializedName("tender_id")
    private String tenderId;

    @SerializedName("description")
    private String description;

    @SerializedName("title")
    private String title;

    @SerializedName("contractNumber")
    private String contractNumber;

    @SerializedName("period")
    private Period period;

    @SerializedName("dateSigned")
    private String dateSigned;

    @SerializedName("dateModified")
    private String dateModified;

    @SerializedName("procuringEntity")
    private ProcuringEntity procuringEntity;

    @SerializedName("owner")
    private String owner;

    @SerializedName("changes")
    private List<Changes> changes = null;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Organization> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<Organization> suppliers) {
        this.suppliers = suppliers;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public String getAwardID() {
        return awardID;
    }

    public void setAwardID(String awardID) {
        this.awardID = awardID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContractID() {
        return contractID;
    }

    public void setContractID(String contractID) {
        this.contractID = contractID;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public String getTenderId() {
        return tenderId;
    }

    public void setTenderId(String tenderId) {
        this.tenderId = tenderId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public String getDateSigned() {
        return dateSigned;
    }

    public void setDateSigned(String dateSigned) {
        this.dateSigned = dateSigned;
    }

    public String getDateModified() {
        return dateModified;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

    public ProcuringEntity getProcuringEntity() {
        return procuringEntity;
    }

    public void setProcuringEntity(ProcuringEntity procuringEntity) {
        this.procuringEntity = procuringEntity;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public List<Changes> getChanges() {
        return changes;
    }

    public void setChanges(List<Changes> changes) {
        this.changes = changes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Contract contract = (Contract) o;

        if (id != null ? !id.equals(contract.id) : contract.id != null)
            return false;
        if (status != null ? !status.equals(contract.status) : contract.status != null)
            return false;
        if (items != null ? !items.equals(contract.items) : contract.items != null)
            return false;
        if (suppliers != null ? !suppliers.equals(contract.suppliers) : contract.suppliers != null)
            return false;
        if (value != null ? !value.equals(contract.value) : contract.value != null)
            return false;
        if (awardID != null ? !awardID.equals(contract.awardID) : contract.awardID != null)
            return false;
        if (contractID != null ? !contractID.equals(contract.contractID) : contract.contractID != null)
            return false;
        if (documents != null ? !documents.equals(contract.documents) : contract.documents != null)
            return false;
        if (tenderId != null ? !tenderId.equals(contract.tenderId) : contract.tenderId != null)
            return false;
        if (description != null ? !description.equals(contract.description) : contract.description != null)
            return false;
        if (title != null ? !title.equals(contract.title) : contract.title != null)
            return false;
        if (contractNumber != null ? !contractNumber.equals(contract.contractNumber) : contract.contractNumber != null)
            return false;
        if (period != null ? !period.equals(contract.period) : contract.period != null)
            return false;
        if (dateSigned != null ? !dateSigned.equals(contract.dateSigned) : contract.dateSigned != null)
            return false;
        if (dateModified != null ? !dateModified.equals(contract.dateModified) : contract.dateModified != null)
            return false;
        if (procuringEntity != null ? !procuringEntity.equals(contract.procuringEntity) :
                contract.procuringEntity != null)
            return false;
        if (owner != null ? !owner.equals(contract.owner) : contract.owner != null)
            return false;
        return changes != null ? changes.equals(contract.changes) : contract.changes == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (items != null ? items.hashCode() : 0);
        result = 31 * result + (suppliers != null ? suppliers.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (awardID != null ? awardID.hashCode() : 0);
        result = 31 * result + (contractID != null ? contractID.hashCode() : 0);
        result = 31 * result + (documents != null ? documents.hashCode() : 0);
        result = 31 * result + (tenderId != null ? tenderId.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (contractNumber != null ? contractNumber.hashCode() : 0);
        result = 31 * result + (period != null ? period.hashCode() : 0);
        result = 31 * result + (dateSigned != null ? dateSigned.hashCode() : 0);
        result = 31 * result + (dateModified != null ? dateModified.hashCode() : 0);
        result = 31 * result + (procuringEntity != null ? procuringEntity.hashCode() : 0);
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        result = 31 * result + (changes != null ? changes.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Contract{");
        sb.append("id='").append(id).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append(", items=").append(items);
        sb.append(", suppliers=").append(suppliers);
        sb.append(", value=").append(value);
        sb.append(", awardID='").append(awardID).append('\'');
        sb.append(", contractID='").append(contractID).append('\'');
        sb.append(", documents=").append(documents);
        sb.append(", tenderId='").append(tenderId).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", contractNumber='").append(contractNumber).append('\'');
        sb.append(", period=").append(period);
        sb.append(", dateSigned='").append(dateSigned).append('\'');
        sb.append(", dateModified='").append(dateModified).append('\'');
        sb.append(", procuringEntity=").append(procuringEntity);
        sb.append(", owner='").append(owner).append('\'');
        sb.append(", changes=").append(changes);
        sb.append('}');
        return sb.toString();
    }
}
