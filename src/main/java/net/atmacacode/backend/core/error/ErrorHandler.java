package net.atmacacode.backend.core.error;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import net.atmacacode.backend.core.exception.*;
import net.atmacacode.backend.core.messages.Messages;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler({
            MethodArgumentNotValidException.class,
            NotUniqueEmailException.class,
            ActivationNotificationException.class,
            InvalidTokenException.class,
            EntityNotFoundException.class,
            AuthenticationException.class,
            NotFoundException.class
    })
    ResponseEntity<ApiError> handleException(Exception exception, HttpServletRequest request) {
        ApiError apiError = new ApiError();
        apiError.setPath(request.getRequestURI());
        apiError.setMessage(exception.getMessage());
        if (exception instanceof MethodArgumentNotValidException) {
            String message = Messages.getMessageForLocale("gidertakibi.error.validation", LocaleContextHolder.getLocale());
            apiError.setMessage(message);
            apiError.setStatus(400);
            var validationErrors =((MethodArgumentNotValidException)exception).getBindingResult().getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage, (existing, replacing) -> existing));
            apiError.setErrorList(validationErrors);
        } else if(exception instanceof NotUniqueEmailException) {
            apiError.setStatus(400);
            apiError.setErrorList(((NotUniqueEmailException) exception).getValidationErrors());
        } else if(exception instanceof ActivationNotificationException){
            apiError.setStatus(502);
        } else if (exception instanceof InvalidTokenException) {
            apiError.setStatus(400);
        } else if (exception instanceof EntityNotFoundException) {
            apiError.setStatus(404);
        } else if (exception instanceof AuthenticationException) {
            apiError.setStatus(401);
        } else if (exception instanceof NotFoundException) {
            apiError.setStatus(400);
        }
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }
}
