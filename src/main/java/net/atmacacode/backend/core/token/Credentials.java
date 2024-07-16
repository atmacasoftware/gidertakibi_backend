package net.atmacacode.backend.core.token;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record Credentials (@Email String email, @NotNull String password) {
}
