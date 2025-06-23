/*
 * Copyright (c) 2025
 * Gihan Viduranga - 2025
 */

package lk.ijse.userauthservice.service;

import jakarta.validation.Valid;
import lk.ijse.userauthservice.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;


public interface UserService {

    boolean updateUser(UserDto userDTO);

    List<UserDto> getAllUsers();

    boolean registerUser(UserDto userDTO);

    UserDto loginUser(UserDto userDTO);
}
