package ua.prozorro.sourceService;

import ua.prozorro.parser.DataParser;
import ua.prozorro.parser.exchangeRates.ExchangeRateNBUParser;
import ua.prozorro.parser.prozorro.ContractParser;
import ua.prozorro.parser.prozorro.PlanParser;
import ua.prozorro.parser.prozorro.TenderParser;

public enum DataType {
    TENDERS("Тендеры", new TenderParser()), CONTRACTS("Контракты", new ContractParser()),
    PLANS("Планы", new PlanParser()), NBU_RATES("Курсы НБУ", new ExchangeRateNBUParser());

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
