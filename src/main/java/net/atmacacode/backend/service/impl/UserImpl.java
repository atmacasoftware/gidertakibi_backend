package net.atmacacode.backend.service.impl;

import net.atmacacode.backend.core.exception.AuthenticationException;
import net.atmacacode.backend.core.exception.NotUniqueEmailException;
import net.atmacacode.backend.core.token.Credentials;
import net.atmacacode.backend.core.token.Token;
import net.atmacacode.backend.dao.UserRepository;
import net.atmacacode.backend.dto.request.user.UserRequest;
import net.atmacacode.backend.dto.response.user.AuthResponse;
import net.atmacacode.backend.dto.response.user.UserResponse;
import net.atmacacode.backend.entities.User;
import net.atmacacode.backend.service.abstracts.EmailService;
import net.atmacacode.backend.service.abstracts.TokenService;
import net.atmacacode.backend.service.abstracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailService emailService;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private TokenService tokenService;

    @Override
    public void save(UserRequest userRequest) {
        try{
            User user = userRequest.toUser();
            user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
            userRepository.save(user);
        } catch(DataIntegrityViolationException e){
            throw new NotUniqueEmailException();
        }
    }

    @Override
    public UserResponse getUserById(long id) {
        return null;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(AuthenticationException::new);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return List.of();
    }

    @Override
    public UserResponse update(long id, UserRequest userRequest) {
        return null;
    }

    @Override
    public void deleteById(long id) {

    }

    @Override
    public AuthResponse authenticate(Credentials creds) {
        User user = this.findByEmail(creds.email());
        if(!passwordEncoder.matches(creds.password(), user.getPassword())) throw new AuthenticationException();

        Token token = tokenService.createToken(user,creds);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(token);
        authResponse.setUser(new UserResponse(user));
        return authResponse;
    }
}
