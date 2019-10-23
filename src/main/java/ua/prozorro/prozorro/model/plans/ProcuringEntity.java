package ua.prozorro.prozorro.model.plans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import ua.prozorro.prozorro.model.tenders.Address;
import ua.prozorro.prozorro.model.tenders.ContactPoint;
import ua.prozorro.prozorro.model.tenders.Identifier;

public class ProcuringEntity {

    @SerializedName("identifier")
    @Expose
    private Identifier identifier;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("contactPoint")
    @Expose
    private ContactPoint contactPoint;
    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("address")
    @Expose
    private Address address;

    public Identifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

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

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        ProcuringEntity that = (ProcuringEntity) o;

        if (identifier != null ? !identifier.equals(that.identifier) : that.identifier != null)
            return false;
        if (name != null ? !name.equals(that.name) : that.name != null)
            return false;
        if (contactPoint != null ? !contactPoint.equals(that.contactPoint) : that.contactPoint != null)
            return false;
        if (kind != null ? !kind.equals(that.kind) : that.kind != null)
            return false;
        return address != null ? address.equals(that.address) : that.address == null;
    }

    @Override
    public int hashCode() {
        int result = identifier != null ? identifier.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (contactPoint != null ? contactPoint.hashCode() : 0);
        result = 31 * result + (kind != null ? kind.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ProcuringEntity{");
        sb.append("identifier=").append(identifier);
        sb.append(", name='").append(name).append('\'');
        sb.append(", contactPoint=").append(contactPoint);
        sb.append(", kind='").append(kind).append('\'');
        sb.append(", address=").append(address);
        sb.append('}');
        return sb.toString();
    }
}
