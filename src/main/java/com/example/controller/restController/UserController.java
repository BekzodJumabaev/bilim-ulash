package com.example.controller.restController;


import com.example.dto.userDto.UserCreateDto;
import com.example.dto.userDto.UserDto;
import com.example.dto.userDto.UserUpdateDto;
import com.example.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@PathVariable Long id,
                                          @RequestBody UserUpdateDto dto,
                                          Authentication auth) {
        service.checkOwnership(auth.getName(), id);
        return ResponseEntity.ok(service.update(dto, id));
    }
}
