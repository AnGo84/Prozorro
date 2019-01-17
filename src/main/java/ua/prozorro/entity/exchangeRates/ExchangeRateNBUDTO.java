package ua.prozorro.entity.exchangeRates;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "NBU_rates")
@IdClass(RateNBUPK.class)
public class ExchangeRateNBUDTO implements Serializable {
	@Id
	@Column
	private String date;

	@Id
	@Column
	private String currencyCode;

	@Column
	private String currencyCodeL;

	@Column
	private String timeSign;

	@Column
	private int units;
	@Column
	private double amount;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getCurrencyCodeL() {
		return currencyCodeL;
	}

	public void setCurrencyCodeL(String currencyCodeL) {
		this.currencyCodeL = currencyCodeL;
	}

	public String getTimeSign() {
		return timeSign;
	}

	public void setTimeSign(String timeSign) {
		this.timeSign = timeSign;
	}

	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ExchangeRateNBUDTO that = (ExchangeRateNBUDTO) o;

		if (units != that.units) return false;
		if (Double.compare(that.amount, amount) != 0) return false;
		if (date != null ? !date.equals(that.date) : that.date != null) return false;
		if (currencyCode != null ? !currencyCode.equals(that.currencyCode) : that.currencyCode != null) return false;
		if (currencyCodeL != null ? !currencyCodeL.equals(that.currencyCodeL) : that.currencyCodeL != null)
			return false;
		return timeSign != null ? timeSign.equals(that.timeSign) : that.timeSign == null;
	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = date != null ? date.hashCode() : 0;
		result = 31 * result + (currencyCode != null ? currencyCode.hashCode() : 0);
		result = 31 * result + (currencyCodeL != null ? currencyCodeL.hashCode() : 0);
		result = 31 * result + (timeSign != null ? timeSign.hashCode() : 0);
		result = 31 * result + units;
		temp = Double.doubleToLongBits(amount);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("ExchangeRateNBUDTO{");
		sb.append("date='").append(date).append('\'');
		sb.append(", currencyCode='").append(currencyCode).append('\'');
		sb.append(", currencyCodeL='").append(currencyCodeL).append('\'');
		sb.append(", timeSign='").append(timeSign).append('\'');
		sb.append(", units=").append(units);
		sb.append(", amount=").append(amount);
		sb.append('}');
		return sb.toString();
	}
}
