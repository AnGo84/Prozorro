package ua.prozorro.entity.tenders;

import com.google.gson.annotations.SerializedName;
import ua.prozorro.entity.plans.ProcuringEntityDTO;
import ua.prozorro.prozorro.model.plans.ProcuringEntity;
import ua.prozorro.prozorro.model.tenders.Document;
import ua.prozorro.prozorro.model.tenders.Period;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Contracts")
public class ContractDTO {
    @Id
    //@GeneratedValue(strategy= GenerationType.AUTO)
    @Column
    private String id;

    @Column
    private String status;

    /*@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "Contracts_Items", joinColumns = { @JoinColumn(name = "contract_id") }, inverseJoinColumns = {
            @JoinColumn(name = "item_id") })*/
    @Transient
    private List<ItemDTO> items = null;

    /*@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "Contracts_Organizations", joinColumns = { @JoinColumn(name = "contract_id") }, inverseJoinColumns = {
            @JoinColumn(name = "organization_id") })*/
    @Transient
    private List<OrganizationDTO> suppliers = null;

    @Column
    private String currency;
    @Column
    private String amount;
    @Column
    private String valueAddedTaxIncluded;

    @Column(name = "award_id")
    private String awardID;

    @Column(name = "contract_id")
    private String contractID;

    @Transient
    private List<DocumentDTO> documents = null;

    @Column
    private String tenderId;

    @Lob
    @Column
    private String description;

    @Column(length = 4000)
    private String title;

    @Column
    private String contractNumber;

    @Column(name = "start_date")
    private String contractPeriodStartDate;
    @Column(name = "clarifications_until")
    private String contractPeriodClarificationsUntil;
    @Column(name = "end_date")
    private String contractPeriodEndDate;
    @Column(name = "invalidation_date")
    private String contractPeriodInvalidationDate;

    @Column
    private String dateSigned;

    @Column
    private String dateModified;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "procuringEntity_id")
    private ProcuringEntityDTO procuringEntity;

    @Column
    private String owner;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ItemDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemDTO> items) {
        this.items = items;
    }

    public List<OrganizationDTO> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<OrganizationDTO> suppliers) {
        this.suppliers = suppliers;
    }

    public String getAwardID() {
        return awardID;
    }

    public void setAwardID(String awardID) {
        this.awardID = awardID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContractID() {
        return contractID;
    }

    public void setContractID(String contractID) {
        this.contractID = contractID;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getValueAddedTaxIncluded() {
        return valueAddedTaxIncluded;
    }

    public void setValueAddedTaxIncluded(String valueAddedTaxIncluded) {
        this.valueAddedTaxIncluded = valueAddedTaxIncluded;
    }

    public List<DocumentDTO> getDocuments() {
        return documents;
    }

    public void setDocuments(List<DocumentDTO> documents) {
        this.documents = documents;
    }

    public String getTenderId() {
        return tenderId;
    }

    public void setTenderId(String tenderId) {
        this.tenderId = tenderId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getContractPeriodStartDate() {
        return contractPeriodStartDate;
    }

    public void setContractPeriodStartDate(String contractPeriodStartDate) {
        this.contractPeriodStartDate = contractPeriodStartDate;
    }

    public String getContractPeriodClarificationsUntil() {
        return contractPeriodClarificationsUntil;
    }

    public void setContractPeriodClarificationsUntil(String contractPeriodClarificationsUntil) {
        this.contractPeriodClarificationsUntil = contractPeriodClarificationsUntil;
    }

    public String getContractPeriodEndDate() {
        return contractPeriodEndDate;
    }

    public void setContractPeriodEndDate(String contractPeriodEndDate) {
        this.contractPeriodEndDate = contractPeriodEndDate;
    }

    public String getContractPeriodInvalidationDate() {
        return contractPeriodInvalidationDate;
    }

    public void setContractPeriodInvalidationDate(String contractPeriodInvalidationDate) {
        this.contractPeriodInvalidationDate = contractPeriodInvalidationDate;
    }

    public String getDateSigned() {
        return dateSigned;
    }

    public void setDateSigned(String dateSigned) {
        this.dateSigned = dateSigned;
    }

    public String getDateModified() {
        return dateModified;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

    public ProcuringEntityDTO getProcuringEntity() {
        return procuringEntity;
    }

    public void setProcuringEntity(ProcuringEntityDTO procuringEntity) {
        this.procuringEntity = procuringEntity;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContractDTO that = (ContractDTO) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (items != null ? !items.equals(that.items) : that.items != null) return false;
        if (suppliers != null ? !suppliers.equals(that.suppliers) : that.suppliers != null) return false;
        if (currency != null ? !currency.equals(that.currency) : that.currency != null) return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        if (valueAddedTaxIncluded != null ? !valueAddedTaxIncluded.equals(that.valueAddedTaxIncluded) : that.valueAddedTaxIncluded != null)
            return false;
        if (awardID != null ? !awardID.equals(that.awardID) : that.awardID != null) return false;
        if (contractID != null ? !contractID.equals(that.contractID) : that.contractID != null) return false;
        if (documents != null ? !documents.equals(that.documents) : that.documents != null) return false;
        if (tenderId != null ? !tenderId.equals(that.tenderId) : that.tenderId != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (contractNumber != null ? !contractNumber.equals(that.contractNumber) : that.contractNumber != null)
            return false;
        if (contractPeriodStartDate != null ? !contractPeriodStartDate.equals(that.contractPeriodStartDate) : that.contractPeriodStartDate != null)
            return false;
        if (contractPeriodClarificationsUntil != null ? !contractPeriodClarificationsUntil.equals(that.contractPeriodClarificationsUntil) : that.contractPeriodClarificationsUntil != null)
            return false;
        if (contractPeriodEndDate != null ? !contractPeriodEndDate.equals(that.contractPeriodEndDate) : that.contractPeriodEndDate != null)
            return false;
        if (contractPeriodInvalidationDate != null ? !contractPeriodInvalidationDate.equals(that.contractPeriodInvalidationDate) : that.contractPeriodInvalidationDate != null)
            return false;
        if (dateSigned != null ? !dateSigned.equals(that.dateSigned) : that.dateSigned != null) return false;
        if (dateModified != null ? !dateModified.equals(that.dateModified) : that.dateModified != null) return false;
        if (procuringEntity != null ? !procuringEntity.equals(that.procuringEntity) : that.procuringEntity != null)
            return false;
        return owner != null ? owner.equals(that.owner) : that.owner == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (items != null ? items.hashCode() : 0);
        result = 31 * result + (suppliers != null ? suppliers.hashCode() : 0);
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (valueAddedTaxIncluded != null ? valueAddedTaxIncluded.hashCode() : 0);
        result = 31 * result + (awardID != null ? awardID.hashCode() : 0);
        result = 31 * result + (contractID != null ? contractID.hashCode() : 0);
        result = 31 * result + (documents != null ? documents.hashCode() : 0);
        result = 31 * result + (tenderId != null ? tenderId.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (contractNumber != null ? contractNumber.hashCode() : 0);
        result = 31 * result + (contractPeriodStartDate != null ? contractPeriodStartDate.hashCode() : 0);
        result = 31 * result + (contractPeriodClarificationsUntil != null ? contractPeriodClarificationsUntil.hashCode() : 0);
        result = 31 * result + (contractPeriodEndDate != null ? contractPeriodEndDate.hashCode() : 0);
        result = 31 * result + (contractPeriodInvalidationDate != null ? contractPeriodInvalidationDate.hashCode() : 0);
        result = 31 * result + (dateSigned != null ? dateSigned.hashCode() : 0);
        result = 31 * result + (dateModified != null ? dateModified.hashCode() : 0);
        result = 31 * result + (procuringEntity != null ? procuringEntity.hashCode() : 0);
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ContractDTO{");
        sb.append("id='").append(id).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append(", items=").append(items);
        sb.append(", suppliers=").append(suppliers);
        sb.append(", currency='").append(currency).append('\'');
        sb.append(", amount='").append(amount).append('\'');
        sb.append(", valueAddedTaxIncluded='").append(valueAddedTaxIncluded).append('\'');
        sb.append(", awardID='").append(awardID).append('\'');
        sb.append(", contractID='").append(contractID).append('\'');
        sb.append(", documents=").append(documents);
        sb.append(", tenderId='").append(tenderId).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", contractNumber='").append(contractNumber).append('\'');
        sb.append(", contractPeriodStartDate='").append(contractPeriodStartDate).append('\'');
        sb.append(", contractPeriodClarificationsUntil='").append(contractPeriodClarificationsUntil).append('\'');
        sb.append(", contractPeriodEndDate='").append(contractPeriodEndDate).append('\'');
        sb.append(", contractPeriodInvalidationDate='").append(contractPeriodInvalidationDate).append('\'');
        sb.append(", dateSigned='").append(dateSigned).append('\'');
        sb.append(", dateModified='").append(dateModified).append('\'');
        sb.append(", procuringEntity=").append(procuringEntity);
        sb.append(", owner='").append(owner).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
