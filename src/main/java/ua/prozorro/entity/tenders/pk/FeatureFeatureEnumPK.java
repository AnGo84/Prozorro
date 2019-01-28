package ua.prozorro.entity.tenders.pk;

import java.io.Serializable;

public class FeatureFeatureEnumPK implements Serializable {
	protected String featureId;
	protected long featureEnumId;

	public FeatureFeatureEnumPK() {
	}

	public FeatureFeatureEnumPK(String featureId, long featureEnumId) {
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

		FeatureFeatureEnumPK that = (FeatureFeatureEnumPK) o;

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
		final StringBuffer sb = new StringBuffer("FeatureFeatureEnumPK{");
		sb.append("featureId='").append(featureId).append('\'');
		sb.append(", featureEnumId=").append(featureEnumId);
		sb.append('}');
		return sb.toString();
	}
}
