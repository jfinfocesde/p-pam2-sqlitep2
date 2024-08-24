package com.example.sqlitep2.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseManager {
    private static DatabaseManager instance;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    private DatabaseManager(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public static synchronized DatabaseManager getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseManager(context.getApplicationContext());
        }
        return instance;
    }

    public SQLiteDatabase openDatabase() {
        if (database == null || !database.isOpen()) {
            database = databaseHelper.getWritableDatabase();
        }
        return database;
    }

    public void closeDatabase() {
        if (database != null && database.isOpen()) {
            database.close();
        }
    }
}
