package ua.prozorro.entity.mappers.prozorroObjectMapper;

import ua.prozorro.entity.mappers.AbstractMapper;
import ua.prozorro.entity.tenders.AddressDTO;
import ua.prozorro.prozorro.model.tenders.Address;

public class AddressMapper extends AbstractMapper<Address, AddressDTO> {
    @Override
    public AddressDTO convertToEntity(Address address) {

        if (address == null) {
            return null;
        }
        AddressDTO addressDTO = new AddressDTO();

        addressDTO.setId(address.hashCode());
        addressDTO.setPostalCode(address.getPostalCode());
        addressDTO.setCountryName(address.getCountryName());
        addressDTO.setStreetAddress(address.getStreetAddress());
        addressDTO.setRegion(address.getRegion());
        addressDTO.setLocality(address.getLocality());

        return addressDTO;
    }

    @Override
    public Address convertToObject(AddressDTO entity) {
        return null;
    }
}
