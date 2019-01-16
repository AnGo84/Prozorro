package ua.prozorro.entity.tenders;

/*@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
@JoinTable(name = "Tenders_Documents", joinColumns = {@JoinColumn(name = "tender_id")}, inverseJoinColumns = {
        @JoinColumn(name = "document_id")})*/

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Contracts_Documents")
public class ContractDocumentDTO {

    @Id
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
    private DocumentDTO document;

    public ContractDocumentDTO() {
    }

    public ContractDocumentDTO(ContractDTO contract, DocumentDTO document) {
        this.contract = contract;
        this.document = document;
        //this.id = 31 * tender.getId().hashCode() + document.getId().hashCode();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ContractDTO getContract() {
        return contract;
    }

    public void setContract(ContractDTO contract) {
        this.contract = contract;
    }

    public DocumentDTO getDocument() {
        return document;
    }

    public void setDocument(DocumentDTO document) {
        this.document = document;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContractDocumentDTO that = (ContractDocumentDTO) o;

        if (id != that.id) return false;
        if (contract != null ? !contract.equals(that.contract) : that.contract != null) return false;
        return document != null ? document.equals(that.document) : that.document == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (contract != null ? contract.hashCode() : 0);
        result = 31 * result + (document != null ? document.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ContractDocumentDTO{");
        sb.append("id=").append(id);
        sb.append(", contract=").append(contract);
        sb.append(", document=").append(document);
        sb.append('}');
        return sb.toString();
    }
}
