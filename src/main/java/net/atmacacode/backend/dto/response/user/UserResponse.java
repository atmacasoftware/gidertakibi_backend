package net.atmacacode.backend.dto.response.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}
