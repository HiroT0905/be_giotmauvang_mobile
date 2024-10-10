package com.example.DACN.mapper;


import com.example.DACN.dto.request.UserCreationRequest;
import com.example.DACN.dto.viewModel.UserInfoDTO;
import com.example.DACN.model.UserInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserInfoMapper {
    UserInfoMapper INSTANCE = Mappers.getMapper(UserInfoMapper.class);
    UserInfo toUserInfo(UserCreationRequest request);
}
