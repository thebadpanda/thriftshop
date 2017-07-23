package com.shop.thrift.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="category", indexes=@Index(columnList = "category_name"))

public class Category {
    @Id
    @GeneratedValue (strategy= GenerationType.IDENTITY)
    private int id;

    @Column (name="category_name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "category")
    private List<Subcategory> subcategories = new ArrayList<>();


    //test method for debug
    public void getSubCatList(){
        if(subcategories.size() != 0) {
            for (int i = 0; i < subcategories.size(); i++) {
                System.out.println(subcategories.get(i).getName());
            }
        }else{
            System.out.println("Subcatlist is empty :(");
        }
    }

    public Subcategory getSubcategory(int index){
        return subcategories.get(index);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Category))
            return false;
        Category other = (Category) obj;
        if (id != other.id)
            return false;
        return true;
    }

}
