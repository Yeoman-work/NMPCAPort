package net.yeoman.nmpcaport.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping
    public String getUser(){

        return "inside get users";
    }

    @PostMapping
    public String createUser(){

        return "inside create";
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
