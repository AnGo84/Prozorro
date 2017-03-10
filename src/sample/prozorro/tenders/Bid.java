package sample.prozorro.tenders;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AnGo on 04.12.2016.
 */
public class Bid {
    private String id;
    private String status;
    private String date;
    private String participationUrl;
    private Value value;
    private List<Organization> tenderers = new ArrayList<>();

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

    public String getParticipationUrl() {
        return participationUrl;
    }

    public void setParticipationUrl(String participationUrl) {
        this.participationUrl = participationUrl;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public List<Organization> getTenderers() {
        return tenderers;
    }

    public void setTenderers(List<Organization> tenderers) {
        this.tenderers = tenderers;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("tenders.Bid{");
        sb.append("id='").append(id).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append(", date='").append(date).append('\'');
        sb.append(", participationUrl='").append(participationUrl).append('\'');
        sb.append(", value=").append(value);
        sb.append(", tenderers=").append(tenderers);
        sb.append('}');
        return sb.toString();
    }
}
