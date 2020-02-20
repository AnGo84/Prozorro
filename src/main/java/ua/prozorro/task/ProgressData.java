package ua.prozorro.task;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProgressData {
	private int totalCount;
	private boolean withProgress;
}
