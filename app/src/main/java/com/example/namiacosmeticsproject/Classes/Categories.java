package com.example.namiacosmeticsproject.Classes;

public class Categories {

    private String categoryImgUrl;
    private String categoryTitle;

    public Categories(String categoryImgUrl, String categoryTitle) {
        this.categoryImgUrl = categoryImgUrl;
        this.categoryTitle = categoryTitle;
    }

    public String getCategoryImgUrl() {
        return categoryImgUrl;
    }
    public void setCategoryImgUrl(String categoryImgUrl) {
        this.categoryImgUrl = categoryImgUrl;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }
    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

}
