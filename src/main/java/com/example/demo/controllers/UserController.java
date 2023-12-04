package com.example.demo.controllers;

import com.example.demo.entities.User;
import com.example.demo.payloads.UserDto;
import com.example.demo.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        UserDto createUserDto = this.userService.createUser(userDto);
        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }

    @PutMapping("updateUser")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto) {
        UserDto updatedUser = this.userService.updateUser(userDto);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<String> deleteUser(@RequestBody Integer id) {
        this.userService.deleteUser(id);
        return new ResponseEntity<>("success", HttpStatus.ACCEPTED);
    }

    @GetMapping("getUser")
    public ResponseEntity<UserDto> getUser(@RequestBody Integer id) {

        UserDto userDto = this.userService.getUserById(id);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("getAllUsers")
    public ResponseEntity<List<UserDto>> getAllUser() {
        List<UserDto> userDtos = this.userService.getAllUsers();
        return ResponseEntity.ok(userDtos);
    }
}
