package ua.prozorro.prozorro.model.tenders;

import com.google.gson.annotations.SerializedName;

public class Item {
    @SerializedName("description")
    //@Expose
    private String description;
    @SerializedName("classification")
    private Classification classification;

    @SerializedName("deliveryAddress")
    private Address deliveryAddress;

    @SerializedName("deliveryDate")
    //@Expose

    private Period deliveryDate;

    @SerializedName("id")
    //@Expose
    private String id;

    @SerializedName("unit")
    private Unit unit;

    @SerializedName("quantity")
    private int quantity;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Classification getClassification() {
        return classification;
    }

    public void setClassification(Classification classification) {
        this.classification = classification;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Period getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Period deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Item{");
        sb.append("description='").append(description).append('\'');
        sb.append(", classification=").append(classification);
        sb.append(", deliveryAddress=").append(deliveryAddress);
        sb.append(", deliveryDate=").append(deliveryDate);
        sb.append(", id='").append(id).append('\'');
        sb.append(", unit=").append(unit);
        sb.append(", quantity=").append(quantity);
        sb.append('}');
        return sb.toString();
    }
}
