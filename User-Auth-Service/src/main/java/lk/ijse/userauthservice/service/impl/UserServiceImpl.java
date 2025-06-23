/*
 * Copyright (c) 2025
 * Gihan Viduranga - 2025
 */

package lk.ijse.userauthservice.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.userauthservice.dto.UserDto;
import lk.ijse.userauthservice.entity.User;
import lk.ijse.userauthservice.repo.UserRepository;
import lk.ijse.userauthservice.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public boolean updateUser(UserDto userDTO) {
        Optional<User> existingUser = userRepository.findById(String.valueOf(userDTO.getId()));
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setUsername(userDTO.getUsername());
            user.setDateOfBirth(userDTO.getDateOfBirth());
            user.setEmail(userDTO.getEmail());

            if (!userDTO.getPassword().isEmpty()) {
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            }

            user.setRole(userDTO.getRole());

            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return modelMapper.map(userRepository.findAll(),
                new TypeToken<List<UserDto>>() {}.getType());
    }

    @Override
    public boolean registerUser(UserDto userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            return false;
        } else {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            userRepository.save(modelMapper.map(userDTO, User.class));
            return true;
        }
    }

    @Override
    public UserDto loginUser(UserDto userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            return modelMapper.map(userRepository.findByEmail(userDTO.getEmail()), UserDto.class);
        } else {
            return null;
        }
    }
}
