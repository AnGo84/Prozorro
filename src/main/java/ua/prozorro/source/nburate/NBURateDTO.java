package ua.prozorro.source.nburate;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class NBURateDTO {
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
}
