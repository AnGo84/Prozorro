package ua.prozorro.prozorro;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import ua.prozorro.model.pages.PageContent;
import ua.prozorro.model.pages.PageElement;
import ua.prozorro.model.tenders.*;
import ua.prozorro.utils.DateUtil;
import ua.prozorro.utils.FileUtils;
import ua.prozorro.utils.ParseUtil;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TenderService {

	private static final String TENDER_START_PATH = "https://public.api.openprocurement.org/api/0/tenders/";

	public static List<Tender> getTendersFromPage(PageContent pageContent, Date dateTill) throws IOException, java.text.ParseException {
		if (dateTill == null) {
			dateTill = DateUtil.addDaysToDate(DateUtil.parseDateToFormate(new Date(), DateUtil.DATE_PROZORRO_SHORT_FORMAT),1);
		}
		List<Tender> tenderList = new ArrayList<>();
		for (PageElement pageElement : pageContent.getPageElementList()) {
			Tender tender = getTender(pageElement.getId());
			if (dateTill.compareTo(DateUtil.parseDateFromString(tender.getDateModified(), DateUtil.DATE_PROZORRO_SHORT_FORMAT)) >= 0) {
				tenderList.add(tender);
			} else break;
		}
		return tenderList;
	}

	public static Tender getTender(String tenderId) throws IOException {
		Tender tender = new Tender(tenderId);
		URL currentURL;
		try {
			currentURL = new URL(TENDER_START_PATH.concat(tenderId));
			String res = FileUtils.parseUrl(currentURL);
			JSONObject jsonObj = (JSONObject) JSONValue.parseWithException(res);
			JSONObject joPageItem = (JSONObject) jsonObj.get("data");

			tender.setProcurementMethod(ParseUtil.returnString(joPageItem.get("procurementMethod")));
			tender.setStatus(ParseUtil.returnString(joPageItem.get("status")));
			tender.setTenderID(ParseUtil.returnString(joPageItem.get("tenderID")));

			tender.setTenderPeriod(getPeriod((JSONObject) joPageItem.get("tenderPeriod")));
			tender.setAwardPeriod(getPeriod((JSONObject) joPageItem.get("awardPeriod")));
			tender.setAuctionPeriod(getPeriod((JSONObject) joPageItem.get("auctionPeriod")));
			tender.setEnquiryPeriod(getPeriod((JSONObject) joPageItem.get("enquiryPeriod")));

			tender.setNumberOfBids(ParseUtil.returnString(joPageItem.get("numberOfBids")));
			tender.setAuctionUrl(ParseUtil.returnString(joPageItem.get("auctionUrl")));

			tender.setDescription(ParseUtil.returnString(joPageItem.get("description")));
			tender.setTitle(ParseUtil.returnString(joPageItem.get("title")));
			tender.setDateModified(ParseUtil.returnString(joPageItem.get("dateModified")));

			tender.setItemList(getItemsList((JSONArray) joPageItem.get("items")));

			tender.setProcurementMethod(ParseUtil.returnString(joPageItem.get("procurementMethodType")));

			tender.setValue(getValue((JSONObject) joPageItem.get("value")));
			tender.setMinimalStep(getValue((JSONObject) joPageItem.get("minimalStep")));

			tender.setOwner(ParseUtil.returnString(joPageItem.get("owner")));
			tender.setAwardCriteria(ParseUtil.returnString(joPageItem.get("awardCriteria")));

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

	private static Period getPeriod(JSONObject jsonObject) {
		Period period = new Period();
		if (jsonObject != null) {
			period.setStartDate(ParseUtil.returnString(jsonObject.get("startDate")));
			period.setEndDate(ParseUtil.returnString(jsonObject.get("endDate")));
		} else {
			period.setStartDate("");
			period.setEndDate("");
		}
		return period;
	}

	private static List<Item> getItemsList(JSONArray itemsList) {
		List<Item> itemList = new ArrayList<>();
		if (itemsList != null) {
			for (Object jsObj : itemsList) {
				JSONObject joItem = (JSONObject) jsObj;
				Item item = new Item();
				item.setId(ParseUtil.returnString(joItem.get("id")));
				item.setDescription(ParseUtil.returnString(joItem.get("description")));

				JSONObject joPageItemTemp = (JSONObject) joItem.get("classification");
				if (joPageItemTemp != null) {
					item.setScheme(ParseUtil.returnString(joPageItemTemp.get("scheme")));
					item.setSchemeDescription(ParseUtil.returnString(joPageItemTemp.get("description")));
					item.setSchemeId(ParseUtil.returnString(joPageItemTemp.get("id")));
				}
				item.setAdditionalClassifications(ParseUtil.returnString(joItem.get("additionalClassifications")));
				joPageItemTemp = (JSONObject) joItem.get("unit");
				if (joPageItemTemp != null) {
					item.setUnitCode(ParseUtil.returnString(joPageItemTemp.get("code")));
					item.setUnitName(ParseUtil.returnString(joPageItemTemp.get("name")));
				}
				item.setQuantity(ParseUtil.returnString(joItem.get("quantity")));
				itemList.add(item);
			}
		}
		return itemList;
	}

	private static Value getValue(JSONObject jsonObject) {
		Value value = new Value();
		if (jsonObject != null) {
			value.setCurrency(ParseUtil.returnString(jsonObject.get("currency")));
			value.setAmount(ParseUtil.returnString(jsonObject.get("amount")));
			value.setValueAddedTaxIncluded(ParseUtil.returnString(jsonObject.get("valueAddedTaxIncluded")));
		}
		return value;
	}

	private static Organization getOrganization(JSONObject jsonObject) {
		Organization organization = new Organization();
		if (jsonObject != null) {
			organization.setName(ParseUtil.returnString(jsonObject.get("name")));
			organization.setAddress(getAddress((JSONObject) jsonObject.get("address")));
			organization.setContactPoint(getContactPoint((JSONObject) jsonObject.get("contactPoint")));
			organization.setIdentifier(getIdentifier((JSONObject) jsonObject.get("identifier")));
		}
		return organization;
	}

	private static Address getAddress(JSONObject jsonObject) {
		Address address = new Address();
		if (jsonObject != null) {
			address.setPostalCode(ParseUtil.returnString(jsonObject.get("postalCode")));
			address.setCountryName(ParseUtil.returnString(jsonObject.get("countryName")));
			address.setLocality(ParseUtil.returnString(jsonObject.get("locality")));
			address.setRegion(ParseUtil.returnString(jsonObject.get("region")));
			address.setStreetAddress(ParseUtil.returnString(jsonObject.get("streetAddress")));
		}
		return address;
	}

	private static Identifier getIdentifier(JSONObject jsonObject) {
		Identifier identifier = new Identifier();
		if (jsonObject != null) {
			identifier.setScheme(ParseUtil.returnString(jsonObject.get("scheme")));
			identifier.setId(ParseUtil.returnString(jsonObject.get("id")));
			identifier.setUri(ParseUtil.returnString(jsonObject.get("uri")));
			identifier.setLegalName(ParseUtil.returnString(jsonObject.get("legalName")));
		}
		return identifier;
	}

	private static ContactPoint getContactPoint(JSONObject jsonObject) {
		ContactPoint contactPoint = new ContactPoint();
		if (jsonObject != null) {
			contactPoint.setTelephone(ParseUtil.returnString(jsonObject.get("telephone")));
			contactPoint.setUrl(ParseUtil.returnString(jsonObject.get("url")));
			contactPoint.setName(ParseUtil.returnString(jsonObject.get("name")));
			contactPoint.setEmail(ParseUtil.returnString(jsonObject.get("email")));
		}
		return contactPoint;
	}

	private static List<Bid> getBidList(JSONArray itemsList) {
		List<Bid> bidList = new ArrayList<>();
		if (itemsList != null) {
			for (Object jsObj : itemsList) {
				JSONObject joItem = (JSONObject) jsObj;
				Bid bid = new Bid();
				bid.setId(ParseUtil.returnString(joItem.get("id")));
				bid.setStatus(ParseUtil.returnString(joItem.get("status")));
				bid.setDate(ParseUtil.returnString(joItem.get("date")));
				bid.setParticipationUrl(ParseUtil.returnString(joItem.get("participationUrl")));
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
				award.setId(ParseUtil.returnString(joItem.get("id")));
				award.setStatus(ParseUtil.returnString(joItem.get("status")));
				award.setDate(ParseUtil.returnString(joItem.get("date")));
				award.setBidId(ParseUtil.returnString(joItem.get("bid_id")));
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
				contract.setId(ParseUtil.returnString(joItem.get("id")));
				contract.setAwardId(ParseUtil.returnString(joItem.get("awardID")));
				contract.setStatus(ParseUtil.returnString(joItem.get("status")));

				contractList.add(contract);
			}
		}
		return contractList;
	}

	private static List<Organization> getOrganizationsList(JSONArray itemsList) {
		List<Organization> list = new ArrayList<>();
		if (itemsList != null) {
			for (Object jsObj : itemsList) {
//                JSONObject joItem = (JSONObject) jsObj;
				list.add(getOrganization((JSONObject) jsObj));
			}
		}
		return list;
	}
}
