package net.atmacacode.backend.mapper;

import net.atmacacode.backend.dto.request.user.UserRequest;
import net.atmacacode.backend.dto.response.user.UserResponse;
import net.atmacacode.backend.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    User asEntity(UserRequest userRequest);

    UserResponse asOutput(User user);

    List<UserResponse> asOutput(List<User> users);

    void update(@MappingTarget User user, UserRequest userRequest);

}
