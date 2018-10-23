package ua.prozorro.model.tenders;

/**
 * Created by AnGo on 28.11.2016.
 */
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
    public String toString() {
        final StringBuilder sb = new StringBuilder("tenders.Identifier{");
        sb.append("scheme='").append(scheme).append('\'');
        sb.append(", id='").append(id).append('\'');
        sb.append(", uri='").append(uri).append('\'');
        sb.append(", legalName='").append(legalName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
