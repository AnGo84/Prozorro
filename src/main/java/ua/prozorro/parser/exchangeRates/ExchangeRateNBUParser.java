package ua.prozorro.parser.exchangeRates;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ua.prozorro.entity.exchangeRates.ExchangeRateNBUDTO;
import ua.prozorro.entity.mappers.exchangeRates.ExchangeRateNBUListMapper;
import ua.prozorro.exchangeRates.ExchangeRateNBU;
import ua.prozorro.exchangeRates.ExchangeRateServiceNBU;
import ua.prozorro.prozorro.model.DataType;
import ua.prozorro.parser.DataParser;
import ua.prozorro.service.ExchangeRateNBUService;
import ua.prozorro.utils.DateUtils;

import java.util.Date;
import java.util.List;

public class ExchangeRateNBUParser implements DataParser {
	private static final Logger logger = LogManager.getRootLogger();
	
	private SessionFactory sessionFactory;
	
	private ExchangeRateServiceNBU exchangeRateServiceNBU;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public ExchangeRateServiceNBU getExchangeRateServiceNBU() {
		return exchangeRateServiceNBU;
	}
	
	public void setExchangeRateServiceNBU(ExchangeRateServiceNBU exchangeRateServiceNBU) {
		this.exchangeRateServiceNBU = exchangeRateServiceNBU;
	}
	
	@Override
	public boolean parseAndSave(Date dateFrom, Date dateTill) throws Exception {
		
		logger.info("Start parsing for period from " + DateUtils.dateToString(dateFrom) + " till " +
					DateUtils.dateToString(dateTill));
		
		List<String> list = exchangeRateServiceNBU.getRatePagesList(DataType.NBU_RATES, dateFrom, dateTill);
		if (list != null && !list.isEmpty()) {
			
			logger.info("Start parsing from URL " + list.get(0));
			
			Session session = null;
			Transaction transaction = null;
			
			String text = "";
			String currentURL = "";
			
			int pageCount = 0;
			
			try {
				
				session = sessionFactory.openSession();

				ExchangeRateNBUListMapper exchangeRateNBUListMapper = new ExchangeRateNBUListMapper();

				for (String url : list) {
					currentURL = url;
					pageCount++;
					List<ExchangeRateNBU> rateNBUList = exchangeRateServiceNBU.getRateContentFromURL(url);
					text = rateNBUList.toString();
					transaction = session.beginTransaction();
					
					ExchangeRateNBUService nbuService = new ExchangeRateNBUService(session);
					
					/*List<ExchangeRateNBUDTO> exchangeRateNBUDTOS =
							ExchangeRateNBUDTOUtils.getExchangeRateNBUDTOList(rateNBUList);*/
					List<ExchangeRateNBUDTO> exchangeRateNBUDTOS =
							exchangeRateNBUListMapper.convertToEntitiesList(rateNBUList);
					
					boolean saveResult = nbuService.saveExchangeRateNBUList(exchangeRateNBUDTOS);
					
					session.flush();
					session.clear();
					transaction.commit();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("Page № " + pageCount + " url " + currentURL + ", msg: " + e.getMessage());
				logger.error("Rates: " + text);
				
				if (transaction != null) {
					transaction.rollback();
				}
				
				throw new Exception(e);
			} finally {
				session.close();
			}
			
			
		} else {
			throw new NullPointerException("Did not found data for " + DataType.NBU_RATES + " for period from " +
										   DateUtils.parseDateToString(dateFrom, DateUtils.DATE_PATTERN_DD_MM_YYYY) +
										   " to " +
										   DateUtils.parseDateToString(dateFrom, DateUtils.DATE_PATTERN_DD_MM_YYYY));
		}
		
		return true;
	}
	
}
