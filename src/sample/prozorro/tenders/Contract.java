package sample.prozorro.tenders;

/**
 * Created by AnGo on 09.02.2017.
 */
public class Contract {
    private String id;
    private String awardId;
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAwardId() {
        return awardId;
    }

    public void setAwardId(String awardId) {
        this.awardId = awardId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("tenders.Contract{");
        sb.append("id='").append(id).append('\'');
        sb.append(", awardId='").append(awardId).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
