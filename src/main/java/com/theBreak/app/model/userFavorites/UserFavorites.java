package com.theBreak.app.model.userFavorites;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
/**
 * Klasse für die Nutzerfavoriten.
 */
@Schema(description = "Nutzerfavorit")
public class UserFavorites {
    /**
     * favoriteId = ID des Favoriten-Datensatzes
     * userMailAddress = Identifikation des Nutzers
     * favoriteArticle = normaler Artikel, wie bspw. ein Kaffee
     * favoriteBowl = Konfigurierte Bowl als Liste. Liste besteht aus ihren Zutaten.
     */
    @Schema(description = "Zahl *Wird automatisiert im Backend vergeben")
    private int favoriteId;
    @Schema(description = "max@mustermann.de")
    private String userMailAddress;
    @Schema(description = "Kaffee groß")
    private String favoriteArticle;
    @Schema(description = "Haferflocken, Joghurt, Erdbeere, Banane, Schokolade")
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
