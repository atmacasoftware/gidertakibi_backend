package net.atmacacode.backend.service.abstracts;

import net.atmacacode.backend.core.token.Credentials;
import net.atmacacode.backend.core.token.Token;
import net.atmacacode.backend.entities.User;
import org.springframework.stereotype.Service;

@Service
public interface TokenService {
    public Token createToken(User user, Credentials creds);

    public User verifyToken(String authorizationHeader);
}
