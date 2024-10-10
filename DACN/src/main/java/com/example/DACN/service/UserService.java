package com.example.DACN.service;

import com.example.DACN.dto.request.UserCreationRequest;
import com.example.DACN.dto.request.UserUpdateRequest;
import com.example.DACN.dto.response.UserResponse;
import com.example.DACN.dto.viewModel.UserInfoDTO;
import com.example.DACN.exception.AppException;
import com.example.DACN.exception.ErrorCode;
import com.example.DACN.exception.UserNotFoundException;
import com.example.DACN.mapper.UserInfoMapper;
import com.example.DACN.mapper.UserMapper;
import com.example.DACN.model.UserInfo;
import com.example.DACN.model.user;
import com.example.DACN.repository.UserInfoRepo;
import com.example.DACN.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor()
public class UserService {
    @Autowired
    UserRepo userRepository;
@Autowired
    UserInfoRepo userInfoRepository;
@Autowired
    private UserInfoRepo userInfoRepo;
//

    private UserMapper userMapper;

    private UserInfoMapper userInfoMapper;

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toUserResponse).collect(Collectors.toList());
    }

    public UserResponse getUserById(long id) {
        return userMapper.toUserResponse(userRepository.findById(id).orElseThrow(()->new UserNotFoundException(String.valueOf(ErrorCode.USER_NOTFOUND))));
    }

        public UserResponse createUser(UserCreationRequest request){

            user user = userMapper.toUser(request);
            UserInfo userInfo = userInfoMapper.toUserInfo(request);

            userInfo.setUser(user);
            user.setUserInfo(userInfo);
    //        user.setPassword(passwordEncoder.encode(request.getPassword()));
            userInfo.setUser(user);
            user.setUserInfo(userInfo);
            try {
                // Lưu `User` và tự động lưu `UserInfo` do cascade
                user savedUser = userRepository.save(user);
                return userMapper.toUserResponse(savedUser);
            } catch (DataIntegrityViolationException exception) {
                throw new AppException(ErrorCode.USER_EXISTED);
            }
//            return userMapper.toUserResponse(saveduser);
        }

    public UserResponse updateUser(long id, UserUpdateRequest request){
        user existingUser =(userRepository.findById(id).orElseThrow(()-> new UserNotFoundException(String.valueOf(ErrorCode.USER_NOTFOUND))));
        userMapper.updateUser(existingUser,request);
//        existingUser.setUserInfoD(request);
        existingUser.setPassword(request.getPassword());
        UserInfo existingUserInfo = existingUser.getUserInfo();
        if(existingUserInfo == null) {
            // Tạo mới UserInfo nếu chưa tồn tại
            existingUserInfo = new UserInfo();
            existingUser.setUserInfo(existingUserInfo);
        }

        UserInfoDTO userInfoDTO = request.getUserInfo();
        existingUserInfo.setFullName(userInfoDTO.getFullName());
        existingUserInfo.setDob(userInfoDTO.getDob());
        existingUserInfo.setSex(userInfoDTO.getSex());
        existingUserInfo.setPhone(userInfoDTO.getPhone());
        existingUserInfo.setAddress(userInfoDTO.getAddress());


        return userMapper.toUserResponse(userRepository.save(existingUser));
    }

    public void DeleteUser(long id){
        userRepository.deleteById(id);
    }

}
