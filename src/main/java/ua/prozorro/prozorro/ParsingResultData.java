package ua.prozorro.prozorro;

public class ParsingResultData {
	//nanoTime
	private long parsingTime = -1;
	private int listSize = -1;
	private boolean hasData = false;

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

	public long getParsingTimeInMilliSeconds() {
		if (parsingTime>0) {
			return this.parsingTime / 1000000;
		}
		else return parsingTime;
	}
}
