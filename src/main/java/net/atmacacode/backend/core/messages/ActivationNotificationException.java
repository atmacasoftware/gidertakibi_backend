package net.atmacacode.backend.core.messages;

import org.springframework.context.i18n.LocaleContextHolder;

public class ActivationNotificationException extends RuntimeException {
    public ActivationNotificationException() {
        super(Messages.getMessageForLocale("gidertakibi.create.user.email.failure", LocaleContextHolder.getLocale()));
    }
}
