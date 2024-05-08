package com.quanlyhoc.quanlyhoc.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonProperty;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    @JsonProperty("fullname")
    private String fullName;

    @JsonProperty("phone_number")
//    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    private String address;

//    @NotBlank(message = "Password cannot be blank")
    @JsonProperty("password")
    private String password;

    @JsonProperty("retype_password")
    private String retypePassword;

    @JsonProperty("student_code")
    private String studentCode;

    private String email;

    @JsonProperty("date_of_birth")
    private Date dateOfBirth;

//    @NotNull(message = "Role ID is required")
    @JsonProperty("role_id")
    private Long roleId;
}
