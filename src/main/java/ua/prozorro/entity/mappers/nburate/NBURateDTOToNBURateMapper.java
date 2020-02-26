package ua.prozorro.entity.mappers.nburate;

import ua.prozorro.entity.mappers.AbstractMapper;
import ua.prozorro.entity.nburate.NBURate;
import ua.prozorro.source.nburate.NBURateDTO;

public class NBURateDTOToNBURateMapper extends AbstractMapper<NBURateDTO, NBURate> {
	
	@Override
	public NBURateDTO convertToObject(NBURate nbuRate) {
		if (nbuRate == null) {
			return null;
		}
		NBURateDTO nbuRateDTO = new NBURateDTO();
		nbuRateDTO.setStartDate(nbuRate.getDate());
		nbuRateDTO.setCurrencyCode(nbuRate.getCurrencyCode());
		nbuRateDTO.setCurrencyCodeL(nbuRate.getCurrencyCodeL());
		nbuRateDTO.setAmount(nbuRate.getAmount());
		nbuRateDTO.setUnits(nbuRate.getUnits());
		nbuRateDTO.setTimeSign(nbuRate.getTimeSign());
		
		return nbuRateDTO;
	}
	
	@Override
	public NBURate convertToEntity(NBURateDTO entity) {
		if (entity == null) {
			return null;
		}
		NBURate nbuRate = new NBURate();
		nbuRate.setDate(entity.getStartDate());
		nbuRate.setCurrencyCode(entity.getCurrencyCode());
		nbuRate.setCurrencyCodeL(entity.getCurrencyCodeL());
		nbuRate.setAmount(entity.getAmount());
		nbuRate.setUnits(entity.getUnits());
		nbuRate.setTimeSign(entity.getTimeSign());
		
		return nbuRate;
	}
}
