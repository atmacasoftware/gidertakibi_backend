package net.atmacacode.backend.service.abstracts;

import net.atmacacode.backend.core.token.Credentials;
import net.atmacacode.backend.dto.request.user.UserRequest;
import net.atmacacode.backend.dto.request.user.UserUpdateRequest;
import net.atmacacode.backend.dto.response.user.AuthResponse;
import net.atmacacode.backend.dto.response.user.UserResponse;
import net.atmacacode.backend.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    void save(UserRequest userRequest);

    User getUserById(long id);

    User findByEmail(String email);

    Page<User> getAllUsers(Pageable pageable);

    User update(long id, UserUpdateRequest userUpdateRequest);

    void deleteById(long id);

    AuthResponse authenticate(Credentials creds);
}
