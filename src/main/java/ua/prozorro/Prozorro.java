package ua.prozorro;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import ua.prozorro.model.pages.PageContentURL;
import ua.prozorro.model.pages.PageURL;
import ua.prozorro.model.pages.PageElement;
import ua.prozorro.model.tenders.*;
import ua.prozorro.sql.SQLConnection;
import ua.prozorro.utils.DateUtils;

import java.beans.XMLEncoder;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;


/**
 * Created by AnGo on 13.10.2016.
 */
public class Prozorro {
    private static final String DB_PROPERTY_PATH = "src/sample/resources/Prozorro.properties";

    public static final String START_PAGE = "https://public.api.openprocurement.org/api/0/tenders";
    private static final String PAGE_BEGIN_WITH = "https://public.api.openprocurement.org/api/2.3/tenders?offset=";
    private static final String PAGE_END_WITH = "T00%3A00%3A00.000000%2B03%3A00";
//    private static final String START_PATH = "/api/0/tenders";
    private static final String TENDER_START_PATH = "https://public.api.openprocurement.org/api/0/tenders/";
    // Example date:  2015-06-19T21:00:03.340891+03:00
    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX";
    private static final String DATE_SHORT_FORMAT = "yyyy-MM-dd";
    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
    public static SimpleDateFormat simpleDateShotFormat = new SimpleDateFormat(DATE_SHORT_FORMAT);

    private static String MAX_DATE_TILL = "3000-01-01";

    private static String DateBegin = "2016-10-03";
    private static String DateEnd = "2016-10-04";

    public static void main(String[] args) throws IOException {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
//        System.out.println(simpleDateFormat.format(new Date()));
        try {
            Properties property = getConnectionProperties(DB_PROPERTY_PATH);
            System.out.println(getPropertyText(property));
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("Searching pages...");
        List<PageContentURL> pageContentList = null;
        try {
            pageContentList = getPagesList(simpleDateShotFormat.parse(DateBegin), simpleDateShotFormat.parse(DateEnd));
        } catch (java.text.ParseException|IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }


        if (pageContentList.size() > 0) {
            System.out.println("Find pages: " + pageContentList.size());
            System.out.println("Count the approximate time...");
            try {
                System.out.println("Count time: " + DateUtils.getTextTime(getAvgParsingSize(pageContentList.get(0), simpleDateShotFormat.parse(DateEnd)) * pageContentList.size()));
            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }
            System.out.println("Begin parsing. Please wait...");

            int pageCount = 0;
            int allTenders = 0;
            try {
                try (Connection dbConnection = SQLConnection.getDBConnection(getConnectionProperties(DB_PROPERTY_PATH))) {
                    SQLConnection.cleanTables(dbConnection);
                    for (PageContentURL pageContent : pageContentList) {
                        int count = 0;
                        List<TenderOld> tenderList = getTendersFromPage(pageContent, simpleDateShotFormat.parse(DateEnd));
                        pageCount++;
                        allTenders += tenderList.size();

                        try {
                            SQLConnection.insertTenders(dbConnection, tenderList);
                            System.out.println("Added " + tenderList.size() + " new Tenders to DB");
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                        try {
                            writeToXMLFile(tenderList, new File("D:\\tenders\\page_" + pageCount + ".xml"));
                            System.out.println("File created");
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("Find tenders: " + allTenders);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (java.text.ParseException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
//        try {
//            writeToXMLFile(tenderList, new File("D:\\Test.xml"));
//            System.out.println("File created");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
    }

    public static List<TenderOld> getTendersFromPage(PageContentURL pageContent, Date dateTill) throws IOException, java.text.ParseException {
        if (dateTill == null) {
            dateTill = parseDateShotFromString(MAX_DATE_TILL);
        }
        List<TenderOld> tenderList = new ArrayList<>();
        for (PageElement pageElement : pageContent.getPageElementList()) {
            TenderOld tender = getTender(pageElement.getId());
            if (dateTill.compareTo(parseDateShotFromString(tender.getDateModified())) >= 0) {
                tenderList.add(tender);
            } else break;
        }
        return tenderList;
    }

    public static void writeToXMLFile(List<TenderOld> tenderList, File file) throws FileNotFoundException {
        XMLEncoder xmlEncoder = new XMLEncoder(
                new BufferedOutputStream(
                        new FileOutputStream(file)));
        xmlEncoder.writeObject(tenderList);
        xmlEncoder.close();
    }

    public static List<PageContentURL> getPagesList(Date dateFrom, Date dateTill) throws IOException, java.text.ParseException, ParseException {
        String startPage = START_PAGE;
        if (dateFrom != null) {
            startPage = PAGE_BEGIN_WITH + dateToShotFormat(dateFrom) + PAGE_END_WITH;
        }
        if (dateTill == null) {
            dateTill = parseDateShotFromString(MAX_DATE_TILL);
        }
//        System.out.println("Start_page: " + startPage);
        List<PageContentURL> pageContentList = new ArrayList<>();
        URL currentURL = new URL(startPage);
        PageContentURL pageContent;
        do {
            pageContent = getPageContent(currentURL);
            pageContentList.add(pageContent);
//                System.out.println("Pages: " + pageContentList.size() + "; Current: " + currentURL.toString() + "; Next: " + pageContent.getNextPage().getUrl());
            if (dateTill.compareTo(parseDateShotFromString(pageContent.getNextPage().getOffset())) >= 0) {
                currentURL = pageContent.getNextPage().getUrl();
            }
        } while (!currentURL.equals(pageContent.getUrl()));
        return pageContentList;
    }

    public static PageContentURL getPageContent(URL pageURL) throws IOException, ParseException {
        String res = parseUrl(pageURL);
        return getPageContent(pageURL, res);
    }

    public static PageContentURL getPageContent(URL pageURL, String res) throws ParseException {
        JSONObject jsonObj = (JSONObject) JSONValue.parseWithException(res);
        JSONObject joPageItem = (JSONObject) jsonObj.get("next_page");
        PageURL nextPage = new PageURL(joPageItem.get("path").toString(), joPageItem.get("uri").toString(), joPageItem.get("offset").toString());

        PageContentURL pageContent = new PageContentURL(pageURL, nextPage);
        JSONArray tendersList = (JSONArray) jsonObj.get("data");
        for (Object jsObj : tendersList) {
            JSONObject joItem = (JSONObject) jsObj;
            PageElement pageElement = new PageElement(joItem.get("id").toString(), joItem.get("dateModified").toString());
            pageContent.getPageElementList().add(pageElement);
        }
        return pageContent;
    }

    public static TenderOld getTender(String tenderId) throws IOException {
        TenderOld tender = new TenderOld(tenderId);
        URL currentURL;
        try {
            currentURL = new URL(TENDER_START_PATH.concat(tenderId));
            String res = parseUrl(currentURL);
            JSONObject jsonObj = (JSONObject) JSONValue.parseWithException(res);
            JSONObject joPageItem = (JSONObject) jsonObj.get("data");

            tender.setProcurementMethod(returnString(joPageItem.get("procurementMethod")));
            tender.setStatus(returnString(joPageItem.get("status")));
            tender.setTenderID(returnString(joPageItem.get("tenderID")));

            tender.setTenderPeriod(getPeriod((JSONObject) joPageItem.get("tenderPeriod")));
            tender.setAwardPeriod(getPeriod((JSONObject) joPageItem.get("awardPeriod")));
            tender.setAuctionPeriod(getPeriod((JSONObject) joPageItem.get("auctionPeriod")));
            tender.setEnquiryPeriod(getPeriod((JSONObject) joPageItem.get("enquiryPeriod")));

            tender.setNumberOfBids(returnString(joPageItem.get("numberOfBids")));
            tender.setAuctionUrl(returnString(joPageItem.get("auctionUrl")));

            tender.setDescription(returnString(joPageItem.get("description")));
            tender.setTitle(returnString(joPageItem.get("title")));
            tender.setDateModified(returnString(joPageItem.get("dateModified")));

            tender.setItemList(getItemsList((JSONArray) joPageItem.get("items")));

            tender.setProcurementMethod(returnString(joPageItem.get("procurementMethodType")));

            tender.setValue(getValue((JSONObject) joPageItem.get("value")));
            tender.setMinimalStep(getValue((JSONObject) joPageItem.get("minimalStep")));

            tender.setOwner(returnString(joPageItem.get("owner")));
            tender.setAwardCriteria(returnString(joPageItem.get("awardCriteria")));

            tender.setProcuringEntity(getOrganization((JSONObject) joPageItem.get("procuringEntity")));

            tender.setBidList(getBidList((JSONArray) joPageItem.get("bids")));
            tender.setAwardList(getAwardList((JSONArray) joPageItem.get("awards")));
            tender.setContractList(getContractList((JSONArray) joPageItem.get("contracts")));

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return tender;
    }

    private static List<ItemOld> getItemsList(JSONArray itemsList) {
        List<ItemOld> itemList = new ArrayList<>();
        if (itemsList != null) {
            for (Object jsObj : itemsList) {
                JSONObject joItem = (JSONObject) jsObj;
                ItemOld item = new ItemOld();
                item.setId(returnString(joItem.get("id")));
                item.setDescription(returnString(joItem.get("description")));

                JSONObject joPageItemTemp = (JSONObject) joItem.get("classification");
                if (joPageItemTemp != null) {
                    item.setScheme(returnString(joPageItemTemp.get("scheme")));
                    item.setSchemeDescription(returnString(joPageItemTemp.get("description")));
                    item.setSchemeId(returnString(joPageItemTemp.get("id")));
                }
                item.setAdditionalClassifications(returnString(joItem.get("additionalClassifications")));
                joPageItemTemp = (JSONObject) joItem.get("unit");
                if (joPageItemTemp != null) {
                    item.setUnitCode(returnString(joPageItemTemp.get("code")));
                    item.setUnitName(returnString(joPageItemTemp.get("name")));
                }
                item.setQuantity(returnString(joItem.get("quantity")));
                itemList.add(item);
            }
        }
        return itemList;
    }


    private static List<Bid> getBidList(JSONArray itemsList) {
        List<Bid> bidList = new ArrayList<>();
        if (itemsList != null) {
            for (Object jsObj : itemsList) {
                JSONObject joItem = (JSONObject) jsObj;
                Bid bid = new Bid();
                bid.setId(returnString(joItem.get("id")));
                bid.setStatus(returnString(joItem.get("status")));
                bid.setDate(returnString(joItem.get("date")));
                bid.setParticipationUrl(returnString(joItem.get("participationUrl")));
                bid.setValue(getValue((JSONObject) joItem.get("value")));
                bid.setTenderers(getOrganizationsList((JSONArray) joItem.get("tenderers")));

                bidList.add(bid);
            }
        }
        return bidList;
    }

    private static List<Award> getAwardList(JSONArray itemsList) {
        List<Award> awardList = new ArrayList<>();
        if (itemsList != null) {
            for (Object jsObj : itemsList) {
                JSONObject joItem = (JSONObject) jsObj;
                Award award = new Award();
                award.setId(returnString(joItem.get("id")));
                award.setStatus(returnString(joItem.get("status")));
                award.setDate(returnString(joItem.get("date")));
                award.setBidId(returnString(joItem.get("bid_id")));
                award.setValue(getValue((JSONObject) joItem.get("value")));
                award.setSuppliers(getOrganizationsList((JSONArray) joItem.get("suppliers")));
                award.setComplaintPeriod(getPeriod((JSONObject) joItem.get("complaintPeriod")));
                awardList.add(award);
            }
        }
        return awardList;
    }

    private static List<Contract> getContractList(JSONArray itemsList) {
        List<Contract> contractList = new ArrayList<>();
        if (itemsList != null) {
            for (Object jsObj : itemsList) {
                JSONObject joItem = (JSONObject) jsObj;
                Contract contract = new Contract();
                contract.setId(returnString(joItem.get("id")));
                contract.setAwardID(returnString(joItem.get("awardID")));
                contract.setStatus(returnString(joItem.get("status")));

                contractList.add(contract);
            }
        }
        return contractList;
    }

    private static List<Organization> getOrganizationsList(JSONArray itemsList) {
        List<Organization> tendersList = new ArrayList<>();
        if (itemsList != null) {
            for (Object jsObj : itemsList) {
//                JSONObject joItem = (JSONObject) jsObj;
                tendersList.add(getOrganization((JSONObject) jsObj));
            }
        }
        return tendersList;
    }


    private static Value getValue(JSONObject jsonObject) {
        Value value = new Value();
        if (jsonObject != null) {
            value.setCurrency(returnString(jsonObject.get("currency")));
            value.setAmount(returnString(jsonObject.get("amount")));
            value.setValueAddedTaxIncluded(returnString(jsonObject.get("valueAddedTaxIncluded")));
        }
        return value;
    }

    private static Period getPeriod(JSONObject jsonObject) {
        Period period = new Period();
        if (jsonObject != null) {
            period.setStartDate(returnString(jsonObject.get("startDate")));
            period.setEndDate(returnString(jsonObject.get("endDate")));
        } else {
            period.setStartDate("");
            period.setEndDate("");
        }
        return period;
    }

    private static Address getAddress(JSONObject jsonObject) {
        Address address = new Address();
        if (jsonObject != null) {
            address.setPostalCode(returnString(jsonObject.get("postalCode")));
            address.setCountryName(returnString(jsonObject.get("countryName")));
            address.setLocality(returnString(jsonObject.get("locality")));
            address.setRegion(returnString(jsonObject.get("region")));
            address.setStreetAddress(returnString(jsonObject.get("streetAddress")));
        }
        return address;
    }

    private static Identifier getIdentifier(JSONObject jsonObject) {
        Identifier identifier = new Identifier();
        if (jsonObject != null) {
            identifier.setScheme(returnString(jsonObject.get("scheme")));
            identifier.setId(returnString(jsonObject.get("id")));
            identifier.setUri(returnString(jsonObject.get("uri")));
            identifier.setLegalName(returnString(jsonObject.get("legalName")));
        }
        return identifier;
    }

    private static ContactPoint getContactPoint(JSONObject jsonObject) {
        ContactPoint contactPoint = new ContactPoint();
        if (jsonObject != null) {
            contactPoint.setTelephone(returnString(jsonObject.get("telephone")));
            contactPoint.setUrl(returnString(jsonObject.get("url")));
            contactPoint.setName(returnString(jsonObject.get("name")));
            contactPoint.setEmail(returnString(jsonObject.get("email")));
        }
        return contactPoint;
    }

    private static Organization getOrganization(JSONObject jsonObject) {
        Organization organization = new Organization();
        if (jsonObject != null) {
            organization.setName(returnString(jsonObject.get("name")));
            organization.setAddress(getAddress((JSONObject) jsonObject.get("address")));
            organization.setContactPoint(getContactPoint((JSONObject) jsonObject.get("contactPoint")));
            organization.setIdentifier(getIdentifier((JSONObject) jsonObject.get("identifier")));
        }
        return organization;
    }


    public static String returnString(Object object) {
        if (object == null)
            return "";
        return object.toString().replaceAll("\"", "\\\\\"").replaceAll("\'", "\\\\\'");
    }

    public static String parseUrl(URL url) throws IOException {
        if (url == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        // открываем соедиение к указанному URL
        try (BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                stringBuilder.append(inputLine);
//                System.out.println(inputLine);
            }
        }
        return stringBuilder.toString();
    }

    public static Date parseDateFromString(String stringDate) throws java.text.ParseException {
        Date date = null;
        date = simpleDateFormat.parse(stringDate);
//            System.out.println("tenders.TenderOld date: "+ date.toString());

        return date;
    }

    public static Date parseDateShotFromString(String stringDate) throws java.text.ParseException {
        Date date = simpleDateShotFormat.parse(stringDate);
        return date;
    }

    public static String dateToShotFormat(Date date) {
        return simpleDateShotFormat.format(date);
    }

    public static long getAvgParsingSize(PageContentURL pageContent, Date dateEnd) throws IOException, java.text.ParseException {
        long start = System.currentTimeMillis();
        List<TenderOld> tenderList = getTendersFromPage(pageContent, dateEnd);
        return (System.currentTimeMillis() - start);
    }


    public static Properties getConnectionProperties(String filename) throws IOException {
        Properties property = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(filename);) {
            property.load(fileInputStream);
        }
        return property;
    }

    public static String getPropertyText(Properties properties) {
        return String.format("Server: %s. Host: %s. Scheme name: %s. User name: %s",
                properties.getProperty("db.type"), properties.getProperty("db.host"), properties.getProperty("db.name"), properties.getProperty("db.login"));
    }


}
