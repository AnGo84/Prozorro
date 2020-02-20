package ua.prozorro.source.prozorro;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class ProzorroPageElement {
	
	@SerializedName("id")
	private String id;
	
	@SerializedName("dateModified")
	private String dateModified;
	
}
