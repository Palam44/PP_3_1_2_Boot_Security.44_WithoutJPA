package ru.kata.spring.boot_security.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<User> getAllUsers() {
        String JPAql = "SELECT user FROM User user";
        return entityManager.createQuery(JPAql, User.class).getResultList();
    }

    @Override
    public void create(User user) {
        entityManager.persist(user);
    }

    @Override
    public User getUserById(Long id) {

        return entityManager.find(User.class, id);
    }

    @Override
    public void delete(Long id) {
        String JPAql = "DELETE FROM User user WHERE user.id = :id";
        entityManager.createQuery(JPAql).setParameter("id", id).executeUpdate();
    }

    @Override
    public void update(User user) {

        entityManager.merge(user);
    }

    @Override
    public User findByUsername(String username) {
        String JPAql = "SELECT user FROM User user WHERE user.username = :username";
        return entityManager
                .createQuery(JPAql, User.class)
                .setParameter("username", username)
                .getSingleResult();
//                .query.setMaxResults(1).uniqueResult()
    }
}
