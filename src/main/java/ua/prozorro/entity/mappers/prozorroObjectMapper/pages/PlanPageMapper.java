package ua.prozorro.entity.mappers.prozorroObjectMapper.pages;

import ua.prozorro.entity.PageElement;
import ua.prozorro.entity.mappers.AbstractMapper;
import ua.prozorro.entity.pages.PlanPageDTO;
import ua.prozorro.prozorro.model.pages.ProzorroPageElement;

public class PlanPageMapper extends AbstractMapper<PageElement, PlanPageDTO>  {
    @Override
    public PlanPageDTO convertToEntity(PageElement object) {
        if(object==null){
            return null;
        }
        PlanPageDTO pageDTO = new PlanPageDTO(object.getId(),object.getDateModified());

        return pageDTO;
    }

    @Override
    public PageElement convertToObject(PlanPageDTO entity) {
        return null;
    }
}
