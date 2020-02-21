package ua.prozorro.service.prozorro;

import lombok.extern.log4j.Log4j2;
import org.hibernate.SessionFactory;
import ua.prozorro.entity.mappers.prozorro.ContentDataToPlanJSONMapper;
import ua.prozorro.entity.prozorro.PlanJSON;
import ua.prozorro.repositories.prozorro.PlanJSONRepository;
import ua.prozorro.service.ResultType;
import ua.prozorro.service.Service;
import ua.prozorro.source.ContentData;
import ua.prozorro.urlreader.ActionResult;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class PlanService implements Service {
	private PlanJSONRepository planJSONRepository;
	private ContentDataToPlanJSONMapper contentDataToPlanJSONMapper = new ContentDataToPlanJSONMapper();
	
	public PlanService(SessionFactory sessionFactory) {
		this.planJSONRepository = new PlanJSONRepository(sessionFactory);
	}
	
	@Override
	public List<ActionResult> saveOrUpdate(List<ContentData> data) throws IOException {
		if (data == null) {
			log.error("Empty Plans content data list");
			return Arrays.asList(ActionResult.builder().resultType(ResultType.ERROR)
											 .resultDescription("Empty Plans content data list").build());
		}
		
		List<PlanJSON> contractList = data.parallelStream().map(
				contentData -> contentDataToPlanJSONMapper.convertToEntity(contentData)).collect(Collectors.toList());
		
		List<ActionResult> actionResults = planJSONRepository.saveOrUpdateAll(contractList);
		
		return actionResults;
	}
	
}
