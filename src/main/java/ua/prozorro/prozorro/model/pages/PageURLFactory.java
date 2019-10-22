package ua.prozorro.prozorro.model.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.prozorro.properties.AppProperty;
import ua.prozorro.properties.PropertyFields;
import ua.prozorro.prozorro.model.DataType;
import ua.prozorro.utils.DateUtils;

import java.util.Date;

public class PageURLFactory {
    private static final Logger logger = LogManager.getRootLogger();
    private PropertyFields propertyFields;

    public PageURLFactory(PropertyFields propertyFields) {
        this.propertyFields = propertyFields;
    }

    /*public String getPageURL(DataType dataType, Date date) {*/
    public String getPageURL(Date date) {
        DataType dataType = propertyFields.getSearchDateType();
        if (dataType.equals(DataType.TENDERS)) {
            return getTenderPageURL(date);

        } else if (dataType.equals(DataType.CONTRACTS)) {
            return getContractPageURL(date);
        } else if (dataType.equals(DataType.PLANS)) {
            return getPlanPageURL(date);
        }
        return null;
    }

    /*public String getPageURL(DataType dataType, Date date, Boolean withLimit) {
        if(withLimit==null || withLimit==false){
            return getPageURL(dataType,date);
        }*/
    public String getPageURL(Date date, Boolean withLimit) {
        if (withLimit == null || withLimit == false) {
            return getPageURL(date);
        }
        DataType dataType = propertyFields.getSearchDateType();
        if (dataType.equals(DataType.TENDERS)) {
            return getTenderPageURLWithLimit(date);

        } else if (dataType.equals(DataType.CONTRACTS)) {
            return getTenderContractURLWithLimit(date);
        } else if (dataType.equals(DataType.PLANS)) {
            return getPlanPageURLWithLimit(date);
        }
        return null;
    }

    private String getTenderPageURL(Date date) {
        if (date == null) {
            return propertyFields.getPropertiesStringValue(AppProperty.TENDER_START_PAGE);
        }
        String pageURL = propertyFields.getPropertiesStringValue(AppProperty.TENDER_START_PAGE) + "?" +
                propertyFields.getPropertiesStringValue(AppProperty.TENDER_PAGE_OFFSET) + "=" + DateUtils
                .parseDateToString(date, propertyFields
                        .getPropertiesStringValue(AppProperty.SHORT_DATE_FORMAT)) +
                propertyFields.getPropertiesStringValue(AppProperty.TENDER_PAGE_END);
        //logger.info("Get page from date "+ DateUtils.dateToString(date) +" with URL: " + pageURL);
        return pageURL;
    }

    private String getContractPageURL(Date date) {
        if (date == null) {
            return propertyFields.getPropertiesStringValue(AppProperty.CONTRACT_START_PAGE);
        }
        String pageURL = propertyFields.getPropertiesStringValue(AppProperty.CONTRACT_START_PAGE) + "?" +
                propertyFields.getPropertiesStringValue(AppProperty.CONTRACT_PAGE_OFFSET) + "=" + DateUtils
                .parseDateToString(date, propertyFields
                        .getPropertiesStringValue(AppProperty.SHORT_DATE_FORMAT)) +
                propertyFields.getPropertiesStringValue(AppProperty.CONTRACT_PAGE_END);
        //logger.info("Get page from date "+ DateUtils.dateToString(date) +" with URL: " + pageURL);
        return pageURL;
    }

    private String getPlanPageURL(Date date) {
        if (date == null) {
            return propertyFields.getPropertiesStringValue(AppProperty.PLAN_START_PAGE);
        }
        String pageURL = propertyFields.getPropertiesStringValue(AppProperty.PLAN_START_PAGE) + "?" +
                propertyFields.getPropertiesStringValue(AppProperty.PLAN_PAGE_OFFSET) + "=" + DateUtils
                .parseDateToString(date, propertyFields
                        .getPropertiesStringValue(AppProperty.SHORT_DATE_FORMAT)) +
                propertyFields.getPropertiesStringValue(AppProperty.PLAN_PAGE_END);
        //logger.info("Get page from date "+ DateUtils.dateToString(date) +" with URL: " + pageURL);
        return pageURL;
    }

    private String getTenderPageURLWithLimit(Date date) {
        if (date == null) {
            return propertyFields.getPropertiesStringValue(AppProperty.TENDER_START_PAGE);
        }
        String pageURL = propertyFields.getPropertiesStringValue(AppProperty.TENDER_START_PAGE) + "?" +
                propertyFields.getPropertiesStringValue(AppProperty.TENDER_PAGE_LIMIT) + "=" +
                propertyFields.getPropertiesStringValue(AppProperty.TENDER_PAGE_LIMIT_VALUE) + "&" +
                propertyFields.getPropertiesStringValue(AppProperty.TENDER_PAGE_OFFSET) + "=" + DateUtils
                .parseDateToString(date, propertyFields
                        .getPropertiesStringValue(AppProperty.SHORT_DATE_FORMAT)) +
                propertyFields.getPropertiesStringValue(AppProperty.TENDER_PAGE_END);
        logger.info("Get page from date " + DateUtils.dateToString(date) + " with URL: " + pageURL);
        return pageURL;
    }

    private String getTenderContractURLWithLimit(Date date) {
        if (date == null) {
            return propertyFields.getPropertiesStringValue(AppProperty.CONTRACT_START_PAGE);
        }
        String pageURL = propertyFields.getPropertiesStringValue(AppProperty.CONTRACT_START_PAGE) + "?" +
                propertyFields.getPropertiesStringValue(AppProperty.CONTRACT_PAGE_LIMIT) + "=" +
                propertyFields.getPropertiesStringValue(AppProperty.CONTRACT_PAGE_LIMIT_VALUE) + "&" +
                propertyFields.getPropertiesStringValue(AppProperty.CONTRACT_PAGE_OFFSET) + "=" + DateUtils
                .parseDateToString(date, propertyFields
                        .getPropertiesStringValue(AppProperty.SHORT_DATE_FORMAT)) +
                propertyFields.getPropertiesStringValue(AppProperty.CONTRACT_PAGE_END);
        logger.info("Get page from date " + DateUtils.dateToString(date) + " with URL: " + pageURL);
        return pageURL;
    }

    private String getPlanPageURLWithLimit(Date date) {
        if (date == null) {
            return propertyFields.getPropertiesStringValue(AppProperty.PLAN_START_PAGE);
        }
        String pageURL = propertyFields.getPropertiesStringValue(AppProperty.PLAN_START_PAGE) + "?" +
                propertyFields.getPropertiesStringValue(AppProperty.PLAN_PAGE_LIMIT) + "=" +
                propertyFields.getPropertiesStringValue(AppProperty.PLAN_PAGE_LIMIT_VALUE) + "&" +
                propertyFields.getPropertiesStringValue(AppProperty.PLAN_PAGE_OFFSET) + "=" + DateUtils
                .parseDateToString(date, propertyFields
                        .getPropertiesStringValue(AppProperty.SHORT_DATE_FORMAT)) +
                propertyFields.getPropertiesStringValue(AppProperty.PLAN_PAGE_END);
        logger.info("Get page from date " + DateUtils.dateToString(date) + " with URL: " + pageURL);
        return pageURL;
    }
}
