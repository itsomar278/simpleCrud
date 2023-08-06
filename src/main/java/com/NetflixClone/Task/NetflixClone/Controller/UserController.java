package com.NetflixClone.Task.NetflixClone.Controller;

import com.NetflixClone.Task.NetflixClone.Controller.DTOs.UserDTO;
import com.NetflixClone.Task.NetflixClone.Controller.Mapping.UserMapping;
import com.NetflixClone.Task.NetflixClone.Model.User;
import com.NetflixClone.Task.NetflixClone.Service.UserService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){

        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);

    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){

            return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);

    }

    @PostMapping("/users")
    public ResponseEntity<String> saveUser(@RequestBody UserDTO user){

            userService.save(MapToUser(user));
            return new ResponseEntity<>("Created Succesfully", HttpStatus.CREATED);

    }

    @PutMapping("/users/{id}")
    public ResponseEntity<String> updateUser(@RequestBody UserDTO user , @PathVariable Long id){

            userService.update(MapToUser(user) , id);
            return new ResponseEntity<>("Updated Succesfully", HttpStatus.OK);

    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){

            userService.deleteById(id);
            return new ResponseEntity<>("Deleted !!!!!", HttpStatus.OK);
    }

    public User MapToUser(UserDTO userDTO){
        UserMapping INSTANCE = Mappers.getMapper(UserMapping.class);
        return INSTANCE.oUser(userDTO);
    }
}