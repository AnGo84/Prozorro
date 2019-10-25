package ua.prozorro.prozorro.model.tenders;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Contract {
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

    @SerializedName("id")
    private String id;
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

    @SerializedName("date")
    private String dateModified;

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

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Contract{");
        sb.append("status='").append(status).append('\'');
        sb.append(", items=").append(items);
        sb.append(", suppliers=").append(suppliers);
        sb.append(", value=").append(value);
        sb.append(", awardID='").append(awardID).append('\'');
        sb.append(", id='").append(id).append('\'');
        sb.append(", contractID='").append(contractID).append('\'');
        sb.append(", documents=").append(documents);
        sb.append(", tenderId='").append(tenderId).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", contractNumber='").append(contractNumber).append('\'');
        sb.append(", period=").append(period);
        sb.append(", dateSigned='").append(dateSigned).append('\'');
        sb.append(", dateModified='").append(dateModified).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
