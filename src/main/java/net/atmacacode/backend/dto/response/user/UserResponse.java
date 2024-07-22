package net.atmacacode.backend.dto.response.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.atmacacode.backend.entities.User;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserResponse {
    private long id;
    private String email;
    private String first_name;
    private String last_name;
    private String mobile;
    private int userType;
    private String image;
    private LocalDate created_at;

    public UserResponse(User user) {
        setId(user.getId());
        setEmail(user.getEmail());
        setFirst_name(user.getFirst_name());
        setLast_name(user.getLast_name());
        setMobile(user.getMobile());
        setUserType(user.getUserType());
        setImage(user.getImage());
        setCreated_at(user.getCreated_at());
    }

}
