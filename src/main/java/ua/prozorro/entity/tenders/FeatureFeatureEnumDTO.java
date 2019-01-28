package ua.prozorro.entity.tenders;

import ua.prozorro.entity.tenders.pk.FeatureFeatureEnumPK;

import javax.persistence.*;

@Entity
@Table(name = "Features_FeatureEnums")
@IdClass(FeatureFeatureEnumPK.class)
public class FeatureFeatureEnumDTO {
    @Id
    @Column(name = "feature_id")
    protected String featureId;
    @Id
    @Column(name = "featureEnum_id")
    protected int featureEnumId;

    public FeatureFeatureEnumDTO() {
    }

    public FeatureFeatureEnumDTO(String featureId, int featureEnumId) {
        this.featureId = featureId;
        this.featureEnumId = featureEnumId;
    }

    public String getFeatureId() {
        return featureId;
    }

    public void setFeatureId(String featureId) {
        this.featureId = featureId;
    }

    public int getFeatureEnumId() {
        return featureEnumId;
    }

    public void setFeatureEnumId(int featureEnumId) {
        this.featureEnumId = featureEnumId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof FeatureFeatureEnumDTO))
            return false;

        FeatureFeatureEnumDTO that = (FeatureFeatureEnumDTO) o;

        if (featureEnumId != that.featureEnumId)
            return false;
        return featureId != null ? featureId.equals(that.featureId) : that.featureId == null;
    }

    @Override
    public int hashCode() {
        int result = featureId != null ? featureId.hashCode() : 0;
        result = 31 * result + featureEnumId;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FeatureFeatureEnumDTO{");
        sb.append("featureId='").append(featureId).append('\'');
        sb.append(", featureEnumId=").append(featureEnumId);
        sb.append('}');
        return sb.toString();
    }
}
