package ua.prozorro.prozorro.model.tenders;

public class Question {
	private String id;
	private Organization author;
	private String title;
	private String description;
	private String date;
	private String dateAnswered;
	private String answer;
	private String questionOf;
	private String relatedItem;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public Organization getAuthor() {
		return author;
	}
	
	public void setAuthor(Organization author) {
		this.author = author;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getDateAnswered() {
		return dateAnswered;
	}
	
	public void setDateAnswered(String dateAnswered) {
		this.dateAnswered = dateAnswered;
	}
	
	public String getAnswer() {
		return answer;
	}
	
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	public String getQuestionOf() {
		return questionOf;
	}
	
	public void setQuestionOf(String questionOf) {
		this.questionOf = questionOf;
	}
	
	public String getRelatedItem() {
		return relatedItem;
	}
	
	public void setRelatedItem(String relatedItem) {
		this.relatedItem = relatedItem;
	}
	
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("QuestionDTO{");
		sb.append("id='").append(id).append('\'');
		sb.append(", author=").append(author);
		sb.append(", title='").append(title).append('\'');
		sb.append(", description='").append(description).append('\'');
		sb.append(", date='").append(date).append('\'');
		sb.append(", dateAnswered='").append(dateAnswered).append('\'');
		sb.append(", answer='").append(answer).append('\'');
		sb.append(", questionOf='").append(questionOf).append('\'');
		sb.append(", relatedItem='").append(relatedItem).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
