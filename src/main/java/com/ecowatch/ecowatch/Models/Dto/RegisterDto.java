package com.ecowatch.ecowatch.Models.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDto {
    private String firstname;   
    private String lastname;
    private String email;
    private String password;
}
