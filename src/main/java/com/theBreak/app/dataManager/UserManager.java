package com.theBreak.app.dataManager;

import com.theBreak.app.model.user.User;

public interface UserManager {
    /**
     * Legt einen neuen Benutzer an.
     */
    public void addUser(User user);

    /**
     * Verändert die Nutzerdaten eines Nutzers.
     */
    public void editUser(User user);

    /**
     * Zeigt die Nutzerdaten eines Nutzers.
     */
    public User getUser(String userMailAddress);

    /**
     * Löscht die Daten eines Nutzers.
     */
    public void deleteUser(String userMailAddress);

    /**
     * Logged den Nutzer ein.
     */
    public void login(User user);

    /**
     * Logged den Nutzer aus.
     */
    public void logout(User user);

    /**
     * Prüft, ob die Nutzerdaten bereits vorhanden sind.
     */
    public boolean checkIfUserExists(User user);

    /**
     * Prüft, ob ein Nutzer eingeloggt ist.
     */
    public boolean userLoggedIn(User user);

    /**
     * Prüft das Passwort des entgegengenommenen Nutzers mit dem der Datenbank.
     */
    public boolean checkIfPasswordIsCorrect(User user);
}
