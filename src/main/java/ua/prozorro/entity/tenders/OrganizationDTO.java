package ua.prozorro.entity.tenders;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Table(name = "Organizations")
public class OrganizationDTO {
    @Id
    //@GeneratedValue(strategy= GenerationType.AUTO)
    @Column
    private int id;

    @Column(length = 4000)
    private String name;
    @Column
    private String telephone;
    @Column(length = 2000)
    private String url;
    @Column(length = 2000)
    private String contactPointName;
    @Column
    private String email;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    /*(cascade = {CascadeType.ALL})*/
    @JoinColumn(name = "identifier_id")
    //@Cascade({org.hibernate.annotations.CascadeType.ALL})
    private IdentifierDTO identifier;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "address_id")
    //@Cascade({org.hibernate.annotations.CascadeType.ALL})
    private AddressDTO address;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IdentifierDTO getIdentifier() {
        return identifier;
    }

    public void setIdentifier(IdentifierDTO identifier) {
        this.identifier = identifier;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContactPointName() {
        return contactPointName;
    }

    public void setContactPointName(String contactPointName) {
        this.contactPointName = contactPointName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        OrganizationDTO that = (OrganizationDTO) o;

        if (id != that.id)
            return false;
        if (name != null ? !name.equals(that.name) : that.name != null)
            return false;
        if (telephone != null ? !telephone.equals(that.telephone) : that.telephone != null)
            return false;
        if (url != null ? !url.equals(that.url) : that.url != null)
            return false;
        if (contactPointName != null ? !contactPointName.equals(that.contactPointName) : that.contactPointName != null)
            return false;
        if (email != null ? !email.equals(that.email) : that.email != null)
            return false;
        if (identifier != null ? !identifier.equals(that.identifier) : that.identifier != null)
            return false;
        return address != null ? address.equals(that.address) : that.address == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (contactPointName != null ? contactPointName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (identifier != null ? identifier.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("OrganizationDTO{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", telephone='").append(telephone).append('\'');
        sb.append(", url='").append(url).append('\'');
        sb.append(", contactPointName='").append(contactPointName).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", identifier=").append(identifier);
        sb.append(", address=").append(address);
        sb.append('}');
        return sb.toString();
    }
}
