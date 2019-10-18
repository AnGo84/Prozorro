package ua.prozorro.entity.mappers.prozorroObjectMapper;

import ua.prozorro.entity.mappers.AbstractListMapper;
import ua.prozorro.entity.tenders.LotDTO;
import ua.prozorro.prozorro.model.tenders.Lot;

import java.util.ArrayList;
import java.util.List;

public class LotsListMapper extends AbstractListMapper<Lot, LotDTO> {
    private LotMapper lotMapper = new LotMapper();
    @Override
    public List<LotDTO> convertToEntitiesList(List<Lot> lotList) {
        if (lotList == null || lotList.isEmpty()) {
            return null;
        }
        List<LotDTO> lotDTOList = new ArrayList<>();

        for (Lot lot : lotList) {
            LotDTO lotDTO = lotMapper.convertToEntity(lot);
            if (lotDTO != null) {
                lotDTOList.add(lotDTO);
            }
        }

        return lotDTOList;
    }

    @Override
    public List<Lot> convertToObjectsList(List<LotDTO> entities) {
        return null;
    }
}
