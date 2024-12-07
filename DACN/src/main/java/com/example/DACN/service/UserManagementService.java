package com.example.DACN.service;

import com.example.DACN.config.JWTAuthFilter;
import com.example.DACN.dto.request.ApiResponse;
import com.example.DACN.model.User;
import com.example.DACN.model.UserContact;
import com.example.DACN.model.UserInfo;
import com.example.DACN.repository.UserContactRepo;
import com.example.DACN.repository.UserInfoRepo;
import com.example.DACN.repository.UsersRepo;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class UserManagementService {
    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private UserContactRepo userContactRepo;

    @Autowired
    private UserInfoRepo userInfoRepo;
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public ApiResponse register(ApiResponse registrationRequest ){
        ApiResponse resp = new ApiResponse();
        try{
            User user = new User();
            user.setUsername(registrationRequest.getCccd());
            user.setRole(registrationRequest.getRole());
            user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            User userResult=  usersRepo.save(user);


            //save userinfo
            UserInfo userInfo = new UserInfo();
            userInfo.setFullName(registrationRequest.getFullName());
            userInfo.setDob(registrationRequest.getDob());
            userInfo.setUser(userResult);
            userInfo.setSex(registrationRequest.getSex());
            userInfoRepo.save(userInfo);

            //save usercontact
            UserContact userContact = new UserContact();
            userContact.setEmail(registrationRequest.getEmail());
            userContact.setPhone(registrationRequest.getPhone());
            userContact.setAddress(registrationRequest.getAddress());
            userContact.setUser(userResult);
            userContactRepo.save(userContact);

            resp.setUser(userResult);
            resp.setMessage("User, UserInfo, and UserContact saved successfully");
            resp.setCode(200);

        }catch(Exception e){
            resp.setCode(500);
            resp.setError(e.getMessage());
        }

        return resp;
    }

    public  ApiResponse login (ApiResponse loginRequest){
        ApiResponse response = new ApiResponse();
        try{
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getCccd(), loginRequest.getPassword()));
                var user = usersRepo.findUserByUsername(loginRequest.getCccd()).orElseThrow();
                var jwt = jwtUtils.generateToken(user);
                var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
                response.setCode(200);
                response.setToken(jwt);
                response.setRefreshToken(refreshToken);
                response.setExpirationTime("24hours");
                response.setMessage("Successfully logged in");

        }catch(Exception e){
            response.setCode(500);
            response.setError(e.getMessage());
        }
        return response;
    }


    public ApiResponse refreshToken(ApiResponse refreshTokenReqiest){
        ApiResponse response = new ApiResponse();
        try{
            String cccd = jwtUtils.extractUsername(refreshTokenReqiest.getToken());
            User users = usersRepo.findUserByUsername(cccd).orElseThrow();
            if (jwtUtils.isTokenValid(refreshTokenReqiest.getToken(), users)) {
                var jwt = jwtUtils.generateToken(users);
                response.setCode(200);
                response.setToken(jwt);
                response.setRefreshToken(refreshTokenReqiest.getToken());
                response.setExpirationTime("24Hr");
                response.setMessage("Successfully Refreshed Token");
            }
            response.setCode(200);
            return response;

        }catch (Exception e){
            response.setCode(500);
            response.setMessage(e.getMessage());
            return response;
        }
    }


    public ApiResponse getAllUsers() {
        ApiResponse reqRes = new ApiResponse();

        try {
            List<User> result = usersRepo.findAll();
            if (!result.isEmpty()) {
                reqRes.setUserList(result);
                reqRes.setCode(200);
                reqRes.setMessage("Successful");
            } else {
                reqRes.setCode(404);
                reqRes.setMessage("No users found");
            }
            return reqRes;
        } catch (Exception e) {
            reqRes.setCode(500);
            reqRes.setMessage("Error occurred: " + e.getMessage());
            return reqRes;
        }
    }


    public ApiResponse getUsersById(String id) {
        ApiResponse reqRes = new ApiResponse();
        try {
            User usersById = usersRepo.findUserByUsername(id).orElseThrow(() -> new RuntimeException("User Not found"));
            reqRes.setUser(usersById);
            reqRes.setCode(200);
            reqRes.setMessage("Users with id '" + id + "' found successfully");
        } catch (Exception e) {
            reqRes.setCode(500);
            reqRes.setMessage("Error occurred: " + e.getMessage());
        }
        return reqRes;
    }


    public ApiResponse deleteUser(String userId) {
        ApiResponse reqRes = new ApiResponse();
        try {
            Optional<User> userOptional = usersRepo.findById(userId);
            if (userOptional.isPresent()) {
                usersRepo.deleteById(userId);
                reqRes.setCode(200);
                reqRes.setMessage("User deleted successfully");
            } else {
                reqRes.setCode(404);
                reqRes.setMessage("User not found for deletion");
            }
        } catch (Exception e) {
            reqRes.setCode(500);
            reqRes.setMessage("Error occurred while deleting user: " + e.getMessage());
        }
        return reqRes;
    }

    public ApiResponse updateUser(String cccd, User updatedUser) {
        ApiResponse reqRes = new ApiResponse();
        try {
            Optional<User> userOptional = usersRepo.findUserByUsername(cccd);

            if (userOptional.isPresent()) {
                User existingUser = userOptional.get();

                // Cập nhật thông tin cơ bản của User
                if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
                    existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
                }

                if (updatedUser.getRole() != null) {
                    existingUser.setRole(updatedUser.getRole());
                }

                // Cập nhật thông tin UserInfo nếu có
                if (updatedUser.getUserInfo() != null) {
                    UserInfo updatedInfo = updatedUser.getUserInfo();
                    UserInfo existingInfo = existingUser.getUserInfo();

                    if (updatedInfo.getFullName() != null) {
                        existingInfo.setFullName(updatedInfo.getFullName());
                    }
                    if (updatedInfo.getDob() != null) {
                        existingInfo.setDob(updatedInfo.getDob());
                    }
                    if (updatedInfo.getSex() != null) {
                        existingInfo.setSex(updatedInfo.getSex());
                    }
                }

                // Cập nhật thông tin UserContact nếu có
                if (updatedUser.getUserContact() != null) {
                    UserContact updatedContact = updatedUser.getUserContact();
                    UserContact existingContact = existingUser.getUserContact();

                    if (updatedContact.getPhone() != null) {
                        existingContact.setPhone(updatedContact.getPhone());
                    }
                    if (updatedContact.getEmail() != null) {
                        existingContact.setEmail(updatedContact.getEmail());
                    }
                    if (updatedContact.getAddress() != null) {
                        existingContact.setAddress(updatedContact.getAddress());
                    }
                }

                // Lưu thay đổi
                User savedUser = usersRepo.save(existingUser);

                // Đóng gói phản hồi
                reqRes.setCode(200);
                reqRes.setMessage("User updated successfully");
                reqRes.setUser(savedUser);
            } else {
                reqRes.setCode(404);
                reqRes.setMessage("User not found for update");
            }
        } catch (Exception e) {
            reqRes.setCode(500);
            reqRes.setMessage("Error occurred while updating user: " + e.getMessage());
        }
        return reqRes;
    }


    public ApiResponse getMyInfo(String cccd){
        ApiResponse reqRes = new ApiResponse();
        try {
            Optional<User> userOptional = usersRepo.findUserByUsername(cccd);
            if (userOptional.isPresent()) {
                reqRes.setUser(userOptional.get());
                reqRes.setCode(200);
                reqRes.setMessage("successful");
            } else {
                reqRes.setCode(404);
                reqRes.setMessage("User not found for update");
            }

        }catch (Exception e){
            reqRes.setCode(500);
            reqRes.setMessage("Error occurred while getting user info: " + e.getMessage());
        }
        return reqRes;

    }

}

