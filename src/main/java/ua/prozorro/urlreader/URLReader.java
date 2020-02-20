package ua.prozorro.urlreader;

import ua.prozorro.source.DataURL;

public interface URLReader<T> {
	T read(DataURL dataURL);
}
