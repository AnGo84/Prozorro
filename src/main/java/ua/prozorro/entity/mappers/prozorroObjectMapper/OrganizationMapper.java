package ua.prozorro.entity.mappers.prozorroObjectMapper;

import ua.prozorro.entity.mappers.AbstractMapper;
import ua.prozorro.entity.tenders.OrganizationDTO;
import ua.prozorro.prozorro.model.tenders.Organization;

public class OrganizationMapper extends AbstractMapper<Organization, OrganizationDTO> {
    private AddressMapper addressMapper = new AddressMapper();
    private IdentifierMapper identifierMapper = new IdentifierMapper();
    @Override
    public OrganizationDTO convertToEntity(Organization organization) {

        OrganizationDTO organizationDTO = new OrganizationDTO();

        organizationDTO.setName(organization.getName());
        if (organization.getContactPoint() != null) {
            organizationDTO.setTelephone(organization.getContactPoint().getTelephone());
            organizationDTO.setContactPointName(organization.getContactPoint().getName());
            organizationDTO.setEmail(organization.getContactPoint().getEmail());
            organizationDTO.setUrl(organization.getContactPoint().getUrl());
        }
        organizationDTO.setIdentifier(identifierMapper.convertToEntity(organization.getIdentifier()));
        organizationDTO.setId(organizationDTO.getIdentifier().getId());
        organizationDTO.setAddress(addressMapper.convertToEntity(organization.getAddress()));

        return organizationDTO;
    }

    @Override
    public Organization convertToObject(OrganizationDTO entity) {
        return null;
    }
}
