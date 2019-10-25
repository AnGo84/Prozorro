package ua.prozorro.entity.mappers.prozorroObjectMapper;

import ua.prozorro.entity.mappers.AbstractMapper;
import ua.prozorro.entity.plans.ProcuringEntityDTO;
import ua.prozorro.prozorro.model.plans.ProcuringEntity;

public class ProcuringEntityMapper extends AbstractMapper<ProcuringEntity, ProcuringEntityDTO> {
    private AddressMapper addressMapper = new AddressMapper();
    private IdentifierMapper identifierMapper = new IdentifierMapper();
    @Override
    public ProcuringEntityDTO convertToEntity(ProcuringEntity procuringEntity) {

        if (procuringEntity == null) {
            return null;
        }
        ProcuringEntityDTO procuringEntityDTO = new ProcuringEntityDTO();

        procuringEntityDTO.setId(procuringEntity.getIdentifier().hashCode());

        procuringEntityDTO.setIdentifier(identifierMapper.convertToEntity(procuringEntity.getIdentifier()));
        procuringEntityDTO.setName(procuringEntity.getName());

        if (procuringEntity.getContactPoint() != null) {
            procuringEntityDTO.setTelephone(procuringEntity.getContactPoint().getTelephone());
            procuringEntityDTO.setContactPointName(procuringEntity.getContactPoint().getName());
            procuringEntityDTO.setEmail(procuringEntity.getContactPoint().getEmail());
            procuringEntityDTO.setContactUrl(procuringEntity.getContactPoint().getUrl());
        }
        procuringEntityDTO.setKind(procuringEntity.getKind());
        procuringEntityDTO.setAddress(addressMapper.convertToEntity(procuringEntity.getAddress()));

        return procuringEntityDTO;
    }

    @Override
    public ProcuringEntity convertToObject(ProcuringEntityDTO entity) {
        return null;
    }
}
