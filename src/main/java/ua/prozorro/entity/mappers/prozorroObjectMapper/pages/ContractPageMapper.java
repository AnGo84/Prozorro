package ua.prozorro.entity.mappers.prozorroObjectMapper.pages;

import ua.prozorro.entity.PageElement;
import ua.prozorro.entity.mappers.AbstractMapper;
import ua.prozorro.entity.pages.ContractPageDTO;
import ua.prozorro.prozorro.model.pages.ProzorroPageElement;

public class ContractPageMapper extends AbstractMapper<PageElement, ContractPageDTO>  {
    @Override
    public ContractPageDTO convertToEntity(PageElement object) {
        if(object==null){
            return null;
        }
        ContractPageDTO pageDTO = new ContractPageDTO(object.getId(),object.getDateModified());

        return pageDTO;
    }

    @Override
    public PageElement convertToObject(ContractPageDTO entity) {
        return null;
    }
}
