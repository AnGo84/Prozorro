package ua.prozorro.prozorro.model.tenders;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Award {
    @SerializedName("id")
    //@Expose
    private String id;

    @SerializedName("status")
    private String status;

    @SerializedName("documents")
    private List<Document> documents = null;

    @SerializedName("date")
    private String date;

    @SerializedName("bid_id")
    private String bidId;

    @SerializedName("value")
    private Value value;

    @SerializedName("complaintPeriod")
    private Period complaintPeriod;

    @SerializedName("suppliers")
    private List<Organization> suppliers = null;

    @SerializedName("eligible")
    private boolean eligible;

    @SerializedName("qualified")
    private boolean qualified;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBidId() {
        return bidId;
    }

    public void setBidId(String bidId) {
        this.bidId = bidId;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public Period getComplaintPeriod() {
        return complaintPeriod;
    }

    public void setComplaintPeriod(Period complaintPeriod) {
        this.complaintPeriod = complaintPeriod;
    }

    public List<Organization> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<Organization> suppliers) {
        this.suppliers = suppliers;
    }

    public boolean isEligible() {
        return eligible;
    }

    public void setEligible(boolean eligible) {
        this.eligible = eligible;
    }

    public boolean isQualified() {
        return qualified;
    }

    public void setQualified(boolean qualified) {
        this.qualified = qualified;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("AwardDTO{");
        sb.append("id='").append(id).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append(", documents=").append(documents);
        sb.append(", date='").append(date).append('\'');
        sb.append(", bidId='").append(bidId).append('\'');
        sb.append(", value=").append(value);
        sb.append(", complaintPeriod=").append(complaintPeriod);
        sb.append(", suppliers=").append(suppliers);
        sb.append(", eligible=").append(eligible);
        sb.append(", qualified=").append(qualified);
        sb.append('}');
        return sb.toString();
    }
}
