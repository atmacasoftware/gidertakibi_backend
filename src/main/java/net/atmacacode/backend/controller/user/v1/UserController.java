package net.atmacacode.backend.controller.user.v1;

import jakarta.validation.Valid;
import net.atmacacode.backend.core.messages.GenericMessage;
import net.atmacacode.backend.core.messages.Messages;
import net.atmacacode.backend.dto.request.user.UserRequest;
import net.atmacacode.backend.service.abstracts.UserService;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public GenericMessage save(@Valid @RequestBody UserRequest userRequest) {
        userService.save(userRequest);
        String message = Messages.getMessageForLocale("gidertakibi.user.created.success", LocaleContextHolder.getLocale());
        return new GenericMessage(message);
    }
}
