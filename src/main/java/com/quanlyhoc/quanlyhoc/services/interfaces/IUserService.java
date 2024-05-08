package com.quanlyhoc.quanlyhoc.services.interfaces;

import com.quanlyhoc.quanlyhoc.dtos.UserDTO;
import com.quanlyhoc.quanlyhoc.models.User;

public interface IUserService {
    User createUser(UserDTO userDTO) throws Exception;
    String login(String email, String password) throws Exception;
}