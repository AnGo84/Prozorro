package ua.prozorro.repositories.nburate;

import lombok.extern.log4j.Log4j2;
import org.hibernate.SessionFactory;
import ua.prozorro.entity.nburate.NBURate;
import ua.prozorro.repositories.AbstractDataRepository;
import ua.prozorro.service.ResultType;
import ua.prozorro.source.SourceType;
import ua.prozorro.urlreader.ActionResult;

@Log4j2
public class NBURateRepository extends AbstractDataRepository<NBURate> {
	public NBURateRepository(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	@Override
	public ActionResult getAddActionResult(NBURate data) {
		return ActionResult.builder().resultType(ResultType.ADD).object(data).date(data.getDate())
						   .sourceType(SourceType.NBU_RATE).build();
	}
	
}
