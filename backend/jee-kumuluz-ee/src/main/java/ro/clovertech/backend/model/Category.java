package ro.clovertech.backend.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Category {
    @Id
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "categories")
    private Collection<Item> items;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private Collection<Item> children;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Item> getItems() {
        return items;
    }

    public void setItems(Collection<Item> items) {
        this.items = items;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public Collection<Item> getChildren() {
        return children;
    }

    public void setChildren(Collection<Item> children) {
        this.children = children;
    }
}
