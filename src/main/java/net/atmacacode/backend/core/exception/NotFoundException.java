package net.atmacacode.backend.core.exception;

import net.atmacacode.backend.core.messages.Messages;
import org.springframework.context.i18n.LocaleContextHolder;

public class NotFoundException extends RuntimeException{
    public NotFoundException(long id) {
        super(Messages.getMessageForLocale("gidertakibi.user.not.found", LocaleContextHolder.getLocale(),id));
    }
}
