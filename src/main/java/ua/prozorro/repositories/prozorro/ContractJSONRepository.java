package ua.prozorro.repositories.prozorro;

import org.hibernate.SessionFactory;
import ua.prozorro.entity.prozorro.ContractJSON;
import ua.prozorro.repositories.AbstractDataRepository;
import ua.prozorro.service.ResultType;
import ua.prozorro.source.SourceType;
import ua.prozorro.urlreader.ActionResult;

public class ContractJSONRepository extends AbstractDataRepository<ContractJSON> {
	public ContractJSONRepository(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	
	@Override
	public ActionResult getAddActionResult(ContractJSON data) {
		return ActionResult.builder().resultType(ResultType.ADD).object(data).date(data.getDateModified())
						   .sourceType(SourceType.NBU_RATE).build();
	}
}
