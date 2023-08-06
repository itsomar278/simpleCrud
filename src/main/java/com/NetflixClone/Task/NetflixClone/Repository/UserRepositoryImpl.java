package com.NetflixClone.Task.NetflixClone.Repository;

import com.NetflixClone.Task.NetflixClone.Model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRepositoryImpl {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private IUserRepository IUserRepository;

    public User findByEmail(String email) {
        String hql = "SELECT u FROM User u WHERE u.email = :email";
        TypedQuery<User> query = entityManager.createQuery(hql, User.class);
        query.setParameter("email", email);
        return query.getSingleResult();
    }
}
