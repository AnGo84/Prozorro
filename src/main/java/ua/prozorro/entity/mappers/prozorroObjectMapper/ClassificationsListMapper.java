package ua.prozorro.entity.mappers.prozorroObjectMapper;

import ua.prozorro.entity.mappers.AbstractListMapper;
import ua.prozorro.entity.tenders.ClassificationDTO;
import ua.prozorro.prozorro.model.tenders.Classification;

import java.util.ArrayList;
import java.util.List;

public class ClassificationsListMapper extends AbstractListMapper<Classification, ClassificationDTO> {
    private ClassificationMapper classificationMapper = new ClassificationMapper();
    @Override
    public List<ClassificationDTO> convertToEntitiesList(List<Classification> classificationList) {
        if (classificationList == null || classificationList.isEmpty()) {
            return null;
        }
        List<ClassificationDTO> classificationsDTO = new ArrayList<>();

        for (Classification classification : classificationList) {
            ClassificationDTO classificationDTO = classificationMapper.convertToEntity(classification);
            if (classificationDTO != null) {
                classificationsDTO.add(classificationDTO);
            }
        }

        return classificationsDTO;
    }

    @Override
    public List<Classification> convertToObjectsList(List<ClassificationDTO> entities) {
        return null;
    }
}
