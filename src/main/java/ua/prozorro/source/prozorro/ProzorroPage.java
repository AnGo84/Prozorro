package ua.prozorro.source.prozorro;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProzorroPage {
	
	@SerializedName("path")
	//@Expose
	private String path;
	@SerializedName("uri")
	//@Expose
	private String uri;
	@SerializedName("offset")
	//@Expose
	private String offset;
	
}
