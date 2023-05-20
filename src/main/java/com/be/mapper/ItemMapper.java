package com.be.mapper;

import com.be.dto.ItemDTO;
import com.be.entity.ItemEntity;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

    public ItemDTO getMapper(ItemEntity itemEntity) {
        ItemDTO item = new ItemDTO();
        item.setId(itemEntity.getItemId());
        item.setImage(itemEntity.getImage());
        item.setName(itemEntity.getName());
        item.setDescription(itemEntity.getDescription());
        item.setPrice(itemEntity.getPrice());
        return item;
    }

    public ItemEntity saveMapper(ItemDTO saveItemRequest) {
        ItemEntity item = new ItemEntity();
        item.setImage(saveItemRequest.getImage());
        item.setName(saveItemRequest.getName());
        item.setDescription(saveItemRequest.getDescription());
        item.setPrice(saveItemRequest.getPrice());
        return item;
    }
}
