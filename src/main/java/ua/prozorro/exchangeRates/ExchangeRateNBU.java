package ua.prozorro.exchangeRates;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExchangeRateNBU {
	@SerializedName("StartDate")
	@Expose
	private String startDate;
	@SerializedName("TimeSign")
	@Expose
	private String timeSign;
	@SerializedName("CurrencyCode")
	@Expose
	private String currencyCode;
	@SerializedName("CurrencyCodeL")
	@Expose
	private String currencyCodeL;
	@SerializedName("Units")
	@Expose
	private int units;
	@SerializedName("Amount")
	@Expose
	private double amount;
	
	public String getStartDate() {
		return startDate;
	}
	
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	public String getTimeSign() {
		return timeSign;
	}
	
	public void setTimeSign(String timeSign) {
		this.timeSign = timeSign;
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
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		
		ExchangeRateNBU that = (ExchangeRateNBU) o;
		
		if (units != that.units)
			return false;
		if (Double.compare(that.amount, amount) != 0)
			return false;
		if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null)
			return false;
		if (timeSign != null ? !timeSign.equals(that.timeSign) : that.timeSign != null)
			return false;
		if (currencyCode != null ? !currencyCode.equals(that.currencyCode) : that.currencyCode != null)
			return false;
		return currencyCodeL != null ? currencyCodeL.equals(that.currencyCodeL) : that.currencyCodeL == null;
	}
	
	@Override
	public int hashCode() {
		int result;
		long temp;
		result = startDate != null ? startDate.hashCode() : 0;
		result = 31 * result + (timeSign != null ? timeSign.hashCode() : 0);
		result = 31 * result + (currencyCode != null ? currencyCode.hashCode() : 0);
		result = 31 * result + (currencyCodeL != null ? currencyCodeL.hashCode() : 0);
		result = 31 * result + units;
		temp = Double.doubleToLongBits(amount);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	
	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("ExchangeRateNBU{");
		sb.append("startDate='").append(startDate).append('\'');
		sb.append(", timeSign='").append(timeSign).append('\'');
		sb.append(", currencyCode='").append(currencyCode).append('\'');
		sb.append(", currencyCodeL='").append(currencyCodeL).append('\'');
		sb.append(", units=").append(units);
		sb.append(", amount=").append(amount);
		sb.append('}');
		return sb.toString();
	}
}
