package ua.prozorro.model.pages;

import com.google.gson.annotations.SerializedName;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ALL_tenders_IMPORT")
public class PageElement {

    @SerializedName("id")
    @Id
    @Column(name = "contracts_ID")
    private String id;

    @SerializedName("dateModified")
    @Column(name = "dateModified")
    private String dateModified;

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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PageElement that = (PageElement) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
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
        sb.append("dateModified='").append(dateModified).append('\'');
        sb.append(", id='").append(id).append('\'');
        sb.append('}').append('\n');
        return sb.toString();
    }
}
