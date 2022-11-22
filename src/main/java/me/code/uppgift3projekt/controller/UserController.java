package me.code.uppgift3projekt.controller;

import me.code.uppgift3projekt.data.User;
import me.code.uppgift3projekt.exception.UserAlreadyExistsException;
import me.code.uppgift3projekt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/users")
    public Collection<User> getUsers(){
        return userService.getAll();
    }

    @PutMapping("/user/add")
    public User addUser(@RequestBody User user) throws UserAlreadyExistsException {
        System.out.println(user);
        return userService.register(user.getUsername(), user.getPassword());
    }

    @PostMapping("/test")
    public User login(@RequestBody User user){
        return userService.login(user);
    }



}
