package ua.prozorro.entity.exchangeRates;

import ua.prozorro.entity.pages.ContractPageDTO;
import ua.prozorro.entity.plans.ProcuringEntityDTO;
import ua.prozorro.entity.tenders.*;
import ua.prozorro.exchangeRates.ExchangeRateNBU;
import ua.prozorro.prozorro.model.contracts.Contract;
import ua.prozorro.prozorro.model.pages.ProzorroPageElement;
import ua.prozorro.prozorro.model.plans.ProcuringEntity;
import ua.prozorro.prozorro.model.tenders.*;

import java.util.ArrayList;
import java.util.List;

public class ExchangeRateNBUDTOUtils {


    public static ExchangeRateNBUDTO getExchangeRateNBUDTO(ExchangeRateNBU exchangeRateNBU) {
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

    public static List<ExchangeRateNBUDTO> getExchangeRateNBUDTOList(List<ExchangeRateNBU> exchangeRateNBUList) {
        if (exchangeRateNBUList == null || exchangeRateNBUList.isEmpty()) {
            return null;
        }
        List<ExchangeRateNBUDTO> exchangeRateNBUDTOList = new ArrayList<>();

        for (ExchangeRateNBU exchangeRateNBU : exchangeRateNBUList) {
            ExchangeRateNBUDTO exchangeRateNBUDTO = getExchangeRateNBUDTO(exchangeRateNBU);
            exchangeRateNBUDTOList.add(exchangeRateNBUDTO);
        }

        return exchangeRateNBUDTOList;
    }

}
