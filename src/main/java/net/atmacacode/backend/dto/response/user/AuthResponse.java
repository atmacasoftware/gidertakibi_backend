package net.atmacacode.backend.dto.response.user;

import lombok.Getter;
import lombok.Setter;
import net.atmacacode.backend.core.token.Token;

@Getter
@Setter
public class AuthResponse {
    private UserResponse user;
    private Token token;
}
