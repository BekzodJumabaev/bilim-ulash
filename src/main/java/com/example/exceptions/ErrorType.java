package com.example.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum ErrorType {
    USER_NOT_FOUND("Foydalanuvchi topilmadi", 404),
    BALANCE_NOT_ENOUGH("Balans yetarli emas", 400),
    SELF_TRANSFER("O'ziga o'zi dars o'tish mumkin emas", 400),
    DATABASE_ERROR("Baza bilan bog'liq xato", 500),
    PHONE_ALREADY_REGISTERED("Bu raqam ro'yhatdan o'tgan", 400),
    SKILL_ALREADY_REGISTERED("Bu bilim sizda allaqachon bor", 400),
    SKILL_NOT_FOUND("Bunday bilim topilmadi", 404),
    ACCESS_DENIED("Ruxsat berilmadi", 403);


    private final String message;
    private final int code;

    ErrorType(String message, int code) {
        this.message = message;
        this.code = code;
    }
}