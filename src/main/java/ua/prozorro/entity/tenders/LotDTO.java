package ua.prozorro.entity.tenders;

import javax.persistence.*;

@Entity
@Table(name = "Lots")
public class LotDTO {
    @Id
    //@GeneratedValue(strategy= GenerationType.AUTO)
    @Column
    private String id;
    @Column(length = 4000)
    private String title;
    //@Column(length = 8000)
    @Lob
    @Column
    private String description;

    @Column
    private String currency;
    @Column
    private String amount;
    @Column
    private String addedTaxIncluded;

    @Column
    public String guaranteeCurrency;
    @Column
    public String guaranteeAmount;

    @Column
    private String date;

    @Column
    private String minimalStepCurrency;
    @Column
    private String minimalStepAmount;
    @Column
    private String minimalStepValueAddedTaxIncluded;

    @Column
    private String auctionPeriodStartDate;
    @Column
    private String auctionPeriodClarificationsUntil;
    @Column
    private String auctionPeriodEndDate;
    @Column
    private String auctionPeriodInvalidationDate;

    @Column(length = 1000)
    private String auctionUrl;
    @Column
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getAuctionUrl() {
        return auctionUrl;
    }

    public void setAuctionUrl(String auctionUrl) {
        this.auctionUrl = auctionUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getAddedTaxIncluded() {
        return addedTaxIncluded;
    }

    public void setAddedTaxIncluded(String addedTaxIncluded) {
        this.addedTaxIncluded = addedTaxIncluded;
    }

    public String getGuaranteeCurrency() {
        return guaranteeCurrency;
    }

    public void setGuaranteeCurrency(String guaranteeCurrency) {
        this.guaranteeCurrency = guaranteeCurrency;
    }

    public String getGuaranteeAmount() {
        return guaranteeAmount;
    }

    public void setGuaranteeAmount(String guaranteeAmount) {
        this.guaranteeAmount = guaranteeAmount;
    }

    public String getMinimalStepCurrency() {
        return minimalStepCurrency;
    }

    public void setMinimalStepCurrency(String minimalStepCurrency) {
        this.minimalStepCurrency = minimalStepCurrency;
    }

    public String getMinimalStepAmount() {
        return minimalStepAmount;
    }

    public void setMinimalStepAmount(String minimalStepAmount) {
        this.minimalStepAmount = minimalStepAmount;
    }

    public String getMinimalStepValueAddedTaxIncluded() {
        return minimalStepValueAddedTaxIncluded;
    }

    public void setMinimalStepValueAddedTaxIncluded(String minimalStepValueAddedTaxIncluded) {
        this.minimalStepValueAddedTaxIncluded = minimalStepValueAddedTaxIncluded;
    }

    public String getAuctionPeriodStartDate() {
        return auctionPeriodStartDate;
    }

    public void setAuctionPeriodStartDate(String auctionPeriodStartDate) {
        this.auctionPeriodStartDate = auctionPeriodStartDate;
    }

    public String getAuctionPeriodClarificationsUntil() {
        return auctionPeriodClarificationsUntil;
    }

    public void setAuctionPeriodClarificationsUntil(String auctionPeriodClarificationsUntil) {
        this.auctionPeriodClarificationsUntil = auctionPeriodClarificationsUntil;
    }

    public String getAuctionPeriodEndDate() {
        return auctionPeriodEndDate;
    }

    public void setAuctionPeriodEndDate(String auctionPeriodEndDate) {
        this.auctionPeriodEndDate = auctionPeriodEndDate;
    }

    public String getAuctionPeriodInvalidationDate() {
        return auctionPeriodInvalidationDate;
    }

    public void setAuctionPeriodInvalidationDate(String auctionPeriodInvalidationDate) {
        this.auctionPeriodInvalidationDate = auctionPeriodInvalidationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        LotDTO lotDTO = (LotDTO) o;

        if (id != null ? !id.equals(lotDTO.id) : lotDTO.id != null)
            return false;
        if (title != null ? !title.equals(lotDTO.title) : lotDTO.title != null)
            return false;
        if (description != null ? !description.equals(lotDTO.description) : lotDTO.description != null)
            return false;
        if (currency != null ? !currency.equals(lotDTO.currency) : lotDTO.currency != null)
            return false;
        if (amount != null ? !amount.equals(lotDTO.amount) : lotDTO.amount != null)
            return false;
        if (addedTaxIncluded != null ? !addedTaxIncluded.equals(lotDTO.addedTaxIncluded) :
                lotDTO.addedTaxIncluded != null)
            return false;
        if (guaranteeCurrency != null ? !guaranteeCurrency.equals(lotDTO.guaranteeCurrency) :
                lotDTO.guaranteeCurrency != null)
            return false;
        if (guaranteeAmount != null ? !guaranteeAmount.equals(lotDTO.guaranteeAmount) : lotDTO.guaranteeAmount != null)
            return false;
        if (date != null ? !date.equals(lotDTO.date) : lotDTO.date != null)
            return false;
        if (minimalStepCurrency != null ? !minimalStepCurrency.equals(lotDTO.minimalStepCurrency) :
                lotDTO.minimalStepCurrency != null)
            return false;
        if (minimalStepAmount != null ? !minimalStepAmount.equals(lotDTO.minimalStepAmount) :
                lotDTO.minimalStepAmount != null)
            return false;
        if (minimalStepValueAddedTaxIncluded != null ?
                !minimalStepValueAddedTaxIncluded.equals(lotDTO.minimalStepValueAddedTaxIncluded) :
                lotDTO.minimalStepValueAddedTaxIncluded != null)
            return false;
        if (auctionPeriodStartDate != null ? !auctionPeriodStartDate.equals(lotDTO.auctionPeriodStartDate) :
                lotDTO.auctionPeriodStartDate != null)
            return false;
        if (auctionPeriodClarificationsUntil != null ?
                !auctionPeriodClarificationsUntil.equals(lotDTO.auctionPeriodClarificationsUntil) :
                lotDTO.auctionPeriodClarificationsUntil != null)
            return false;
        if (auctionPeriodEndDate != null ? !auctionPeriodEndDate.equals(lotDTO.auctionPeriodEndDate) :
                lotDTO.auctionPeriodEndDate != null)
            return false;
        if (auctionPeriodInvalidationDate != null ?
                !auctionPeriodInvalidationDate.equals(lotDTO.auctionPeriodInvalidationDate) :
                lotDTO.auctionPeriodInvalidationDate != null)
            return false;
        if (auctionUrl != null ? !auctionUrl.equals(lotDTO.auctionUrl) : lotDTO.auctionUrl != null)
            return false;
        return status != null ? status.equals(lotDTO.status) : lotDTO.status == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (addedTaxIncluded != null ? addedTaxIncluded.hashCode() : 0);
        result = 31 * result + (guaranteeCurrency != null ? guaranteeCurrency.hashCode() : 0);
        result = 31 * result + (guaranteeAmount != null ? guaranteeAmount.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (minimalStepCurrency != null ? minimalStepCurrency.hashCode() : 0);
        result = 31 * result + (minimalStepAmount != null ? minimalStepAmount.hashCode() : 0);
        result = 31 * result +
                 (minimalStepValueAddedTaxIncluded != null ? minimalStepValueAddedTaxIncluded.hashCode() : 0);
        result = 31 * result + (auctionPeriodStartDate != null ? auctionPeriodStartDate.hashCode() : 0);
        result = 31 * result +
                 (auctionPeriodClarificationsUntil != null ? auctionPeriodClarificationsUntil.hashCode() : 0);
        result = 31 * result + (auctionPeriodEndDate != null ? auctionPeriodEndDate.hashCode() : 0);
        result = 31 * result + (auctionPeriodInvalidationDate != null ? auctionPeriodInvalidationDate.hashCode() : 0);
        result = 31 * result + (auctionUrl != null ? auctionUrl.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("LotDTO{");
        sb.append("id='").append(id).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", currency='").append(currency).append('\'');
        sb.append(", amount='").append(amount).append('\'');
        sb.append(", addedTaxIncluded='").append(addedTaxIncluded).append('\'');
        sb.append(", guaranteeCurrency='").append(guaranteeCurrency).append('\'');
        sb.append(", guaranteeAmount='").append(guaranteeAmount).append('\'');
        sb.append(", date='").append(date).append('\'');
        sb.append(", minimalStepCurrency='").append(minimalStepCurrency).append('\'');
        sb.append(", minimalStepAmount='").append(minimalStepAmount).append('\'');
        sb.append(", minimalStepValueAddedTaxIncluded='").append(minimalStepValueAddedTaxIncluded).append('\'');
        sb.append(", auctionPeriodStartDate='").append(auctionPeriodStartDate).append('\'');
        sb.append(", auctionPeriodClarificationsUntil='").append(auctionPeriodClarificationsUntil).append('\'');
        sb.append(", auctionPeriodEndDate='").append(auctionPeriodEndDate).append('\'');
        sb.append(", auctionPeriodInvalidationDate='").append(auctionPeriodInvalidationDate).append('\'');
        sb.append(", auctionUrl='").append(auctionUrl).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
