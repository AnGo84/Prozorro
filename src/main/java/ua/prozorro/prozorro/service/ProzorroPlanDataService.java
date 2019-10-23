package ua.prozorro.prozorro.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.prozorro.properties.PropertyFields;
import ua.prozorro.prozorro.model.plans.PlanData;


import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class ProzorroPlanDataService extends AbstractProzorroSourceDataService<PlanData> {
    private static final Logger logger = LogManager.getRootLogger();

    public ProzorroPlanDataService(PropertyFields propertyFields) {
        super(PlanData.class, propertyFields);
    }

    @Override
    public List<PlanData> getPagesList() throws IOException, ParseException {
        return null;
    }

	/*public List<ContractData> getContractsDataFromPageContent(ProzorroPageContent pageContent) throws IOException {
		if (pageContent == null || pageContent.getPageElementList() == null) {
			return null;
		}
		List<ContractData> contractDataList = new ArrayList<>();
		for (ProzorroPageElement element : pageContent.getPageElementList()) {
			ContractData contractData = getContractDataFromPageElement(element);
					contractDataList.add(contractData);
		}

		return contractDataList;
	}*/

}
