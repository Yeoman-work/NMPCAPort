package net.yeoman.nmpcaport.controller;

import net.yeoman.nmpcaport.io.request.user.UserDetailsRequestModel;
import net.yeoman.nmpcaport.io.response.user.UserDetailsResponseModel;
import net.yeoman.nmpcaport.services.Impl.UserServiceImpl;
import net.yeoman.nmpcaport.shared.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")

public class UserController {

    @Autowired
    private UserServiceImpl userService;
    @GetMapping(path = "/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public UserDetailsResponseModel getUser(@PathVariable("userId") String userId){

        UserDto userDto = this.userService.getUserByUserId(userId);

        return new ModelMapper().map(userDto, UserDetailsResponseModel.class);
    }


    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
                  produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public UserDetailsResponseModel createUser(@RequestBody UserDetailsRequestModel userDetails){

        UserDto userDto = new ModelMapper().map(userDetails, UserDto.class);

        UserDto newUser =  this.userService.createUser(userDto);

        return new ModelMapper().map(newUser, UserDetailsResponseModel.class);

    }

    @PutMapping( path = "/{userId}",consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
                                      produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public UserDetailsResponseModel updateUser(@PathVariable("userId") String userId, @RequestBody UserDetailsRequestModel userDetails){

        UserDto updatedUser = new ModelMapper().map(userDetails, UserDto.class);

        UserDto storedUser = this.userService.updateUser(new ModelMapper().map(userDetails, UserDto.class), userId);

        return new ModelMapper().map(storedUser, UserDetailsResponseModel.class);

    }

    @DeleteMapping("/{userId}")
    public UserDetailsResponseModel deleteUser(@PathVariable("userId") String userId){

        UserDto userDto = this.userService.deleteUser(userId);

        return  new ModelMapper().map(userDto, UserDetailsResponseModel.class);
    }
}
