package sample.prozorro.tenders;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AnGo on 04.12.2016.
 */
public class Award {
    private String id;
    private String status;
    private String date;
    private String bidId;
    private Value value;
    private Period complaintPeriod;
    private List<Organization> suppliers = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBidId() {
        return bidId;
    }

    public void setBidId(String bid_id) {
        this.bidId = bid_id;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public Period getComplaintPeriod() {
        return complaintPeriod;
    }

    public void setComplaintPeriod(Period complaintPeriod) {
        this.complaintPeriod = complaintPeriod;
    }

    public List<Organization> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<Organization> suppliers) {
        this.suppliers = suppliers;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("tenders.Award{");
        sb.append("id='").append(id).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append(", date='").append(date).append('\'');
        sb.append(", bidId='").append(bidId).append('\'');
        sb.append(", value=").append(value);
        sb.append(", complaintPeriod=").append(complaintPeriod);
        sb.append(", suppliers=").append(suppliers);
        sb.append('}');
        return sb.toString();
    }
}
