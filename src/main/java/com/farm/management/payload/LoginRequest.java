package com.farm.management.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class LoginRequest {
    @NotBlank
    private String usernameOrEmail;

    @NotBlank
    private String password;

//    public String getUsernameOrEmail() {
//        return usernameOrEmail;
//    }
//
//    public void setUsernameOrEmail(String usernameOrEmail) {
//        this.usernameOrEmail = usernameOrEmail;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
}
