package ua.prozorro.entity.tenders;

/*@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinTable(name = "Tenders_Awards", joinColumns = {@JoinColumn(name = "tender_id")}, inverseJoinColumns = {
			@JoinColumn(name = "award_id")})*/

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Tenders_Awards")
public class TenderAwardDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tender_id")
    //@Cascade({org.hibernate.annotations.CascadeType.ALL})
    private TenderDTO tender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "award_id")
    //@Cascade({org.hibernate.annotations.CascadeType.ALL})
    private AwardDTO awardDTO;

    public TenderAwardDTO() {
    }

    public TenderAwardDTO(TenderDTO tender, AwardDTO awardDTO) {
        this.tender = tender;
        this.awardDTO = awardDTO;
        //this.id = 31 * tender.getId().hashCode() + awardDTO.getId().hashCode();
    }

    public TenderDTO getTender() {
        return tender;
    }

    public void setTender(TenderDTO tender) {
        this.tender = tender;
    }

    public AwardDTO getAwardDTO() {
        return awardDTO;
    }

    public void setAwardDTO(AwardDTO awardDTO) {
        this.awardDTO = awardDTO;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof TenderAwardDTO))
            return false;
        TenderAwardDTO that = (TenderAwardDTO) o;
        return id == that.id && Objects.equals(tender, that.tender) && Objects.equals(awardDTO, that.awardDTO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tender, awardDTO);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TenderAwardDTO{");
        sb.append("id=").append(id);
        sb.append(", tender=").append(tender);
        sb.append(", awardDTO=").append(awardDTO);
        sb.append('}');
        return sb.toString();
    }
}
