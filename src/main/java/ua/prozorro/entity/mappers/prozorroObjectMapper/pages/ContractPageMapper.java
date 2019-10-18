package ua.prozorro.entity.mappers.prozorroObjectMapper.pages;

import ua.prozorro.entity.mappers.AbstractMapper;
import ua.prozorro.entity.pages.ContractPageDTO;
import ua.prozorro.prozorro.model.pages.ProzorroPageElement;

public class ContractPageMapper extends AbstractMapper<ProzorroPageElement, ContractPageDTO>  {
    @Override
    public ContractPageDTO convertToEntity(ProzorroPageElement object) {
        if(object==null){
            return null;
        }
        ContractPageDTO pageDTO = new ContractPageDTO(object.getId(),object.getDateModified());

        return pageDTO;
    }

    @Override
    public ProzorroPageElement convertToObject(ContractPageDTO entity) {
        return null;
    }
}
