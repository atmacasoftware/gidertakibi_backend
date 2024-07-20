package net.atmacacode.backend.core.config;

import net.atmacacode.backend.entities.User;
import net.atmacacode.backend.service.abstracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User inDB = userService.findByEmail(email);
        if(inDB == null) {
            throw new UsernameNotFoundException(email + " is not found");
        }

        return new CurrentUser(inDB);
    }
}
