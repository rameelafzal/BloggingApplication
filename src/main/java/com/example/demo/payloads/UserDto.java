package com.example.demo.payloads;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class UserDto {
    private int id;
    @NotEmpty
    @Size(min = 4,message = "user name must be greater than 4 characters")
    private String name;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",message = "password must be have this many characters")
    private String password;
    @NotNull
    private String about;

}
