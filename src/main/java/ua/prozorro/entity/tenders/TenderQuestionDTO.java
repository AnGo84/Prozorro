package ua.prozorro.entity.tenders;

import ua.prozorro.entity.tenders.pk.TenderQuestionPK;

import javax.persistence.*;

@Entity
@Table(name = "Tenders_Questions")
@IdClass(TenderQuestionPK.class)
public class TenderQuestionDTO {
    @Id
    @Column(name = "tender_id")
    private String tenderId;

    @Id
    @Column(name = "question_id")
    private String questionId;

    public TenderQuestionDTO() {
    }

    public TenderQuestionDTO(String tenderId, String questionId) {
        this.tenderId = tenderId;
        this.questionId = questionId;
    }

    public String getTenderId() {
        return tenderId;
    }

    public void setTenderId(String tenderId) {
        this.tenderId = tenderId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TenderQuestionDTO that = (TenderQuestionDTO) o;

        if (tenderId != null ? !tenderId.equals(that.tenderId) : that.tenderId != null) return false;
        return questionId != null ? questionId.equals(that.questionId) : that.questionId == null;
    }

    @Override
    public int hashCode() {
        int result = tenderId != null ? tenderId.hashCode() : 0;
        result = 31 * result + (questionId != null ? questionId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("TenderQuestionDTO{");
        sb.append("tenderId='").append(tenderId).append('\'');
        sb.append(", questionId='").append(questionId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
