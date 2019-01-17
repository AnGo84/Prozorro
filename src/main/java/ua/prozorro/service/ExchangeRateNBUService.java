package ua.prozorro.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;
import ua.prozorro.entity.exchangeRates.ExchangeRateNBUDTO;
import ua.prozorro.entity.tenders.*;

import java.util.List;

public class ExchangeRateNBUService {
    private static final Logger logger = LogManager.getRootLogger();

    private Session session;

    public ExchangeRateNBUService(Session session) {
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
