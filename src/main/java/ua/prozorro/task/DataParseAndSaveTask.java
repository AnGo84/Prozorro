package ua.prozorro.task;

import javafx.concurrent.Task;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ua.prozorro.entity.EventResultData;
import ua.prozorro.entity.PageElement;
import ua.prozorro.parser.AbstractDataParserAndSaver;
import ua.prozorro.parser.DataParserAndSaverFactory;
import ua.prozorro.properties.PropertyFields;
import ua.prozorro.timeMeasure.ParsingResultData;

public class DataParseAndSaveTask extends Task<Boolean> {
    private static final Logger logger = LogManager.getRootLogger();

    private SessionFactory sessionFactory;
    private PropertyFields propertyFields;
    private ParsingResultData resultData;

    public DataParseAndSaveTask() {
    }

    public DataParseAndSaveTask(SessionFactory sessionFactory, PropertyFields propertyFields, ParsingResultData resultData) {
        this.propertyFields = propertyFields;
        this.sessionFactory = sessionFactory;
        this.resultData = resultData;
    }

    public PropertyFields getPropertyFields() {
        return propertyFields;
    }

    public void setPropertyFields(PropertyFields propertyFields) {
        this.propertyFields = propertyFields;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public ParsingResultData getResultData() {
        return resultData;
    }

    public void setResultData(ParsingResultData resultData) {
        this.resultData = resultData;
    }

    @Override
    protected Boolean call() throws Exception {
        AbstractDataParserAndSaver dataParserAndSaver = DataParserAndSaverFactory.getDataParserAndSaver(propertyFields, resultData);

        String currentPageURL = dataParserAndSaver.startPageURL();

        infoLogging("Start parsing from URL " + currentPageURL);
        /*logger.info("Start parsing from URL " + currentPageURL);
        updateMessage("Start parsing from URL " + currentPageURL + "\n");*/

        Transaction transaction = null;

        int pageCount = 0;
        try (Session session = sessionFactory.openSession();) {
            updateProgress(pageCount, resultData.getListSize());
            dataParserAndSaver.initDataByURL(currentPageURL);

            logger.info("Get first Page for " + propertyFields.getSearchDateType());

            //session =
            while (dataParserAndSaver.hasNextData()) {

                if (isCancelled()) {
                    updateMessage("Cancelled \n");
                    break;
                }
                pageCount++;
                int pageElementCount = 0;

                infoLogging("Start parsing page №" + pageCount + ": ");
                /*logger.info("Start parsing page №" + pageCount + ": ");
                updateMessage("Start parsing page №" + pageCount + ": \n");*/

                for (PageElement pageElement : dataParserAndSaver.getPageElementList()) {
                    EventResultData checkExpireElement = dataParserAndSaver.checkExpireElement(pageElement);
                    pageElementCount++;
                    String checkResult;
                    if (checkExpireElement.isHasResult()) {
                        checkResult = String.format(checkExpireElement.getEventResult(), pageCount, resultData.getListSize(), pageElementCount);
                        logger.warn(checkResult);
                        updateMessage(checkResult);
                        break;
                    }

                    transaction = session.beginTransaction();
                    EventResultData parseAndSaveResult = dataParserAndSaver.parseAndSave(pageElement, session);

                    infoLogging(String.format(parseAndSaveResult.getEventResult(), pageCount, resultData.getListSize(), pageElementCount));
                    /* checkResult = String.format(parseAndSaveResult.getEventResult(), pageCount, pageElementCount);
                    logger.info(checkResult);
                    updateMessage(checkResult);*/

                    session.flush();
                    session.clear();
                    transaction.commit();
                }

                dataParserAndSaver.getNextData();
                currentPageURL = dataParserAndSaver.getCurrentPageURL();

                infoLogging("Get next " + propertyFields.getSearchDateType() + " page with URL: " + currentPageURL);

                updateProgress(pageCount, resultData.getListSize());

                updateMessage("Найдено " + ((pageCount - 1) * 100 + pageElementCount) + " " +
                        propertyFields.getSearchDateType().getTypeName() + "\n");
            }
        } catch (Exception e) {
            //catch (ParseException | IOException | Exception e){
            e.printStackTrace();

            logger.error("ERROR on URL: " + currentPageURL);
            logger.error("ERROR message: " + e.getClass());

            updateMessage("!!! ERROR on URL: " + currentPageURL);
            updateMessage("!!! ERROR message: " + e.getMessage());
            updateMessage("!!! ERROR : For details see log");
            if (transaction != null) {
                transaction.rollback();
            }
            throw new Exception(e);
        }
        //finally {session.close();}
        return true;
    }

    private void infoLogging(String logText) {
        logger.info(logText);
        updateMessage(logText + " \n");
    }
}
