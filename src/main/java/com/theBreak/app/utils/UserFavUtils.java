package com.theBreak.app.utils;

import com.theBreak.app.model.userFavorites.UserFavorites;

import java.util.Arrays;
import java.util.List;

public class UserFavUtils {

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

    public List<String> favoriteBowlToList(String favBowl) {
        List<String> configuredBowl = null;
        if (favBowl != null) {
            configuredBowl = Arrays.asList(favBowl.split(","));
        }
        return configuredBowl;
    }
}
