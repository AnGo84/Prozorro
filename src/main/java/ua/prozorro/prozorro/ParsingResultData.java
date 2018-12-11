package ua.prozorro.prozorro;

public class ParsingResultData {
	private long parsingTime;
	private int listSize;
	private boolean hasData;

	public long getParsingTime() {
		return parsingTime;
	}

	public void setParsingTime(long parsingTime) {
		this.parsingTime = parsingTime;
	}

	public int getListSize() {
		return listSize;
	}

	public void setListSize(int listSize) {
		this.listSize = listSize;
	}

	public boolean isHasData() {
		return hasData;
	}

	public void setHasData(boolean hasData) {
		this.hasData = hasData;
	}
}
