package com.quanlyhoc.quanlyhoc.controllers;

import com.quanlyhoc.quanlyhoc.dtos.UserDTO;
import com.quanlyhoc.quanlyhoc.dtos.UserLoginDTO;
import com.quanlyhoc.quanlyhoc.models.Token;
import com.quanlyhoc.quanlyhoc.models.User;
import com.quanlyhoc.quanlyhoc.responses.LoginResponse;
import com.quanlyhoc.quanlyhoc.responses.RegisterResponse;
import com.quanlyhoc.quanlyhoc.services.interfaces.ITokenService;
import com.quanlyhoc.quanlyhoc.services.interfaces.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/users")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private final IUserService iUserService;
    @Autowired
    private final ITokenService iTokenService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> createUser(
            @Valid @RequestBody UserDTO userDTO,
            BindingResult result
    ) {
        RegisterResponse registerResponse = new RegisterResponse();

        if (result.hasErrors()) { //check loi trong DTO
            List<String> errorMsg = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            registerResponse.setMessage(errorMsg.toString());
            return new ResponseEntity<>(registerResponse, HttpStatus.BAD_REQUEST);
        }
        //check retype password
        if (!userDTO.getPassword().equals(userDTO.getRetypePassword())) {
            registerResponse.setMessage("Password does not match");
            return new ResponseEntity<>(registerResponse, HttpStatus.BAD_REQUEST);
        }
        try {
            User user = iUserService.createUser(userDTO);
            registerResponse.setUser(user);
            registerResponse.setMessage("Register successfully");
            return new ResponseEntity<>(registerResponse, HttpStatus.OK);
        } catch (Exception e) {
            registerResponse.setMessage(e.getMessage());
            return new ResponseEntity<>(registerResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody UserLoginDTO userLoginDTO,
            HttpServletRequest request) {
        // Kiểm tra thông tin đăng nhập và sinh token
        try {
            String token = iUserService.login(
                    userLoginDTO.getPhoneNumber(),
                    userLoginDTO.getPassword());

            String userAgent = request.getHeader("User-Agent");
            User userDetail = iUserService.getUserDetailsFromToken(token);
            Token jwtToken = iTokenService.addToToken(userDetail, token);

            return new ResponseEntity<>(LoginResponse.builder()
                    .message("Login successfully")
                    .token(jwtToken.getToken())
                    .tokenType(jwtToken.getTokenType())
                    .userId(userDetail.getId())
                    .phoneNumber(userDetail.getPhoneNumber())
                    .role(userDetail.getRole().getName())
                    .build(),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(LoginResponse.builder()
                    .message(e.getMessage())
                    .build(), HttpStatus.UNAUTHORIZED);
        }
    }
}
