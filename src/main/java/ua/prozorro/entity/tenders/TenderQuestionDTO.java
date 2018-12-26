package ua.prozorro.entity.tenders;

import javax.persistence.*;

/*@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinTable(name = "Tenders_Questions", joinColumns = {@JoinColumn(name = "tender_id")}, inverseJoinColumns = {
			@JoinColumn(name = "question_id")})*/
@Entity
@Table(name = "Tenders_Questions")
public class TenderQuestionDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tender_id")
    //@Cascade({org.hibernate.annotations.CascadeType.ALL})
    private TenderDTO tender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    //@Cascade({org.hibernate.annotations.CascadeType.ALL})
    private QuestionDTO questionDTO;

    public TenderQuestionDTO() {
    }

    public TenderQuestionDTO(TenderDTO tender, QuestionDTO questionDTO) {
        this.tender = tender;
        this.questionDTO = questionDTO;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TenderDTO getTender() {
        return tender;
    }

    public void setTender(TenderDTO tender) {
        this.tender = tender;
    }

    public QuestionDTO getQuestionDTO() {
        return questionDTO;
    }

    public void setQuestionDTO(QuestionDTO questionDTO) {
        this.questionDTO = questionDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof TenderQuestionDTO))
            return false;

        TenderQuestionDTO that = (TenderQuestionDTO) o;

        if (id != that.id)
            return false;
        if (tender != null ? !tender.equals(that.tender) : that.tender != null)
            return false;
        return questionDTO != null ? questionDTO.equals(that.questionDTO) : that.questionDTO == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (tender != null ? tender.hashCode() : 0);
        result = 31 * result + (questionDTO != null ? questionDTO.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TenderQuestionDTO{");
        sb.append("id=").append(id);
        sb.append(", tender=").append(tender);
        sb.append(", questionDTO=").append(questionDTO);
        sb.append('}');
        return sb.toString();
    }
}
