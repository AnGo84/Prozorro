package ua.prozorro.entity.tenders;

import com.google.gson.annotations.SerializedName;

import javax.persistence.*;

@Entity
@Table(name = "Classifications")
public class ClassificationDTO {
	@Id
	//@GeneratedValue(strategy= GenerationType.AUTO)
	@Column
	private String id;

	@Column(length = 8000)
	private String scheme;
	@Column
	private String description;


	public String getScheme() {
		return scheme;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ClassificationDTO that = (ClassificationDTO) o;

		if (id != null ? !id.equals(that.id) : that.id != null) return false;
		if (scheme != null ? !scheme.equals(that.scheme) : that.scheme != null) return false;
		return description != null ? description.equals(that.description) : that.description == null;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (scheme != null ? scheme.hashCode() : 0);
		result = 31 * result + (description != null ? description.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("ClassificationDTO{");
		sb.append("scheme='").append(scheme).append('\'');
		sb.append(", description='").append(description).append('\'');
		sb.append(", id='").append(id).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
