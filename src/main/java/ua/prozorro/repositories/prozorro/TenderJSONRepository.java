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
		return ActionResult.builder().resultType(ResultType.ADD).object(data).date(data.getDateModified())
						   .sourceType(SourceType.NBU_RATE).build();
	}
}
