package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.entities.UserEntity;
import net.yeoman.nmpcaport.repositories.UserRepository;
import net.yeoman.nmpcaport.services.UserService;
import net.yeoman.nmpcaport.shared.dto.UserDto;

import net.yeoman.nmpcaport.shared.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    Utils utils;

    @Override
    public UserDto createUser(UserDto user) {

        UserEntity userEntity = new ModelMapper().map(user, UserEntity.class);

        if(this.userRepository.existsByEmail(user.getEmail())) throw new RuntimeException("Record exist");
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userEntity.setUserId(utils.generateRandomID());
        userEntity.setEmailVerificationStatus(false);

        UserEntity storedUser = userRepository.save(userEntity);

        UserDto returnValue = new ModelMapper().map(storedUser, UserDto.class);

        return returnValue;
    }

    @Override
    public UserDto getUser(String email) {
        if(!this.userRepository.existsByEmail(email)) throw new UsernameNotFoundException(email);
        UserEntity userEntity = userRepository.findByEmail(email);
        UserDto returnValue = new ModelMapper().map(userEntity, UserDto.class);
        return returnValue;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = this.userRepository.findByEmail(email);

        if(!this.userRepository.existsByEmail(email)) throw new UsernameNotFoundException(email);

        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
    }
}
