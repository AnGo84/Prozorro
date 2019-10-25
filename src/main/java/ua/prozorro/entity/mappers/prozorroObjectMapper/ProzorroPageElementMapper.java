package ua.prozorro.entity.mappers.prozorroObjectMapper;

import ua.prozorro.entity.PageElement;
import ua.prozorro.entity.mappers.AbstractMapper;
import ua.prozorro.entity.tenders.LotDTO;
import ua.prozorro.prozorro.model.pages.ProzorroPageElement;
import ua.prozorro.prozorro.model.tenders.Lot;

public class ProzorroPageElementMapper extends AbstractMapper<ProzorroPageElement, PageElement> {


    @Override
    public PageElement convertToEntity(ProzorroPageElement object) {
        if(object==null){
            return null;
        }
        PageElement pageElement = new PageElement(object.getId(),object.getDateModified());
        return pageElement;
    }

    @Override
    public ProzorroPageElement convertToObject(PageElement entity) {
        if(entity==null){
            return null;
        }
        ProzorroPageElement prozorroPageElement = new ProzorroPageElement(entity.getId(),entity.getDateModified());
        return prozorroPageElement;
    }
}
