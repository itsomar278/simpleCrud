package com.NetflixClone.Task.NetflixClone.Controller;

import com.NetflixClone.Task.NetflixClone.Model.User;
import com.NetflixClone.Task.NetflixClone.Service.UserService;
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
        List<User> users = userService.findAll();
        if(users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        User user = userService.findById(id);
        if(user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
        }
    }

    @PostMapping("/users")
    public ResponseEntity<String> saveUser(@RequestBody User user){
        if(user == null || user.getFirstName().isEmpty() || user.getLastName().isEmpty() || user.getEmail().isEmpty() || user.getDateOfBirth() == null) {
            return new ResponseEntity<>("FAILED TO CREATE " ,HttpStatus.BAD_REQUEST);
        }
        else {
            userService.save(user);
            return new ResponseEntity<>("Created Succesfully", HttpStatus.CREATED);
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<String> updateUser(@RequestBody User user , @PathVariable Long id){
        User olduser = userService.findById(id);
        if(olduser == null) {
            return new ResponseEntity<>("NO USER WITH SUCH ID" ,HttpStatus.NOT_FOUND);
        }
        if(user == null || user.getFirstName().isEmpty() || user.getLastName().isEmpty() || user.getEmail().isEmpty() || user.getDateOfBirth() == null) {
            return new ResponseEntity<>("FAILED TO UPDATE PLEASE PROVIDE FULL USER DATA " ,HttpStatus.BAD_REQUEST);
        }

        userService.update(user , id);
            return new ResponseEntity<>("Updated Succesfully", HttpStatus.OK);

    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        User user = userService.findById(id);
        if(user == null) {
            return new ResponseEntity<>("NO USER WITH SUCH ID" ,HttpStatus.NOT_FOUND);
        }
        else {
            userService.deleteById(id);
            return new ResponseEntity<>("Deleted !!!!!", HttpStatus.OK);
        }
    }
}