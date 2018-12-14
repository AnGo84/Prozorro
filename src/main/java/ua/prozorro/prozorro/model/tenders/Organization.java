package ua.prozorro.prozorro.model.tenders;

import com.google.gson.annotations.SerializedName;

public class Organization {

	@SerializedName("name")
	//@Expose
	private String name;

	@SerializedName("contactPoint")
	private ContactPoint contactPoint;

	@SerializedName("identifier")
	private Identifier identifier;

	@SerializedName("address")
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
		final StringBuffer sb = new StringBuffer("Organization{");
		sb.append(", name='").append(name).append('\'');
		sb.append(", contactPoint=").append(contactPoint);
		sb.append(", identifier=").append(identifier);
		sb.append(", address=").append(address);
		sb.append('}');
		return sb.toString();
	}
}
