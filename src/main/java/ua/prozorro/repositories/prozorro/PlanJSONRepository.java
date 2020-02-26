package ua.prozorro.repositories.prozorro;

import org.hibernate.SessionFactory;
import ua.prozorro.entity.prozorro.PlanJSON;
import ua.prozorro.repositories.AbstractDataRepository;
import ua.prozorro.service.ResultType;
import ua.prozorro.source.SourceType;
import ua.prozorro.urlreader.ActionResult;

public class PlanJSONRepository extends AbstractDataRepository<PlanJSON> {
	public PlanJSONRepository(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	
	@Override
	public ActionResult getAddActionResult(PlanJSON data) {
		ActionResult actionResult = ActionResult.builder().resultType(ResultType.ADD).sourceType(
				SourceType.PROZORRO_PLAN).build();
		if (data != null) {
			actionResult.setObject(data);
			actionResult.setDate(data.getDateModified());
		}
		return actionResult;
		
	}
}
