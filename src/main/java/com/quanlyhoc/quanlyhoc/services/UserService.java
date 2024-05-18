package com.quanlyhoc.quanlyhoc.services;

import com.quanlyhoc.quanlyhoc.dtos.UserDTO;
import com.quanlyhoc.quanlyhoc.exceptions.DataNotFoundException;
import com.quanlyhoc.quanlyhoc.exceptions.PermissionDenyException;
import com.quanlyhoc.quanlyhoc.models.Role;
import com.quanlyhoc.quanlyhoc.models.Token;
import com.quanlyhoc.quanlyhoc.models.User;
import com.quanlyhoc.quanlyhoc.repositories.RoleRepository;
import com.quanlyhoc.quanlyhoc.repositories.TokenRepository;
import com.quanlyhoc.quanlyhoc.repositories.UserRepository;
import com.quanlyhoc.quanlyhoc.services.interfaces.IUserService;
import com.quanlyhoc.quanlyhoc.shared.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final RoleRepository roleRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final UserDetailsService userDetailsService;

    @Autowired
    private final AuthenticationManager authenticationManager;

    @Autowired
    private final TokenRepository tokenRepository;

    @Autowired
    private final JwtUtils jwtUtils;

    @Override
    public User createUser(UserDTO userDTO) throws Exception {
        String phoneNum = userDTO.getPhoneNumber();

        if (userRepository.existsByPhoneNumber(phoneNum)) {
            throw new DataIntegrityViolationException("Phone number already exists");
        }
        Role existingRole = roleRepository.findById(userDTO.getRoleId())
                .orElseThrow(() -> new DataIntegrityViolationException("Role not found"));
//        if(existingRole.getName().toUpperCase().equals(Role.TEACHER)) {
//            throw new PermissionDenyException("You cannot register an teacher account");
//        }
        //convert from userDTO => user
        User newUser = User
                .builder()
                .fullName(userDTO.getFullName())
                .phoneNumber(userDTO.getPhoneNumber())
                .userCode(userDTO.getUserCode())
                .address(userDTO.getAddress())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .dateOfBirth(userDTO.getDateOfBirth())
                .role(existingRole)
                .build();

        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
        newUser.setPassword(encodedPassword);

        return userRepository.save(newUser);
    }

    @Override
    public String login(String phoneNumber, String password) throws Exception {
        //Authenticate
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findByPhoneNumber(phoneNumber));
        if (optionalUser.isEmpty()) {
            throw new DataNotFoundException("Invalid phone number or password");
        }
        User exUser = optionalUser.get();
        //check password
        if (!passwordEncoder.matches(password, exUser.getPassword())) {
            throw new BadCredentialsException("Wrong phone number or password");
        }
        //Generate JWT TOKEN
        return jwtUtils.generateToken(exUser.getPhoneNumber());
    }

    @Override
    public User getUserDetailsFromToken(String token) throws Exception {
        if (jwtUtils.isTokenExpired(token)) {
            throw new Exception("Token is expired");
        }
        String phoneNumber = jwtUtils.extractPhoneNumber(token);
        Optional<User> exUser = Optional.ofNullable(userRepository.findByPhoneNumber(phoneNumber));
        if (exUser.isPresent()) {
            return exUser.get();
        } else {
            throw new Exception("User not found");
        }
    }

}
