package ua.prozorro.entity.mappers.nburate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.prozorro.entity.nburate.NBURate;
import ua.prozorro.source.nburate.NBURateDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class NBURateDTOToNBURateMapperTest {
	private NBURate nbuRate;
	private NBURateDTO nbuRateDTO;
	private NBURateDTOToNBURateMapper nbuRateDTOToNBURateMapper = new NBURateDTOToNBURateMapper();
	
	@BeforeEach
	public void init() {
		nbuRate = TestNBURatesData.testNBURate();
		nbuRateDTO = TestNBURatesData.testNBURateDTO();
	}
	
	@Test
	public void convertToObject_when_null() {
		assertNull(nbuRateDTOToNBURateMapper.convertToEntity(null));
		assertNull(nbuRateDTOToNBURateMapper.convertToObject(null));
	}
	
	@Test
	public void convertToObject() {
		assertEquals(nbuRateDTO, nbuRateDTOToNBURateMapper.convertToObject(nbuRate));
		
	}
	
	@Test
	public void convertToEntity() {
		assertEquals(nbuRate, nbuRateDTOToNBURateMapper.convertToEntity(nbuRateDTO));
	}
}