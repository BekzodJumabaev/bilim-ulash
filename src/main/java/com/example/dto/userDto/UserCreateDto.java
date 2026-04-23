package com.example.dto.userDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserCreateDto {

    @NotBlank(message = "Ism sharif bo'sh bo'lishi mumkin emas")
    private String fullName;

    @NotBlank(message = "Telefon raqami bo'lishi shart")
    @Pattern(regexp = "^\\+998\\d{9}$", message = "Telefon raqam +998912345678 formatida bo'lsin")
    private String phoneNumber;

    @NotBlank(message = "Parol bo'lishi shart")
    @Size(min = 6, message = "Parol kamida 6 ta belgidan iborat bo'lishi kerak")
    private String password;
}
