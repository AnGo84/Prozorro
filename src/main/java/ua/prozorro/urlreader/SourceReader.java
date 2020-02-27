package ua.prozorro.urlreader;

import java.io.IOException;

@FunctionalInterface
public interface SourceReader<T> {
	String read(T source) throws IOException;
}
