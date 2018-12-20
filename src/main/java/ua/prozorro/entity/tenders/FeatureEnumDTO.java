package ua.prozorro.entity.tenders;

import javax.persistence.*;

@Entity
@Table(name = "FeatureEnums")
public class FeatureEnumDTO {
	@Id
	//@GeneratedValue(strategy= GenerationType.AUTO)
	@Column
	private int id;
	@Column
	private String value;
	@Column
	private String title;
	@Column(length = 8000)
	private String description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		FeatureEnumDTO that = (FeatureEnumDTO) o;

		if (id != that.id) return false;
		if (value != null ? !value.equals(that.value) : that.value != null) return false;
		if (title != null ? !title.equals(that.title) : that.title != null) return false;
		return description != null ? description.equals(that.description) : that.description == null;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (value != null ? value.hashCode() : 0);
		result = 31 * result + (title != null ? title.hashCode() : 0);
		result = 31 * result + (description != null ? description.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("FeatureEnumDTO{");
		sb.append("id=").append(id);
		sb.append(", value='").append(value).append('\'');
		sb.append(", title='").append(title).append('\'');
		sb.append(", description='").append(description).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
