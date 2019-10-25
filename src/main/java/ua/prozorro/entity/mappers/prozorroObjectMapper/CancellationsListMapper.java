package ua.prozorro.entity.mappers.prozorroObjectMapper;

import ua.prozorro.entity.mappers.AbstractListMapper;
import ua.prozorro.entity.tenders.CancellationDTO;
import ua.prozorro.prozorro.model.tenders.Cancellation;

import java.util.ArrayList;
import java.util.List;

public class CancellationsListMapper extends AbstractListMapper<Cancellation, CancellationDTO> {
    private CancellationMapper cancellationMapper = new CancellationMapper();

    @Override
    public List<CancellationDTO> convertToEntitiesList(List<Cancellation> cancellationList) {
        if (cancellationList == null || cancellationList.isEmpty()) {
            return null;
        }
        List<CancellationDTO> cancellationDTOList = new ArrayList<>();

        for (Cancellation cancellation : cancellationList) {
            CancellationDTO cancellationDTO = cancellationMapper.convertToEntity(cancellation);
            if (cancellationDTO != null) {
                cancellationDTOList.add(cancellationDTO);
            }
        }

        return cancellationDTOList;
    }

    @Override
    public List<Cancellation> convertToObjectsList(List<CancellationDTO> entities) {
        return null;
    }
}
