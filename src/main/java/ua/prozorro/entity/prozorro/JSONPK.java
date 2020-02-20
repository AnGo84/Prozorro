package ua.prozorro.entity.prozorro;

import java.io.Serializable;

public class JSONPK implements Serializable {
	
	private String id;
	private String dateModified;

	public JSONPK() {
	}
	
	public JSONPK(String id, String dateModified) {
		this.id = id;
		this.dateModified = dateModified;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof JSONPK))
			return false;
		
		JSONPK jsonpk = (JSONPK) o;
		
		if (id != null ? !id.equals(jsonpk.id) : jsonpk.id != null)
			return false;
		return dateModified != null ? dateModified.equals(jsonpk.dateModified) : jsonpk.dateModified == null;
	}
	
	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (dateModified != null ? dateModified.hashCode() : 0);
		return result;
	}
	
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("JSONPK{");
		sb.append("id='").append(id).append('\'');
		sb.append(", dateModified='").append(dateModified).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
