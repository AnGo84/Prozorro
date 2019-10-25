package ua.prozorro.entity.mappers.prozorroObjectMapper;

import ua.prozorro.entity.mappers.AbstractMapper;
import ua.prozorro.entity.tenders.ComplaintDTO;
import ua.prozorro.entity.tenders.ComplaintDTO;
import ua.prozorro.prozorro.model.tenders.Complaint;

public class ComplaintMapper extends AbstractMapper<Complaint, ComplaintDTO> {
    private DocumentsListMapper documentsListMapper = new DocumentsListMapper();
    private OrganizationMapper organizationMapper = new OrganizationMapper();

    @Override
    public ComplaintDTO convertToEntity(Complaint complaint) {
        if (complaint == null) {
            return null;
        }
        ComplaintDTO complaintDTO = new ComplaintDTO();

        complaintDTO.setId(complaint.getId());
        complaintDTO.setAuthor(organizationMapper.convertToEntity(complaint.getAuthor()));
        complaintDTO.setTitle(complaint.getTitle());
        complaintDTO.setDescription(complaint.getDescription());
        complaintDTO.setDate(complaint.getDate());
        complaintDTO.setDateSubmitted(complaint.getDateSubmitted());
        complaintDTO.setDateAnswered(complaint.getDateAnswered());
        complaintDTO.setDateEscalated(complaint.getDateEscalated());
        complaintDTO.setDateDecision(complaint.getDateDecision());
        complaintDTO.setDateCanceled(complaint.getDateCanceled());
        complaintDTO.setStatus(complaint.getStatus());
        complaintDTO.setType(complaint.getType());
        complaintDTO.setResolution(complaint.getResolution());
        complaintDTO.setResolutionType(complaint.getResolutionType());
        complaintDTO.setSatisfied(complaint.isSatisfied());
        complaintDTO.setDecision(complaint.getDecision());
        complaintDTO.setCancellationReason(complaint.getCancellationReason());
        complaintDTO.setDocuments(documentsListMapper.convertToEntitiesList(complaint.getDocuments()));
        complaintDTO.setRelatedLot(complaint.getRelatedLot());
        complaintDTO.setTendererAction(complaint.getTendererAction());
        complaintDTO.setTendererActionDate(complaint.getTendererActionDate());

        return complaintDTO;
    }

    @Override
    public Complaint convertToObject(ComplaintDTO entity) {
        return null;
    }
}
