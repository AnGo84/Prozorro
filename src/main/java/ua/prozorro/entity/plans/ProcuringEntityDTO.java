package ua.prozorro.entity.plans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.hibernate.annotations.Cascade;
import ua.prozorro.entity.tenders.AddressDTO;
import ua.prozorro.entity.tenders.IdentifierDTO;
import ua.prozorro.prozorro.model.tenders.Address;
import ua.prozorro.prozorro.model.tenders.ContactPoint;
import ua.prozorro.prozorro.model.tenders.Identifier;

import javax.persistence.*;

@Entity
@Table(name = "ProcuringEntities")
public class ProcuringEntityDTO {
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "identifier_id")
	@Cascade({org.hibernate.annotations.CascadeType.ALL})
	private IdentifierDTO identifier;
	@Column(length = 2000)
	private String name;
	@Id
	@Column
	private int id;

	@Column
	private String telephone;
	@Column(length = 2000)
	private String contactUrl;
	@Column(length = 2000)
	private String contactPointName;
	@Column
	private String email;

	@Column
	private String kind;

	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "address_id")
	private AddressDTO address;

	public IdentifierDTO getIdentifier() {
		return identifier;
	}

	public void setIdentifier(IdentifierDTO identifier) {
		this.identifier = identifier;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getContactUrl() {
		return contactUrl;
	}

	public void setContactUrl(String contactUrl) {
		this.contactUrl = contactUrl;
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

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public AddressDTO getAddress() {
		return address;
	}

	public void setAddress(AddressDTO address) {
		this.address = address;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ProcuringEntityDTO that = (ProcuringEntityDTO) o;

		if (id != that.id) return false;
		if (identifier != null ? !identifier.equals(that.identifier) : that.identifier != null) return false;
		if (name != null ? !name.equals(that.name) : that.name != null) return false;
		if (telephone != null ? !telephone.equals(that.telephone) : that.telephone != null)
			return false;
		if (contactUrl != null ? !contactUrl.equals(that.contactUrl) : that.contactUrl != null) return false;
		if (contactPointName != null ? !contactPointName.equals(that.contactPointName) : that.contactPointName != null)
			return false;
		if (email != null ? !email.equals(that.email) : that.email != null) return false;
		if (kind != null ? !kind.equals(that.kind) : that.kind != null) return false;
		return address != null ? address.equals(that.address) : that.address == null;
	}

	@Override
	public int hashCode() {
		int result = identifier != null ? identifier.hashCode() : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + id;
		result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
		result = 31 * result + (contactUrl != null ? contactUrl.hashCode() : 0);
		result = 31 * result + (contactPointName != null ? contactPointName.hashCode() : 0);
		result = 31 * result + (email != null ? email.hashCode() : 0);
		result = 31 * result + (kind != null ? kind.hashCode() : 0);
		result = 31 * result + (address != null ? address.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("ProcuringEntityDTO{");
		sb.append("identifier=").append(identifier);
		sb.append(", name='").append(name).append('\'');
		sb.append(", id=").append(id);
		sb.append(", contactTelephone='").append(telephone).append('\'');
		sb.append(", contactUrl='").append(contactUrl).append('\'');
		sb.append(", contactPointName='").append(contactPointName).append('\'');
		sb.append(", email='").append(email).append('\'');
		sb.append(", kind='").append(kind).append('\'');
		sb.append(", address=").append(address);
		sb.append('}');
		return sb.toString();
	}
}
