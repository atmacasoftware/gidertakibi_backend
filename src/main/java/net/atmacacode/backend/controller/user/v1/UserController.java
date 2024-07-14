package net.atmacacode.backend.controller.user.v1;

import jakarta.validation.Valid;
import net.atmacacode.backend.core.messages.ResultHelper;
import net.atmacacode.backend.core.result.ResultData;
import net.atmacacode.backend.dto.request.user.UserRequest;
import net.atmacacode.backend.dto.response.user.UserResponse;
import net.atmacacode.backend.service.abstracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResultData<UserResponse> save(@Valid @RequestBody UserRequest userRequest) {
        return ResultHelper.userCreated(this.userService.save(userRequest));
    }
}
