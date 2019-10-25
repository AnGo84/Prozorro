package ua.prozorro.timeMeasure;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.prozorro.properties.PropertyFields;
import ua.prozorro.prozorro.model.pages.ProzorroPageContent;
import ua.prozorro.sourceService.prozorro.ProzorroPageDataService;

import java.util.Date;
import java.util.List;

public class ContractTimeMeasureImp implements TimeMeasureInterface {
    private static final Logger logger = LogManager.getRootLogger();
    //2000- approximately time in milliseconds for saving one contract to database
    private static final long TIME_FOR_SAVING_ONE_ITEMS = 2000;

    private PropertyFields propertyFields;

    public ContractTimeMeasureImp(PropertyFields propertyFields) {
        this.propertyFields = propertyFields;
    }

    @Override
    public ParsingResultData getListParsingData() throws Exception {
        Date start = new Date();
        /*PageServiceProzorro pageServiceProzorro = new PageServiceProzorro(propertyFields);
        List<ProzorroPageContent> list = prozorroPageDataService
                .getPagesList(propertyFields.getSearchDateType(), propertyFields.getSearchDateFrom(),
                        propertyFields.getSearchDateTill(), false);*/
        ProzorroPageDataService prozorroPageDataService = new ProzorroPageDataService(propertyFields);
        List<ProzorroPageContent> list = prozorroPageDataService.getPagesList();
        Date finish = new Date();
        long timeForPages = finish.getTime() - start.getTime();

        int listSize = 0;

        if (list != null && !list.isEmpty()) {
            listSize = list.size();
        }

        ParsingResultData resultData = new ParsingResultData();
        resultData.setParsingTime(timeForPages);
        resultData.setListSize(listSize);
        resultData.setHasData(listSize > 0);

        return resultData;
    }

    @Override
    public long getPageContentParseTime() throws Exception {
        /*Date start = new Date();
        PageServiceProzorro pageServiceProzorro = new PageServiceProzorro(propertyFields);
        ProzorroPageContent pageContent = pageServiceProzorro
                .getPageContentFromURL(pageServiceProzorro.getPageURL(propertyFields.getSearchDateFrom()));

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
            }

        Date finish = new Date();
        long timeForPageRecords = finish.getTime() - start.getTime();
        */
        //return timeForPageRecords;
        return 100;
    }

    @Override
    public long getTimeForSavingOneItems() {
        return TIME_FOR_SAVING_ONE_ITEMS;
    }
}
