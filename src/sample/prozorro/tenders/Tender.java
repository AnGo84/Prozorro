package sample.prozorro.tenders;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AnGo on 30.10.2016.
 */
public class Tender {
    private String id;
    private String tenderID;
    private String procurementMethod;
    private String procurementMethodType;
    private String dateModified;
    private String status;
    private Period awardPeriod;
    private Period enquiryPeriod;
    private Period tenderPeriod;
    private Period auctionPeriod;

    private String numberOfBids;
    private String auctionUrl;
    private String description;
    private String title;
    private List<Item> itemList=new ArrayList<>();
    private Value value;
    private Value minimalStep;
    private String owner;
    private String awardCriteria;

    private Organization procuringEntity;
    private List<Bid> bidList= new ArrayList<>();
    private List<Award> awardList= new ArrayList<>();
    private List<Contract> contractList= new ArrayList<>();

    public Tender() {
    }

    public Tender(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProcurementMethod() {
        return procurementMethod;
    }

    public void setProcurementMethod(String procurementMethod) {
        this.procurementMethod = procurementMethod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getNumberOfBids() {
        return numberOfBids;
    }

    public void setNumberOfBids(String numberOfBids) {
        this.numberOfBids = numberOfBids;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public String getProcurementMethodType() {
        return procurementMethodType;
    }

    public void setProcurementMethodType(String procurementMethodType) {
        this.procurementMethodType = procurementMethodType;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getTenderID() {
        return tenderID;
    }

    public void setTenderID(String tenderID) {
        this.tenderID = tenderID;
    }

    public String getAuctionUrl() {
        return auctionUrl;
    }

    public void setAuctionUrl(String auctionUrl) {
        this.auctionUrl = auctionUrl;
    }

    public String getDateModified() {
        return dateModified;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

    public Period getAwardPeriod() {
        return awardPeriod;
    }

    public void setAwardPeriod(Period awardPeriod) {
        this.awardPeriod = awardPeriod;
    }

    public Period getEnquiryPeriod() {
        return enquiryPeriod;
    }

    public void setEnquiryPeriod(Period enquiryPeriod) {
        this.enquiryPeriod = enquiryPeriod;
    }

    public Period getTenderPeriod() {
        return tenderPeriod;
    }

    public void setTenderPeriod(Period tenderPeriod) {
        this.tenderPeriod = tenderPeriod;
    }

    public Period getAuctionPeriod() {
        return auctionPeriod;
    }

    public void setAuctionPeriod(Period auctionPeriod) {
        this.auctionPeriod = auctionPeriod;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public Value getMinimalStep() {
        return minimalStep;
    }

    public void setMinimalStep(Value minimalStep) {
        this.minimalStep = minimalStep;
    }

    public String getAwardCriteria() {
        return awardCriteria;
    }

    public void setAwardCriteria(String awardCriteria) {
        this.awardCriteria = awardCriteria;
    }

    public Organization getProcuringEntity() {
        return procuringEntity;
    }

    public void setProcuringEntity(Organization procuringEntity) {
        this.procuringEntity = procuringEntity;
    }

    public List<Bid> getBidList() {
        return bidList;
    }

    public void setBidList(List<Bid> bidList) {
        this.bidList = bidList;
    }

    public List<Award> getAwardList() {
        return awardList;
    }

    public void setAwardList(List<Award> awardList) {
        this.awardList = awardList;
    }

    public List<Contract> getContractList() {
        return contractList;
    }

    public void setContractList(List<Contract> contractList) {
        this.contractList = contractList;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("tenders.Tender{");
        sb.append("id='").append(id).append('\'');
        sb.append(", tenderID='").append(tenderID).append('\'');
        sb.append(", procurementMethod='").append(procurementMethod).append('\'');
        sb.append(", procurementMethodType='").append(procurementMethodType).append('\'');
        sb.append(", dateModified='").append(dateModified).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append(", awardPeriod=").append(awardPeriod);
        sb.append(", enquiryPeriod=").append(enquiryPeriod);
        sb.append(", tenderPeriod=").append(tenderPeriod);
        sb.append(", auctionPeriod=").append(auctionPeriod);
        sb.append(", numberOfBids='").append(numberOfBids).append('\'');
        sb.append(", auctionUrl='").append(auctionUrl).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", itemList=").append(itemList);
        sb.append(", value=").append(value);
        sb.append(", minimalStep=").append(minimalStep);
        sb.append(", owner='").append(owner).append('\'');
        sb.append(", awardCriteria='").append(awardCriteria).append('\'');
        sb.append(", procuringEntity=").append(procuringEntity);
        sb.append(", bidList=").append(bidList);
        sb.append(", awardList=").append(awardList);
        sb.append(", contractList=").append(contractList);
        sb.append('}');
        return sb.toString();
    }
}
