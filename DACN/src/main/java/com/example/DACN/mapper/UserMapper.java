package com.example.DACN.mapper;

import com.example.DACN.dto.request.UserCreationRequest;
import com.example.DACN.dto.request.UserUpdateRequest;
import com.example.DACN.dto.response.UserResponse;
import com.example.DACN.model.user;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    user toUser(UserCreationRequest request);

    UserResponse toUserResponse(user user);
    
    void updateUser(@MappingTarget user user,   UserUpdateRequest request);

    List<UserResponse> toUserResponse(List<user> all);
}
