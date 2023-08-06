package com.NetflixClone.Task.NetflixClone.Service;

import com.NetflixClone.Task.NetflixClone.Model.CustomExceptions.EmptyUserResult;
import com.NetflixClone.Task.NetflixClone.Model.CustomExceptions.UserAlreadyExists;
import com.NetflixClone.Task.NetflixClone.Model.CustomExceptions.UserNotFound;
import com.NetflixClone.Task.NetflixClone.Model.CustomExceptions.WrongInputData;
import com.NetflixClone.Task.NetflixClone.Model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import com.NetflixClone.Task.NetflixClone.Repository.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private IUserRepository IUserRepository;

    public UserService() {
    }

    public List<User> findAll() {
        List<User> Result = IUserRepository.findAll();

        if (Result.isEmpty()) {
            throw new EmptyUserResult();
        }

        else {
            return IUserRepository.findAll();
        }
    }

    public User findByEmail(String email) {
        User Result = IUserRepository.findByEmail(email);

        if(Result == null) {
            throw new UserNotFound();
        }

        else {
            return Result;
        }
    }

    public User findById(Long id) {
        User Result = IUserRepository.findById(id).orElse(null);

        if(Result==null) {
            throw new UserNotFound();
        }

        else {
            return Result;
        }
    }

    public User save(User user) {
        if(IUserRepository.findByEmail(user.getEmail()) != null) {
            throw new UserAlreadyExists();
        }

        else {
           validateUser(user);

            return IUserRepository.save(user);
        }
    }

    public void deleteById(Long id) {
        User user = IUserRepository.findById(id).orElse(null);
        if(user==null)
        {
            throw new UserNotFound();
        }
        else
        {
            IUserRepository.deleteById(id);
        }
    }

    public void update(User newUser , Long id) {
        User oldUser = IUserRepository.findById(id).orElse(null);

        if (oldUser == null) {
            throw new UserNotFound();
        }

        validateUser(newUser);
        ServiceUserMapping INSTANCE = Mappers.getMapper(ServiceUserMapping.class);
        oldUser = INSTANCE.userToUser(newUser, oldUser);
        IUserRepository.save(oldUser);
    }

    public boolean validateEmail(String email) {
        boolean match = email.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$");

        if (match) {
            return true;
        }
        else
        {
            throw new WrongInputData();
        }
    }
    public boolean validateDateBirth(Date DateOfBirth) {
        boolean TrueDate = DateOfBirth.before(new Date());
        boolean OlderThan18 = DateOfBirth.before(new Date(System.currentTimeMillis() - 18 * 31556952000L));

        if(TrueDate&& OlderThan18) {
            return true;
        }
        else {
            throw new WrongInputData();
        }
    }

    public boolean validateFirstName(String firstName) {
        boolean match = firstName.matches("^[a-zA-Z]+$");
        if (match) {
            return true;
        }
        else
        {
            throw new WrongInputData();
        }
    }
    public boolean validateLastName(String lastName) {
        boolean match = lastName.matches("^[a-zA-Z]+$");
        if (match) {
            return true;
        }
        else
        {
            throw new WrongInputData();
        }
    }

    public boolean validateUser(User user) {
        return validateEmail(user.getEmail()) &&
                validateDateBirth(user.getDateOfBirth()) &&
                validateFirstName(user.getFirstName()) &&
                validateLastName(user.getLastName());
    }

}
