package ua.prozorro.entity.mappers.prozorroObjectMapper;

import ua.prozorro.entity.mappers.AbstractMapper;
import ua.prozorro.entity.tenders.IdentifierDTO;
import ua.prozorro.prozorro.model.tenders.Identifier;

public class IdentifierMapper extends AbstractMapper<Identifier, IdentifierDTO> {
    @Override
    public IdentifierDTO convertToEntity(Identifier identifier) {

        if (identifier == null) {
            return null;
        }
        IdentifierDTO identifierDTO = new IdentifierDTO();

        identifierDTO.setId(identifier.hashCode());
        identifierDTO.setScheme(identifier.getScheme());
        identifierDTO.setSchemeId(identifier.getId());
        identifierDTO.setUri(identifier.getUri());
        identifierDTO.setLegalName(identifier.getLegalName());
        identifierDTO.setLegalNameEn(identifier.getLegalNameEn());

        return identifierDTO;
    }

    @Override
    public Identifier convertToObject(IdentifierDTO entity) {
        return null;
    }
}
