package net.atmacacode.backend.service.impl;

import net.atmacacode.backend.core.exception.AuthenticationException;
import net.atmacacode.backend.core.exception.NotFoundException;
import net.atmacacode.backend.core.exception.NotUniqueEmailException;
import net.atmacacode.backend.core.token.Credentials;
import net.atmacacode.backend.core.token.Token;
import net.atmacacode.backend.dao.UserRepository;
import net.atmacacode.backend.dto.request.user.UserRequest;
import net.atmacacode.backend.dto.request.user.UserUpdateRequest;
import net.atmacacode.backend.dto.response.user.AuthResponse;
import net.atmacacode.backend.dto.response.user.UserResponse;
import net.atmacacode.backend.entities.User;
import net.atmacacode.backend.service.abstracts.EmailService;
import net.atmacacode.backend.service.abstracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailService emailService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    BasicTokenImpl basicToken;
    @Autowired
    private FileService fileService;

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
    public User getUserById(long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(AuthenticationException::new);
    }

    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User update(long id, UserUpdateRequest userUpdateRequest) {
        User inDb =this.getUserById(id);
        inDb.setFirst_name(userUpdateRequest.getFirst_name());
        inDb.setLast_name(userUpdateRequest.getLast_name());
        if(userUpdateRequest.getImage() != null){
            String filename = fileService.saveBase64StringAsFile(userUpdateRequest.getImage());
            fileService.deleteProfileImage(inDb.getImage());
            inDb.setImage(filename);
        }
        return userRepository.save(inDb);
    }


    @Override
    public void deleteById(long id) {

    }

    @Override
    public AuthResponse authenticate(Credentials creds) {
        User user = this.findByEmail(creds.email());
        if(!passwordEncoder.matches(creds.password(), user.getPassword())) throw new AuthenticationException();

        Token token = basicToken.createToken(user,creds);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(token);
        authResponse.setUser(new UserResponse(user));
        return authResponse;
    }
}
