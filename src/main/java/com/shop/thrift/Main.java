package com.shop.thrift;


import com.mysql.fabric.jdbc.FabricMySQLDriver;
import com.shop.thrift.Entity.Category;
import com.shop.thrift.Entity.Subcategory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/shop";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "barsik";


    public static List<Category> catList;
    public static List<Subcategory> subCatList;


    public static void main(String[] args) {

        //private List<Category> categoryList = new ArrayList<>();


        try {

            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);




        } catch (SQLException e) {
            System.out.println("Не загрузило драйвер менеджера");
        }

        try(Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD); Statement statement = connection.createStatement()){
           ResultSet res = statement.executeQuery("SELECT * FROM SHOP.category");

           catList = new ArrayList<Category>();

           System.out.println();
            while (res.next()) {
                Category tempCat = new Category();

                tempCat.setName(res.getString("category_name"));
                //System.out.println(res.getString("category_name"));
                tempCat.setId(res.getInt("id"));
                //System.out.println(res.getInt("id"));
                catList.add(tempCat);


                //String str = res.getString("category_name") + ":" + res.getString(1);
                //System.out.println("Category:" + str);
            }

            System.out.println("Size of catlist:" + catList.size());

            res.close();
            statement.close();
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        try(Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD); Statement statement = connection.createStatement()){
            ResultSet res = statement.executeQuery("SELECT * FROM SHOP.subcategory");

            subCatList = new ArrayList<Subcategory>();

           // System.out.println();
            while (res.next()) {
                Subcategory tempSubCat = new Subcategory();

                tempSubCat.setName(res.getString("subcategory_name"));
                tempSubCat.setId(res.getInt("id"));
                tempSubCat.setCategoryId(res.getInt("category_id"));

                subCatList.add(tempSubCat);

                //String str = res.getString("subcategory_name") + ":" + res.getString(1);
                //System.out.println("Subcategory:" + str);
            }

            System.out.println("Size of subCatlist: " + subCatList.size());

            res.close();
            statement.close();
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        for(int i  = 0; i < catList.size(); i++){
            System.out.println(catList.get(i).getName() + ":");
            for(int j = 0;j<subCatList.size(); j++){
                if(subCatList.get(j).getCategoryId() == i+1){
                   System.out.println("---"+ subCatList.get(j).getName());
                }

            }

        }


        //part 2 ggg

        final EntityManagerFactory factory = Persistence
                .createEntityManagerFactory("primary");
        final EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        //block 1
        List<Subcategory> subCategoryTEST1 = em.createQuery("SELECT sc FROM Subcategory sc WHERE sc.name LIKE :subcatid", Subcategory.class)
                .setParameter("subcatid", "Л%")
                .getResultList();
        //System.out.println(subCategoryTEST1.get(0).getName() + "   !!!!   block 1 test");

        //block 2
        Category categoryTEST2 = em.createQuery("SELECT DISTINCT c FROM Category c LEFT JOIN FETCH c.subcategories WHERE c.id = :id", Category.class)
                .setParameter("id",1)
                .getSingleResult();

        System.out.println(subCategoryTEST1.get(0).getName() + "   !!!!   block 1 test");
        System.out.println(categoryTEST2.getName() + "   !!!!   block 2 test");
        categoryTEST2.getSubCatList();

        em.getTransaction().commit();
        em.close();
        factory.close();



    }
}