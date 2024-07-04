package net.atmacacode.backend.service.abstracts;

import net.atmacacode.backend.dto.request.user.UserRequest;
import net.atmacacode.backend.dto.response.user.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    UserResponse save(UserRequest userRequest);

    UserResponse getUserById(long id);

    List<UserResponse> getAllUsers();

    UserResponse update(long id, UserRequest userRequest);

    void deleteById(long id);
}
