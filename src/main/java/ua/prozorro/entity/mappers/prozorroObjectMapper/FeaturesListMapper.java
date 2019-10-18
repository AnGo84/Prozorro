package ua.prozorro.entity.mappers.prozorroObjectMapper;

import ua.prozorro.entity.mappers.AbstractListMapper;
import ua.prozorro.entity.tenders.FeatureDTO;
import ua.prozorro.prozorro.model.tenders.Feature;

import java.util.ArrayList;
import java.util.List;

public class FeaturesListMapper extends AbstractListMapper<Feature, FeatureDTO> {
    private FeatureMapper featureMapper = new FeatureMapper();
    @Override
    public List<FeatureDTO> convertToEntitiesList(List<Feature> featureList) {
        if (featureList == null || featureList.isEmpty()) {
            return null;
        }
        List<FeatureDTO> featureDTOList = new ArrayList<>();

        for (Feature feature : featureList) {
            FeatureDTO featureDTO = featureMapper.convertToEntity(feature);
            if (featureDTO != null) {
                featureDTOList.add(featureDTO);
            }
        }

        return featureDTOList;
    }

    @Override
    public List<Feature> convertToObjectsList(List<FeatureDTO> entities) {
        return null;
    }
}
