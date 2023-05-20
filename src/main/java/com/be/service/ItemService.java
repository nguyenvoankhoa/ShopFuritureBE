package com.be.service;

import com.be.dto.ItemDTO;
import com.be.entity.ItemEntity;
import com.be.mapper.ItemMapper;
import com.be.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class ItemService {
    private final ItemRepository itemRepository;

    private final ItemMapper itemMapper;

    public ItemService(ItemMapper itemMapper, ItemRepository itemRepository) {
        this.itemMapper = itemMapper;
        this.itemRepository = itemRepository;
    }

    public ItemEntity saveItem(ItemDTO saveItemDTO) {
        ItemEntity item = itemMapper.saveMapper(saveItemDTO);
        return itemRepository.save(item);
    }

    public void deleteItem(long id) {
        Optional<ItemEntity> optionalItem = itemRepository.findById(id);
        ItemEntity item = optionalItem.orElseThrow(() -> new RuntimeException("Item not found"));
        itemRepository.delete(item);
    }

    public List<ItemDTO> getItems() {
        List<ItemEntity> itemsEntity = itemRepository.findAll();
        List<ItemDTO> itemsDto = new ArrayList<>();
        for (ItemEntity itemEntity : itemsEntity) {
            ItemDTO itemDTO = itemMapper.getMapper(itemEntity);
            itemsDto.add(itemDTO);
        }
        return itemsDto;
    }

    public ItemDTO getItem(long id) {
        Optional<ItemEntity> optionalItem = itemRepository.findById(id);
        ItemEntity item = optionalItem.orElseThrow(() -> new RuntimeException("Item not found"));
        ItemDTO itemDTO = itemMapper.getMapper(item);
        return itemDTO;
    }

    public List<ItemDTO> getSpecialItems() {
        List<ItemEntity> itemsEntity = itemRepository.findTop3ByOrderByPriceDesc();
        List<ItemDTO> itemsDto = new ArrayList<>();
        for (ItemEntity itemEntity : itemsEntity) {
            ItemDTO itemDTO = itemMapper.getMapper(itemEntity);
            itemsDto.add(itemDTO);
        }
        return itemsDto;
    }

    public ItemEntity updateItem(long id, ItemDTO updateItemDTO) {
        Optional<ItemEntity> optionalItem = itemRepository.findById(id);
        ItemEntity item = optionalItem.orElseThrow(() -> new RuntimeException("Item not found"));
        if (item != null) {
            item.setItemId(id);
            item.setImage(updateItemDTO.getImage());
            item.setName(updateItemDTO.getName());
            item.setDescription(updateItemDTO.getDescription());
            item.setPrice(updateItemDTO.getPrice());
        }
        return itemRepository.save(item);
    }

}
