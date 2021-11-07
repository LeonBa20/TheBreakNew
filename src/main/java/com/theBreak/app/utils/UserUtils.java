package com.theBreak.app.utils;

import com.theBreak.app.model.user.User;

import java.sql.Timestamp;
import java.util.Date;
/**
 * Klasse mit Tools für die Verarbeitung der Nutzerdaten.
 */
public class UserUtils {
    /**
     * Gibt einen Timestamp zurück. Diese Methode wird bei Nutzererstellung aufgerufen und speichert die Zeit in der Datenbank.
     */
    public String getTimestamp(){
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        Date date = ts;
        return date.toString();
    }
    /**
     * Ausgelagerte Methode zur Prüfung der Übereinstimmung der Passwörter des Nutzers.
     */
    public boolean checkPassword(User referenceUser, User actualUser){
        if (referenceUser.getPassword().equals(actualUser.getPassword())){
            return true;
        } else {
            return false;
        }
    }
}
