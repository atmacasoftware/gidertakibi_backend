package net.atmacacode.backend.core.messages;

import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Collections;
import java.util.Map;

public class NotUniqueEmailException extends RuntimeException{

    public NotUniqueEmailException() {
        super(Messages.getMessageForLocale("backend.error.validation", LocaleContextHolder.getLocale()));
    }

    public Map<String, String> getValidationErrors(){
        return Collections.singletonMap("email", Messages.getMessageForLocale("backend.constraint.email.notUnique", LocaleContextHolder.getLocale()));
    }

}
