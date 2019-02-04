package ua.prozorro.prozorro.model.tenders;

public class Guarantee {
	private String amount;
	private String currency = "UAH";
	
	public String getAmount() {
		return amount;
	}
	
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	public String getCurrency() {
		return currency;
	}
	
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("Guarantee{");
		sb.append("guaranteeAmount=").append(amount);
		sb.append(", guaranteeCurrency='").append(currency).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
