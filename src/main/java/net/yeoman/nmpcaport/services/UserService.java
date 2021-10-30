package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.UserEntity;
import net.yeoman.nmpcaport.shared.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserDto createUser(UserDto user);
    UserDto getUser(String email);
    UserDto getUserByUserId(String userID);
    UserDto updateUser(UserDto user, String userId);
    UserDto deleteUser(String userId);
    UserEntity getUserEntity(String userId);

}
