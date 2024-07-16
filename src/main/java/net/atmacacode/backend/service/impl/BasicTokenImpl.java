package net.atmacacode.backend.service.impl;

import net.atmacacode.backend.core.token.Credentials;
import net.atmacacode.backend.core.token.Token;
import net.atmacacode.backend.entities.User;
import net.atmacacode.backend.service.abstracts.TokenService;

import java.util.Base64;

public class BasicTokenImpl implements TokenService {
    @Override
    public Token createToken(User user, Credentials creds) {
        String emailColonPassword = creds.email() + ":" + creds.password();
        String token = Base64.getEncoder().encodeToString(emailColonPassword.getBytes());
        return new Token("Basic", token);
    }

    @Override
    public User verifyToken(String authorizationHeader) {
        return null;
    }
}
