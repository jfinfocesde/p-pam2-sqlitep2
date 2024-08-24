package com.example.sqlitep2.util;

public class Constants {
    public static final String DATABASE_NAME = "myapp.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_USER = "users";
    public static final String TABLE_PRODUCT = "products";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PRICE = "price";

    public static final String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_USERNAME + " TEXT,"
            + COLUMN_EMAIL + " TEXT"
            + ")";

    public static final String CREATE_PRODUCT_TABLE = "CREATE TABLE " + TABLE_PRODUCT + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_NAME + " TEXT,"
            + COLUMN_PRICE + " REAL"
            + ")";
}
