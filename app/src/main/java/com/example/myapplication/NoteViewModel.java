package com.example.myapplication;
// hoort bij de version 1.1 met viewnotes

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    private final LiveData<List<Product>> products;
    private NotesDatabase database;

    public NoteViewModel(Application application){
        super(application);
        database = NotesDatabase.getInstance(application);
        products = database.ProductDao().getAllProducts();
    }

    public LiveData<List<Product>> getProducts(){
        return products;
    }

    public void insertProduct(final Product p){
        NotesDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                database.ProductDao().insertProduct(p);
            }
        });
    }
}
