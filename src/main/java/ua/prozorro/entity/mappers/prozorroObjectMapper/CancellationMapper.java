package ua.prozorro.entity.mappers.prozorroObjectMapper;

import ua.prozorro.entity.mappers.AbstractMapper;
import ua.prozorro.entity.tenders.CancellationDTO;
import ua.prozorro.prozorro.model.tenders.Cancellation;

public class CancellationMapper extends AbstractMapper<Cancellation, CancellationDTO> {
    private DocumentsListMapper documentsListMapper = new DocumentsListMapper();

    @Override
    public CancellationDTO convertToEntity(Cancellation cancellation) {
        if (cancellation == null) {
            return null;
        }
        CancellationDTO cancellationDTO = new CancellationDTO();

        cancellationDTO.setId(cancellation.getId());
        cancellationDTO.setStatus(cancellation.getStatus());
        cancellationDTO.setDocuments(documentsListMapper.convertToEntitiesList(cancellation.getDocuments()));
        cancellationDTO.setReason(cancellation.getReason());
        cancellationDTO.setReasonType(cancellation.getReasonType());
        cancellationDTO.setDate(cancellation.getDate());
        cancellationDTO.setCancellationOf(cancellation.getCancellationOf());

        return cancellationDTO;
    }

    @Override
    public Cancellation convertToObject(CancellationDTO entity) {
        return null;
    }
}
