package ua.prozorro.entity.tenders;

import javax.persistence.*;

/*@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinTable(name = "Tenders_Complaints", joinColumns = {@JoinColumn(name = "tender_id")}, inverseJoinColumns = {
			@JoinColumn(name = "complaint_id")})*/
@Entity
@Table(name = "Tenders_Complaints")
public class TenderComplaintDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tender_id")
    //@Cascade({org.hibernate.annotations.CascadeType.ALL})
    private TenderDTO tender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "complaint_id")
    //@Cascade({org.hibernate.annotations.CascadeType.ALL})
    private ComplaintDTO complaintDTO;

    public TenderComplaintDTO() {
    }

    public TenderComplaintDTO(TenderDTO tender, ComplaintDTO complaintDTO) {
        this.tender = tender;
        this.complaintDTO = complaintDTO;
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

    public ComplaintDTO getComplaintDTO() {
        return complaintDTO;
    }

    public void setComplaintDTO(ComplaintDTO complaintDTO) {
        this.complaintDTO = complaintDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof TenderComplaintDTO))
            return false;

        TenderComplaintDTO that = (TenderComplaintDTO) o;

        if (id != that.id)
            return false;
        if (tender != null ? !tender.equals(that.tender) : that.tender != null)
            return false;
        return complaintDTO != null ? complaintDTO.equals(that.complaintDTO) : that.complaintDTO == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (tender != null ? tender.hashCode() : 0);
        result = 31 * result + (complaintDTO != null ? complaintDTO.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TenderComplaintDTO{");
        sb.append("id=").append(id);
        sb.append(", tender=").append(tender);
        sb.append(", complaintDTO=").append(complaintDTO);
        sb.append('}');
        return sb.toString();
    }
}
