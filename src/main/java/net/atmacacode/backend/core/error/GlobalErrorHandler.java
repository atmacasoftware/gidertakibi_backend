package net.atmacacode.backend.core.error;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class GlobalErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({DisabledException.class, AccessDeniedException.class})
    ResponseEntity<?> handleDisabledException(HttpServletRequest request, Exception exception){
        ApiError apiError = new ApiError();
        apiError.setMessage(exception.getMessage());
        apiError.setPath(request.getRequestURI());
        if(exception instanceof DisabledException){
            apiError.setStatus(401);
        } else if (exception instanceof AccessDeniedException){
            apiError.setStatus(403);
        }
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }
}
