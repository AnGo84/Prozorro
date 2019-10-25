package ua.prozorro.entity.mappers.prozorroObjectMapper;

import ua.prozorro.entity.mappers.AbstractListMapper;
import ua.prozorro.entity.tenders.ItemDTO;
import ua.prozorro.prozorro.model.tenders.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemsListMapper extends AbstractListMapper<Item, ItemDTO> {
    private ItemMapper itemMapper = new ItemMapper();
    @Override
    public List<ItemDTO> convertToEntitiesList(List<Item> itemList) {
        if (itemList == null || itemList.isEmpty()) {
            return null;
        }
        List<ItemDTO> itemDTOList = new ArrayList<>();

        for (Item item : itemList) {
            ItemDTO itemDTO = itemMapper.convertToEntity(item);
            if (itemDTO != null) {
                itemDTOList.add(itemDTO);
            }
        }

        return itemDTOList;
    }

    @Override
    public List<Item> convertToObjectsList(List<ItemDTO> entities) {
        return null;
    }
}
