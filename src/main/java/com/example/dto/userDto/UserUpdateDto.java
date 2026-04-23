package com.example.dto.userDto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UserUpdateDto {
    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

}
