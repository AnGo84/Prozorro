package ua.prozorro.entity.mappers.prozorroObjectMapper.pages;

import ua.prozorro.entity.PageElement;
import ua.prozorro.entity.mappers.AbstractMapper;
import ua.prozorro.entity.pages.TenderPageDTO;

public class TenderPageMapper extends AbstractMapper<PageElement, TenderPageDTO> {
    @Override
    public TenderPageDTO convertToEntity(PageElement object) {
        if (object == null) {
            return null;
        }
        TenderPageDTO pageDTO = new TenderPageDTO(object.getId(), object.getDateModified());

        return pageDTO;
    }

    @Override
    public PageElement convertToObject(TenderPageDTO entity) {
        return null;
    }
}
