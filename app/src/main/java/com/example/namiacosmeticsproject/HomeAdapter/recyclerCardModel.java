package com.example.namiacosmeticsproject.HomeAdapter;

public class recyclerCardModel {
    private int productImg;
    private String productTitle;
    private String productPrice;
    private String productCategory;

    public int getProductImg() {
        return productImg;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public recyclerCardModel(int productImg, String productTitle, String productPrice, String productCategory) {
        this.productImg = productImg;
        this.productTitle = productTitle;
        this.productPrice = productPrice;
        this.productCategory = productCategory;
    }
}
