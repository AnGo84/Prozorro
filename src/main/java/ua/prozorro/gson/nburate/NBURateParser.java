package ua.prozorro.gson.nburate;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.logging.log4j.util.Strings;
import ua.prozorro.gson.DataParser;
import ua.prozorro.source.nburate.NBURateDTO;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class NBURateParser implements DataParser<List<NBURateDTO>> {
	
	@Override
	public List<NBURateDTO> parse(String JSON) {
		if (Strings.isBlank(JSON)) {
			return null;
		}
		Gson gson = new Gson();
		Type listType = new TypeToken<ArrayList<NBURateDTO>>() {
		}.getType();
		List<NBURateDTO> nbuRateList = gson.fromJson(JSON, listType);
		return nbuRateList;
	}
	
}
