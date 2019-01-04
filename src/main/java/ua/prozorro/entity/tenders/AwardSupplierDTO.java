package ua.prozorro.entity.tenders;

/*@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinTable(name = "Awards_Suppliers", joinColumns = {@JoinColumn(name = "award_id")}, inverseJoinColumns = {
			@JoinColumn(name = "supplier_id")})*/


import javax.persistence.*;

@Entity
@Table(name = "Awards_Suppliers")
public class AwardSupplierDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "award_id")
    //@Cascade({org.hibernate.annotations.CascadeType.ALL})
    private AwardDTO award;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    //@Cascade({org.hibernate.annotations.CascadeType.ALL})
    private OrganizationDTO organization;

    public AwardSupplierDTO() {
    }

    public AwardSupplierDTO(AwardDTO award, OrganizationDTO organization) {
        this.award = award;
        this.organization = organization;
        //this.id = 31 * award.getId().hashCode() + organization.getId();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public AwardDTO getAward() {
        return award;
    }

    public void setAward(AwardDTO award) {
        this.award = award;
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
        if (!(o instanceof AwardSupplierDTO))
            return false;

        AwardSupplierDTO that = (AwardSupplierDTO) o;

        if (id != that.id)
            return false;
        if (award != null ? !award.equals(that.award) : that.award != null)
            return false;
        return organization != null ? organization.equals(that.organization) : that.organization == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (award != null ? award.hashCode() : 0);
        result = 31 * result + (organization != null ? organization.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AwardSupplierDTO{");
        sb.append("id=").append(id);
        sb.append(", award=").append(award);
        sb.append(", organization=").append(organization);
        sb.append('}');
        return sb.toString();
    }
}
