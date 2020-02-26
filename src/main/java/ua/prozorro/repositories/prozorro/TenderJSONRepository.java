package ua.prozorro.repositories.prozorro;

import org.hibernate.SessionFactory;
import ua.prozorro.entity.prozorro.TenderJSON;
import ua.prozorro.repositories.AbstractDataRepository;
import ua.prozorro.service.ResultType;
import ua.prozorro.source.SourceType;
import ua.prozorro.urlreader.ActionResult;

public class TenderJSONRepository extends AbstractDataRepository<TenderJSON> {
	public TenderJSONRepository(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	@Override
	public ActionResult getAddActionResult(TenderJSON data) {
		ActionResult actionResult = ActionResult.builder().resultType(ResultType.ADD).sourceType(
				SourceType.PROZORRO_TENDER).build();
		if (data != null) {
			actionResult.setObject(data);
			actionResult.setDate(data.getDateModified());
		}
		return actionResult;
	}
}
