package com.example.exceptions;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ErrorMessage {
   private String message;
   private int errorCode;
}
