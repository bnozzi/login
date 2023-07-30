package com.bnozzi.login.model;

import com.bnozzi.login.enumeration.UserRol;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String firstName;

    @NotNull
    @Size(max = 255)
    private String lastName;

    @NotNull
    @Size(max = 255)
    private String email;

    @NotNull
    @Size(max = 255)
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRol rol;

    public boolean verificarCredenciales(String email, String pw) {
        return this.email.equalsIgnoreCase(email) && this.password.equals(pw);
    }

}
