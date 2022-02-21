package se.iths.entity;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;

import java.util.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;

    @OneToMany (mappedBy = "user", cascade = CascadeType.ALL)      // mapped by the field "user" in the Item entity
    private List<Item> items = new ArrayList<>();

    @JsonbTransient     // resolves problem with recursive loop in JSON response -> items are not shown in a "Get item" response
    public List<Item> getItems() {
        return Collections.unmodifiableList(items);
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public Long getId() {
        return id;
    }

    public void addItem(Item item) {
        items.add(item);
        item.setUser(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        return Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return username != null ? username.hashCode() : 0;
    }
}
