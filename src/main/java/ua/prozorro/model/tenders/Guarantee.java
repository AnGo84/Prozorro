package ua.prozorro.model.tenders;

public class Guarantee {
	private double amount;
	private String currency="UAH";

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
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
		sb.append("amount=").append(amount);
		sb.append(", currency='").append(currency).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
