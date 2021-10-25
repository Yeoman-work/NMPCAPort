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

        return new ModelMapper().map(storedUser, UserDto.class);


    }

    @Override
    public UserDto getUser(String userId) {

        UserEntity userEntity = userRepository.findByUserId(userId);
        if(userEntity == null) throw new UsernameNotFoundException(userId);

        return  new ModelMapper().map(userEntity, UserDto.class);

    }

    @Override
    public UserDto updateUser(UserDto user, String userId) {

        UserEntity userEntity = this.userRepository.findByUserId(userId);
        if(!user.getFirstName().equals(userEntity.getFirstName())){
            userEntity.setFirstName(user.getFirstName());
        }
        if(!user.getLastName().equals(userEntity.getLastName())){
            userEntity.setLastName(user.getLastName());
        }

        UserEntity storedUser = this.userRepository.save(userEntity);

        return new ModelMapper().map(storedUser, UserDto.class);
    }

    @Override
    public UserDto deleteUser(String userId) {

        UserEntity userEntity = this.userRepository.findByUserId(userId);

        this.userRepository.delete(userEntity);

        return new ModelMapper().map(userEntity, UserDto.class);
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = this.userRepository.findByEmail(email);

        if(!this.userRepository.existsByEmail(email)) throw new UsernameNotFoundException(email);

        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
    }
}
