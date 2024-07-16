package net.atmacacode.backend.core.exception;

import net.atmacacode.backend.core.messages.Messages;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Collections;
import java.util.Map;

public class NotUniqueEmailException extends RuntimeException{

    public NotUniqueEmailException() {
        super(Messages.getMessageForLocale("gidertakibi.error.validation", LocaleContextHolder.getLocale()));
    }

    public Map<String, String> getValidationErrors(){
        return Collections.singletonMap("email", Messages.getMessageForLocale("gidertakibi.constraint.email.notUnique", LocaleContextHolder.getLocale()));
    }

}
