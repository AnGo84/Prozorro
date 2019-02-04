package ua.prozorro.sql;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
	private static final Logger logger = LogManager.getRootLogger();
	
	private static final String MYSQL_CONNECTION =
			"jdbc:mysql://%s/%s?autoReconnect=true&useSSL=false&noAccessToProcedureBodies=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	
	
	public static Connection getDBConnection(Properties properties) throws ClassNotFoundException, SQLException {
		Connection dbConnection = null;
		if (properties != null) {
			//            Class.forName(DB_DRIVER);
			if (properties.getProperty("db.type").toUpperCase().equals(DatabaseType.MYSQL.name())) {
				//Class.forName("com.mysql.cj.jdbc.Driver");
				Class.forName(DatabaseType.MYSQL.getDtiverName());

				/*logger.info(String.format(MYSQL_CONNECTION, properties.getProperty("db.host"), properties.getProperty("db.name")),
						properties.getProperty("db.login"), properties.getProperty("db.password"));*/
				
				dbConnection = DriverManager.getConnection(
						String.format(MYSQL_CONNECTION, properties.getProperty("db.host"),
									  properties.getProperty("db.name")), properties.getProperty("db.login"),
						properties.getProperty("db.password"));
			}
			return dbConnection;
		}
		return dbConnection;
	}
	
}
