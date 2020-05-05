package com.example.myapplication;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProductDao {
    // hoort bij de version 1.0
    /*@Query("SELECT * FROM Product")
    List<Product> getAllProducts();*/

    // hoort bij de version 1.1 met viewnotes
    @Query("SELECT * FROM Product ORDER BY name DESC")
    LiveData<List<Product>> getAllProducts();

    @Query("SELECT * FROM Product WHERE name like :name")
    Product findByName(String name);

    @Insert
    void insertProduct(Product p);

    @Update
    void updateProduct(Product p);

    @Delete
    void deleteProduct(Product p);

    @Query("DELETE FROM Product" )
    void deleteAllProduct();
}
