package net.atmacacode.backend.dto.request.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import net.atmacacode.backend.entities.User;
import net.atmacacode.backend.core.validation.UniqueEmail;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UserRequest {

    @UniqueEmail(message = "{gidertakibi.constraint.email.notUnique}")
    @Email
    private String email;

    @Size(min = 1, max = 100, message = "{gidertakibi.constraint.firstname.sizeWarning}")
    private String first_name;

    @Size(min = 1, max = 100, message = "{gidertakibi.constraint.lastname.sizeWarning}")
    private String last_name;

    @NotNull
    private String password;

    @NotNull
    private int userType;

    public User toUser() {
        User user = new User();
        user.setEmail(email);
        user.setFirst_name(first_name);
        user.setLast_name(last_name);
        user.setPassword(password);
        user.setUserType(userType);
        return user;
    }
}
