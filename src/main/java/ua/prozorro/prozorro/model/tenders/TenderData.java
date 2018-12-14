package ua.prozorro.prozorro.model.tenders;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TenderData implements Serializable {

	@SerializedName("data")
	private  Tender tender;

	public Tender getTender() {
		return tender;
	}

	public void setTender(Tender tender) {
		this.tender = tender;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("TenderData{");
		sb.append("tender=").append(tender);
		sb.append('}');
		return sb.toString();
	}
}
