package ua.prozorro.entity.tenders.pk;

import java.io.Serializable;

public class FeatureFeatureEnumPK implements Serializable {
	protected String featureId;
	protected int featureEnumId;

	public FeatureFeatureEnumPK() {
	}

	public FeatureFeatureEnumPK(String featureId, int featureEnumId) {
		this.featureId = featureId;
		this.featureEnumId = featureEnumId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof FeatureFeatureEnumPK))
			return false;

		FeatureFeatureEnumPK that = (FeatureFeatureEnumPK) o;

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
		final StringBuilder sb = new StringBuilder("FeatureFeatureEnumPK{");
		sb.append("featureId='").append(featureId).append('\'');
		sb.append(", featureEnumId=").append(featureEnumId);
		sb.append('}');
		return sb.toString();
	}
}
