package com.NetflixClone.Task.NetflixClone.Repository;

import com.NetflixClone.Task.NetflixClone.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    public  User findByEmail(String email);
}
