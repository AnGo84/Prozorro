package ua.prozorro.task;

import javafx.concurrent.Task;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ua.prozorro.entity.PlanDTOUtils;
import ua.prozorro.entity.exchangeRates.ExchangeRateNBUDTO;
import ua.prozorro.entity.exchangeRates.ExchangeRateNBUDTOUtils;
import ua.prozorro.entity.plans.PlanDTO;
import ua.prozorro.exchangeRates.ExchangeRateNBU;
import ua.prozorro.exchangeRates.ExchangeRateServiceNBU;
import ua.prozorro.properties.AppProperty;
import ua.prozorro.properties.PropertyFields;
import ua.prozorro.prozorro.ParsingResultData;
import ua.prozorro.prozorro.model.DataType;
import ua.prozorro.prozorro.model.pages.ProzorroPageElement;
import ua.prozorro.prozorro.model.plans.PlanData;
import ua.prozorro.prozorro.service.PlanDataServiceProzorro;
import ua.prozorro.service.ExchangeRateNBUService;
import ua.prozorro.service.PageService;
import ua.prozorro.service.PlanService;
import ua.prozorro.utils.DateUtils;

import java.util.Date;
import java.util.List;

public class NBURatesParserTask extends Task<Boolean> {
	private static final Logger logger = LogManager.getRootLogger();

	private SessionFactory sessionFactory;
	private PropertyFields propertyFields;
	private ParsingResultData resultData;

	public NBURatesParserTask() {
	}

	public NBURatesParserTask(PropertyFields propertyFields) {
		this.propertyFields = propertyFields;
	}

	public NBURatesParserTask(SessionFactory sessionFactory, PropertyFields propertyFields, ParsingResultData resultData) {
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

		ExchangeRateServiceNBU exchangeRateServiceNBU = new ExchangeRateServiceNBU(propertyFields);

		List<String> list = exchangeRateServiceNBU.getRatePagesList(DataType.NBU_RATES, propertyFields.getSearchDateFrom(), propertyFields.getSearchDateTill());
		if (list != null && !list.isEmpty()) {

			logger.info("Start parsing from URL " + list.get(0) + "\n");
			updateMessage("Start parsing from URL " + list.get(0) + "\n");

			Session session = null;
			Transaction transaction = null;

			String text = "";
			String currentURL = "";

			int pageCount = 0;

			try {

				session = sessionFactory.openSession();
				for (String url : list) {

					if (isCancelled()) {
						updateMessage("Cancelled \n");
						break;
					}

					currentURL = url;
					pageCount++;

					logger.info("Start parsing page №" + pageCount + "/" + resultData.getListSize() + ": \n");
					updateMessage("Start parsing page №" + pageCount + "/" + resultData.getListSize() + ": \n");

					List<ExchangeRateNBU> rateNBUList = exchangeRateServiceNBU.getRateContentFromURL(url);
					text = rateNBUList.toString();
					transaction = session.beginTransaction();

					ExchangeRateNBUService nbuService = new ExchangeRateNBUService(session);

					List<ExchangeRateNBUDTO> exchangeRateNBUDTOS = ExchangeRateNBUDTOUtils.getExchangeRateNBUDTOList(rateNBUList);

					boolean saveResult = nbuService.saveExchangeRateNBUList(exchangeRateNBUDTOS);

					session.flush();
					session.clear();
					transaction.commit();


					logger.info(propertyFields.getSearchDateType().getTypeName() + ": Страница № " + pageCount + "/" +
							            resultData.getListSize() + ". added/updated: " + saveResult + " \n");
					updateMessage(propertyFields.getSearchDateType().getTypeName() + ": Страница № " + pageCount + "/" +
							              resultData.getListSize() + ". added/updated: " + saveResult + " \n");

					updateProgress(pageCount, resultData.getListSize());
				}

			} catch (Exception e) {
				e.printStackTrace();
				logger.error("Page № " + pageCount + " url " + currentURL + ", msg: " + e.getMessage()+ "\n");
				logger.error("Rates: " + text+ "\n");

				updateMessage("Page № " + pageCount + " url " + currentURL + ", msg: " + e.getMessage()+ "\n");
				updateMessage("Rates: " + text+ "\n");

				if (transaction != null) {
					transaction.rollback();
				}

				throw new Exception(e);
			} finally {
				session.close();
			}


		} else {
			throw new NullPointerException("Did not found data for " + DataType.NBU_RATES + " for period from " +
					                               DateUtils.parseDateToString(propertyFields.getSearchDateFrom(), DateUtils.DATE_PATTERN_DD_MM_YYYY) +
					                               " to " + DateUtils.parseDateToString(propertyFields.getSearchDateTill(), DateUtils.DATE_PATTERN_DD_MM_YYYY));
		}

		return true;

	}
}
