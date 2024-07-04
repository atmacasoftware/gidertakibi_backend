package net.atmacacode.backend.core.messages;

import org.springframework.context.i18n.LocaleContextHolder;

public class Msg {
    public static final String USER_CREATED = Messages.getMessageForLocale("backend.user.created.success", LocaleContextHolder.getLocale());
    public static final String BODY_MISSING = Messages.getMessageForLocale("backend.body.missing", LocaleContextHolder.getLocale());
}
