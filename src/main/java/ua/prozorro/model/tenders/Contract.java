package ua.prozorro.model.tenders;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by AnGo on 09.02.2017.
 */
public class Contract {
    @SerializedName("status")
    //@Expose
    private String status;
    @SerializedName("items")
    //@Expose
    private List<Item> items = null;
    @SerializedName("suppliers")
    //@Expose
    public List<Supplier> suppliers = null;
    @SerializedName("value")
    //@Expose
    public Value value;

    @SerializedName("awardID")
    //@Expose
    public String awardID;

    @SerializedName("id")
    //@Expose
    public String id;
    @SerializedName("contractID")
    //@Expose
    public String contractID;

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

    public List<Supplier> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<Supplier> suppliers) {
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
