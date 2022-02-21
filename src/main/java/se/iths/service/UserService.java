package se.iths.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import se.iths.entity.Item;
import se.iths.entity.User;

import java.util.List;

@Transactional
public class UserService {

    @PersistenceContext
    EntityManager entityManager;

    public User findUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    public User createUser(User user) {
        user.addItem(new Item("sofa", "furniture", 1, 2000.00));
        user.addItem(new Item("socks", "clothing", 10, 50.00));

        entityManager.persist(user);
        return user;
    }

    public List<User> getAllUsers() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

}
