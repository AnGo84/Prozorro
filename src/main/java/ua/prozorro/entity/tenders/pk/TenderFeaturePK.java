package ua.prozorro.entity.tenders.pk;

import java.io.Serializable;

public class TenderFeaturePK implements Serializable {

	protected String tenderId;
	protected String featureId;


	public TenderFeaturePK() {
	}

	public TenderFeaturePK(String tenderId, String featureId) {
		this.tenderId = tenderId;
		this.featureId = featureId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		TenderFeaturePK that = (TenderFeaturePK) o;

		if (tenderId != null ? !tenderId.equals(that.tenderId) : that.tenderId != null) return false;
		return featureId != null ? featureId.equals(that.featureId) : that.featureId == null;
	}

	@Override
	public int hashCode() {
		int result = tenderId != null ? tenderId.hashCode() : 0;
		result = 31 * result + (featureId != null ? featureId.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("TenderFeaturePK{");
		sb.append("tenderId='").append(tenderId).append('\'');
		sb.append(", featureId='").append(featureId).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
