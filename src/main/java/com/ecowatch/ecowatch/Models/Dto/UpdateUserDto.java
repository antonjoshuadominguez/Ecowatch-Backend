package com.ecowatch.ecowatch.Models.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserDto {
    private long userId;
    private String email;
    private String firstname;   
    private String lastname;
}
