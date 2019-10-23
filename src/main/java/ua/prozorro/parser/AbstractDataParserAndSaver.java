package ua.prozorro.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ua.prozorro.entity.EventResultData;
import ua.prozorro.entity.PageElement;
import ua.prozorro.properties.PropertyFields;
import ua.prozorro.timeMeasure.ParsingResultData;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public abstract class AbstractDataParserAndSaver implements DataParserAndSaver {
    private static final Logger logger = LogManager.getRootLogger();

    protected String currentPageURL;
    protected PropertyFields propertyFields;
    protected ParsingResultData resultData;

    public AbstractDataParserAndSaver() {
    }

    public AbstractDataParserAndSaver(PropertyFields propertyFields, ParsingResultData resultData) {
        this.propertyFields = propertyFields;
        this.resultData = resultData;
    }

    public String getCurrentPageURL() {
        return currentPageURL;
    }

    @Override
    public abstract String startPageURL();

    @Override
    public abstract void initDataByURL(String url) throws IOException, ParseException;

    @Override
    public abstract boolean hasNextData();

    @Override
    public abstract List<PageElement> getPageElementList();

    @Override
    public abstract EventResultData checkExpireElement(PageElement pageElement) throws ParseException;

    @Override
    public abstract EventResultData parseAndSave(PageElement pageElement, Session session) throws Exception;

    @Override
    public abstract boolean getNextData() throws Exception;

    public boolean parseAndSave(SessionFactory sessionFactory) throws Exception {
        String currentPageURL = startPageURL();

        logger.info("Start parsing from URL " + currentPageURL);

        Transaction transaction = null;

        int pageCount = 0;
        try (Session session = sessionFactory.openSession();) {

            initDataByURL(currentPageURL);
            logger.info("Get first Page for " + propertyFields.getSearchDateType());

            while (hasNextData()) {
                pageCount++;
                int pageElementCount = 0;
                logger.info("Start parsing page №" + pageCount + ": ");
                for (PageElement pageElement : getPageElementList()) {
                    EventResultData checkExpireElement = checkExpireElement(pageElement);
                    pageElementCount++;
                    String checkResult;
                    if (checkExpireElement.isHasResult()) {
                        checkResult = String.format(checkExpireElement.getEventResult(), pageCount, pageElementCount);
                        logger.warn(checkResult);
                        break;
                    }

                    transaction = session.beginTransaction();
                    EventResultData parseAndSaveResult = parseAndSave(pageElement, session);

                    checkResult = String.format(parseAndSaveResult.getEventResult(), pageCount, pageElementCount);
                    logger.info(checkResult);

                    session.flush();
                    session.clear();
                    transaction.commit();
                }

                getNextData();
                currentPageURL = getCurrentPageURL();

                logger.info("Get next " + propertyFields.getSearchDateType() + " page with URL: " + currentPageURL);
                logger.info("Найдено " + ((pageCount - 1) * 100 + pageElementCount) + " " +
                        propertyFields.getSearchDateType().getTypeName() + "\n");
            }
        } catch (Exception e) {
            //catch (ParseException | IOException | Exception e){
            e.printStackTrace();

            logger.error("ERROR on URL: " + currentPageURL);
            logger.error("ERROR message: " + e.getClass());

            if (transaction != null) {
                transaction.rollback();
            }
            throw new Exception(e);
        }
        //finally {session.close();}
        return true;
    }
}
