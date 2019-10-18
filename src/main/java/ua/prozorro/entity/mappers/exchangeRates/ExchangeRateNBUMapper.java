package ua.prozorro.entity.mappers.exchangeRates;

import ua.prozorro.entity.exchangeRates.ExchangeRateNBUDTO;
import ua.prozorro.entity.mappers.AbstractMapper;
import ua.prozorro.exchangeRates.ExchangeRateNBU;

public class ExchangeRateNBUMapper extends AbstractMapper<ExchangeRateNBU, ExchangeRateNBUDTO> {

    @Override
    public ExchangeRateNBUDTO convertToEntity(ExchangeRateNBU exchangeRateNBU) {
        if (exchangeRateNBU == null) {
            return null;
        }
        ExchangeRateNBUDTO exchangeRateNBUDTO = new ExchangeRateNBUDTO();
        exchangeRateNBUDTO.setDate(exchangeRateNBU.getStartDate());
        exchangeRateNBUDTO.setCurrencyCode(exchangeRateNBU.getCurrencyCode());
        exchangeRateNBUDTO.setCurrencyCodeL(exchangeRateNBU.getCurrencyCodeL());
        exchangeRateNBUDTO.setAmount(exchangeRateNBU.getAmount());
        exchangeRateNBUDTO.setTimeSign(exchangeRateNBU.getTimeSign());

        return exchangeRateNBUDTO;
    }

    @Override
    public ExchangeRateNBU convertToObject(ExchangeRateNBUDTO entity) {
        return null;
    }
}
