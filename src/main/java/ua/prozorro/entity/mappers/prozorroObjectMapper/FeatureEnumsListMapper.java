package ua.prozorro.entity.mappers.prozorroObjectMapper;

import ua.prozorro.entity.mappers.AbstractListMapper;
import ua.prozorro.entity.tenders.FeatureEnumDTO;
import ua.prozorro.prozorro.model.tenders.FeatureEnum;

import java.util.ArrayList;
import java.util.List;

public class FeatureEnumsListMapper extends AbstractListMapper<FeatureEnum, FeatureEnumDTO> {
    private FeatureEnumMapper featureEnumMapper = new FeatureEnumMapper();

    @Override
    public List<FeatureEnumDTO> convertToEntitiesList(List<FeatureEnum> featureEnumList) {
        if (featureEnumList == null || featureEnumList.isEmpty()) {
            return null;
        }

        List<FeatureEnumDTO> featureEnumDTOList = new ArrayList<>();

        for (FeatureEnum featureEnum : featureEnumList) {
            FeatureEnumDTO featureEnumDTO = featureEnumMapper.convertToEntity(featureEnum);
            if (featureEnumDTO != null) {
                featureEnumDTOList.add(featureEnumDTO);
            }
        }

        return featureEnumDTOList;
    }

    @Override
    public List<FeatureEnum> convertToObjectsList(List<FeatureEnumDTO> entities) {
        return null;
    }
}
