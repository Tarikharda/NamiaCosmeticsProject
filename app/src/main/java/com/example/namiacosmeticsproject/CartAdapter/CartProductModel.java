package com.example.namiacosmeticsproject.CartAdapter;

public class CartProductModel {
    private int cartProductImg;
    private String cartProductTitle;
    private String cartProductPrice;
    private String cartProductCategory;

    public int getCartProductImg() {
        return cartProductImg;
    }

    public String getCartProductTitle() {
        return cartProductTitle;
    }

    public String getCartProductPrice() {
        return cartProductPrice;
    }

    public String getCartProductCategory() {
        return cartProductCategory;
    }

    public CartProductModel(int cartProductImg, String cartProductTitle, String cartProductPrice, String cartProductCategory) {
        this.cartProductImg = cartProductImg;
        this.cartProductTitle = cartProductTitle;
        this.cartProductPrice = cartProductPrice;
        this.cartProductCategory = cartProductCategory;
    }
}
