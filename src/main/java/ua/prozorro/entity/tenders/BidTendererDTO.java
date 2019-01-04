package ua.prozorro.entity.tenders;

import javax.persistence.*;

/*@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "Bids_Organizations", joinColumns = {@JoinColumn(name = "bid_id")},
            inverseJoinColumns = {@JoinColumn(name = "organization_id")})*/
@Entity
@Table(name = "Bids_Organizations")
public class BidTendererDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bid_id")
    //@Cascade({org.hibernate.annotations.CascadeType.ALL})
    private BidDTO bid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_id")
    //@Cascade({org.hibernate.annotations.CascadeType.ALL})
    private OrganizationDTO organization;

    public BidTendererDTO() {
    }

    public BidTendererDTO(BidDTO bid, OrganizationDTO organization) {
        this.bid = bid;
        this.organization = organization;
        //this.id = 31 * bid.getId().hashCode() + organization.getId();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BidDTO getBid() {
        return bid;
    }

    public void setBid(BidDTO bid) {
        this.bid = bid;
    }

    public OrganizationDTO getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationDTO organization) {
        this.organization = organization;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof BidTendererDTO))
            return false;

        BidTendererDTO that = (BidTendererDTO) o;

        if (id != that.id)
            return false;
        if (bid != null ? !bid.equals(that.bid) : that.bid != null)
            return false;
        return organization != null ? organization.equals(that.organization) : that.organization == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (bid != null ? bid.hashCode() : 0);
        result = 31 * result + (organization != null ? organization.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BidTendererDTO{");
        sb.append("id=").append(id);
        sb.append(", bid=").append(bid);
        sb.append(", organization=").append(organization);
        sb.append('}');
        return sb.toString();
    }
}
