package com.shop.thrift.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="category")

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
            System.out.println("Sub cat list is empty :(");
        }
    }

    public Subcategory getSubCategory(int index){
        return subcategories.get(index);
    }

}
