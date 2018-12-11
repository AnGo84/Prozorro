package ua.prozorro.model.tenders;

import com.google.gson.annotations.SerializedName;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by AnGo on 28.11.2016.
 */
@Entity
@Table(name = "organizations")
public class Organization {
    @SerializedName("name")
    //@Expose
    @Column
    private String name;
    @SerializedName("contactPoint")
    //@Expose
    @Column
    private ContactPoint contactPoint;
    @SerializedName("identifier")
    //@Expose
    private Identifier identifier;
    @SerializedName("address")
    //@Expose
    private Address address;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ContactPoint getContactPoint() {
        return contactPoint;
    }

    public void setContactPoint(ContactPoint contactPoint) {
        this.contactPoint = contactPoint;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("tenders.Organization{");
        sb.append("name='").append(name).append('\'');
        sb.append(", contactPoint=").append(contactPoint);
        sb.append(", identifier=").append(identifier);
        sb.append(", address=").append(address);
        sb.append('}');
        return sb.toString();
    }
}
