package ua.prozorro.entity.nburate;

import java.io.Serializable;

public class NBURatePK implements Serializable {
	protected String date;
	protected String currencyCode;
	
	public NBURatePK() {
	}
	
	public NBURatePK(String date, String currencyCode) {
		this.date = date;
		this.currencyCode = currencyCode;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		
		NBURatePK rateNBUPK = (NBURatePK) o;
		
		if (date != null ? !date.equals(rateNBUPK.date) : rateNBUPK.date != null)
			return false;
		return currencyCode != null ? currencyCode.equals(rateNBUPK.currencyCode) : rateNBUPK.currencyCode == null;
	}
	
	@Override
	public int hashCode() {
		int result = date != null ? date.hashCode() : 0;
		result = 31 * result + (currencyCode != null ? currencyCode.hashCode() : 0);
		return result;
	}
	
	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("RateNBUPK{");
		sb.append("date='").append(date).append('\'');
		sb.append(", currencyCode='").append(currencyCode).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
