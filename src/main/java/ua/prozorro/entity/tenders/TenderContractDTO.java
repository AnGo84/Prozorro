package ua.prozorro.entity.tenders;

/*@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinTable(name = "Tenders_Contracts", joinColumns = {@JoinColumn(name = "tender_id")}, inverseJoinColumns = {
			@JoinColumn(name = "contract_id")})*/

import javax.persistence.*;

@Entity
@Table(name = "Tenders_Contracts")
public class TenderContractDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;
    @Column(name = "tender_id")
    private String tenderId;
    @Column(name = "contract_id")
    private String contractId;

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tender_id")
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private TenderDTO tender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_id")
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private ContractDTO contract;*/

    public TenderContractDTO() {
    }

    public TenderContractDTO(String tenderId, String contractId) {
        this.tenderId = tenderId;
        this.contractId = contractId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTenderId() {
        return tenderId;
    }

    public void setTenderId(String tenderId) {
        this.tenderId = tenderId;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof TenderContractDTO))
            return false;

        TenderContractDTO that = (TenderContractDTO) o;

        if (id != that.id)
            return false;
        if (tenderId != null ? !tenderId.equals(that.tenderId) : that.tenderId != null)
            return false;
        return contractId != null ? contractId.equals(that.contractId) : that.contractId == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (tenderId != null ? tenderId.hashCode() : 0);
        result = 31 * result + (contractId != null ? contractId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TenderContractDTO{");
        sb.append("id=").append(id);
        sb.append(", tenderId='").append(tenderId).append('\'');
        sb.append(", contractId='").append(contractId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

