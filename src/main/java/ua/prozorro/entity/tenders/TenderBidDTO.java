package ua.prozorro.entity.tenders;

import ua.prozorro.entity.tenders.pk.TenderBidPK;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Tenders_Bids")
@IdClass(TenderBidPK.class)
public class TenderBidDTO {

    @Id
    @Column(name = "tender_id")
    private String tenderId;

    @Id
    @Column(name = "bid_id")
    private String bidId;

    public TenderBidDTO() {
    }

    public TenderBidDTO(String tenderId, String bidId) {
        this.tenderId = tenderId;
        this.bidId = bidId;
    }

    public String getTenderId() {
        return tenderId;
    }

    public void setTenderId(String tenderId) {
        this.tenderId = tenderId;
    }

    public String getBidId() {
        return bidId;
    }

    public void setBidId(String bidId) {
        this.bidId = bidId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TenderBidDTO that = (TenderBidDTO) o;

        if (tenderId != null ? !tenderId.equals(that.tenderId) : that.tenderId != null) return false;
        return bidId != null ? bidId.equals(that.bidId) : that.bidId == null;
    }

    @Override
    public int hashCode() {
        int result = tenderId != null ? tenderId.hashCode() : 0;
        result = 31 * result + (bidId != null ? bidId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("TenderBidDTO{");
        sb.append("tenderId='").append(tenderId).append('\'');
        sb.append(", bidId='").append(bidId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
