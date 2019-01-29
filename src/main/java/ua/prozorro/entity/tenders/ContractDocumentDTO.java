package ua.prozorro.entity.tenders;

import ua.prozorro.entity.tenders.pk.ContractDocumentPK;
import ua.prozorro.entity.tenders.pk.FeatureFeatureEnumPK;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Contracts_Documents")
@IdClass(ContractDocumentPK.class)
public class ContractDocumentDTO {

    /*@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_id")
    //@Cascade({org.hibernate.annotations.CascadeType.ALL})
    private ContractDTO contract;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_id")
    //@Cascade({org.hibernate.annotations.CascadeType.ALL})
    private DocumentDTO document;*/

    @Id
    @Column(name = "contract_id")
    private String contractId;
    @Id
    @Column(name = "document_id")
    private String documentId;

    public ContractDocumentDTO() {
    }

    public ContractDocumentDTO(String contractId, String documentId) {
        this.contractId = contractId;
        this.documentId = documentId;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContractDocumentDTO that = (ContractDocumentDTO) o;

        if (contractId != null ? !contractId.equals(that.contractId) : that.contractId != null) return false;
        return documentId != null ? documentId.equals(that.documentId) : that.documentId == null;
    }

    @Override
    public int hashCode() {
        int result = contractId != null ? contractId.hashCode() : 0;
        result = 31 * result + (documentId != null ? documentId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ContractDocumentDTO{");
        sb.append("contractId='").append(contractId).append('\'');
        sb.append(", documentId='").append(documentId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
