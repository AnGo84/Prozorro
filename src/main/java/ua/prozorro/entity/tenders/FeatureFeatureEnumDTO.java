package ua.prozorro.entity.tenders;

import ua.prozorro.entity.exchangeRates.RateNBUPK;
import ua.prozorro.entity.tenders.pk.FeatureFeatureEnumPK;

import javax.persistence.*;

@Entity
@Table(name = "FeatureEnums")
@IdClass(FeatureFeatureEnumPK.class)
public class FeatureFeatureEnumDTO {
    @Id
    @Column
    protected String featureId;
    @Id
    @Column
    protected long featureEnumId;

    public FeatureFeatureEnumDTO() {
    }

    public FeatureFeatureEnumDTO(String featureId, long featureEnumId) {
        this.featureId = featureId;
        this.featureEnumId = featureEnumId;
    }

    public String getFeatureId() {
        return featureId;
    }

    public void setFeatureId(String featureId) {
        this.featureId = featureId;
    }

    public long getFeatureEnumId() {
        return featureEnumId;
    }

    public void setFeatureEnumId(long featureEnumId) {
        this.featureEnumId = featureEnumId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FeatureFeatureEnumDTO that = (FeatureFeatureEnumDTO) o;

        if (featureEnumId != that.featureEnumId) return false;
        return featureId != null ? featureId.equals(that.featureId) : that.featureId == null;
    }

    @Override
    public int hashCode() {
        int result = featureId != null ? featureId.hashCode() : 0;
        result = 31 * result + (int) (featureEnumId ^ (featureEnumId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("FeatureFeatureEnumDTO{");
        sb.append("featureId='").append(featureId).append('\'');
        sb.append(", featureEnumId=").append(featureEnumId);
        sb.append('}');
        return sb.toString();
    }
}
