package ua.prozorro.prozorro.model.tenders;

public class Lot {
    private String id;
    private String title;
    private String description;


    private Value value;

    private Guarantee guarantee;

    private String date;

    private Value minimalStep;

    private Period auctionPeriod;

    private String auctionUrl;

    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public Guarantee getGuarantee() {
        return guarantee;
    }

    public void setGuarantee(Guarantee guarantee) {
        this.guarantee = guarantee;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Value getMinimalStep() {
        return minimalStep;
    }

    public void setMinimalStep(Value minimalStep) {
        this.minimalStep = minimalStep;
    }

    public Period getAuctionPeriod() {
        return auctionPeriod;
    }

    public void setAuctionPeriod(Period auctionPeriod) {
        this.auctionPeriod = auctionPeriod;
    }

    public String getAuctionUrl() {
        return auctionUrl;
    }

    public void setAuctionUrl(String auctionUrl) {
        this.auctionUrl = auctionUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Lot{");
        sb.append("id='").append(id).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", value=").append(value);
        sb.append(", guarantee=").append(guarantee);
        sb.append(", date='").append(date).append('\'');
        sb.append(", minimalStep=").append(minimalStep);
        sb.append(", auctionPeriod=").append(auctionPeriod);
        sb.append(", auctionUrl='").append(auctionUrl).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
