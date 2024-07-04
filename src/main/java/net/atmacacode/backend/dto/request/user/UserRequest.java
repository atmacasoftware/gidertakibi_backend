package net.atmacacode.backend.dto.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import net.atmacacode.backend.core.validation.UniqueEmail;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    @UniqueEmail(message = "{backend.constraint.email.notUnique}")
    @Email
    private String email;

    @Size(min = 1, max = 100, message = "{backend.constraint.firstname.sizeWarning}")
    private String first_name;

    @Size(min = 1, max = 100, message = "{backend.constraint.lastname.sizeWarning}")
    private String last_name;

    private String password;
}
