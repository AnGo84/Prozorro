package ua.prozorro.dataparser;

import ua.prozorro.source.ContentData;

public interface DataParser<T> {
	T parse(String JSON);
}
