package com.example.namiacosmeticsproject.Classes;

public class Profile {
    private String userId;
    private String userName;
    private String userEmail;
    private String userPassword;
    private String userImgUrl;
    private String Country;
    private String CPostal;
    private String phone;
    private String city;
    private String address;


    //region getters and setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserImgUrl() {
        return userImgUrl;
    }

    public void setUserImgUrl(String userImgUrl) {
        this.userImgUrl = userImgUrl;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getCPostal() {
        return CPostal;
    }

    public void setCPostal(String CPostal) {
        this.CPostal = CPostal;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    //endregion

    public Profile(String userId,
                   String userName,
                   String userEmail,
                   String userPassword,
                   String userImgUrl,
                   String CPostal,
                   String phone,
                   String city,
                   String address
    ) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userImgUrl = userImgUrl;
        this.CPostal = CPostal;
        this.phone = phone;
        this.city = city;
        this.address = address;
    }
}
