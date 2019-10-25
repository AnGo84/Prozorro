package ua.prozorro.entity.mappers.prozorroObjectMapper;

import ua.prozorro.entity.mappers.AbstractMapper;
import ua.prozorro.entity.plans.PlanDTO;
import ua.prozorro.prozorro.model.plans.Plan;

public class PlanMapper extends AbstractMapper<Plan, PlanDTO> {
    private DocumentsListMapper documentsListMapper = new DocumentsListMapper();
    private ClassificationMapper classificationMapper = new ClassificationMapper();
    private ClassificationsListMapper classificationsListMapper = new ClassificationsListMapper();
    private ItemsListMapper itemsListMapper = new ItemsListMapper();
    private ProcuringEntityMapper procuringEntityMapper = new ProcuringEntityMapper();
    private BudgetMapper budgetMapper = new BudgetMapper();
    private PlanTenderMapper planTenderMapper = new PlanTenderMapper();

    @Override
    public PlanDTO convertToEntity(Plan plan) {
        if (plan == null) {
            return null;
        }

        PlanDTO planDTO = new PlanDTO();

        planDTO.setId(plan.getId());

        planDTO.setPlanID(plan.getPlanID());

        planDTO.setDocuments(documentsListMapper.convertToEntitiesList(plan.getDocuments()));

        planDTO.setClassification(classificationMapper.convertToEntity(plan.getClassification()));

        planDTO.setItems(itemsListMapper.convertToEntitiesList(plan.getItems()));

        planDTO.setBudget(budgetMapper.convertToEntity(plan.getBudget()));

        planDTO.setAdditionalClassifications(classificationsListMapper.convertToEntitiesList(plan.getAdditionalClassifications()));

        planDTO.setProcuringEntity(procuringEntityMapper.convertToEntity(plan.getProcuringEntity()));

        planDTO.setTender(planTenderMapper.convertToEntity(plan.getTender()));
        if (planDTO.getTender() != null) {
            planDTO.getTender().setId(planDTO.getId());
        }

        planDTO.setDatePublished(plan.getDatePublished());
        planDTO.setOwner(plan.getOwner());
        planDTO.setDateModified(plan.getDateModified());

        return planDTO;
    }

    @Override
    public Plan convertToObject(PlanDTO entity) {
        return null;
    }
}
