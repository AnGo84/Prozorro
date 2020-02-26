package ua.prozorro.entity.mappers.nburate;

import ua.prozorro.entity.nburate.NBURate;
import ua.prozorro.source.nburate.NBURateDTO;

import java.util.ArrayList;
import java.util.List;

public class TestNBURatesData {
	public static NBURateDTO testNBURateDTO() {
		NBURateDTO nbuRateDTO = new NBURateDTO();
		nbuRateDTO.setStartDate("20.01.2020");
		nbuRateDTO.setTimeSign("0000");
		nbuRateDTO.setCurrencyCode("036");
		nbuRateDTO.setCurrencyCodeL("AUD");
		nbuRateDTO.setUnits(1);
		nbuRateDTO.setAmount(16.7077);
		return nbuRateDTO;
	}
	
	public static NBURate testNBURate() {
		NBURate nbuRate = new NBURate();
		nbuRate.setDate("20.01.2020");
		nbuRate.setTimeSign("0000");
		nbuRate.setCurrencyCode("036");
		nbuRate.setCurrencyCodeL("AUD");
		nbuRate.setUnits(1);
		nbuRate.setAmount(16.7077);
		return nbuRate;
	}
	
	public static List<NBURate> testNbuRatesList() {
		List<NBURate> nbuRates = new ArrayList<>();
		nbuRates.add(testNBURate());
		return nbuRates;
	}
	
	public static List<NBURateDTO> testNbuRateDTOSList() {
		List<NBURateDTO> nbuRateDTOS = new ArrayList<>();
		nbuRateDTOS.add(testNBURateDTO());
		return nbuRateDTOS;
	}
}
