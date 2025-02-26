package com.princez1.learn_springboot.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequest {
    @Size(min = 3,message = "USERNAME_INVALID")
    private String username;

    @Size(min = 5, message = "INVALID_PASSWORD")
    private String password;
    private String firstName;
    private String lastName;

    private LocalDate dob;
}
