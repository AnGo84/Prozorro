package sample.prozorro.pages;

/**
 * Created by AnGo on 29.10.2016.
 */
public class PageElement {
    private String id;
    private String dateModified;



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
    public String toString() {
        final StringBuilder sb = new StringBuilder("PageElement{");
        sb.append("dateModified='").append(dateModified).append('\'');
        sb.append(", id='").append(id).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
