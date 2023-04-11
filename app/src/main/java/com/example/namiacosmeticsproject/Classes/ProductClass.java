package com.example.namiacosmeticsproject.Classes;

public class ProductClass {
    private String ProductId;
    private String ProductName;
    private String ProductDes;
    private float ProductPrice;
    private String ProductImgUrl;
    private String ProductCategory;

    public int getProductCount() {
        return ProductCount;
    }

    private int ProductCount;


    public String getProductId() {
        return ProductId;
    }

    public String getProductDes() {
        return ProductDes;
    }

    public String getProductImgUrl() {
        return ProductImgUrl;
    }

    public String getProductName() {
        return ProductName;
    }

    public float getProductPrice() {
        return ProductPrice;
    }

    public String getProductCategory() {
        return ProductCategory;
    }

    public ProductClass(String ProductId, String ProductName, String ProductDes, float ProductPrice, String ProductImgUrl, String ProductCategory) {
        this.ProductId =ProductId;
        this.ProductName = ProductName;
        this.ProductDes = ProductDes;
        this.ProductPrice = ProductPrice;
        this.ProductImgUrl = ProductImgUrl;
        this.ProductCategory = ProductCategory;
    }
    public ProductClass(String ProductId, String ProductName, String ProductDes, float ProductPrice, String ProductImgUrl, String ProductCategory,int ProductCount) {
        this.ProductId =ProductId;
        this.ProductName = ProductName;
        this.ProductDes = ProductDes;
        this.ProductPrice = ProductPrice;
        this.ProductImgUrl = ProductImgUrl;
        this.ProductCategory = ProductCategory;
        this.ProductCount = ProductCount;

    }
}
