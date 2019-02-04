package ua.prozorro.prozorro.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ua.prozorro.entity.PlanDTOUtils;
import ua.prozorro.entity.pages.PlanPageDTO;
import ua.prozorro.entity.plans.PlanDTO;
import ua.prozorro.prozorro.model.pages.ProzorroPageContent;
import ua.prozorro.prozorro.model.pages.ProzorroPageElement;
import ua.prozorro.prozorro.model.plans.PlanData;
import ua.prozorro.prozorro.service.PageServiceProzorro;
import ua.prozorro.prozorro.service.PlanDataServiceProzorro;
import ua.prozorro.service.PageService;
import ua.prozorro.service.PlanService;
import ua.prozorro.utils.DateUtils;

import java.util.Date;

public class PlanParser implements DataParser {
	private static final Logger logger = LogManager.getRootLogger();

	private SessionFactory sessionFactory;

	private PageServiceProzorro pageServiceProzorro;
	private PlanDataServiceProzorro planDataServiceProzorro;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public PageServiceProzorro getPageServiceProzorro() {
		return pageServiceProzorro;
	}

	public void setPageServiceProzorro(PageServiceProzorro pageServiceProzorro) {
		this.pageServiceProzorro = pageServiceProzorro;
	}

	public PlanDataServiceProzorro getPlanDataServiceProzorro() {
		return planDataServiceProzorro;
	}

	public void setPlanDataServiceProzorro(PlanDataServiceProzorro planDataServiceProzorro) {
		this.planDataServiceProzorro = planDataServiceProzorro;
	}

	@Override
	public boolean parseAndSave(Date dateFrom, Date dateTill) throws Exception {

		logger.info("Start parsing for period from " + DateUtils.dateToString(dateFrom) + " till " +
					DateUtils.dateToString(dateTill));
		String startPageURL = pageServiceProzorro.getPlanPageURL(dateFrom);
		logger.info("Start parsing from URL " + startPageURL);

		PlanPageDTO page = null;

		Session session = null;
		Transaction transaction = null;

		String text = "";

		int pageCount = 0;
		int pageElementCount = 0;
		try {
			dateTill = pageServiceProzorro.getDateTill(dateTill);

			ProzorroPageContent pageContent = pageServiceProzorro.getPageContentFromURL(startPageURL);

			Date nextOffsetDate = pageServiceProzorro.getDateFromPageOffset(pageContent.getNextPage().getOffset());
			//logger.info("Get first ProzorroPage: " + pageContent);
			logger.info("Get first ProzorroPage");

			session = sessionFactory.openSession();
			while (dateTill.compareTo(nextOffsetDate) >= 0 && pageContent.getPageElementList() != null &&
				   !pageContent.getPageElementList().isEmpty()) {
				pageCount++;

				pageElementCount = 0;
				logger.info("Start parsing page №" + pageCount + ": ");

				for (ProzorroPageElement pageElement : pageContent.getPageElementList()) {
					pageElementCount++;

					PageService pageService = new PageService(session);

					transaction = session.beginTransaction();
					page = PlanDTOUtils.getPageDTO(pageElement);

					boolean updatedPage = pageService.savePlanPage(page, session);
					if (updatedPage) {
						PlanService planService = new PlanService(session);
						PlanData planData = planDataServiceProzorro.getPlanDataFromPageElement(pageElement);
						text = planData.toString();
						PlanDTO planDTO = PlanDTOUtils.getPlanDTO(planData.getPlan());
						planService.savePlan(planDTO, session);
					}
					logger.info("ProzorroPage № " + pageCount + ", tender on page № " + pageElementCount +
								", added/updated: " + updatedPage);

					session.flush();
					session.clear();
					transaction.commit();
				}
				logger.info("Get next page with URL: " + pageContent.getNextPage().getUri());
				pageContent = pageServiceProzorro.getPageContentFromURL(pageContent.getNextPage().getUri());
				nextOffsetDate = pageServiceProzorro.getDateFromPageOffset(pageContent.getNextPage().getOffset());
			}

		} catch (Exception e) {
			//catch (ParseException | IOException | Exception e){
			e.printStackTrace();
			logger.error("Page № " + pageCount + ", plan № " + pageElementCount + ", Parse error: " + page + ", msg: " +
						 e.getMessage());
			logger.error("Plan: " + text);

			if (transaction != null) {
				transaction.rollback();
			}

			throw new Exception(e);
		} finally {
			session.close();
		}

		return true;
	}

}
