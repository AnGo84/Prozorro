package ua.prozorro.sourceService.prozorro;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.prozorro.properties.PropertyFields;
import ua.prozorro.prozorro.model.tenders.TenderData;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class ProzorroTenderDataService extends AbstractProzorroSourceDataService<TenderData> {
    private static final Logger logger = LogManager.getRootLogger();

    public ProzorroTenderDataService(PropertyFields propertyFields) {
        super(TenderData.class, propertyFields);
    }

    @Override
    public List<TenderData> getPagesList() throws IOException, ParseException {
        return null;
    }

	/*public List<TenderData> getTendersDataFromPageContent(ProzorroPageContent pageContent) throws IOException {
        if (pageContent == null || pageContent.getPageElementList() == null) {
            return null;
        }
        List<TenderData> tenderDataList = new ArrayList<>();
        for (ProzorroPageElement element : pageContent.getPageElementList()) {
            String currentTenderURL = tenderURL + element.getId();

            TenderData tenderData = getPageContentFromURL(currentTenderURL);
            tenderDataList.add(tenderData);
        }

        return tenderDataList;
    }}*/

}
