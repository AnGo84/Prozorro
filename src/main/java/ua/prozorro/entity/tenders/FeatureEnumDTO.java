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
	@Column(length = 2000)
	private String title;
	//@Column(length = 8000)
	@Lob
	@Column
	private String description;
	
	@Column(name = "title_en", length = 2000)
	private String titleEn;
	@Lob
	@Column(name = "description_en")
	private String descriptionEn;
	
	
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
	
	public String getTitleEn() {
		return titleEn;
	}
	
	public void setTitleEn(String titleEn) {
		this.titleEn = titleEn;
	}
	
	public String getDescriptionEn() {
		return descriptionEn;
	}
	
	public void setDescriptionEn(String descriptionEn) {
		this.descriptionEn = descriptionEn;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		
		FeatureEnumDTO that = (FeatureEnumDTO) o;
		
		if (id != that.id)
			return false;
		if (value != null ? !value.equals(that.value) : that.value != null)
			return false;
		if (title != null ? !title.equals(that.title) : that.title != null)
			return false;
		if (description != null ? !description.equals(that.description) : that.description != null)
			return false;
		if (titleEn != null ? !titleEn.equals(that.titleEn) : that.titleEn != null)
			return false;
		return descriptionEn != null ? descriptionEn.equals(that.descriptionEn) : that.descriptionEn == null;
	}
	
	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (value != null ? value.hashCode() : 0);
		result = 31 * result + (title != null ? title.hashCode() : 0);
		result = 31 * result + (description != null ? description.hashCode() : 0);
		result = 31 * result + (titleEn != null ? titleEn.hashCode() : 0);
		result = 31 * result + (descriptionEn != null ? descriptionEn.hashCode() : 0);
		return result;
	}
	
	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("FeatureEnumDTO{");
		sb.append("id=").append(id);
		sb.append(", value='").append(value).append('\'');
		sb.append(", title='").append(title).append('\'');
		sb.append(", description='").append(description).append('\'');
		sb.append(", titleEn='").append(titleEn).append('\'');
		sb.append(", descriptionEn='").append(descriptionEn).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
