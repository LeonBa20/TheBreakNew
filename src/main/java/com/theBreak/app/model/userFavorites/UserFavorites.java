package com.theBreak.app.model.userFavorites;

import java.util.List;

public class UserFavorites {
    private int favoriteId;
    private String userMailAddress;
    private String favoriteArticle;
    private List<String> favoriteBowl;

    public UserFavorites(){}

    public UserFavorites(int favoriteId, String userMailAddress, String favoriteArticle, List<String> favoriteBowl) {
        this.favoriteId = favoriteId;
        this.userMailAddress = userMailAddress;
        this.favoriteArticle = favoriteArticle;
        this.favoriteBowl = favoriteBowl;
    }

    public int getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(int favoriteId) {
        this.favoriteId = favoriteId;
    }

    public String getUserMailAddress() {
        return userMailAddress;
    }

    public void setUserMailAddress(String userMailAddress) {
        this.userMailAddress = userMailAddress;
    }

    public String getFavoriteArticle() {
        return favoriteArticle;
    }

    public void setFavoriteArticle(String favoriteArticle) {
        this.favoriteArticle = favoriteArticle;
    }

    public List<String> getFavoriteBowl() {
        return favoriteBowl;
    }

    public void setFavoriteBowl(List<String> favoriteBowl) {
        this.favoriteBowl = favoriteBowl;
    }
}
