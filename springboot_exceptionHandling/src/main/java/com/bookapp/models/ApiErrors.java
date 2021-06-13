package com.bookapp.models;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ApiErrors {

    String message;
    List<String> details;
    HttpStatus status;
    LocalDateTime timestamp;

}
