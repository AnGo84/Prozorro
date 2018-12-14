package ua.prozorro.prozorro.model.tenders;

import com.google.gson.annotations.SerializedName;

import javax.persistence.*;
import java.util.List;

public class Contract {
    @SerializedName("status")
    @Column
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
        sb.append('}');
        return sb.toString();
    }
}
