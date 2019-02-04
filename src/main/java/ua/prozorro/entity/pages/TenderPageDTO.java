package ua.prozorro.entity.pages;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ALL_tenders_IMPORT")
public class TenderPageDTO {
	
	@Id
	//@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name = "tender_id")
	private String id;
	
	@Column(name = "dateModified")
	private String dateModified;
	
	public TenderPageDTO() {
	}
	
	public TenderPageDTO(String id, String dateModified) {
		this.dateModified = dateModified;
		this.id = id;
	}
	
	public String getDateModified() {
		return dateModified;
	}
	
	public void setDateModified(String dateModified) {
		this.dateModified = dateModified;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		
		TenderPageDTO that = (TenderPageDTO) o;
		
		if (id != null ? !id.equals(that.id) : that.id != null)
			return false;
		return dateModified != null ? dateModified.equals(that.dateModified) : that.dateModified == null;
	}
	
	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (dateModified != null ? dateModified.hashCode() : 0);
		return result;
	}
	
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("ProzorroPage{");
		sb.append("dateModified='").append(dateModified).append('\'');
		sb.append(", id='").append(id).append('\'');
		sb.append('}').append('\n');
		return sb.toString();
	}
}
