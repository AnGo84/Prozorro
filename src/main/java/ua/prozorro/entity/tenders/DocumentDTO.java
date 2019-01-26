package ua.prozorro.entity.tenders;

import javax.persistence.*;

@Entity
@Table(name = "Documents")
public class DocumentDTO {
    @Id
    //@GeneratedValue(strategy= GenerationType.AUTO)
    @Column
    private String id;
    @Column
    private String hash;
    //@Column(length = 8000)
    @Lob
    @Column
    private String description;
    @Column(length = 4000)
    private String title;
    @Column(length = 2000)
    private String url;
    @Column
    private String format;
    @Column
    private String titleEn;
    @Column
    private String documentOf;
    @Column
    private String datePublished;
    @Column
    private String author;
    @Column(length = 4000)
    private String titleRu;
    @Column
    private String documentType;
    @Column
    private String dateModified;

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getTitleEn() {
        return titleEn;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }

    public String getDocumentOf() {
        return documentOf;
    }

    public void setDocumentOf(String documentOf) {
        this.documentOf = documentOf;
    }

    public String getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(String datePublished) {
        this.datePublished = datePublished;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitleRu() {
        return titleRu;
    }

    public void setTitleRu(String titleRu) {
        this.titleRu = titleRu;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
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

        DocumentDTO that = (DocumentDTO) o;

        if (id != null ? !id.equals(that.id) : that.id != null)
            return false;
        if (hash != null ? !hash.equals(that.hash) : that.hash != null)
            return false;
        if (description != null ? !description.equals(that.description) : that.description != null)
            return false;
        if (title != null ? !title.equals(that.title) : that.title != null)
            return false;
        if (url != null ? !url.equals(that.url) : that.url != null)
            return false;
        if (format != null ? !format.equals(that.format) : that.format != null)
            return false;
        if (titleEn != null ? !titleEn.equals(that.titleEn) : that.titleEn != null)
            return false;
        if (documentOf != null ? !documentOf.equals(that.documentOf) : that.documentOf != null)
            return false;
        if (datePublished != null ? !datePublished.equals(that.datePublished) : that.datePublished != null)
            return false;
        if (author != null ? !author.equals(that.author) : that.author != null)
            return false;
        if (titleRu != null ? !titleRu.equals(that.titleRu) : that.titleRu != null)
            return false;
        if (documentType != null ? !documentType.equals(that.documentType) : that.documentType != null)
            return false;
        return dateModified != null ? dateModified.equals(that.dateModified) : that.dateModified == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (hash != null ? hash.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (format != null ? format.hashCode() : 0);
        result = 31 * result + (titleEn != null ? titleEn.hashCode() : 0);
        result = 31 * result + (documentOf != null ? documentOf.hashCode() : 0);
        result = 31 * result + (datePublished != null ? datePublished.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (titleRu != null ? titleRu.hashCode() : 0);
        result = 31 * result + (documentType != null ? documentType.hashCode() : 0);
        result = 31 * result + (dateModified != null ? dateModified.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DocumentDTO{");
        sb.append("hash='").append(hash).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", url='").append(url).append('\'');
        sb.append(", format='").append(format).append('\'');
        sb.append(", titleEn='").append(titleEn).append('\'');
        sb.append(", documentOf='").append(documentOf).append('\'');
        sb.append(", datePublished='").append(datePublished).append('\'');
        sb.append(", author='").append(author).append('\'');
        sb.append(", titleRu='").append(titleRu).append('\'');
        sb.append(", documentType='").append(documentType).append('\'');
        sb.append(", dateModified='").append(dateModified).append('\'');
        sb.append(", id='").append(id).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

