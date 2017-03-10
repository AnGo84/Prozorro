package sample.prozorro.tenders;

/**
 * Created by AnGo on 28.11.2016.
 */
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
    public String toString() {
        final StringBuilder sb = new StringBuilder("tenders.Address{");
        sb.append("postalCode='").append(postalCode).append('\'');
        sb.append(", countryName='").append(countryName).append('\'');
        sb.append(", streetAddress='").append(streetAddress).append('\'');
        sb.append(", region='").append(region).append('\'');
        sb.append(", locality='").append(locality).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
