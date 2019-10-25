package ua.prozorro.entity.mappers.prozorroObjectMapper;

import ua.prozorro.entity.mappers.AbstractMapper;
import ua.prozorro.entity.plans.BudgetDTO;
import ua.prozorro.prozorro.model.plans.Budget;

public class BudgetMapper extends AbstractMapper<Budget,BudgetDTO> {
    private ProjectMapper projectMapper = new ProjectMapper();
    @Override
    public BudgetDTO convertToEntity(Budget budget) {
        if (budget == null) {
            return null;
        }
        BudgetDTO budgetDTO = new BudgetDTO();


        budgetDTO.setId(budget.getId());
        budgetDTO.setAmountNet(budget.getAmountNet());
        budgetDTO.setDescription(budget.getDescription());
        budgetDTO.setNotes(budget.getNotes());

        budgetDTO.setProject(projectMapper.convertToEntity(budget.getProject()));

        budgetDTO.setCurrency(budget.getCurrency());
        budgetDTO.setAmount(budget.getAmount());
        budgetDTO.setYear(budget.getYear());

        return budgetDTO;
    }

    @Override
    public Budget convertToObject(BudgetDTO entity) {
        return null;
    }
}
