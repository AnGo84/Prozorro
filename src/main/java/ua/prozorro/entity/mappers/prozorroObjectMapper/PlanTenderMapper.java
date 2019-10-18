package ua.prozorro.entity.mappers.prozorroObjectMapper;

import ua.prozorro.entity.mappers.AbstractMapper;
import ua.prozorro.entity.plans.PlanTenderDTO;
import ua.prozorro.prozorro.model.tenders.Tender;

public class PlanTenderMapper extends AbstractMapper<Tender, PlanTenderDTO> {
    private ClassificationMapper classificationMapper = new ClassificationMapper();
    private AddressMapper addressMapper = new AddressMapper();
    private UnitMapper unitMapper = new UnitMapper();
    @Override
    public PlanTenderDTO convertToEntity(Tender tender) {
        if (tender == null) {
            return null;
        }

        PlanTenderDTO planTenderDTO = new PlanTenderDTO();

        planTenderDTO.setProcurementMethod(tender.getProcurementMethod());
        planTenderDTO.setProcurementMethodType(tender.getProcurementMethodType());

        if (tender.getTenderPeriod() != null) {
            planTenderDTO.setTenderPeriodStartDate(tender.getTenderPeriod().getStartDate());
            planTenderDTO.setTenderPeriodClarificationsUntil(tender.getTenderPeriod().getClarificationsUntil());
            planTenderDTO.setTenderPeriodEndDate(tender.getTenderPeriod().getEndDate());
            planTenderDTO.setTenderPeriodInvalidationDate(tender.getTenderPeriod().getInvalidationDate());
        }

        return planTenderDTO;
    }

    @Override
    public Tender convertToObject(PlanTenderDTO entity) {
        return null;
    }
}
