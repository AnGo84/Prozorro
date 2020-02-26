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
		ActionResult actionResult = ActionResult.builder().resultType(ResultType.ADD).sourceType(
				SourceType.PROZORRO_CONTRACT).build();
		if (data != null) {
			actionResult.setObject(data);
			actionResult.setDate(data.getDateModified());
		}
		return actionResult;
	}
}
