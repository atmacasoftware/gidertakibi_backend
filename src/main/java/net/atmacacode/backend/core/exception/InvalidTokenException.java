package net.atmacacode.backend.core.exception;


import net.atmacacode.backend.core.messages.Messages;
import org.springframework.context.i18n.LocaleContextHolder;

public class InvalidTokenException extends RuntimeException{
    public InvalidTokenException(){
        super(Messages.getMessageForLocale("gidertakibi.activate.user.invalid.token", LocaleContextHolder.getLocale()));
    }
}
