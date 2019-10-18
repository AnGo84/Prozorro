package ua.prozorro.entity.mappers.prozorroObjectMapper;

import ua.prozorro.entity.mappers.AbstractMapper;
import ua.prozorro.entity.tenders.ClassificationDTO;
import ua.prozorro.prozorro.model.tenders.Classification;

public class ClassificationMapper extends AbstractMapper<Classification, ClassificationDTO> {
    @Override
    public ClassificationDTO convertToEntity(Classification classification) {

        if (classification.getId() == null || classification.getId().equals("")) {
            return null;
        }
        ClassificationDTO classificationDTO = new ClassificationDTO();

        classificationDTO.setId(classification.getId());
        classificationDTO.setScheme(classification.getScheme());
        classificationDTO.setDescription(classification.getDescription());
        return classificationDTO;
    }

    @Override
    public Classification convertToObject(ClassificationDTO entity) {
        return null;
    }
}
