package ua.prozorro.urlreader;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class URLSourceReader implements SourceReader<String> {
	
	public String read(String url) throws IOException {
		return IOUtils.toString(new URL(url), StandardCharsets.UTF_8);
	}
}
