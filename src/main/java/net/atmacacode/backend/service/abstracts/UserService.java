package net.atmacacode.backend.service.abstracts;

import net.atmacacode.backend.core.token.Credentials;
import net.atmacacode.backend.dto.request.user.UserRequest;
import net.atmacacode.backend.dto.response.user.AuthResponse;
import net.atmacacode.backend.dto.response.user.UserResponse;
import net.atmacacode.backend.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    void save(UserRequest userRequest);

    UserResponse getUserById(long id);

    User findByEmail(String email);

    List<UserResponse> getAllUsers();

    UserResponse update(long id, UserRequest userRequest);

    void deleteById(long id);

    AuthResponse authenticate(Credentials creds);
}
