package ua.prozorro.entity.mappers.nburate;

import ua.prozorro.entity.mappers.AbstractMapper;
import ua.prozorro.entity.nburate.NBURate;
import ua.prozorro.source.nburate.NBURateDTO;

import java.util.List;
import java.util.stream.Collectors;

public class NBURateDTOListToNBURateListMapper extends AbstractMapper<List<NBURateDTO>, List<NBURate>> {
	private NBURateDTOToNBURateMapper mapper = new NBURateDTOToNBURateMapper();
	
	@Override
	public List<NBURate> convertToEntity(List<NBURateDTO> object) {
		if (object == null || object.isEmpty()) {
			return null;
		}
		List<NBURate> nbuRatesList = object.stream().parallel().map(nbuRateDTO -> mapper.convertToEntity(nbuRateDTO))
										   .collect(Collectors.toList());
		return nbuRatesList;
	}
	
	@Override
	public List<NBURateDTO> convertToObject(List<NBURate> entity) {
		return null;
	}
}
