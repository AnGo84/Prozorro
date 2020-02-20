package ua.prozorro.source;

import lombok.Data;
import ua.prozorro.service.ResultType;
@Data
public class ReadResult {
	private ResultType resultType;
	private String description;
	
	public ReadResult(ResultType resultType, String description) {
		this.resultType = resultType;
		this.description = description;
	}
	
	public ReadResult(ResultType resultType) {
		this.resultType = resultType;
	}
}
