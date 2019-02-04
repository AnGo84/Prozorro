package ua.prozorro.entity.tenders;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Addresses")
public class AddressDTO {
	@Id
	//@GeneratedValue(strategy= GenerationType.AUTO)
	@Column
	private int id;
	@Column
	private String postalCode;
	@Column(length = 2000)
	private String countryName;
	@Column(length = 4000)
	/*@Lob
	@Column*/ private String streetAddress;
	@Column
	private String region;
	@Column(length = 2000)
	private String locality;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getPostalCode() {
		return postalCode;
	}
	
	public void setPostalCode(String postalCode) {
		this.id = this.hashCode();
		this.postalCode = postalCode;
	}
	
	public String getCountryName() {
		return countryName;
	}
	
	public void setCountryName(String countryName) {
		this.id = this.hashCode();
		this.countryName = countryName;
	}
	
	public String getStreetAddress() {
		return streetAddress;
	}
	
	public void setStreetAddress(String streetAddress) {
		this.id = this.hashCode();
		this.streetAddress = streetAddress;
	}
	
	public String getRegion() {
		return region;
	}
	
	public void setRegion(String region) {
		this.id = this.hashCode();
		this.region = region;
	}
	
	public String getLocality() {
		return locality;
	}
	
	public void setLocality(String locality) {
		this.id = this.hashCode();
		this.locality = locality;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		
		AddressDTO that = (AddressDTO) o;
		
		if (id != that.id)
			return false;
		if (postalCode != null ? !postalCode.equals(that.postalCode) : that.postalCode != null)
			return false;
		if (countryName != null ? !countryName.equals(that.countryName) : that.countryName != null)
			return false;
		if (streetAddress != null ? !streetAddress.equals(that.streetAddress) : that.streetAddress != null)
			return false;
		if (region != null ? !region.equals(that.region) : that.region != null)
			return false;
		return locality != null ? locality.equals(that.locality) : that.locality == null;
	}
	
	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (postalCode != null ? postalCode.hashCode() : 0);
		result = 31 * result + (countryName != null ? countryName.hashCode() : 0);
		result = 31 * result + (streetAddress != null ? streetAddress.hashCode() : 0);
		result = 31 * result + (region != null ? region.hashCode() : 0);
		result = 31 * result + (locality != null ? locality.hashCode() : 0);
		return result;
	}
	
	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("AddressDTO{");
		sb.append("id=").append(id);
		sb.append(", postalCode='").append(postalCode).append('\'');
		sb.append(", countryName='").append(countryName).append('\'');
		sb.append(", streetAddress='").append(streetAddress).append('\'');
		sb.append(", region='").append(region).append('\'');
		sb.append(", locality='").append(locality).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
