package ua.prozorro.repositories;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import ua.prozorro.entity.exchangeRates.ExchangeRateNBUDTO;

import java.util.List;

public class ExchangeRateNBURepository {
	private static final Logger logger = LogManager.getRootLogger();
	
	private Session session;
	
	public ExchangeRateNBURepository(Session session) {
		this.session = session;
	}
	
	public boolean saveExchangeRateNBUList(List<ExchangeRateNBUDTO> exchangeRateNBUDTOList) throws Exception {
		if (session == null) {
			throw new Exception("Session did not set");
		}
		
		session.flush();
		session.clear();
		
		if (exchangeRateNBUDTOList != null && !exchangeRateNBUDTOList.isEmpty()) {
			for (ExchangeRateNBUDTO rateNBUDTO : exchangeRateNBUDTOList) {
				session.saveOrUpdate(rateNBUDTO);
				session.flush();
				session.clear();
				
			}
		}
		
		session.flush();
		session.clear();
		
		return true;
	}
	
}
