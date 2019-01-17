package ua.prozorro.prozorro.model;

import ua.prozorro.exchangeRates.parser.ExchangeRateNBUParser;
import ua.prozorro.prozorro.parser.ContractParser;
import ua.prozorro.prozorro.parser.DataParser;
import ua.prozorro.prozorro.parser.PlanParser;
import ua.prozorro.prozorro.parser.TenderParser;

public enum DataType {
	TENDERS("Тендеры", new TenderParser()), CONTRACTS("Контракты", new ContractParser()), PLANS("Планы",
			new PlanParser()), NBU_RATES("Курсы НБУ", new ExchangeRateNBUParser());

	private String typeName;
	private DataParser dataParser;

	DataType(String typeName, DataParser dataParser) {
		this.typeName = typeName;
		this.dataParser = dataParser;
	}

	public String getTypeName() {
		return typeName;
	}

	public DataParser getDataParser() {
		return dataParser;
	}
}
