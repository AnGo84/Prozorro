package ua.prozorro.source.prozorro;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class ProzorroPageContent {
	
	private ProzorroPage currentPage;
	
	@SerializedName("next_page")
	private ProzorroPage nextPage;
	
	@SerializedName("data")
	private List<ProzorroPageElement> pageElementList;
	
	@SerializedName("prev_page")
	private ProzorroPage prevPage;
	
}
