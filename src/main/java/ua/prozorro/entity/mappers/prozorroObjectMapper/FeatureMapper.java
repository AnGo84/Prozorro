package ua.prozorro.entity.mappers.prozorroObjectMapper;

import ua.prozorro.entity.mappers.AbstractMapper;
import ua.prozorro.entity.tenders.FeatureDTO;
import ua.prozorro.prozorro.model.tenders.Feature;

public class FeatureMapper extends AbstractMapper<Feature, FeatureDTO> {
    private FeatureEnumsListMapper featureEnumsListMapper = new FeatureEnumsListMapper();

    @Override
    public FeatureDTO convertToEntity(Feature feature) {
        if (feature == null) {
            return null;
        }
        FeatureDTO featureDTO = new FeatureDTO();

        featureDTO.setCode(feature.getCode());
        featureDTO.setFeatureOf(feature.getFeatureOf());
        featureDTO.setRelatedItem(feature.getRelatedItem());
        featureDTO.setTitle(feature.getTitle());
        featureDTO.setDescription(feature.getDescription());
        featureDTO.setTitleEn(feature.getTitleEn());
        featureDTO.setDescriptionEn(feature.getDescriptionEn());

        featureDTO.setFeatureEnums(featureEnumsListMapper.convertToEntitiesList(feature.getFeatureEnums()));

        return featureDTO;
    }

    @Override
    public Feature convertToObject(FeatureDTO entity) {
        return null;
    }
}
