package ua.prozorro.entity.mappers.prozorroObjectMapper;

import ua.prozorro.entity.mappers.AbstractMapper;
import ua.prozorro.entity.tenders.FeatureEnumDTO;
import ua.prozorro.prozorro.model.tenders.FeatureEnum;

public class FeatureEnumMapper extends AbstractMapper<FeatureEnum, FeatureEnumDTO> {

    @Override
    public FeatureEnumDTO convertToEntity(FeatureEnum featureEnum) {
        if (featureEnum == null) {
            return null;
        }
        FeatureEnumDTO featureEnumDTO = new FeatureEnumDTO();

        featureEnumDTO.setId(featureEnum.hashCode());
        featureEnumDTO.setTitle(featureEnum.getTitle());
        featureEnumDTO.setDescription(featureEnum.getDescription());
        featureEnumDTO.setValue(featureEnum.getValue());

        featureEnumDTO.setTitleEn(featureEnum.getTitleEn());
        featureEnumDTO.setDescriptionEn(featureEnum.getDescriptionEn());

        return featureEnumDTO;
    }

    @Override
    public FeatureEnum convertToObject(FeatureEnumDTO entity) {
        return null;
    }
}
