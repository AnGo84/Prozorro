package ua.prozorro.repositories;

import ua.prozorro.urlreader.ActionResult;

import java.io.IOException;
import java.util.List;

public interface DataRepository<T> {
	ActionResult saveOrUpdate(T data) throws IOException;
	
	List<ActionResult> saveOrUpdateAll(List<T> listOfData) throws IOException;
	
	ActionResult getAddActionResult(T data);
}
