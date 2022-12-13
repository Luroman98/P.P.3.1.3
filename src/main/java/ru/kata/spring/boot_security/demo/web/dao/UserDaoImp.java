package ru.kata.spring.boot_security.demo.web.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void add(User user) {
        entityManager.persist(user);
    }
    @Transactional
    public User getUser(Long id) {
        return entityManager.find(User.class, id);
    }
    @Transactional
    public List<User> listUsers() {
        return entityManager.createQuery("select w from User w", User.class).getResultList();
    }
    @Transactional
    public void update(Long id, User updateUser) {
        User userToBeUpdated = getUser(id);
        userToBeUpdated.setUsername(updateUser.getUsername());
        userToBeUpdated.setLastName(updateUser.getLastName());
        userToBeUpdated.setPassword(updateUser.getPassword());

    }
    @Transactional
    public void deleteUser(Long id) {
        System.out.println(getUser(id));
        entityManager.remove(getUser(id));

    }
    @Transactional
    public User getUserByUserName(String name) {
        return entityManager.createQuery("select w from User w where w.username=:name", User.class).setParameter("name", name).getSingleResult();
    }
}
