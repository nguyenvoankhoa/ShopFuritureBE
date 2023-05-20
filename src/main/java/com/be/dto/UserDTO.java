package com.be.dto;

import com.be.user.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    long id;
    String firstname;
    String lastname;
    String email;
    Role role;
}
