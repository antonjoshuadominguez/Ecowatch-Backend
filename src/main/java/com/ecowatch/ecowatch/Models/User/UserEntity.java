package com.ecowatch.ecowatch.Models.User;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
@Getter 
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private long user_id;
    private String firstname;   
    private String lastname;
    private String email;
    @JsonIgnore
    private String password;
    public UserEntity(String firstname, String lastname, String email, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }
   
    
}
