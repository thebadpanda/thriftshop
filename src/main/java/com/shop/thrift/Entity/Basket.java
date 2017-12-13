package com.shop.thrift.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="basket")
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private Users users;

    @OneToMany(mappedBy = "basket")
    private List<Users> users = new ArrayList<>();

    @ManyToMany
    private List<Item> item = new ArrayList<>();

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "item_id")
//    private Item item;

    @Column(name = "_count")
    private int count;

    public void add(Item e) {
        item.add(e);
        count = item.size();
    }

    public void remove(Item e) {
        item.remove(e);
        count = item.size();
    }

    public void removeAll(List<Item> item) {
        item.removeAll(item);
        count = item.size();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }

    public List<Item> getItem() {
        return item;
    }

    public void setItem(List<Item> item) {
        this.item = item;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}