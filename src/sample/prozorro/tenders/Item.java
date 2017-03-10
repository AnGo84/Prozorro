package sample.prozorro.tenders;

/**
 * Created by AnGo on 30.10.2016.
 */
public class Item {
    private String id;
    private String description;
    private String scheme;
    private String schemeDescription;
    private String schemeId;
    private String unitCode;
    private String unitName;
    private String quantity;
    private String additionalClassifications;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getSchemeDescription() {
        return schemeDescription;
    }

    public void setSchemeDescription(String schemeDescription) {
        this.schemeDescription = schemeDescription;
    }

    public String getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(String schemeId) {
        this.schemeId = schemeId;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getAdditionalClassifications() {
        return additionalClassifications;
    }

    public void setAdditionalClassifications(String additionalClassifications) {
        this.additionalClassifications = additionalClassifications;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("tenders.Item{");
        sb.append("id='").append(id).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", scheme='").append(scheme).append('\'');
        sb.append(", schemeDescription='").append(schemeDescription).append('\'');
        sb.append(", schemeId='").append(schemeId).append('\'');
        sb.append(", unitCode='").append(unitCode).append('\'');
        sb.append(", unitName='").append(unitName).append('\'');
        sb.append(", quantity='").append(quantity).append('\'');
        sb.append(", additionalClassifications='").append(additionalClassifications).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
