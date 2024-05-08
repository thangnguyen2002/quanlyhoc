package com.quanlyhoc.quanlyhoc.services;

import com.quanlyhoc.quanlyhoc.dtos.UserDTO;
import com.quanlyhoc.quanlyhoc.models.Role;
import com.quanlyhoc.quanlyhoc.models.User;
import com.quanlyhoc.quanlyhoc.repositories.RoleRepository;
import com.quanlyhoc.quanlyhoc.repositories.UserRepository;
import com.quanlyhoc.quanlyhoc.services.interfaces.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final RoleRepository roleRepository;

    @Override
    public User createUser(UserDTO userDTO) throws Exception {
        String email = userDTO.getEmail();

        if (userRepository.existsByPhoneNumber(email)) {
            throw new DataIntegrityViolationException("Email already exists");
        }
        Role existingRole = roleRepository.findById(userDTO.getRoleId())
                .orElseThrow(() -> new DataIntegrityViolationException("Role not found"));
//        if(existingRole.getName().toUpperCase().equals(Role.ADMIN)) {
//            throw new PermissionDenyException("You cannot register an admin account");
//        }
        //convert from userDTO => user
        User newUser = User
                .builder()
                .fullName(userDTO.getFullName())
                .phoneNumber(userDTO.getPhoneNumber())
                .studentCode(userDTO.getAddress())
                .password(userDTO.getPassword())
                .dateOfBirth(userDTO.getDateOfBirth())
                .role(existingRole)
                .build();

//            String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
//            newUser.setPassword(encodedPassword);

        return userRepository.save(newUser);
    }

    @Override
    public String login(String email, String password) throws Exception {
        return null;
    }
}
