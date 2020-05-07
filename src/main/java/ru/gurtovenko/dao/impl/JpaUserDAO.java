package ru.gurtovenko.dao.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.gurtovenko.dao.UserDAO;
import ru.gurtovenko.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
@Transactional(readOnly = true)
public class JpaUserDAO implements UserDAO {

    @PersistenceContext(unitName = "emf")
    private EntityManager entityManager;

    @Override
    public List<User> getAll() {
        return entityManager.createQuery(
                "select u from User u", User.class
        ).getResultList();
    }

    @Override
    public User getOne(String email) {
        TypedQuery<User> q = entityManager.createQuery(
                "select u from User u where u.email = :email",
                User.class);
        q.setParameter("email", email);
        return q.getResultList().stream().findAny().orElse(null);
    }

    @Override
    @Transactional
    public void add(User user) {
        entityManager.persist(user);
    }
}
