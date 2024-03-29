package com.example.namiacosmeticsproject.Data;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.namiacosmeticsproject.Classes.ProductClass;
import com.example.namiacosmeticsproject.Classes.Profile;

import java.util.ArrayList;

import com.example.namiacosmeticsproject.Classes.favoriteProducts;

public class LocalDataBase extends SQLiteOpenHelper {
    private static final String DB_NAME = "db_Products";
    private static final String PRODUCT_ID = "productId";
    private static final String PRODUCT_NAME = "productName";
    private static final String PRODUCT_DES = "productDes";
    private static final String PRODUCT_PRICE = "productPrice";
    private static final String PRODUCT_CATEGORY = "productCategory";
    private static final String PRODUCT_IMG_URL = "productImgUrl";
    private static final String PRODUCT_COUNT = "productCount";
    private static final String PRODUCT_TABLE_NAME = "products";

    public LocalDataBase(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + PRODUCT_TABLE_NAME + "(" +
                PRODUCT_ID + " INTEGER PRIMARY KEY," +
                PRODUCT_NAME + " TEXT," +
                PRODUCT_DES + " TEXT," +
                PRODUCT_PRICE + " FLOAT," +
                PRODUCT_CATEGORY + " TEXT," +
                PRODUCT_IMG_URL + " TEXT," +
                PRODUCT_COUNT + " INTEGER)");

        db.execSQL("CREATE TABLE profileInfo (" +
                "userId INTEGER PRIMARY KEY NOT NULL," +
                "userName TEXT," +
                "userEmail TEXT," +
                "userPassword TEXT," +
                "userImgUrl TEXT," +
                "Country TEXT," +
                "CPostal TEXT," +
                "phone TEXT," +
                "city TEXT," +
                "Address TEXT)");

        db.execSQL("CREATE TABLE favoriteProduct (" +
                "userId INTEGER  NOT NULL," +
                "productId INTEGER  NOT NULL," +
                "isFav TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PRODUCT_TABLE_NAME + "");
        onCreate(db);
    }

    public void insertProduct(ProductClass productClass) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PRODUCT_ID, productClass.getProductId());
        values.put(PRODUCT_NAME, productClass.getProductName());
        values.put(PRODUCT_DES, productClass.getProductDes());
        values.put(PRODUCT_PRICE, productClass.getProductPrice());
        values.put(PRODUCT_CATEGORY, productClass.getProductCategory());
        values.put(PRODUCT_IMG_URL, productClass.getProductImgUrl());
        values.put(PRODUCT_COUNT, productClass.getProductCount());
        db.insert(PRODUCT_TABLE_NAME, null, values);
    }

    public void insertInfoProfile(Profile profile) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("userId", profile.getUserId());
        values.put("userName", profile.getUserName());
        values.put("userEmail", profile.getUserEmail());
        values.put("userPassword", profile.getUserPassword());
        values.put("userImgUrl", profile.getUserImgUrl());
        values.put("CPostal", profile.getCPostal());
        values.put("phone", profile.getPhone());
        values.put("city", profile.getCity());
        values.put("address", profile.getAddress());
        db.insert("profileInfo", null, values);
    }

    public long countProduct() {
        SQLiteDatabase db = getReadableDatabase();
        return DatabaseUtils.queryNumEntries(db, PRODUCT_TABLE_NAME);
    }

    public ArrayList<ProductClass> getAllProducts() {
        ArrayList<ProductClass> ProductList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + PRODUCT_TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String productId = cursor.getString(cursor.getColumnIndex(PRODUCT_ID));
                @SuppressLint("Range") String productName = cursor.getString(cursor.getColumnIndex(PRODUCT_NAME));
                @SuppressLint("Range") String productDes = cursor.getString(cursor.getColumnIndex(PRODUCT_DES));
                @SuppressLint("Range") float productPrice = cursor.getFloat(cursor.getColumnIndex(PRODUCT_PRICE));
                @SuppressLint("Range") String productCategory = cursor.getString(cursor.getColumnIndex(PRODUCT_CATEGORY));
                @SuppressLint("Range") String productImgUrl = cursor.getString(cursor.getColumnIndex(PRODUCT_IMG_URL));
                @SuppressLint("Range") String productCount = cursor.getString(cursor.getColumnIndex(PRODUCT_COUNT));
                ProductClass product = new ProductClass(productId, productName, productDes, productPrice, productImgUrl, productCategory, Integer.parseInt(productCount));
                ProductList.add(product);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return ProductList;
    }

    public long getAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("SELECT * FROM " + PRODUCT_TABLE_NAME, null);
        return cursor.getCount();
    }

    @SuppressLint("Range")
    public int plusProductCounter(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        int newCount = 0;
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("SELECT " + PRODUCT_COUNT + " FROM " + PRODUCT_TABLE_NAME + " WHERE " + PRODUCT_ID + " == " + id, null);
        if (cursor.moveToFirst()) {
            newCount = Integer.parseInt(cursor.getString(cursor.getColumnIndex(PRODUCT_COUNT))) + 1;
            db = this.getWritableDatabase();
            db.execSQL("UPDATE " + PRODUCT_TABLE_NAME + " SET " + PRODUCT_COUNT + "=" + newCount + " WHERE " + PRODUCT_ID + " == " + id);
        }
        return newCount;
    }

    @SuppressLint("Range")
    public int minusProductCounter(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        int newCount = 0;
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("SELECT " + PRODUCT_COUNT + " FROM " + PRODUCT_TABLE_NAME + " WHERE " + PRODUCT_ID + " == " + id, null);
        if (cursor.moveToFirst()) {
            newCount = Integer.parseInt(cursor.getString(cursor.getColumnIndex(PRODUCT_COUNT))) - 1;
            if (newCount <= 0) {
                deleteProduct(id);
            } else {
                db = this.getWritableDatabase();
                db.execSQL("UPDATE " + PRODUCT_TABLE_NAME + " SET " + PRODUCT_COUNT + "=" + newCount + " WHERE " + PRODUCT_ID + " == " + id);
            }
        }
        return newCount;
    }

    public void deleteProduct(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("DELETE FROM " + PRODUCT_TABLE_NAME + " WHERE " + PRODUCT_ID + " == " + id);
    }

    @SuppressLint("Range")
    public float sumPrices() {
        SQLiteDatabase db = this.getReadableDatabase();
        float sumOfPrices = 0;
//        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("SELECT SUM( " + PRODUCT_PRICE + " ) as sum  FROM " + PRODUCT_TABLE_NAME, null);
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("SELECT " + PRODUCT_PRICE + " , " + PRODUCT_COUNT + " FROM " + PRODUCT_TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                float productPrice = cursor.getFloat(cursor.getColumnIndex(PRODUCT_PRICE));
                int productCount = Integer.parseInt(cursor.getString(cursor.getColumnIndex(PRODUCT_COUNT)));
                sumOfPrices += productPrice * productCount;
            } while (cursor.moveToNext());
//            sumOfPrices = cursor.getFloat(cursor.getColumnIndex("sum"));
        }
        return sumOfPrices;
    }

    public int allproductscounter() {
        SQLiteDatabase db = this.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("SELECT DISTINCT *  FROM " + PRODUCT_TABLE_NAME, null);
        return cursor.getCount();
    }

    public void rememberFavorite(boolean flag, int userId, int productId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("userId", userId);
        values.put("productId", productId);
        values.put("favorite", flag);
        db.insert("favoriteProduct", null, values);
    }

    public ArrayList<favoriteProducts> getIsFavArray() {
        ArrayList<favoriteProducts> arraylist = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM favoriteProduct", null);
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int userId = cursor.getInt(cursor.getColumnIndex("userId"));
                @SuppressLint("Range") int productId = cursor.getInt(cursor.getColumnIndex("productId"));
                @SuppressLint("Range") String productName = cursor.getString(cursor.getColumnIndex("productName"));
                @SuppressLint("Range") String isFav = cursor.getString(cursor.getColumnIndex("isFav"));
                favoriteProducts favoriteProducts = new favoriteProducts(userId, productId, productName, isFav);
                arraylist.add(favoriteProducts);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return arraylist;
    }

    public boolean removeFavorite(int idProduct) {
        SQLiteDatabase db = this.getReadableDatabase();
        for (favoriteProducts fv : getIsFavArray()) {
            if (fv.getProductId() == idProduct) {
                db.execSQL("DELETE FROM favoriteProduct WHERE id=" + idProduct);
                return true;
            }
        }
        return false;
    }
}
