package ua.prozorro.model.tenders;

/**
 * Created by AnGo on 28.11.2016.
 */
public class Organization {
    private String name;
    private ContactPoint contactPoint;
    private Identifier identifier;
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
