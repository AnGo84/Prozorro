package ua.prozorro.entity.tenders;

import ua.prozorro.entity.tenders.pk.BidTendererPK;

import javax.persistence.*;

/*@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "Bids_Organizations", joinColumns = {@JoinColumn(name = "bid_id")},
            inverseJoinColumns = {@JoinColumn(name = "organization_id")})*/
@Entity
@Table(name = "Bids_Organizations")
@IdClass(BidTendererPK.class)
public class BidTendererDTO {
    @Id
    @JoinColumn(name = "bid_id")
    private String bidId;

    @Id
    @Column(name = "organization_id")
    private int tendererId;

    public BidTendererDTO() {
    }

    public BidTendererDTO(String bidId, int tendererId) {
        this.bidId = bidId;
        this.tendererId = tendererId;
    }

    public String getBidId() {
        return bidId;
    }

    public void setBidId(String bidId) {
        this.bidId = bidId;
    }

    public int getTendererId() {
        return tendererId;
    }

    public void setTendererId(int tendererId) {
        this.tendererId = tendererId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BidTendererDTO that = (BidTendererDTO) o;

        if (tendererId != that.tendererId) return false;
        return bidId != null ? bidId.equals(that.bidId) : that.bidId == null;
    }

    @Override
    public int hashCode() {
        int result = bidId != null ? bidId.hashCode() : 0;
        result = 31 * result + tendererId;
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("BidTendererDTO{");
        sb.append("bidId='").append(bidId).append('\'');
        sb.append(", tendererId=").append(tendererId);
        sb.append('}');
        return sb.toString();
    }
}
