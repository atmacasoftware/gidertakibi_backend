package net.atmacacode.backend.controller.user.v1;

import jakarta.validation.Valid;
import net.atmacacode.backend.core.messages.GenericMessage;
import net.atmacacode.backend.core.messages.Messages;
import net.atmacacode.backend.core.token.Credentials;
import net.atmacacode.backend.dto.request.user.UserRequest;
import net.atmacacode.backend.dto.request.user.UserUpdateRequest;
import net.atmacacode.backend.dto.response.user.AuthResponse;
import net.atmacacode.backend.dto.response.user.UserResponse;
import net.atmacacode.backend.service.abstracts.UserService;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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
        System.out.println(userRequest.getFirst_name());
        System.out.println(userRequest.getLast_name());
        userService.save(userRequest);
        String message = Messages.getMessageForLocale("gidertakibi.user.created.success", LocaleContextHolder.getLocale());
        return new GenericMessage(message);
    }

    @PostMapping("/auth")
    AuthResponse handleAuthentication(@Valid @RequestBody Credentials cred) {
        return userService.authenticate(cred);
    }

    @GetMapping()
    Page<UserResponse> getUsers(Pageable pageable) {
        System.out.println(pageable);
        return userService.getAllUsers(pageable).map(UserResponse::new);
    }

    @PutMapping("/{id}")
    @PreAuthorize("#id == principal.id")
    UserResponse updateUser(@PathVariable long id, @Valid @RequestBody UserUpdateRequest userUpdate){
        return new UserResponse(userService.update(id, userUpdate));
    }

}
