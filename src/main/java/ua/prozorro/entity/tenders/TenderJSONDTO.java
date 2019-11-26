package ua.prozorro.entity.tenders;

import ua.prozorro.entity.plans.pk.JSONPK;

import javax.persistence.*;

@Entity
@Table(name = "tenders_JSON")
@IdClass(JSONPK.class)
public class TenderJSONDTO {
	
	@Id
	//@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name = "tender_id")
	private String id;
	@Id
	@Column(name = "dateModified")
	private String dateModified;
	
	@Lob
	@Column(name = "model")
	private String model;
	
	public TenderJSONDTO() {
	}
	
	public TenderJSONDTO(String id, String dateModified) {
		this.id = id;
		this.dateModified = dateModified;
	}
	
	public TenderJSONDTO(String id, String dateModified, String model) {
		this.id = id;
		this.dateModified = dateModified;
		this.model = model;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getDateModified() {
		return dateModified;
	}
	
	public void setDateModified(String dateModified) {
		this.dateModified = dateModified;
	}
	
	public String getModel() {
		return model;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof TenderJSONDTO))
			return false;
		
		TenderJSONDTO that = (TenderJSONDTO) o;
		
		if (id != null ? !id.equals(that.id) : that.id != null)
			return false;
		if (dateModified != null ? !dateModified.equals(that.dateModified) : that.dateModified != null)
			return false;
		return model != null ? model.equals(that.model) : that.model == null;
	}
	
	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (dateModified != null ? dateModified.hashCode() : 0);
		result = 31 * result + (model != null ? model.hashCode() : 0);
		return result;
	}
	
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("TenderJSONDTO{");
		sb.append("id='").append(id).append('\'');
		sb.append(", dateModified='").append(dateModified).append('\'');
		sb.append(", model='").append(model).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
