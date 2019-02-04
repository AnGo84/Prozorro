package ua.prozorro.prozorro.model.tenders;

import com.google.gson.annotations.SerializedName;

public class Classification {
	@SerializedName("scheme")
	//@Expose
	
	private String scheme;
	@SerializedName("description")
	private String description;
	@SerializedName("id")
	private String id;
	
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
	public String toString() {
		final StringBuffer sb = new StringBuffer("ClassificationDTO{");
		sb.append("scheme='").append(scheme).append('\'');
		sb.append(", description='").append(description).append('\'');
		sb.append(", id='").append(id).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
