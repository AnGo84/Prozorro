package ua.prozorro.prozorro.model.tenders;


public class Address {
	private String postalCode;
	private String countryName;
	private String streetAddress;
	private String region;
	private String locality;
	
	public String getPostalCode() {
		return postalCode;
	}
	
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	public String getCountryName() {
		return countryName;
	}
	
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	
	public String getStreetAddress() {
		return streetAddress;
	}
	
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	
	public String getRegion() {
		return region;
	}
	
	public void setRegion(String region) {
		this.region = region;
	}
	
	public String getLocality() {
		return locality;
	}
	
	public void setLocality(String locality) {
		this.locality = locality;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		
		Address address = (Address) o;
		
		if (postalCode != null ? !postalCode.equals(address.postalCode) : address.postalCode != null)
			return false;
		if (countryName != null ? !countryName.equals(address.countryName) : address.countryName != null)
			return false;
		if (streetAddress != null ? !streetAddress.equals(address.streetAddress) : address.streetAddress != null)
			return false;
		if (region != null ? !region.equals(address.region) : address.region != null)
			return false;
		return locality != null ? locality.equals(address.locality) : address.locality == null;
	}
	
	@Override
	public int hashCode() {
		int result = postalCode != null ? postalCode.hashCode() : 0;
		result = 31 * result + (countryName != null ? countryName.hashCode() : 0);
		result = 31 * result + (streetAddress != null ? streetAddress.hashCode() : 0);
		result = 31 * result + (region != null ? region.hashCode() : 0);
		result = 31 * result + (locality != null ? locality.hashCode() : 0);
		return result;
	}
	
	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("AddressDTO{");
		sb.append(", postalCode='").append(postalCode).append('\'');
		sb.append(", countryName='").append(countryName).append('\'');
		sb.append(", streetAddress='").append(streetAddress).append('\'');
		sb.append(", region='").append(region).append('\'');
		sb.append(", locality='").append(locality).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
