package ua.prozorro.entity.tenders;

import org.hibernate.annotations.Cascade;
import ua.prozorro.entity.tenders.pk.TenderFeaturePK;

import javax.persistence.*;

@Entity
@Table(name = "Tenders_Features")
@IdClass(TenderFeaturePK.class)
public class TenderFeatureDTO {

	@Id
	@Column(name = "tender_id")
	private String tenderId;

	@Id
	@Column(name = "feature_id")
	private String featureId;

	public TenderFeatureDTO() {
	}

	public TenderFeatureDTO(String tenderId, String featureId) {
		this.tenderId = tenderId;
		this.featureId = featureId;
	}

	public String getTenderId() {
		return tenderId;
	}

	public void setTenderId(String tenderId) {
		this.tenderId = tenderId;
	}

	public String getFeatureId() {
		return featureId;
	}

	public void setFeatureId(String featureId) {
		this.featureId = featureId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		TenderFeatureDTO that = (TenderFeatureDTO) o;

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
		final StringBuffer sb = new StringBuffer("TenderFeatureDTO{");
		sb.append("tenderId='").append(tenderId).append('\'');
		sb.append(", featureId='").append(featureId).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
