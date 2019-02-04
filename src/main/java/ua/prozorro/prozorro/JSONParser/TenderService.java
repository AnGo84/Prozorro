package ua.prozorro.prozorro.JSONParser;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import ua.prozorro.prozorro.model.pages.PageContentURL;
import ua.prozorro.prozorro.model.pages.ProzorroPageElement;
import ua.prozorro.prozorro.model.tenders.*;
import ua.prozorro.utils.DateUtils;
import ua.prozorro.utils.FileUtils;
import ua.prozorro.utils.ParseUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TenderService {

	private static final String TENDER_START_PATH = "https://public.api.openprocurement.org/api/0/tenders/";

	public static List<TenderOld> getTendersFromPage(PageContentURL pageContent, Date dateTill)
			throws IOException, java.text.ParseException {
		if (dateTill == null) {
			dateTill = DateUtils
					.addDaysToDate(DateUtils.parseDateToFormate(new Date(), DateUtils.DATE_PROZORRO_SHORT_FORMAT), 1);
		}
		List<TenderOld> tenderList = new ArrayList<>();
		for (ProzorroPageElement pageElement : pageContent.getPageElementList()) {
			TenderOld tender = getTender(pageElement.getId());
			if (dateTill.compareTo(
					DateUtils.parseDateFromString(tender.getDateModified(), DateUtils.DATE_PROZORRO_SHORT_FORMAT)) >=
				0) {
				tenderList.add(tender);
			} else
				break;
		}
		return tenderList;
	}

	public static TenderOld getTender(String tenderId) throws IOException {
		TenderOld tender = new TenderOld(tenderId);
		URL currentURL;
		try {
			currentURL = new URL(TENDER_START_PATH.concat(tenderId));
			String res = FileUtils.parseUrl(currentURL);
			JSONObject jsonObj = (JSONObject) JSONValue.parseWithException(res);
			JSONObject joPageItem = (JSONObject) jsonObj.get("data");

			tender.setProcurementMethod(ParseUtils.returnString(joPageItem.get("procurementMethod")));
			tender.setStatus(ParseUtils.returnString(joPageItem.get("status")));
			tender.setTenderID(ParseUtils.returnString(joPageItem.get("tenderID")));

			tender.setTenderPeriod(getPeriod((JSONObject) joPageItem.get("tenderPeriod")));
			tender.setAwardPeriod(getPeriod((JSONObject) joPageItem.get("awardPeriod")));
			tender.setAuctionPeriod(getPeriod((JSONObject) joPageItem.get("auctionPeriod")));
			tender.setEnquiryPeriod(getPeriod((JSONObject) joPageItem.get("enquiryPeriod")));

			tender.setNumberOfBids(ParseUtils.returnString(joPageItem.get("numberOfBids")));
			tender.setAuctionUrl(ParseUtils.returnString(joPageItem.get("auctionUrl")));

			tender.setDescription(ParseUtils.returnString(joPageItem.get("description")));
			tender.setTitle(ParseUtils.returnString(joPageItem.get("title")));
			tender.setDateModified(ParseUtils.returnString(joPageItem.get("dateModified")));

			tender.setItemList(getItemsList((JSONArray) joPageItem.get("items")));

			tender.setProcurementMethod(ParseUtils.returnString(joPageItem.get("procurementMethodType")));

			tender.setValue(getValue((JSONObject) joPageItem.get("value")));
			tender.setMinimalStep(getValue((JSONObject) joPageItem.get("minimalStep")));

			tender.setOwner(ParseUtils.returnString(joPageItem.get("owner")));
			tender.setAwardCriteria(ParseUtils.returnString(joPageItem.get("awardCriteria")));

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
			period.setStartDate(ParseUtils.returnString(jsonObject.get("startDate")));
			period.setEndDate(ParseUtils.returnString(jsonObject.get("endDate")));
		} else {
			period.setStartDate("");
			period.setEndDate("");
		}
		return period;
	}

	private static List<ItemOld> getItemsList(JSONArray itemsList) {
		List<ItemOld> itemList = new ArrayList<>();
		if (itemsList != null) {
			for (Object jsObj : itemsList) {
				JSONObject joItem = (JSONObject) jsObj;
				ItemOld item = new ItemOld();
				item.setId(ParseUtils.returnString(joItem.get("id")));
				item.setDescription(ParseUtils.returnString(joItem.get("description")));

				JSONObject joPageItemTemp = (JSONObject) joItem.get("classification");
				if (joPageItemTemp != null) {
					item.setScheme(ParseUtils.returnString(joPageItemTemp.get("scheme")));
					item.setSchemeDescription(ParseUtils.returnString(joPageItemTemp.get("description")));
					item.setSchemeId(ParseUtils.returnString(joPageItemTemp.get("id")));
				}
				item.setAdditionalClassifications(ParseUtils.returnString(joItem.get("additionalClassifications")));
				joPageItemTemp = (JSONObject) joItem.get("unit");
				if (joPageItemTemp != null) {
					item.setUnitCode(ParseUtils.returnString(joPageItemTemp.get("code")));
					item.setUnitName(ParseUtils.returnString(joPageItemTemp.get("name")));
				}
				item.setQuantity(ParseUtils.returnString(joItem.get("quantity")));
				itemList.add(item);
			}
		}
		return itemList;
	}

	private static Value getValue(JSONObject jsonObject) {
		Value value = new Value();
		if (jsonObject != null) {
			value.setCurrency(ParseUtils.returnString(jsonObject.get("guaranteeCurrency")));
			value.setAmount(ParseUtils.returnString(jsonObject.get("guaranteeAmount")));
			value.setValueAddedTaxIncluded(ParseUtils.returnString(jsonObject.get("valueAddedTaxIncluded")));
		}
		return value;
	}

	private static Organization getOrganization(JSONObject jsonObject) {
		Organization organization = new Organization();
		if (jsonObject != null) {
			organization.setName(ParseUtils.returnString(jsonObject.get("name")));
			organization.setAddress(getAddress((JSONObject) jsonObject.get("address")));
			organization.setContactPoint(getContactPoint((JSONObject) jsonObject.get("contactPoint")));
			organization.setIdentifier(getIdentifier((JSONObject) jsonObject.get("identifier")));
		}
		return organization;
	}

	private static Address getAddress(JSONObject jsonObject) {
		Address address = new Address();
		if (jsonObject != null) {
			address.setPostalCode(ParseUtils.returnString(jsonObject.get("postalCode")));
			address.setCountryName(ParseUtils.returnString(jsonObject.get("countryName")));
			address.setLocality(ParseUtils.returnString(jsonObject.get("locality")));
			address.setRegion(ParseUtils.returnString(jsonObject.get("region")));
			address.setStreetAddress(ParseUtils.returnString(jsonObject.get("streetAddress")));
		}
		return address;
	}

	private static Identifier getIdentifier(JSONObject jsonObject) {
		Identifier identifier = new Identifier();
		if (jsonObject != null) {
			identifier.setScheme(ParseUtils.returnString(jsonObject.get("scheme")));
			identifier.setId(ParseUtils.returnString(jsonObject.get("id")));
			identifier.setUri(ParseUtils.returnString(jsonObject.get("uri")));
			identifier.setLegalName(ParseUtils.returnString(jsonObject.get("legalName")));
		}
		return identifier;
	}

	private static ContactPoint getContactPoint(JSONObject jsonObject) {
		ContactPoint contactPoint = new ContactPoint();
		if (jsonObject != null) {
			contactPoint.setTelephone(ParseUtils.returnString(jsonObject.get("telephone")));
			contactPoint.setUrl(ParseUtils.returnString(jsonObject.get("url")));
			contactPoint.setName(ParseUtils.returnString(jsonObject.get("name")));
			contactPoint.setEmail(ParseUtils.returnString(jsonObject.get("email")));
		}
		return contactPoint;
	}

	private static List<Bid> getBidList(JSONArray itemsList) {
		List<Bid> bidList = new ArrayList<>();
		if (itemsList != null) {
			for (Object jsObj : itemsList) {
				JSONObject joItem = (JSONObject) jsObj;
				Bid bid = new Bid();
				bid.setId(ParseUtils.returnString(joItem.get("id")));
				bid.setStatus(ParseUtils.returnString(joItem.get("status")));
				bid.setDate(ParseUtils.returnString(joItem.get("date")));
				bid.setParticipationUrl(ParseUtils.returnString(joItem.get("participationUrl")));
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
				award.setId(ParseUtils.returnString(joItem.get("id")));
				award.setStatus(ParseUtils.returnString(joItem.get("status")));
				award.setDate(ParseUtils.returnString(joItem.get("date")));
				award.setBidId(ParseUtils.returnString(joItem.get("bid_id")));
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
				contract.setId(ParseUtils.returnString(joItem.get("id")));
				contract.setAwardID(ParseUtils.returnString(joItem.get("awardID")));
				contract.setStatus(ParseUtils.returnString(joItem.get("status")));

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
