package net.yeoman.nmpcaport.controller;

import net.yeoman.nmpcaport.io.request.UserDetailsRequestModel;
import net.yeoman.nmpcaport.io.response.UserDetailsResponseModel;
import net.yeoman.nmpcaport.services.Impl.UserServiceImpl;
import net.yeoman.nmpcaport.shared.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping
    public String getUser(){


        return "inside get user";
    }

    @PostMapping
    public UserDetailsResponseModel createUser(@RequestBody UserDetailsRequestModel userDetails){

        ModelMapper modelMapper = new ModelMapper();

        UserDto userDto = new ModelMapper().map(userDetails, UserDto.class);

        UserDto newUser =  this.userService.createUser(userDto);

        UserDetailsResponseModel returnValue = new ModelMapper().map(newUser, UserDetailsResponseModel.class);

        return returnValue;
    }

    @PutMapping
    public String updateUser(){

        return "update User";
    }

    @DeleteMapping
    public String deleteUser(){

        return "delete Users";
    }
}
