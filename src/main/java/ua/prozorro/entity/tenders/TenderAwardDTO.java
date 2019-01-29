package ua.prozorro.entity.tenders;

import ua.prozorro.entity.tenders.pk.TenderAwardPK;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Tenders_Awards")
@IdClass(TenderAwardPK.class)
public class TenderAwardDTO {

    @Id
    @Column(name = "tender_id")
    private String tenderId;

    @Id
    @Column(name = "award_id")
    private String awardId;

    public TenderAwardDTO() {
    }

    public TenderAwardDTO(String tenderId, String awardId) {
        this.tenderId = tenderId;
        this.awardId = awardId;
    }

    public String getTenderId() {
        return tenderId;
    }

    public void setTenderId(String tenderId) {
        this.tenderId = tenderId;
    }

    public String getAwardId() {
        return awardId;
    }

    public void setAwardId(String awardId) {
        this.awardId = awardId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TenderAwardDTO that = (TenderAwardDTO) o;

        if (tenderId != null ? !tenderId.equals(that.tenderId) : that.tenderId != null) return false;
        return awardId != null ? awardId.equals(that.awardId) : that.awardId == null;
    }

    @Override
    public int hashCode() {
        int result = tenderId != null ? tenderId.hashCode() : 0;
        result = 31 * result + (awardId != null ? awardId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("TenderAwardDTO{");
        sb.append("tenderId='").append(tenderId).append('\'');
        sb.append(", awardId='").append(awardId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
