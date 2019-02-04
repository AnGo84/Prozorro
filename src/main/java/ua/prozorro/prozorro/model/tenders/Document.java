package ua.prozorro.prozorro.model.tenders;

import java.io.Serializable;

public class Document implements Serializable {
	private final static long serialVersionUID = 8338166863039670007L;
	
	private String hash;
	private String description;
	
	private String title;
	
	private String url;
	
	private String format;
	
	private String titleEn;
	
	private String documentOf;
	
	private String datePublished;
	
	private String author;
	
	private String titleRu;
	
	private String documentType;
	
	private String dateModified;
	
	private String id;
	
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

