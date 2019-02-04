package ua.prozorro.sql;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.prozorro.prozorro.model.tenders.*;

import java.sql.*;
import java.util.List;
import java.util.Properties;

/**
 * Created by AnGo on 13.02.2017.
 */
public class SQLConnection {
	//    private static final String DELETE_TENDERS= "SET SQL_SAFE_UPDATES = 0;delete FROM prozorro.tenders;SET SQL_SAFE_UPDATES = 1;";
	//    private static final String DELETE_TENDERS = "delete FROM tenders;";
	//    private static final String DELETE_ITEMS = "delete FROM items;";
	//    private static final String DELETE_BITS = "delete FROM bids;";
	//    private static final String DELETE_BIDTENDERERS = "delete FROM bidtenderers;";
	//    private static final String DELETE_AWARDS = "delete FROM awards;";
	//    private static final String DELETE_AWARDSUPPLIERS = "delete FROM awardsuppliers;";
	//    private static final String DELETE_CONTRACTS = "delete FROM contracts;";
	private static final Logger logger = LogManager.getRootLogger();
	
	private static final String PROCEDURE_FOR_CLEAN_TABLES = "call prozorro.cleartables;";
	
	private static final String MYSQL_CONNECTION =
			"jdbc:mysql://%s/%s?autoReconnect=true&useSSL=false&noAccessToProcedureBodies=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	
	
	public static Connection getDBConnection(Properties properties) throws ClassNotFoundException, SQLException {
		Connection dbConnection = null;
		if (properties != null) {
			//            Class.forName(DB_DRIVER);
			if (properties.getProperty("db.type").toUpperCase().equals(DatabaseType.MYSQL.name())) {
				//Class.forName("com.mysql.cj.jdbc.Driver");
				Class.forName(DatabaseType.MYSQL.getDtiverName());
				
				logger.info(String.format(MYSQL_CONNECTION, properties.getProperty("db.host"),
										  properties.getProperty("db.name")), properties.getProperty("db.login"),
							properties.getProperty("db.password"));
				
				dbConnection = DriverManager.getConnection(
						String.format(MYSQL_CONNECTION, properties.getProperty("db.host"),
									  properties.getProperty("db.name")), properties.getProperty("db.login"),
						properties.getProperty("db.password"));
			}
			return dbConnection;
		}
		return dbConnection;
	}
	
	
	public static void insertTenders(Connection connection, List<TenderOld> tenderList) throws SQLException {
		try (Statement statement = connection.createStatement()) {
			// выполнить SQL запрос
			for (TenderOld tender : tenderList) {
				String insertTableSQL =
						"INSERT INTO TENDERS(" + "ID, TENDERID, status" + ",procurementMethod,procurementMethodType" +
						",dateModified,awardPeriodDateStart,awardPeriodDateEnd" +
						",enquiryPeriodDateStart,enquiryPeriodDateEnd" + ",tenderPeriodDateStart,tenderPeriodDateEnd" +
						",auctionPeriodDateStart,auctionPeriodDateEnd" + ",numberOfBids,auctionUrl,description,title" +
						",owner, awardCriteria" + ",valuecurrency,valueamount,valueAddedTaxIncluded" +
						",minimalStepcurrency,minimalStepamount,minimalStepAddedTaxIncluded" + ",procuringEntityName" +
						",contactPointtelephone, contactPointurl" + ", contactPointname,contactPointemail" +
						",identifierscheme,identifierid" + ",identifieruri,identifierlegalName" +
						",addresspostalCode,addresscountryName" + ",addressstreetAddress,addressregion" +
						",addresslocality" + ")" +
						
						" VALUES('" + tender.getId() + "','" + tender.getTenderID() + "','" + tender.getStatus() +
						"','" + tender.getProcurementMethod() + "','" + tender.getProcurementMethodType() + "',' " +
						tender.getDateModified() + "','" + tender.getAwardPeriod().getStartDate() + "','" +
						tender.getAwardPeriod().getEndDate() + "','" + tender.getEnquiryPeriod().getStartDate() +
						"','" + tender.getEnquiryPeriod().getEndDate() + "','" +
						tender.getTenderPeriod().getStartDate() + "','" + tender.getTenderPeriod().getEndDate() +
						"','" + tender.getAuctionPeriod().getStartDate() + "','" +
						tender.getAuctionPeriod().getEndDate() + "','" + tender.getNumberOfBids().toString() + "','" +
						tender.getAuctionUrl() + "','" + cutString(tender.getDescription(), 9999) + "','" +
						cutString(tender.getTitle(), 1999) + "','" + tender.getOwner() + "','" +
						tender.getAwardCriteria() + "','" + tender.getValue().getCurrency() + "','" +
						tender.getValue().getAmount() + "','" + tender.getValue().getValueAddedTaxIncluded() + "','" +
						tender.getMinimalStep().getCurrency() + "','" + tender.getMinimalStep().getAmount() + "','" +
						tender.getMinimalStep().getValueAddedTaxIncluded() + "','" +
						tender.getProcuringEntity().getName() + "','" +
						tender.getProcuringEntity().getContactPoint().getTelephone() + "','" +
						tender.getProcuringEntity().getContactPoint().getUrl() + "','" +
						tender.getProcuringEntity().getContactPoint().getName() + "','" +
						tender.getProcuringEntity().getContactPoint().getEmail() + "','" +
						tender.getProcuringEntity().getIdentifier().getScheme() + "','" +
						tender.getProcuringEntity().getIdentifier().getId() + "','" +
						tender.getProcuringEntity().getIdentifier().getUri() + "','" +
						tender.getProcuringEntity().getIdentifier().getLegalName() + "','" +
						tender.getProcuringEntity().getAddress().getPostalCode() + "','" +
						tender.getProcuringEntity().getAddress().getCountryName() + "','" +
						tender.getProcuringEntity().getAddress().getStreetAddress() + "','" +
						tender.getProcuringEntity().getAddress().getRegion() + "','" +
						tender.getProcuringEntity().getAddress().getLocality() + "')";
				
				//                System.out.println(insertTableSQL);
				statement.execute(insertTableSQL);
				insertItems(connection, tender.getItemList(), tender.getId());
				insertBids(connection, tender.getBidList(), tender.getId());
				insertAwards(connection, tender.getAwardList(), tender.getId());
				insertContracts(connection, tender.getContractList(), tender.getId());
			}
		}
	}
	
	public static void cleanTables(Connection connection) throws SQLException {
		//        try (Statement statement = connection.createStatement();) {
		//            statement.execute(DELETE_TENDERS);
		//            statement.execute(DELETE_ITEMS);
		//            statement.execute(DELETE_BITS);
		//            statement.execute(DELETE_BIDTENDERERS);
		//            statement.execute(DELETE_AWARDS);
		//            statement.execute(DELETE_AWARDSUPPLIERS);
		//            statement.execute(DELETE_CONTRACTS);
		//        }
		//        System.out.println("Clearing");
		PreparedStatement preparedStatement = connection.prepareStatement(PROCEDURE_FOR_CLEAN_TABLES);
		preparedStatement.execute();
		
	}
	
	
	private static void insertItems(Connection connection, List<ItemOld> itemList, String tenderID)
			throws SQLException {
		try (Statement statement = connection.createStatement()) {
			// выполнить SQL запрос
			for (ItemOld item : itemList) {
				String insertTableSQL =
						"INSERT INTO ITEMS(" + "TENDERID, ID, description " + ",scheme, schemeDescription, schemeId" +
						",unitCode, unitName" + ",quantity, additionalClassifications" + ")" + " VALUES('" + tenderID +
						"','" + item.getId() + "','" + cutString(item.getDescription(), 5999) + "','" +
						item.getScheme() + "',' " + cutString(item.getSchemeDescription(), 5999) + "','" +
						item.getSchemeId() + "','" + item.getUnitCode() + "','" + item.getUnitName() + "','" +
						item.getQuantity() + "','" + cutString(item.getAdditionalClassifications(), 3999) + "')";
				//                System.out.println(insertTableSQL);
				statement.execute(insertTableSQL);
			}
		}
	}
	
	private static void insertBids(Connection connection, List<Bid> bidList, String tenderID) throws SQLException {
		try (Statement statement = connection.createStatement()) {
			// выполнить SQL запрос
			for (Bid bid : bidList) {
				String insertTableSQL = "INSERT INTO BIDS(" + "TENDERID, ID,status, date" + ", participationUrl " +
										", valuecurrency, valueamount,valueAddedTaxIncluded" + ")" + " VALUES('" +
										tenderID + "','" + bid.getId() + "','" + bid.getStatus() + "','" +
										bid.getDate() + "','" + bid.getParticipationUrl() + "',' " +
										bid.getValue().getCurrency() + "','" + bid.getValue().getAmount() + "','" +
										bid.getValue().getValueAddedTaxIncluded() + "')";
				//                System.out.println(insertTableSQL);
				statement.execute(insertTableSQL);
				insertBidTenderers(connection, bid.getTenderers(), bid.getId());
			}
		}
	}
	
	private static void insertBidTenderers(Connection connection, List<Organization> organizationList, String bidID)
			throws SQLException {
		try (Statement statement = connection.createStatement()) {
			// выполнить SQL запрос
			for (Organization organization : organizationList) {
				String insertTableSQL =
						"INSERT INTO BIDTENDERERS(" + "BidID, name " + ",contactPointtelephone, contactPointurl" +
						", contactPointname, contactPointemail" + ", identifierscheme, identifierid" +
						", identifieruri, identifierlegalName" + ", addresspostalCode, addresscountryName" +
						", addressstreetAddress, addressregion, addresslocality" + ")" + " VALUES('" + bidID + "','" +
						cutString(organization.getName(), 1249) + "','" +
						organization.getContactPoint().getTelephone() + "','" +
						organization.getContactPoint().getUrl() + "','" + organization.getContactPoint().getName() +
						"','" + organization.getContactPoint().getEmail() + "','" +
						organization.getIdentifier().getScheme() + "','" + organization.getIdentifier().getId() +
						"','" + organization.getIdentifier().getUri() + "','" +
						cutString(organization.getIdentifier().getLegalName(), 1249) + "','" +
						organization.getAddress().getPostalCode() + "','" + organization.getAddress().getCountryName() +
						"','" + organization.getAddress().getStreetAddress() + "','" +
						organization.getAddress().getRegion() + "','" + organization.getAddress().getLocality() + "')";
				//                System.out.println(insertTableSQL);
				statement.execute(insertTableSQL);
			}
		}
	}
	
	private static void insertAwards(Connection connection, List<Award> awardList, String tenderID)
			throws SQLException {
		//        System.out.println(awardList.toString());
		try (Statement statement = connection.createStatement()) {
			// выполнить SQL запрос
			for (Award award : awardList) {
				String insertTableSQL = "INSERT INTO AWARDS(" + "TENDERID, ID,status, date" + ",bidID " +
										",valuecurrency, valueamount,valueAddedTaxIncluded" +
										",complaintPeriodDateStart, complaintPeriodDateEnd" + ")" +
				
										" VALUES('" + tenderID + "','" + award.getId() + "','" + award.getStatus() +
										"','" + award.getDate() + "','" + award.getBidId() + "','" +
										award.getValue().getCurrency() + "','" + award.getValue().getAmount() + "','" +
										award.getValue().getValueAddedTaxIncluded() + "','" +
										award.getComplaintPeriod().getStartDate() + "','" +
										award.getComplaintPeriod().getEndDate() + "')";
				//                System.out.println(insertTableSQL);
				statement.execute(insertTableSQL);
				insertAwardSuppliers(connection, award.getSuppliers(), award.getId());
			}
		}
	}
	
	private static void insertAwardSuppliers(Connection connection, List<Organization> organizationList, String awardID)
			throws SQLException {
		try (Statement statement = connection.createStatement()) {
			// выполнить SQL запрос
			for (Organization organization : organizationList) {
				String insertTableSQL =
						"INSERT INTO AwardSuppliers(" + "awardid, name " + ",contactPointtelephone, contactPointurl" +
						", contactPointname, contactPointemail" + ", identifierscheme, identifierid" +
						", identifieruri, identifierlegalName" + ", addresspostalCode, addresscountryName" +
						", addressstreetAddress, addressregion, addresslocality" + ")" + " VALUES('" + awardID + "','" +
						organization.getName() + "','" + organization.getContactPoint().getTelephone() + "','" +
						organization.getContactPoint().getUrl() + "','" + organization.getContactPoint().getName() +
						"','" + organization.getContactPoint().getEmail() + "','" +
						organization.getIdentifier().getScheme() + "','" + organization.getIdentifier().getId() +
						"','" + organization.getIdentifier().getUri() + "','" +
						organization.getIdentifier().getLegalName() + "','" +
						organization.getAddress().getPostalCode() + "','" + organization.getAddress().getCountryName() +
						"','" + organization.getAddress().getStreetAddress() + "','" +
						organization.getAddress().getRegion() + "','" + organization.getAddress().getLocality() + "')";
				//                System.out.println(insertTableSQL);
				statement.execute(insertTableSQL);
			}
		}
	}
	
	private static void insertContracts(Connection connection, List<Contract> contractList, String tenderId)
			throws SQLException {
		try (Statement statement = connection.createStatement()) {
			// выполнить SQL запрос
			for (Contract contract : contractList) {
				String insertTableSQL =
						"INSERT INTO CONTRACTS(" + "tenderid, ID, awardid, status " + ")" + " VALUES('" + tenderId +
						"','" + contract.getId() + "','" + contract.getAwardID() + "','" + contract.getStatus() + "')";
				//                System.out.println(insertTableSQL);
				statement.execute(insertTableSQL);
			}
		}
	}
	
	public static String cutString(String inputString, int length) {
		return inputString.length() > length ? inputString.substring(0, length) : inputString;
	}
	
	public static void main(String[] args) throws SQLException {
		//        insertTenders();
	}
}
