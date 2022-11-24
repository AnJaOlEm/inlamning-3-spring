package me.code.uppgift3projekt.controller;

import jdk.jshell.Snippet;
import me.code.uppgift3projekt.data.Post;
import me.code.uppgift3projekt.data.User;
import me.code.uppgift3projekt.dtos.LoginDTO;
import me.code.uppgift3projekt.exception.UserAlreadyExistsException;
import me.code.uppgift3projekt.service.PostService;
import me.code.uppgift3projekt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final PostService postService;


    @Autowired
    public UserController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }


    @GetMapping("/users")
    public Collection<User> getUsers(){
        return userService.getAll();
    }

    @PutMapping("/add")
    public User addUser(@RequestBody User user) throws UserAlreadyExistsException {
        System.out.println(user);
        return userService.register(user.getUsername(), user.getPassword());
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO){

        return null;
    }

    @PostMapping("/posts")
    public Collection<Post> getUserPosts(@RequestBody String username){
        return postService.getAllUserPosts(username);
    }

}
