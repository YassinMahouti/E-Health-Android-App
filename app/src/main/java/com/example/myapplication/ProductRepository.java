package com.example.myapplication;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ProductRepository {
    private ProductDao productDao;
    private LiveData<List<Product>> allproducts;

    public ProductRepository(Application application){
        NotesDatabase database = NotesDatabase.getInstance(application);
        productDao = database.ProductDao();
        allproducts = productDao.getAllProducts();
    }

    public void insertProduct(Product p){

    }

    public void updateProduct(Product p){

    }

    public void deleteProduct(Product p){

    }

    public void deleteAllProduct(Product p){

    }

    public LiveData<List<Product>> getAllproducts(){
        return allproducts;
    }

    private static class InsertProductAsyncTask extends AsyncTask<Product, Void, Void>{
        private ProductDao productDao;

        @Override
        protected Void doInBackground(Product... products){
            return null;
        }
    }

}
