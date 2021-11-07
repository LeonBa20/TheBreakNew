package com.theBreak.app.dataManager;

import com.theBreak.app.model.userFavorites.UserFavorites;

import java.util.Collection;

public interface UserFavManager {
    /**
     * Fügt einen regulären Artikel als neuen Favoriten des Nutzers hinzu.
     */
    void addUserArticleFav (UserFavorites favorite);

    /**
     * Fügt einen konfigurierten Artikel als neuen Favoriten des Nutzers hinzu.
     */
    void addUserConfigFav (UserFavorites favorite);

    /**
     * Löscht einen Favoriten eines Benutzers.
     */
    void deleteUserFav (int favoriteId);

    /**
     * Zeigt alle regulären Artikel aus der Favoritenliste des Nutzers an.
     */
    Collection<UserFavorites> getAllArticleFavs(String userMailAddress);

    /**
     * Zeigt alle konfigurierten Bowls aus der Favoritenliste des Nutzers an.
     */
    Collection<UserFavorites> getAllBowlFavs(String userMailAddress);
}
