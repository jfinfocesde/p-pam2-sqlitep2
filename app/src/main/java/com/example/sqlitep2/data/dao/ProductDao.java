package com.example.sqlitep2.data.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sqlitep2.data.model.Product;
import com.example.sqlitep2.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    private SQLiteDatabase database;

    public ProductDao(SQLiteDatabase database) {
        this.database = database;
    }

    // Create
    public long insert(Product product) {
        ContentValues values = new ContentValues();
        values.put(Constants.COLUMN_NAME, product.getName());
        values.put(Constants.COLUMN_PRICE, product.getPrice());
        return database.insert(Constants.TABLE_PRODUCT, null, values);
    }

    // Read
    public Product getProductById(long id) {
        Cursor cursor = database.query(Constants.TABLE_PRODUCT, null,
                Constants.COLUMN_ID + "=?", new String[]{String.valueOf(id)},
                null, null, null);
        Product product = null;
        if (cursor != null && cursor.moveToFirst()) {
            product = new Product();
            int idIndex = cursor.getColumnIndexOrThrow(Constants.COLUMN_ID);
            int nameIndex = cursor.getColumnIndexOrThrow(Constants.COLUMN_NAME);
            int priceIndex = cursor.getColumnIndexOrThrow(Constants.COLUMN_PRICE);

            product.setId(cursor.getLong(idIndex));
            product.setName(cursor.getString(nameIndex));
            product.setPrice(cursor.getDouble(priceIndex));
            cursor.close();
        }
        return product;
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        Cursor cursor = database.query(Constants.TABLE_PRODUCT, null, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndexOrThrow(Constants.COLUMN_ID);
            int nameIndex = cursor.getColumnIndexOrThrow(Constants.COLUMN_NAME);
            int priceIndex = cursor.getColumnIndexOrThrow(Constants.COLUMN_PRICE);

            do {
                Product product = new Product();
                product.setId(cursor.getLong(idIndex));
                product.setName(cursor.getString(nameIndex));
                product.setPrice(cursor.getDouble(priceIndex));
                products.add(product);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return products;
    }

    // Update
    public int update(Product product) {
        ContentValues values = new ContentValues();
        values.put(Constants.COLUMN_NAME, product.getName());
        values.put(Constants.COLUMN_PRICE, product.getPrice());
        return database.update(Constants.TABLE_PRODUCT, values,
                Constants.COLUMN_ID + "=?", new String[]{String.valueOf(product.getId())});
    }

    // Delete
    public int delete(long id) {
        return database.delete(Constants.TABLE_PRODUCT,
                Constants.COLUMN_ID + "=?", new String[]{String.valueOf(id)});
    }

    public void deleteAllProducts() {
        database.delete(Constants.TABLE_PRODUCT, null, null);
    }
}
