/*
 * Copyright (c) 2025
 * Gihan Viduranga - 2025
 */

package lk.ijse.userauthservice.controller;

import jakarta.validation.Valid;
import lk.ijse.userauthservice.dto.ResponseDTO;
import lk.ijse.userauthservice.dto.UserDto;
import lk.ijse.userauthservice.service.UserService;
import lk.ijse.userauthservice.util.VarList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<ResponseDTO> registerUser(@RequestBody @Valid UserDto userDTO) {
       try {
           boolean registered = userService.registerUser(userDTO);
           if (registered) {
               return ResponseEntity.status(HttpStatus.OK)
                       .body(new ResponseDTO(VarList.Created, "User Registered Successfully", null));
           } else {
               return ResponseEntity.status(HttpStatus.CONFLICT)
                       .body(new ResponseDTO(VarList.Conflict, "User Already Registered", null));
           }
       } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
       }
    }

    @PostMapping(value = "/login")
    public ResponseEntity<ResponseDTO> loginUser(@RequestBody UserDto userDTO) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDTO(VarList.Created, "Success", userService.loginUser(userDTO)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<ResponseDTO> updateUser(@RequestBody UserDto userDTO) {
        System.out.println(userDTO.getId());
        try {
            boolean updated = userService.updateUser(userDTO);
            if (updated) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseDTO(VarList.Created, "User Updated Successfully", null));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseDTO(VarList.Not_Found, "User Not Found", null));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
        }
    }

    @GetMapping(value = "/allUsers")
    public ResponseEntity<ResponseDTO> getAllUsers() {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDTO(VarList.Created, "Success", userService.getAllUsers()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
        }
    }
}
