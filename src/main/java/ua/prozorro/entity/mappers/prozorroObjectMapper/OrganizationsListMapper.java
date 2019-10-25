package ua.prozorro.entity.mappers.prozorroObjectMapper;

import ua.prozorro.entity.mappers.AbstractListMapper;
import ua.prozorro.entity.tenders.OrganizationDTO;
import ua.prozorro.prozorro.model.tenders.Organization;

import java.util.ArrayList;
import java.util.List;

public class OrganizationsListMapper extends AbstractListMapper<Organization, OrganizationDTO> {
    private OrganizationMapper organizationMapper = new OrganizationMapper();

    @Override
    public List<OrganizationDTO> convertToEntitiesList(List<Organization> organizationList) {
        List<OrganizationDTO> organizationDTOList = new ArrayList<>();

        for (Organization organization : organizationList) {
            OrganizationDTO organizationDTO = organizationMapper.convertToEntity(organization);
            if (organizationDTO != null) {
                organizationDTOList.add(organizationDTO);
            }
        }

        return organizationDTOList;
    }

    @Override
    public List<Organization> convertToObjectsList(List<OrganizationDTO> entities) {
        return null;
    }
}
