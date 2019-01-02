package ua.prozorro.entity.tenders;

import javax.persistence.*;

@Entity
@Table(name = "Questions")
public class QuestionDTO {
    @Id
    //@GeneratedValue(strategy= GenerationType.AUTO)
    @Column
    private String id;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "author_id")
    private OrganizationDTO author;
    @Column(length = 4000)
    private String title;
    //@Column(length = 8000)
    @Lob
    @Column
    private String description;
    @Column
    private String date;
    @Column
    private String dateAnswered;
    //@Column(length = 4000)
    @Lob
    @Column
    private String answer;
    @Column(length = 4000)
    private String questionOf;
    @Column
    private String relatedItem;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public OrganizationDTO getAuthor() {
        return author;
    }

    public void setAuthor(OrganizationDTO author) {
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
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        QuestionDTO that = (QuestionDTO) o;

        if (id != null ? !id.equals(that.id) : that.id != null)
            return false;
        if (author != null ? !author.equals(that.author) : that.author != null)
            return false;
        if (title != null ? !title.equals(that.title) : that.title != null)
            return false;
        if (description != null ? !description.equals(that.description) : that.description != null)
            return false;
        if (date != null ? !date.equals(that.date) : that.date != null)
            return false;
        if (dateAnswered != null ? !dateAnswered.equals(that.dateAnswered) : that.dateAnswered != null)
            return false;
        if (answer != null ? !answer.equals(that.answer) : that.answer != null)
            return false;
        if (questionOf != null ? !questionOf.equals(that.questionOf) : that.questionOf != null)
            return false;
        return relatedItem != null ? relatedItem.equals(that.relatedItem) : that.relatedItem == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (dateAnswered != null ? dateAnswered.hashCode() : 0);
        result = 31 * result + (answer != null ? answer.hashCode() : 0);
        result = 31 * result + (questionOf != null ? questionOf.hashCode() : 0);
        result = 31 * result + (relatedItem != null ? relatedItem.hashCode() : 0);
        return result;
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
