package ua.prozorro.entity.mappers.prozorroObjectMapper;

import ua.prozorro.entity.mappers.AbstractListMapper;
import ua.prozorro.entity.tenders.AwardDTO;
import ua.prozorro.prozorro.model.tenders.Award;

import java.util.ArrayList;
import java.util.List;

public class AwardsListMapper extends AbstractListMapper<Award, AwardDTO> {
    private AwardMapper awardMapper = new AwardMapper();

    @Override
    public List<AwardDTO> convertToEntitiesList(List<Award> awardList) {
        if (awardList == null || awardList.isEmpty()) {
            return null;
        }
        List<AwardDTO> awardDTOList = new ArrayList<>();

        for (Award award : awardList) {
            AwardDTO awardDTO = awardMapper.convertToEntity(award);
            if (awardDTO != null) {
                awardDTOList.add(awardDTO);
            }
        }

        return awardDTOList;
    }

    @Override
    public List<Award> convertToObjectsList(List<AwardDTO> entities) {
        return null;
    }
}
