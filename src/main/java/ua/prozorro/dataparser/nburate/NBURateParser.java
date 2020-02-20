package ua.prozorro.dataparser.nburate;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ua.prozorro.dataparser.DataParser;
import ua.prozorro.source.ContentData;
import ua.prozorro.source.nburate.NBURateDTO;
import ua.prozorro.source.nburate.NBURatesPageDTO;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class NBURateParser implements DataParser<List<NBURateDTO>> {
	/*@Override
	public NBURatesPageDTO parse(ContentData data) {
		Gson gson = new Gson();
		Type listType = new TypeToken<ArrayList<NBURateDTO>>() {}.getType();
		List<NBURateDTO> nbuRateList = gson.fromJson(data.getDataJSON(), listType);
		NBURatesPageDTO nbuRatesPageDTO = new NBURatesPageDTO(data, nbuRateList);
		return nbuRatesPageDTO;
	}*/
	
	@Override
	public List<NBURateDTO> parse(String JSON) {
		Gson gson = new Gson();
		Type listType = new TypeToken<ArrayList<NBURateDTO>>() {}.getType();
		List<NBURateDTO> nbuRateList = gson.fromJson(JSON, listType);
		return nbuRateList;
	}
	
	
}
