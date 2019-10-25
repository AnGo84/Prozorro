package ua.prozorro.sourceService;

public enum DataType {
	TENDERS("Тендеры"), CONTRACTS("Контракты"), PLANS("Планы"), NBU_RATES("Курсы НБУ");
	
	private String typeName;
	
	
	DataType(String typeName) {
		this.typeName = typeName;
	}
	
	public String getTypeName() {
		return typeName;
	}
	
}
