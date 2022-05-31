package com.example.hmd2.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @Column(nullable = false, unique = true)
    private Integer id;

    @NotEmpty(message = "userName cant be empty!")
    private String userName;

    private String password;

    @Email(message = " Email Cant be empty and be email formate!")
    private String email;

    @Pattern(regexp = "(Admin|User)")
    private String role;


    private LocalDate joiningYear;

    @NotNull(message = "age cant be empty!")
    private Integer age;


}
