package com.example.DACN.mapper;

import com.example.DACN.dto.request.UserCreationRequest;
import com.example.DACN.dto.request.UserUpdateRequest;
import com.example.DACN.dto.response.UserResponse;
import com.example.DACN.model.user;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-10T22:45:22+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public user toUser(UserCreationRequest request) {
        if ( request == null ) {
            return null;
        }

        user user = new user();

        return user;
    }

    @Override
    public UserResponse toUserResponse(user user) {
        if ( user == null ) {
            return null;
        }

        UserResponse userResponse = new UserResponse();

        return userResponse;
    }

    @Override
    public void updateUser(user user, UserUpdateRequest request) {
        if ( request == null ) {
            return;
        }
    }

    @Override
    public List<UserResponse> toUserResponse(List<user> all) {
        if ( all == null ) {
            return null;
        }

        List<UserResponse> list = new ArrayList<UserResponse>( all.size() );
        for ( user user : all ) {
            list.add( toUserResponse( user ) );
        }

        return list;
    }
}
