package ua.prozorro.gson.nburate;

import com.google.gson.JsonSyntaxException;
import org.junit.jupiter.api.Test;
import ua.prozorro.source.nburate.NBURateDTO;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NBURateParserTest {
	private NBURateParser nbuRateParser = new NBURateParser();
	
	@Test
	public void whenParseNullOrEmptyString() {
		assertNull(nbuRateParser.parse(null));
		assertNull(nbuRateParser.parse(""));
	}
	
	@Test
	public void whenParseWrongString() {
		assertThrows(JsonSyntaxException.class, () -> {
			nbuRateParser.parse("wrong STRING");
		});
	}
	
	@Test
	public void whenParseCorrectString() {
		List<NBURateDTO> nbuRateDTOList = nbuRateParser.parse(getTestNBURatesData());
		assertNotNull(nbuRateDTOList);
		assertFalse(nbuRateDTOList.isEmpty());
		assertEquals(3, nbuRateDTOList.size());
	}
	
	private String getTestNBURatesData() {
		String nbuRates = "[\n { \n" +
						  "\"StartDate\":\"20.01.2020\",\"TimeSign\":\"0000\",\"CurrencyCode\":\"036\",\"CurrencyCodeL\":\"AUD\",\"Units\":1,\"Amount\":16.7077\n" +
						  " }\n" + ",{ \n" +
						  "\"StartDate\":\"20.01.2020\",\"TimeSign\":\"0000\",\"CurrencyCode\":\"944\",\"CurrencyCodeL\":\"AZN\",\"Units\":1,\"Amount\":14.2831\n" +
						  " }\n" + ",{ \n" +
						  "\"StartDate\":\"20.01.2020\",\"TimeSign\":\"0000\",\"CurrencyCode\":\"933\",\"CurrencyCodeL\":\"BYN\",\"Units\":1,\"Amount\":11.4367\n" +
						  " } \n]";
		return nbuRates;
	}
}