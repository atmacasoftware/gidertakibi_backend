package net.atmacacode.backend.core.exception;

import net.atmacacode.backend.core.messages.Messages;
import org.springframework.context.i18n.LocaleContextHolder;

public class AuthenticationException extends RuntimeException{
    public AuthenticationException() {
        super(Messages.getMessageForLocale("gidertakibi.auth.invalid.credentials", LocaleContextHolder.getLocale()));
    }
}
