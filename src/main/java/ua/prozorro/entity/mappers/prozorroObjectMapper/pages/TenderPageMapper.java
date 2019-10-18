package ua.prozorro.entity.mappers.prozorroObjectMapper.pages;

import ua.prozorro.entity.mappers.AbstractMapper;
import ua.prozorro.entity.pages.TenderPageDTO;
import ua.prozorro.prozorro.model.pages.ProzorroPageElement;

public class TenderPageMapper extends AbstractMapper<ProzorroPageElement, TenderPageDTO>  {
    @Override
    public TenderPageDTO convertToEntity(ProzorroPageElement object) {
        if(object==null){
            return null;
        }
        TenderPageDTO pageDTO = new TenderPageDTO(object.getId(),object.getDateModified());

        return pageDTO;
    }

    @Override
    public ProzorroPageElement convertToObject(TenderPageDTO entity) {
        return null;
    }
}
