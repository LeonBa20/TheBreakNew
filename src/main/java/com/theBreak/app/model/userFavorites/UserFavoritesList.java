package com.theBreak.app.model.userFavorites;

import com.theBreak.app.dataManager.UserFavManager;
import com.theBreak.app.dataManagerImpl.PostgresUserFavManagerImpl;

import java.util.Collection;

public class UserFavoritesList {
    private String userMailAddress;
    private Collection<UserFavorites> favorites;
    UserFavManager userFavManager = PostgresUserFavManagerImpl.getPostgresUserFavManagerImpl();

    public UserFavoritesList() {
    }

    public UserFavoritesList(String userMailAddress) {
        this.userMailAddress = userMailAddress;
    }

    public String getUserMailAddress() {
        return this.userMailAddress;
    }

    public void setUserMailAddress(String userMailAddress) {
        this.userMailAddress = userMailAddress;
    }

    public Collection<UserFavorites> getUserFavs() {
        return favorites;
    }

    public void setArticleFavs() {
        favorites = userFavManager.getAllFavs(this.userMailAddress);
    }
}
