package ua.prozorro.sql;

public enum DatabaseType {
	MYSQL("MySql", "com.mysql.cj.jdbc.Driver"), ORACLE("Oracle", "");
	
	private String databaseName;
	private String dtiverName;
	
	DatabaseType(String databaseName, String dtiverName) {
		this.databaseName = databaseName;
		this.dtiverName = dtiverName;
	}
	
	public String getDatabaseName() {
		return databaseName;
	}
	
	public String getDtiverName() {
		return dtiverName;
	}
}
