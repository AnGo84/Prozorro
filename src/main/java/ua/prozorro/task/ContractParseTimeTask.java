package ua.prozorro.task;

import javafx.concurrent.Task;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.prozorro.properties.PropertyFields;
import ua.prozorro.sourceService.prozorro.ProzorroPageDataService;
import ua.prozorro.timeMeasure.ParsingResultData;
import ua.prozorro.prozorro.model.pages.ProzorroPageContent;

import java.util.Date;
import java.util.List;

@Deprecated
public class ContractParseTimeTask extends Task<ParsingResultData> {
    private static final Logger logger = LogManager.getRootLogger();
    private PropertyFields propertyFields;

    public ContractParseTimeTask(PropertyFields propertyFields) {
        this.propertyFields = propertyFields;
    }

    @Override
    protected ParsingResultData call() throws Exception {
        updateMessage("Start searching for '" + propertyFields.getSearchDateType().name() + "' \n");
        Date start = new Date();
        /*PageServiceProzorro pageServiceProzorro = new PageServiceProzorro(propertyFields);
        List<ProzorroPageContent> list = pageServiceProzorro
                .getPagesList(propertyFields.getSearchDateType(), propertyFields.getSearchDateFrom(),
                        propertyFields.getSearchDateTill(), false);*/
        ProzorroPageDataService prozorroPageDataService = new ProzorroPageDataService(propertyFields);
        List<ProzorroPageContent> list = prozorroPageDataService.getPagesList();
        Date finish = new Date();

        long timeForPages = finish.getTime() - start.getTime();
        //System.out.println("Time for page without data: " + timeForPages);

        start = new Date();
        if (list != null && !list.isEmpty()) {
            logger.info("Found pages: " + list.size() + " \n");
            updateMessage("Found pages: " + list.size() + " \n");
            /*ProzorroPageContent pageContent = prozorroPageDataService
                    .getObjectFromURL(prozorroPageDataService.getPageURL(propertyFields.getSearchDateFrom()));

            ContractDataServiceProzorro contractDataServiceProzorro = new ContractDataServiceProzorro(
                    propertyFields.getPropertiesStringValue(AppProperty.CONTRACT_START_PAGE) + "/");
            logger.info("Start getting Contracts from page \n");
            updateMessage("Start getting Contracts from page \n");
            List<ContractData> contractDataList =
                    contractDataServiceProzorro.getContractsDataFromPageContent(pageContent);

            if (contractDataList != null) {
                int i = 0;
                for (ContractData contractData : contractDataList) {
                    i++;
                    logger.info("Get Tender № " + i + "\n");
                    ContractDTO contractDTO = ContractDTOUtils.getContractDTO(contractData.getContract());
                }
            }*/
        } else {
            updateMessage("Contracts not found! \n");
        }

        finish = new Date();
        long timeForPageTenders = finish.getTime() - start.getTime();

        //100- contracts on page
        //2000- approximately time in milliseconds for saving one contract to database
        long totalTime = timeForPages + list.size() * (timeForPageTenders + 100 * 2000);

        logger.info("Finished. Total Time: " + totalTime + "ms \n");
        updateMessage("Finished Total Time: " + totalTime + "ms \n");

        ParsingResultData resultData = new ParsingResultData();
        resultData.setParsingTime(totalTime);
        resultData.setListSize(list.size());
        resultData.setHasData((list != null && !list.isEmpty()));
        return resultData;
    }

}
