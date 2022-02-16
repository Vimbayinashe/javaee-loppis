package se.iths.service;

import se.iths.entity.Item;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional  //this is a class that comms with the database
public class ItemService {

    @PersistenceContext
    EntityManager entityManager;    //sköter interaktion med databasen

    public Item createItem(Item item) {     //item hämtas från REST api
        entityManager.persist(item);
        return item;
    }

    public Item updateItem(Item item) {
        entityManager.merge(item);
        return item;
    }

    public Item findItemById(Long id) {
        return entityManager.find(Item.class, id);
    }

    public List<Item> getAllItems() {
        return entityManager.createQuery("SELECT i FROM Item i", Item.class).getResultList();
    }

}
