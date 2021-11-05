package com.theBreak.app.dataManager;

import com.theBreak.app.model.userFavorites.UserFavorites;

import java.util.Collection;

public interface UserFavManager {
    void addUserFav (UserFavorites favorite);
    void deleteUserFav (int favoriteId);
    Collection<UserFavorites> getAllFavs(String userMailAddress);
}
