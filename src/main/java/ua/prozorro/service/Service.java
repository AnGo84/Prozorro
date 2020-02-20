package ua.prozorro.service;

import ua.prozorro.source.ContentData;
import ua.prozorro.urlreader.ActionResult;

import java.io.IOException;
import java.util.List;

public interface Service {
	List<ActionResult> saveOrUpdate(List<ContentData> data) throws IOException;
}
