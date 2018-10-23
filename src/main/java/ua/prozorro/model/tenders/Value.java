package ua.prozorro.model.tenders;

/**
 * Created by AnGo on 28.11.2016.
 */
public class Value {
    private String currency;
    private String amount;
    private String valueAddedTaxIncluded;

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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("tenders.Value{");
        sb.append("currency='").append(currency).append('\'');
        sb.append(", amount='").append(amount).append('\'');
        sb.append(", valueAddedTaxIncluded='").append(valueAddedTaxIncluded).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
