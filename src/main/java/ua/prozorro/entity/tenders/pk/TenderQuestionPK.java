package ua.prozorro.entity.tenders.pk;

import java.io.Serializable;

public class TenderQuestionPK implements Serializable {

	protected String tenderId;
	protected String questionId;


	public TenderQuestionPK() {
	}

	public TenderQuestionPK(String tenderId, String questionId) {
		this.tenderId = tenderId;
		this.questionId = questionId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		TenderQuestionPK that = (TenderQuestionPK) o;

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
		final StringBuffer sb = new StringBuffer("TenderQuestionPK{");
		sb.append("tenderId='").append(tenderId).append('\'');
		sb.append(", questionId='").append(questionId).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
