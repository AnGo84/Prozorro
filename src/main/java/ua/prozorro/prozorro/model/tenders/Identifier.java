package ua.prozorro.prozorro.model.tenders;


public class Identifier {
	private String scheme;
	private String id;
	private String uri;
	private String legalName;

	public String getScheme() {
		return scheme;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Identifier that = (Identifier) o;

		if (scheme != null ? !scheme.equals(that.scheme) : that.scheme != null) return false;
		if (id != null ? !id.equals(that.id) : that.id != null) return false;
		if (uri != null ? !uri.equals(that.uri) : that.uri != null) return false;
		return legalName != null ? legalName.equals(that.legalName) : that.legalName == null;
	}

	@Override
	public int hashCode() {
		int result = scheme != null ? scheme.hashCode() : 0;
		result = 31 * result + (id != null ? id.hashCode() : 0);
		result = 31 * result + (uri != null ? uri.hashCode() : 0);
		result = 31 * result + (legalName != null ? legalName.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("tenders.IdentifierDTO{");
		sb.append("scheme='").append(scheme).append('\'');
		sb.append(", id='").append(id).append('\'');
		sb.append(", uri='").append(uri).append('\'');
		sb.append(", legalName='").append(legalName).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
