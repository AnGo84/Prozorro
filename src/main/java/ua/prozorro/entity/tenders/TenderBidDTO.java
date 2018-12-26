package ua.prozorro.entity.tenders;

/*@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinTable(name = "Tenders_Bids", joinColumns = {@JoinColumn(name = "tender_id")}, inverseJoinColumns = {
			@JoinColumn(name = "bid_id")})*/

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Tenders_Bids")
public class TenderBidDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tender_id")
    //@Cascade({org.hibernate.annotations.CascadeType.ALL})
    private TenderDTO tender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bid_id")
    //@Cascade({org.hibernate.annotations.CascadeType.ALL})
    private BidDTO bidDTO;

    public TenderBidDTO() {
    }

    public TenderBidDTO(TenderDTO tender, BidDTO bidDTO) {
        this.tender = tender;
        this.bidDTO = bidDTO;
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

    public BidDTO getBidDTO() {
        return bidDTO;
    }

    public void setBidDTO(BidDTO bidDTO) {
        this.bidDTO = bidDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof TenderBidDTO))
            return false;
        TenderBidDTO that = (TenderBidDTO) o;
        return id == that.id && Objects.equals(tender, that.tender) && Objects.equals(bidDTO, that.bidDTO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tender, bidDTO);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TenderBidDTO{");
        sb.append("id=").append(id);
        sb.append(", tender=").append(tender);
        sb.append(", bidDTO=").append(bidDTO);
        sb.append('}');
        return sb.toString();
    }
}
