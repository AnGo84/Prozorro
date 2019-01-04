package ua.prozorro.entity.tenders;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;

/*@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
@JoinTable(name = "Tenders_Features", joinColumns = {@JoinColumn(name = "tender_id")},
		inverseJoinColumns = {@JoinColumn(name = "feature_id")})*/
@Entity
@Table(name = "Tenders_Features")
public class TenderFeatureDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tender_id")
	@Cascade({org.hibernate.annotations.CascadeType.ALL})
	private TenderDTO tender;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "feature_id")
	@Cascade({org.hibernate.annotations.CascadeType.ALL})
	private FeatureDTO feature;

	public TenderFeatureDTO() {
	}

	public TenderFeatureDTO(TenderDTO tender, FeatureDTO feature) {
		this.tender = tender;
		this.feature = feature;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public TenderDTO getTender() {
		return tender;
	}

	public void setTender(TenderDTO tender) {
		this.tender = tender;
	}

	public FeatureDTO getFeature() {
		return feature;
	}

	public void setFeature(FeatureDTO feature) {
		this.feature = feature;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		TenderFeatureDTO that = (TenderFeatureDTO) o;

		if (id != that.id) return false;
		if (tender != null ? !tender.equals(that.tender) : that.tender != null) return false;
		return feature != null ? feature.equals(that.feature) : that.feature == null;
	}

	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + (tender != null ? tender.hashCode() : 0);
		result = 31 * result + (feature != null ? feature.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("TenderFeatureDTO{");
		sb.append("id=").append(id);
		sb.append(", tender=").append(tender);
		sb.append(", feature=").append(feature);
		sb.append('}');
		return sb.toString();
	}
}
