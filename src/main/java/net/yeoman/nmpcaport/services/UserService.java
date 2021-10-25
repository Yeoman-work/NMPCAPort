package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.io.request.UserLoginRequestModel;
import net.yeoman.nmpcaport.shared.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserDto createUser(UserDto user);
    UserDto getUser(String userId);
    UserDto updateUser(UserDto user, String userId);
    UserDto deleteUser(String userId);

}
