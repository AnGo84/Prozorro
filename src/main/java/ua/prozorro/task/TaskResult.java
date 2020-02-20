package ua.prozorro.task;

import lombok.Data;

@Data
public class TaskResult {
	public static final int AVERAGE_PAGE_PROCESSING_TIME = 2;
	private int totalCount;
	private long totalTime;
	private String message;
	
	public TaskResult(int totalCount, long totalTime) {
		this.totalCount = totalCount;
		this.totalTime = totalTime;
	}
	
	public long getProcessingTime() {
		return (totalTime * totalCount * AVERAGE_PAGE_PROCESSING_TIME);
	}
}
