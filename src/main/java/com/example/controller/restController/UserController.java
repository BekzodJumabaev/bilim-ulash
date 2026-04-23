package com.example.controller.restController;


import com.example.dto.userDto.UserCreateDto;
import com.example.dto.userDto.UserDto;
import com.example.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@Valid @RequestBody UserCreateDto dto) {
        return ResponseEntity.ok(service.register(dto));
    }

}
