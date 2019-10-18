package ua.prozorro.entity.mappers.exchangeRates;

import ua.prozorro.entity.exchangeRates.ExchangeRateNBUDTO;
import ua.prozorro.entity.mappers.AbstractListMapper;
import ua.prozorro.exchangeRates.ExchangeRateNBU;

import java.util.ArrayList;
import java.util.List;

public class ExchangeRateNBUListMapper extends AbstractListMapper<ExchangeRateNBU, ExchangeRateNBUDTO> {
    private ExchangeRateNBUMapper exchangeRateNBUMapper = new ExchangeRateNBUMapper();

    @Override
    public List<ExchangeRateNBUDTO> convertToEntitiesList(List<ExchangeRateNBU> exchangeRateNBUList) {
        if (exchangeRateNBUList == null || exchangeRateNBUList.isEmpty()) {
            return null;
        }
        List<ExchangeRateNBUDTO> exchangeRateNBUDTOList = new ArrayList<>();

        for (ExchangeRateNBU exchangeRateNBU : exchangeRateNBUList) {
            ExchangeRateNBUDTO exchangeRateNBUDTO = exchangeRateNBUMapper.convertToEntity(exchangeRateNBU);
            exchangeRateNBUDTOList.add(exchangeRateNBUDTO);
        }

        return exchangeRateNBUDTOList;
    }

    @Override
    public List<ExchangeRateNBU> convertToObjectsList(List<ExchangeRateNBUDTO> entities) {
        return null;
    }
}
