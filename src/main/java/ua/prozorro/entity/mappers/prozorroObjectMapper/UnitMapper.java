package ua.prozorro.entity.mappers.prozorroObjectMapper;

import ua.prozorro.entity.mappers.AbstractMapper;
import ua.prozorro.entity.tenders.UnitDTO;
import ua.prozorro.prozorro.model.tenders.Unit;

public class UnitMapper extends AbstractMapper<Unit, UnitDTO> {
    @Override
    public UnitDTO convertToEntity(Unit unit) {

        if (unit == null || unit.getCode() == null|| unit.getCode().equals("")) {
            return null;
        }
        UnitDTO unitDTO = new UnitDTO();

        unitDTO.setCode(unit.getCode());
        unitDTO.setName(unit.getName());

        return unitDTO;
    }

    @Override
    public Unit convertToObject(UnitDTO entity) {
        return null;
    }
}
