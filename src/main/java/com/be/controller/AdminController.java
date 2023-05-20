package com.be.controller;

import com.be.dto.ItemDTO;
import com.be.dto.Response;
import com.be.dto.UserDTO;
import com.be.entity.ItemEntity;
import com.be.service.ItemService;
import com.be.service.UserService;
import com.be.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth/admin")
@PreAuthorize("hasRole('ADMIN')")

public class AdminController {
    private final ItemService itemService;
    private final UserService userService;

    public AdminController(ItemService itemService, UserService userService) {
        this.itemService = itemService;
        this.userService = userService;
    }


    @DeleteMapping("/delete/{itemID}")
    public ResponseEntity deleteItem(@PathVariable String itemID) {
        long idDeleted = Long.parseLong(itemID);
        itemService.deleteItem(idDeleted);
        Response<ItemEntity> res = new Response<>(201, "delete success", null);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/post")
    public ResponseEntity saveItems(@RequestBody ItemDTO getItemDTO) {
        ItemEntity postedItem = itemService.saveItem(getItemDTO);
        Response<ItemEntity> res = new Response<>(201, "create success", postedItem);
        return ResponseEntity.ok(res);
    }

    @PutMapping("/update/{itemID}")
    public ResponseEntity updateItem(@PathVariable String itemID, @RequestBody ItemDTO updateItemDTO) {
        long idUpdated = Long.parseLong(itemID);
        ItemEntity updatedItem = itemService.updateItem(idUpdated, updateItemDTO);
        Response<ItemEntity> res = new Response<>(201, "update success", updatedItem);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/user")
    public ResponseEntity getUsers() {
        List<UserDTO> userList = userService.getUsers();
        return ResponseEntity.ok(userList);
    }
    @GetMapping("/user/{userID}")
    public ResponseEntity getUser(@PathVariable String userID) {
        long id = Long.parseLong(userID);
        UserDTO user = userService.getUser(id);
        return ResponseEntity.ok(user);
    }
    @PutMapping("/user/{userID}")
    public ResponseEntity updateUsers(@PathVariable String userID, @RequestBody UserDTO userDTO) {
        long id = Long.parseLong(userID);
        userService.updateUser(id, userDTO);
        Response<UserDTO> res = new Response<>(201, "update success", userDTO);
        return ResponseEntity.ok(res);
    }
    @DeleteMapping("/user/{userID}")
    public ResponseEntity deleteUser(@PathVariable String userID) {
        long idDeleted = Long.parseLong(userID);
        userService.deleteUser(idDeleted);
        Response<User> res = new Response<>(201, "delete success", null);
        return ResponseEntity.ok(res);
    }

}
