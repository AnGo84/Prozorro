package ua.prozorro.entity;

public class PageElement {

    protected String id;

    protected String dateModified;

    public PageElement() {
    }

    public PageElement(String id, String dateModified) {
        this.dateModified = dateModified;
        this.id = id;
    }

    public String getDateModified() {
        return dateModified;
    }

    public String getId() {
        return id;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
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

        PageElement that = (PageElement) o;

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
		final StringBuilder sb = new StringBuilder("PageElement{");
		sb.append("id='").append(id).append('\'');
		sb.append(", dateModified='").append(dateModified).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
