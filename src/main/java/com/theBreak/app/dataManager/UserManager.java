package com.theBreak.app.dataManager;

import com.theBreak.app.model.user.User;

public interface UserManager {
    public void addUser(User user);
    public void editUser(User user);
    public User getUser(String userMailAddress);
    public void login(User user);
    public void logout(User user);
    public boolean checkIfUserExists(User user);
    public boolean userLoggedIn(User user);
    public boolean checkIfPasswordIsCorrect(User user);
}
