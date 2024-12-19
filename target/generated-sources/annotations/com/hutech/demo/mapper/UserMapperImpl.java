package com.hutech.demo.mapper;

import com.hutech.demo.createrequest.CreateUserRequest;
import com.hutech.demo.model.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toUser(CreateUserRequest request) {
        if ( request == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.username( request.getUsername() );
        user.password( request.getPassword() );
        user.email( request.getEmail() );
        user.phone( request.getPhone() );
        user.provider( request.getProvider() );
        user.address( request.getAddress() );
        user.gender( request.getGender() );
        user.avatarUrl( request.getAvatarUrl() );

        return user.build();
    }
}
