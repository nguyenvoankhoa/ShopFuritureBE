package com.be.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {
    long id;
    String image;
    String name;
    String description;
    int price;

    public ItemDTO(String image, String name, String description, int price) {
        this.image = image;
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
