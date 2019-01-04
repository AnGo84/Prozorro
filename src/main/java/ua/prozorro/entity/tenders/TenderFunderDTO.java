package ua.prozorro.entity.tenders;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Objects;

/*@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
@JoinTable(name = "Tenders_Funders", joinColumns = {@JoinColumn(name = "tender_id")}, inverseJoinColumns = {
        @JoinColumn(name = "organization_id")})*/
@Entity
@Table(name = "Tenders_Funders")
public class TenderFunderDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tender_id")
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private TenderDTO tender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id")
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private OrganizationDTO funder;

    public TenderFunderDTO() {
    }

    public TenderFunderDTO(TenderDTO tender, OrganizationDTO funder) {
        this.tender = tender;
        this.funder = funder;
        //this.id = 31 * tender.getId().hashCode() + funder.getId();
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

    public OrganizationDTO getFunder() {
        return funder;
    }

    public void setFunder(OrganizationDTO funder) {
        this.funder = funder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof TenderFunderDTO))
            return false;
        TenderFunderDTO that = (TenderFunderDTO) o;
        return id == that.id && Objects.equals(tender, that.tender) && Objects.equals(funder, that.funder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tender, funder);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TenderFunderDTO{");
        sb.append("id=").append(id);
        sb.append(", tender=").append(tender);
        sb.append(", funder=").append(funder);
        sb.append('}');
        return sb.toString();
    }
}
