package ua.prozorro.entity.tenders;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Identifiers")
public class IdentifierDTO {
	@Id
	@Column
	//@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	@Column
	private String scheme;
	@Column
	private String schemeId;
	@Column
	private String uri;
	@Column(length = 4000)
	private String legalName;
	@Column(name = "legalName_en", length = 4000)
	private String legalNameEn;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getSchemeId() {
		return schemeId;
	}
	
	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}
	
	public String getScheme() {
		return scheme;
	}
	
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
	
	public String getUri() {
		return uri;
	}
	
	public void setUri(String uri) {
		this.uri = uri;
	}
	
	public String getLegalName() {
		return legalName;
	}
	
	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}
	
	public String getLegalNameEn() {
		return legalNameEn;
	}
	
	public void setLegalNameEn(String legalNameEn) {
		this.legalNameEn = legalNameEn;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof IdentifierDTO))
			return false;
		
		IdentifierDTO that = (IdentifierDTO) o;
		
		if (id != that.id)
			return false;
		if (scheme != null ? !scheme.equals(that.scheme) : that.scheme != null)
			return false;
		if (schemeId != null ? !schemeId.equals(that.schemeId) : that.schemeId != null)
			return false;
		if (uri != null ? !uri.equals(that.uri) : that.uri != null)
			return false;
		if (legalName != null ? !legalName.equals(that.legalName) : that.legalName != null)
			return false;
		return legalNameEn != null ? legalNameEn.equals(that.legalNameEn) : that.legalNameEn == null;
	}
	
	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (scheme != null ? scheme.hashCode() : 0);
		result = 31 * result + (schemeId != null ? schemeId.hashCode() : 0);
		result = 31 * result + (uri != null ? uri.hashCode() : 0);
		result = 31 * result + (legalName != null ? legalName.hashCode() : 0);
		result = 31 * result + (legalNameEn != null ? legalNameEn.hashCode() : 0);
		return result;
	}
	
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("IdentifierDTO{");
		sb.append("id=").append(id);
		sb.append(", scheme='").append(scheme).append('\'');
		sb.append(", schemeId='").append(schemeId).append('\'');
		sb.append(", uri='").append(uri).append('\'');
		sb.append(", legalName='").append(legalName).append('\'');
		sb.append(", legalNameEn='").append(legalNameEn).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
