package ua.prozorro.entity.mappers.prozorroObjectMapper;

import ua.prozorro.entity.mappers.AbstractMapper;
import ua.prozorro.entity.tenders.ItemDTO;
import ua.prozorro.prozorro.model.tenders.Item;
import ua.prozorro.utils.CommonUtils;

public class ItemMapper extends AbstractMapper<Item, ItemDTO> {
    private ClassificationMapper classificationMapper = new ClassificationMapper();
    private AddressMapper addressMapper = new AddressMapper();
    private UnitMapper unitMapper = new UnitMapper();
    @Override
    public ItemDTO convertToEntity(Item item) {
        if(item==null){
            return null;
        }
        ItemDTO itemDTO = new ItemDTO();

        itemDTO.setId(item.getId());
        itemDTO.setDescription(item.getDescription());

        itemDTO.setClassification(classificationMapper.convertToEntity(item.getClassification()));
        itemDTO.setDeliveryAddress(addressMapper.convertToEntity(item.getDeliveryAddress()));
        if (item.getDeliveryDate() != null) {
            itemDTO.setDeliveryStartDate(item.getDeliveryDate().getStartDate());
            itemDTO.setDeliveryClarificationsUntil(item.getDeliveryDate().getClarificationsUntil());
            itemDTO.setDeliveryEndDate(item.getDeliveryDate().getEndDate());
            itemDTO.setDeliveryInvalidationDate(item.getDeliveryDate().getInvalidationDate());
        }
        itemDTO.setUnit(unitMapper.convertToEntity(item.getUnit()));

        try {
            itemDTO.setQuantity((new Double(item.getQuantity())).longValue());
        } catch (NumberFormatException|NullPointerException e) {
            itemDTO.setQuantity(1);
            CommonUtils.saveParsingErrorLog("CONTRACTS","ITEMS", item.getId(), "QUANTITY", item.getQuantity());
        }

        return itemDTO;
    }

    @Override
    public Item convertToObject(ItemDTO entity) {
        return null;
    }
}
