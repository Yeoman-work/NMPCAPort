package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.entities.UserEntity;
import net.yeoman.nmpcaport.repositories.UserRepository;
import net.yeoman.nmpcaport.services.UserService;
import net.yeoman.nmpcaport.shared.dto.UserDto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto user) {

        UserEntity userEntity = new ModelMapper().map(user, UserEntity.class);
        userEntity.setEncryptedPassword("asdasvecerc");
        userEntity.setUserId("testToken");
        userEntity.setEmailVerificationStatus(false);

        UserEntity storedUser = userRepository.save(userEntity);

        UserDto returnValue = new ModelMapper().map(storedUser, UserDto.class);

        return returnValue;
    }
}
