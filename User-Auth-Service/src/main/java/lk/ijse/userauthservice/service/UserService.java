/*
 * Copyright (c) 2025
 * Gihan Viduranga - 2025
 */

package lk.ijse.userauthservice.service;

import jakarta.validation.Valid;
import lk.ijse.userauthservice.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetails;


public interface UserService {
    int saveUser(UserDto userDTO);

    UserDto loadUserDetailsByUsername(String email);

}
