package ua.prozorro.prozorro.model.tenders;

import com.google.gson.annotations.SerializedName;

public class Period {

    @SerializedName("startDate")
    //@Expose
    private String startDate;
    @SerializedName("clarificationsUntil")
    //@Expose
    private String clarificationsUntil;
    @SerializedName("endDate")
    //@Expose
    private String endDate;
    @SerializedName("invalidationDate")
    //@Expose
    private String invalidationDate;


    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getClarificationsUntil() {
        return clarificationsUntil;
    }

    public void setClarificationsUntil(String clarificationsUntil) {
        this.clarificationsUntil = clarificationsUntil;
    }

    public String getInvalidationDate() {
        return invalidationDate;
    }

    public void setInvalidationDate(String invalidationDate) {
        this.invalidationDate = invalidationDate;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Period{");
        sb.append("startDate='").append(startDate).append('\'');
        sb.append(", clarificationsUntil='").append(clarificationsUntil).append('\'');
        sb.append(", endDate='").append(endDate).append('\'');
        sb.append(", invalidationDate='").append(invalidationDate).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
