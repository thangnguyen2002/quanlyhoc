package com.quanlyhoc.quanlyhoc.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDTO {
    @JsonProperty("email")
//    @NotBlank(message = "Email is required")
    private String email;

//    @NotBlank(message = "Password cannot be blank")
    private String password;
}