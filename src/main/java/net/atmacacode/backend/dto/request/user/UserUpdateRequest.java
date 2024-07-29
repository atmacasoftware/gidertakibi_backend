package net.atmacacode.backend.dto.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.atmacacode.backend.core.validation.FileType;
import net.atmacacode.backend.core.validation.UniqueEmail;
import net.atmacacode.backend.entities.User;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UserUpdateRequest {

    @Size(min = 1, max = 100, message = "{gidertakibi.constraint.firstname.sizeWarning}")
    private String first_name;

    @Size(min = 1, max = 100, message = "{gidertakibi.constraint.lastname.sizeWarning}")
    private String last_name;

    @NotNull
    private int userType;

    @FileType(types={"jpeg", "png"})
    private String image;

    private String mobile;

    public User toUser() {
        User user = new User();
        user.setFirst_name(first_name);
        user.setLast_name(last_name);
        user.setUserType(userType);
        user.setImage(image);
        setMobile(mobile);
        return user;
    }
}
