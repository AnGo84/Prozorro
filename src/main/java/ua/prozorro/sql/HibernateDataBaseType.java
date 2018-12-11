package ua.prozorro.sql;

public enum HibernateDataBaseType {
	MYSQL("hibernate_mysql.cfg.xml"), ORACLE("hibernate_oracle.cfg.xml"), DEFAULT("hibernate.cfg.xml");
	private String configFileName;

	HibernateDataBaseType(String configFileName) {
		this.configFileName = configFileName;
	}

	public String getConfigFileName() {
		return configFileName;
	}

}
