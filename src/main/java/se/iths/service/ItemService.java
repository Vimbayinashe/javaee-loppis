package se.iths.service;

import se.iths.entity.Item;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

@Transactional  //this is a class that communicates with the database
public class ItemService {

    @PersistenceContext
    EntityManager entityManager;    //sköter interaktion med databasen

    public void createItem(Item item) {     //item hämtas från REST api
        entityManager.persist(item);
    }

    public void updateItem(Item item) {
        entityManager.merge(item);
    }

    public Item findItemById(Long id) {
        return entityManager.find(Item.class, id);
    }

    public List<Item> getAllItems() {
        return entityManager.createQuery("SELECT i FROM Item i", Item.class).getResultList();
    }

    public void deleteItem(Long id) {
        Item foundItem = entityManager.find(Item.class, id);
        entityManager.remove(foundItem);
    }

}
