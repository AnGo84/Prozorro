package ua.prozorro.entity.mappers.prozorroObjectMapper;

import ua.prozorro.entity.mappers.AbstractMapper;
import ua.prozorro.entity.tenders.AwardDTO;
import ua.prozorro.prozorro.model.tenders.Award;

public class AwardMapper extends AbstractMapper<Award, AwardDTO> {
    private DocumentsListMapper documentsListMapper = new DocumentsListMapper();
    private OrganizationsListMapper organizationsListMapper = new OrganizationsListMapper();

    @Override
    public AwardDTO convertToEntity(Award award) {
        if (award == null) {
            return null;
        }

        AwardDTO awardDTO = new AwardDTO();

        awardDTO.setId(award.getId());
        awardDTO.setStatus(award.getStatus());

        awardDTO.setDocuments(documentsListMapper.convertToEntitiesList(award.getDocuments()));

        awardDTO.setDate(award.getDate());
        awardDTO.setBidId(award.getBidId());
        awardDTO.setCurrency(award.getBidId());
        if (award.getValue() != null) {
            awardDTO.setAmount(award.getValue().getAmount());
            awardDTO.setCurrency(award.getValue().getCurrency());
            awardDTO.setValueAddedTaxIncluded(award.getValue().getValueAddedTaxIncluded());
        }
        if (award.getComplaintPeriod() != null) {
            awardDTO.setComplaintPeriodStartDate(award.getComplaintPeriod().getStartDate());
            awardDTO.setComplaintPeriodClarificationsUntil(award.getComplaintPeriod().getClarificationsUntil());
            awardDTO.setComplaintPeriodEndDate(award.getComplaintPeriod().getEndDate());
            awardDTO.setComplaintPeriodInvalidationDate(award.getComplaintPeriod().getInvalidationDate());
        }
        awardDTO.setSuppliers(organizationsListMapper.convertToEntitiesList(award.getSuppliers()));

        awardDTO.setEligible(award.isEligible());
        awardDTO.setQualified(award.isQualified());


        return awardDTO;
    }

    @Override
    public Award convertToObject(AwardDTO entity) {
        return null;
    }
}
