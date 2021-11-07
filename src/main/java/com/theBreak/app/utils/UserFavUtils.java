package com.theBreak.app.utils;

import com.theBreak.app.model.userFavorites.UserFavorites;

import java.util.Arrays;
import java.util.List;
/**
 * Klasse mit Tools zur Verarbeitung der User-Favoritendaten.
 */
public class UserFavUtils {
    /**
     * Methode, um die Liste der Zutaten einer konfigurierten Bowl in einen String umzuwandeln.
     * Konvertierung wird für die Datenbankspeicherung vorgenommen.
     */
    public String favoriteBowlToString(UserFavorites favorites){
        String ingredientList = "";
        if (favorites.getFavoriteBowl() != null) {
            for (int i = 0; i < favorites.getFavoriteBowl().size(); i++) {
                if (i < favorites.getFavoriteBowl().size() - 1) {
                    ingredientList += favorites.getFavoriteBowl().get(i) + ", ";
                } else {
                    ingredientList += favorites.getFavoriteBowl().get(i);
                }
            }
        }
        return ingredientList;
    }
    /**
     * Methode, um den String der Zutaten einer konfigurierten Bowl in eine Liste umzuwandeln.
     * Konvertierung wird bei der Erstellung des UserFavorites-Objekts für die Ausgabe über
     * einen GET-Call über die REST Schnittstelle vorgenommen.
     */
    public List<String> favoriteBowlToList(String favBowl) {
        List<String> configuredBowl = null;
        if (favBowl != null) {
            configuredBowl = Arrays.asList(favBowl.split(","));
        }
        return configuredBowl;
    }

}
