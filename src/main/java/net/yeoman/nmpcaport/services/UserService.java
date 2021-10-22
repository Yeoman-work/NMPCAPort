package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.shared.dto.UserDto;

public interface UserService {

    UserDto createUser(UserDto user);
}
