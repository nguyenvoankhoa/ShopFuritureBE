package com.be.service;

import com.be.dto.UserDTO;
import com.be.mapper.UserMapper;
import com.be.user.Role;
import com.be.user.User;
import com.be.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapper userMapper;

    public List<UserDTO> getUsers(){
        List<User> userList = userRepository.findAll();
        List<UserDTO> listDTO = new ArrayList<>();
        for(User user: userList){
            UserDTO userDTO = userMapper.getUserMapper(user);
            listDTO.add(userDTO);
        }
        return listDTO;
    }
    public User updateUser(long id, UserDTO userDTO){
        Optional<User> user = userRepository.findById(id);
        User userUpdate = user.orElseThrow(() -> new RuntimeException("Item not found"));
        if(userUpdate != null){
            userUpdate.setId(id);
            userUpdate.setFirstname(userDTO.getFirstname());
            userUpdate.setLastname(userDTO.getLastname());
            userUpdate.setRole(userDTO.getRole());
        }
        return userRepository.save(userUpdate);
    }
    public void deleteUser(long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        User userEntity = optionalUser.orElseThrow(() -> new RuntimeException("User not found"));
        if(userEntity.getRole() != Role.ADMIN) {
            userRepository.delete(userEntity);
        }
    }
    public UserDTO getUser(long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        User userEntity = optionalUser.orElseThrow(() -> new RuntimeException("User not found"));
        UserDTO userDTO = new UserDTO();
        if(userEntity != null){
            userDTO = userMapper.getUserMapper(userEntity);
        }
        return userDTO;
    }
}
