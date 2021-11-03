package com.theBreak.app.utils;

import com.theBreak.app.model.user.User;

import java.sql.Timestamp;
import java.util.Date;

public class UserUtils {
    public String getTimestamp(){
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        Date date = ts;
        return date.toString();
    }

    public boolean checkPassword(User referenceUser, User actualUser){
        if (referenceUser.getPassword().equals(actualUser.getPassword())){
            return true;
        } else {
            return false;
        }
    }
}
