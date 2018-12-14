package ua.prozorro.entity.tenders;

import com.google.gson.annotations.SerializedName;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Features")
public class FeatureDTO {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column
	private String code;
	@Column
	private String featureOf;
	@Column
	private String relatedItem;
	@Column
	private String title;
	@Column(length = 8000)
	private String description;

	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name = "Features_FeatureEnums", joinColumns = { @JoinColumn(name = "feature_id") }, inverseJoinColumns = {
			@JoinColumn(name = "featureEnum_id") })
	private List<FeatureEnumDTO> featureEnums = null;


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getFeatureOf() {
		return featureOf;
	}

	public void setFeatureOf(String featureOf) {
		this.featureOf = featureOf;
	}

	public String getRelatedItem() {
		return relatedItem;
	}

	public void setRelatedItem(String relatedItem) {
		this.relatedItem = relatedItem;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<FeatureEnumDTO> getFeatureEnums() {
		return featureEnums;
	}

	public void setFeatureEnums(List<FeatureEnumDTO> featureEnums) {
		this.featureEnums = featureEnums;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		FeatureDTO that = (FeatureDTO) o;

		if (code != null ? !code.equals(that.code) : that.code != null) return false;
		if (featureOf != null ? !featureOf.equals(that.featureOf) : that.featureOf != null) return false;
		if (relatedItem != null ? !relatedItem.equals(that.relatedItem) : that.relatedItem != null) return false;
		if (title != null ? !title.equals(that.title) : that.title != null) return false;
		if (description != null ? !description.equals(that.description) : that.description != null) return false;
		return featureEnums != null ? featureEnums.equals(that.featureEnums) : that.featureEnums == null;
	}

	@Override
	public int hashCode() {
		int result = code != null ? code.hashCode() : 0;
		result = 31 * result + (featureOf != null ? featureOf.hashCode() : 0);
		result = 31 * result + (relatedItem != null ? relatedItem.hashCode() : 0);
		result = 31 * result + (title != null ? title.hashCode() : 0);
		result = 31 * result + (description != null ? description.hashCode() : 0);
		result = 31 * result + (featureEnums != null ? featureEnums.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("FeatureDTO{");
		sb.append("code='").append(code).append('\'');
		sb.append(", featureOf='").append(featureOf).append('\'');
		sb.append(", relatedItem='").append(relatedItem).append('\'');
		sb.append(", title='").append(title).append('\'');
		sb.append(", description='").append(description).append('\'');
		sb.append(", featureEnums=").append(featureEnums);
		sb.append('}');
		return sb.toString();
	}
}
