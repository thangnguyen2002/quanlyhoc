package com.quanlyhoc.quanlyhoc.services.interfaces;

import com.quanlyhoc.quanlyhoc.models.Token;
import com.quanlyhoc.quanlyhoc.models.User;

public interface ITokenService {
    Token addToToken(User user, String token);
}
