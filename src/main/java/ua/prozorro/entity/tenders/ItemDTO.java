package ua.prozorro.entity.tenders;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Table(name = "Items")
public class ItemDTO {

    @Id
    //@GeneratedValue(strategy= GenerationType.AUTO)
    @Column
    private String id;

    //@Column(length = 8000)
    @Lob
    @Column
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classification_id")
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private ClassificationDTO classification;

    @ManyToOne(fetch = FetchType.LAZY)
    //(cascade = {CascadeType.ALL})
    @JoinColumn(name = "delivery_address_id")
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private AddressDTO deliveryAddress;

    @Column
    private String deliveryStartDate;
    @Column
    private String deliveryClarificationsUntil;
    @Column
    private String deliveryEndDate;
    @Column
    private String deliveryInvalidationDate;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "unit_id")
    private UnitDTO unit;

    @Column
    private long quantity;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ClassificationDTO getClassification() {
        return classification;
    }

    public void setClassification(ClassificationDTO classification) {
        this.classification = classification;
    }

    public AddressDTO getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(AddressDTO deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UnitDTO getUnit() {
        return unit;
    }

    public void setUnit(UnitDTO unit) {
        this.unit = unit;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public String getDeliveryStartDate() {
        return deliveryStartDate;
    }

    public void setDeliveryStartDate(String deliveryStartDate) {
        this.deliveryStartDate = deliveryStartDate;
    }

    public String getDeliveryClarificationsUntil() {
        return deliveryClarificationsUntil;
    }

    public void setDeliveryClarificationsUntil(String deliveryClarificationsUntil) {
        this.deliveryClarificationsUntil = deliveryClarificationsUntil;
    }

    public String getDeliveryEndDate() {
        return deliveryEndDate;
    }

    public void setDeliveryEndDate(String deliveryEndDate) {
        this.deliveryEndDate = deliveryEndDate;
    }

    public String getDeliveryInvalidationDate() {
        return deliveryInvalidationDate;
    }

    public void setDeliveryInvalidationDate(String deliveryInvalidationDate) {
        this.deliveryInvalidationDate = deliveryInvalidationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        ItemDTO itemDTO = (ItemDTO) o;

        if (quantity != itemDTO.quantity)
            return false;
        if (id != null ? !id.equals(itemDTO.id) : itemDTO.id != null)
            return false;
        if (description != null ? !description.equals(itemDTO.description) : itemDTO.description != null)
            return false;
        if (classification != null ? !classification.equals(itemDTO.classification) : itemDTO.classification != null)
            return false;
        if (deliveryAddress != null ? !deliveryAddress.equals(itemDTO.deliveryAddress) :
                itemDTO.deliveryAddress != null)
            return false;
        if (deliveryStartDate != null ? !deliveryStartDate.equals(itemDTO.deliveryStartDate) :
                itemDTO.deliveryStartDate != null)
            return false;
        if (deliveryClarificationsUntil != null ?
                !deliveryClarificationsUntil.equals(itemDTO.deliveryClarificationsUntil) :
                itemDTO.deliveryClarificationsUntil != null)
            return false;
        if (deliveryEndDate != null ? !deliveryEndDate.equals(itemDTO.deliveryEndDate) :
                itemDTO.deliveryEndDate != null)
            return false;
        if (deliveryInvalidationDate != null ? !deliveryInvalidationDate.equals(itemDTO.deliveryInvalidationDate) :
                itemDTO.deliveryInvalidationDate != null)
            return false;
        return unit != null ? unit.equals(itemDTO.unit) : itemDTO.unit == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (classification != null ? classification.hashCode() : 0);
        result = 31 * result + (deliveryAddress != null ? deliveryAddress.hashCode() : 0);
        result = 31 * result + (deliveryStartDate != null ? deliveryStartDate.hashCode() : 0);
        result = 31 * result + (deliveryClarificationsUntil != null ? deliveryClarificationsUntil.hashCode() : 0);
        result = 31 * result + (deliveryEndDate != null ? deliveryEndDate.hashCode() : 0);
        result = 31 * result + (deliveryInvalidationDate != null ? deliveryInvalidationDate.hashCode() : 0);
        result = 31 * result + (unit != null ? unit.hashCode() : 0);
        result = 31 * result + (int) (quantity ^ (quantity >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ItemDTO{");
        sb.append("id='").append(id).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", classification=").append(classification);
        sb.append(", deliveryAddress=").append(deliveryAddress);
        sb.append(", deliveryStartDate='").append(deliveryStartDate).append('\'');
        sb.append(", deliveryClarificationsUntil='").append(deliveryClarificationsUntil).append('\'');
        sb.append(", deliveryEndDate='").append(deliveryEndDate).append('\'');
        sb.append(", deliveryInvalidationDate='").append(deliveryInvalidationDate).append('\'');
        sb.append(", unit=").append(unit);
        sb.append(", quantity=").append(quantity);
        sb.append('}');
        return sb.toString();
    }
}
