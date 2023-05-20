package com.be.controller;

import com.be.config.LogoutService;
import com.be.dto.ItemDTO;
import com.be.dto.Response;
import com.be.dto.RoleRequest;
import com.be.entity.ItemEntity;
import com.be.service.ItemService;
import com.be.user.User;
import com.be.user.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class ItemAPI {
    private final UserRepository userRepository;
    private final ItemService itemService;
    private final LogoutService logoutService;

    public ItemAPI(ItemService itemService, LogoutService logoutService, UserRepository userRepository) {
        this.itemService = itemService;
        this.logoutService = logoutService;
        this.userRepository = userRepository;
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        logoutService.logout(request, response, authentication);
        Response<ItemEntity> res = new Response<>(201, "logout success", null);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/items/{itemID}")
    public ResponseEntity getItem(@PathVariable String itemID) {
        long id = Long.parseLong(itemID);
        ItemDTO itemDTO = itemService.getItem(id);
        Response<ItemDTO> res = new Response<>(201, "get success", itemDTO);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/items")
    public ResponseEntity getItems() {
        List<ItemDTO> items = itemService.getItems();
        return ResponseEntity.ok(items);
    }

    @GetMapping("/items/special")
    public ResponseEntity getTopItems() {
        List<ItemDTO> items = itemService.getSpecialItems();
        return ResponseEntity.ok(items);
    }

    @PostMapping("/role")
    public ResponseEntity getRoles(@RequestBody RoleRequest email) {
        Optional<User> user = userRepository.findByEmail(email.getEmail());

        return ResponseEntity.ok(user.get().getRole());
    }


}
