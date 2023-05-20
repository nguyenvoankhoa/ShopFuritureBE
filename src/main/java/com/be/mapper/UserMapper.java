package com.be.mapper;

import com.be.dto.UserDTO;
import com.be.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDTO getUserMapper(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setFirstname(user.getFirstname());
        userDTO.setLastname(user.getLastname());
        userDTO.setRole(user.getRole());
        return  userDTO;
    }
}
