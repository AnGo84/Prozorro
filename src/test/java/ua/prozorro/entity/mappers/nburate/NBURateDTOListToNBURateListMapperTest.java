package ua.prozorro.entity.mappers.nburate;

import org.junit.jupiter.api.Test;
import ua.prozorro.entity.nburate.NBURate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NBURateDTOListToNBURateListMapperTest {
	private NBURateDTOListToNBURateListMapper nbuRateDTOListToNBURateListMapper =
			new NBURateDTOListToNBURateListMapper();
	
	@Test
	public void convertToEntity() {
		List<NBURate> nbuRates = nbuRateDTOListToNBURateListMapper.convertToEntity(
				TestNBURatesData.testNbuRateDTOSList());
		assertNotNull(nbuRates);
		assertEquals(1, nbuRates.size());
		assertEquals(TestNBURatesData.testNBURate(), nbuRates.get(0));
		assertNotEquals(new NBURate(), nbuRates.get(0));
	}
	
	@Test
	public void convertToObject() {
		assertNull(nbuRateDTOListToNBURateListMapper.convertToObject(null));
		assertNull(nbuRateDTOListToNBURateListMapper.convertToObject(new ArrayList<>()));
		List<NBURate> list = Arrays.asList(new NBURate[]{new NBURate()});
		assertNull(nbuRateDTOListToNBURateListMapper.convertToObject(list));
	}
	
	
}