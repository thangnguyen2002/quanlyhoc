package com.quanlyhoc.quanlyhoc.services;

import com.quanlyhoc.quanlyhoc.models.Token;
import com.quanlyhoc.quanlyhoc.models.User;
import com.quanlyhoc.quanlyhoc.repositories.TokenRepository;
import com.quanlyhoc.quanlyhoc.services.interfaces.ITokenService;
import com.quanlyhoc.quanlyhoc.shared.utils.JwtUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TokenService implements ITokenService {
    @Value("${jwt.expiration}")
    private int expiration; //save to an environment variable

    private final TokenRepository tokenRepository;
    private final JwtUtils jwtUtils;

    @Transactional
    @Override
    //add vao table Token tu token login
    public Token addToToken(User user, String token) {
        List<Token> userTokens = tokenRepository.findByUser(user);
        LocalDateTime expirationDateTime = LocalDateTime.now().plusSeconds(expiration);
        Token newToken = Token.builder()
                .user(user)
                .token(token)
                .revoked(false)
                .expired(false)
                .tokenType("Bearer")
                .expirationDate(expirationDateTime)
                .build();
        tokenRepository.save(newToken);
        return newToken;
    }
}

