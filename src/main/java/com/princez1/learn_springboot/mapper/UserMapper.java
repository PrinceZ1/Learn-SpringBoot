package com.princez1.learn_springboot.mapper;

import com.princez1.learn_springboot.dto.request.UserCreateRequest;
import com.princez1.learn_springboot.dto.request.UserUpdateRequest;
import com.princez1.learn_springboot.dto.response.UserResponse;
import com.princez1.learn_springboot.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreateRequest request);
    UserResponse toUserResponse(User user);
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
