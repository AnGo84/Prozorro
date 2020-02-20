package ua.prozorro.service;

import org.hibernate.SessionFactory;
import ua.prozorro.service.nburate.NBURateService;
import ua.prozorro.service.prozorro.ContractService;
import ua.prozorro.service.prozorro.PlanService;
import ua.prozorro.service.prozorro.TenderService;
import ua.prozorro.source.SourceType;

public class ServiceFactory {
	public static Service getService(SessionFactory sessionFactory, SourceType sourceType) {
		switch (sourceType) {
			case NBU_RATE:
				return new NBURateService(sessionFactory);
			
			case PROZORRO_CONTRACT:
				return new ContractService(sessionFactory);
			case PROZORRO_PLAN:
				return new PlanService(sessionFactory);
			case PROZORRO_TENDER:
				return new TenderService(sessionFactory);
			default:
				return null;
		}
	}
	
	
}
