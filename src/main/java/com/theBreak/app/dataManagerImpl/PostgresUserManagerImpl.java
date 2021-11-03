package com.theBreak.app.dataManagerImpl;

import com.theBreak.app.dataManager.UserManager;
import com.theBreak.app.model.user.User;
import com.theBreak.app.utils.UserUtils;
import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgresUserManagerImpl implements UserManager {

    UserUtils uUtils = new UserUtils();

    String databaseURL = "jdbc:postgresql://ec2-54-228-162-209.eu-west-1.compute.amazonaws.com:5432/d96l8d28b825i5";
    String username = "ctqkmudflbnulg";
    String password = "a01798363ea3e4130085e31ed8a608c66359c57ecb85e3a90f478ac09dd713e2";
    BasicDataSource basicDataSource;

    static PostgresUserManagerImpl postgresUserManager = null;

    private PostgresUserManagerImpl() {
        basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(databaseURL);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);
    }

    static public PostgresUserManagerImpl getPostgresUserManagerImpl() {
        if (postgresUserManager == null)
            postgresUserManager = new PostgresUserManagerImpl();
        return postgresUserManager;
    }

    public void createUserTable() {

        Statement stmt = null;
        Connection connection = null;

        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();

            String createTable = "CREATE TABLE users (" +
                    "userMailAddress varchar(100) PRIMARY KEY, " +
                    "loggedIn boolean, " +
                    "firstName varchar(100) NOT NULL, " +
                    "lastName varchar(100) NOT NULL, " +
                    "streetandNr varchar(150) NOT NULL, " +
                    "city varchar(100) NOT NULL, " +
                    "postcode varchar(20) NOT NULL, " +
                    "password varchar(10) NOT NULL, " +
                    "userCreationDate timestamp NOT NULL)";

            stmt.executeUpdate(createTable);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable(){
        Statement stmt = null;
        Connection connection = null;

        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();

            String dropTable = "DROP TABLE users";
            stmt.executeUpdate(dropTable);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkIfUserExists(User user) {
        boolean userExists = false;
        Statement stmt = null;
        Connection connection = null;

        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE userMailAddress='"+user.getUserMailAddress()+"'");
            if (rs.next()){
                userExists = true;
            } else {
                userExists = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userExists;
    }

    public boolean userLoggedIn(User user){
        boolean loggedIn = false;
        Statement stmt = null;
        Connection connection = null;

        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT loggedIn FROM users WHERE userMailAddress='"+user.getUserMailAddress()+"'");
            while (rs.next()) {
                loggedIn = rs.getBoolean("loggedIn");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loggedIn;
    }

    public boolean checkIfPasswordIsCorrect(User user){
        User referenceUser = new User();
        boolean success = false;
        Statement stmt = null;
        Connection connection = null;

        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE userMailAddress='" + user.getUserMailAddress() + "'");

            while (rs.next()) {
                referenceUser.setUserMailAddress(rs.getString("userMailAddress"));
                referenceUser.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (uUtils.checkPassword(referenceUser, user)) {
            success = true;
        } else {
            success = false;
        }
        return success;
    }



    @Override
    public void addUser(User user) {

        Statement stmt = null;
        Connection connection = null;

        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();
            String udapteSQL = "INSERT into users (userMailAddress, loggedIn, firstName, lastName, streetandNr, city, postcode, password, userCreationDate) VALUES (" +
                    "'" + user.getUserMailAddress() + "', " +
                    "'false', " +
                    "'" + user.getFirstName() + "', " +
                    "'" + user.getLastName() + "', " +
                    "'" + user.getStreetAndNr() + "', " +
                    "'" + user.getCity() + "', " +
                    "'" + user.getPostcode() + "', " +
                    "'" + user.getPassword() + "', " +
                    "'" + uUtils.getTimestamp() + "')";

            stmt.executeUpdate(udapteSQL);

            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void editUser(User user) {
        Statement stmt = null;
        Connection connection = null;

        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();
            String udapteSQL = "UPDATE users " +
                    "SET firstName='"+user.getFirstName()+"', " +
                    "lastName='"+user.getLastName()+"', " +
                    "streetAndNr='"+user.getStreetAndNr()+"', " +
                    "city='"+user.getCity()+"', " +
                    "postcode='"+user.getPostcode()+"', " +
                    "password='"+user.getPassword()+"' " +
                    "WHERE userMailAddress='" + user.getUserMailAddress() + "'";

            stmt.executeUpdate(udapteSQL);

            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUser(String userMailAddress) {
        User user = new User();
        Statement stmt = null;
        Connection connection = null;

        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE userMailAddress='"+userMailAddress+"'");
            while (rs.next()) {
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setStreetAndNr(rs.getString("streetAndNr"));
                user.setCity(rs.getString("city"));
                user.setPostcode(rs.getString("postcode"));
                user.setUserMailAddress(rs.getString("userMailAddress"));
                user.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void login(User user) {
        Statement stmt = null;
        Connection connection = null;

        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();
            String udapteSQL = "UPDATE users SET loggedIn='true' WHERE userMailAddress='" + user.getUserMailAddress() + "'";

            stmt.executeUpdate(udapteSQL);

            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void logout(User user) {
        Statement stmt = null;
        Connection connection = null;

        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();
            String udapteSQL = "UPDATE users SET loggedIn='false' WHERE userMailAddress='" + user.getUserMailAddress() + "'";

            stmt.executeUpdate(udapteSQL);

            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
