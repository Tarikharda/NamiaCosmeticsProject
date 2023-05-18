package com.example.namiacosmeticsproject.Classes;

public class favoriteProducts {

    int userId;
    int productId;
    String isFav;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }


    public String getIsFav() {
        return isFav;
    }

    public void setIsFav(String isFav) {
        this.isFav = isFav;
    }

    public favoriteProducts(int userId, int productId, String productName, String isFav) {
        this.userId = userId;
        this.productId = productId;
        this.isFav = isFav;
    }
}
