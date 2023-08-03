package com.NetflixClone.Task.NetflixClone.Service;

import com.NetflixClone.Task.NetflixClone.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import com.NetflixClone.Task.NetflixClone.Repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserService() {
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElse(null);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public void update(User user , Long id) {
        User oldUser = userRepository.findById(id).get();
        // should have used mapstruct here :)
        oldUser.setFirstName(user.getFirstName());
        oldUser.setLastName(user.getLastName());
        oldUser.setEmail(user.getEmail());
        oldUser.setDateOfBirth(user.getDateOfBirth());

        userRepository.save(oldUser);
    }
}
