package ua.prozorro.service.prozorro;

import lombok.extern.log4j.Log4j2;
import org.hibernate.SessionFactory;
import ua.prozorro.entity.mappers.prozorro.ContentDataToTenderJSONMapper;
import ua.prozorro.entity.prozorro.TenderJSON;
import ua.prozorro.repositories.prozorro.TenderJSONRepository;
import ua.prozorro.service.ResultType;
import ua.prozorro.service.Service;
import ua.prozorro.source.ContentData;
import ua.prozorro.urlreader.ActionResult;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class TenderService implements Service {
	private TenderJSONRepository tenderJSONRepository;
	private ContentDataToTenderJSONMapper contentDataToTenderJSONMapper = new ContentDataToTenderJSONMapper();
	
	public TenderService(SessionFactory sessionFactory) {
		this.tenderJSONRepository = new TenderJSONRepository(sessionFactory);
	}
	
	@Override
	public List<ActionResult> saveOrUpdate(List<ContentData> data) throws IOException {
		if (data == null || data.isEmpty()) {
			log.error("Empty Tenders content data list");
			return Arrays.asList(ActionResult.builder().resultType(ResultType.ERROR)
											 .resultDescription("Empty Tenders content data list").build());
		}
		
		List<TenderJSON> tenderJSONS = data.parallelStream().map(
				contentData -> contentDataToTenderJSONMapper.convertToEntity(contentData)).collect(Collectors.toList());
		
		List<ActionResult> actionResults = tenderJSONRepository.saveOrUpdateAll(tenderJSONS);
		
		/*
		List<ExportResult> exportResults =
				data.parallelStream().map(contentData -> saveContentData(contentData)).collect(Collectors.toList());
		Map<String, TenderJSON> tenderJSONMap = data.parallelStream().collect(
				Collectors.toMap(el -> el.getDataURL().getUrl(), el -> contentDataToTenderJSONMapper.convertToEntity(el)));*/
		
		return actionResults;
	}
	
}
