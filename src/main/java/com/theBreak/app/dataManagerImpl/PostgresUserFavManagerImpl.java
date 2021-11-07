package com.theBreak.app.dataManagerImpl;

import com.theBreak.app.dataManager.UserFavManager;
import com.theBreak.app.model.userFavorites.UserFavorites;
import com.theBreak.app.utils.UserFavUtils;
import org.apache.commons.dbcp.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class PostgresUserFavManagerImpl implements UserFavManager {

    UserFavUtils uFavUtils = new UserFavUtils();

    String databaseURL = "jdbc:postgresql://ec2-54-228-162-209.eu-west-1.compute.amazonaws.com:5432/d96l8d28b825i5";
    String username = "ctqkmudflbnulg";
    String password = "a01798363ea3e4130085e31ed8a608c66359c57ecb85e3a90f478ac09dd713e2";
    BasicDataSource basicDataSource;

    static PostgresUserFavManagerImpl postgresUserFavManager = null;

    private PostgresUserFavManagerImpl() {
        basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(databaseURL);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);
    }

    static public PostgresUserFavManagerImpl getPostgresUserFavManagerImpl() {
        if (postgresUserFavManager == null)
            postgresUserFavManager = new PostgresUserFavManagerImpl();
        return postgresUserFavManager;
    }

    public void createUserFavsTable() {

        Statement stmt = null;
        Connection connection = null;

        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();

            String createTable = "CREATE TABLE userFavorites (" +
                    "favoriteId SERIAL PRIMARY KEY, " +
                    "userMailAddress varchar(100) NOT NULL, " +
                    "favoriteArticle varchar(30), " +
                    "favoriteBowl varchar(350))";

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

    public void dropUserFavsTable(){
        Statement stmt = null;
        Connection connection = null;

        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();

            String dropTable = "DROP TABLE userFavorites";
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

    @Override
    public void addUserArticleFav(UserFavorites favorite) {
        Statement stmt = null;
        Connection connection = null;

        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();
            String udapteSQL = "INSERT into userFavorites (userMailAddress, favoriteArticle) VALUES (" +
                    "'" + favorite.getUserMailAddress().toLowerCase() + "', " +
                    "'" + favorite.getFavoriteArticle() + "')";

            stmt.executeUpdate(udapteSQL);

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
    public void addUserConfigFav(UserFavorites favorite) {
        Statement stmt = null;
        Connection connection = null;

        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();
            String udapteSQL = "INSERT into userFavorites (userMailAddress, favoriteBowl) VALUES (" +
                    "'" + favorite.getUserMailAddress().toLowerCase() + "', " +
                    "'" + favorite.getFavoriteBowl() + "')";

            stmt.executeUpdate(udapteSQL);

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
    public void deleteUserFav(int favoriteId) {
        Statement stmt = null;
        Connection connection = null;

        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();
            String udapteSQL = "DELETE FROM userFavorites WHERE favoriteId=" + favoriteId;

            stmt.executeUpdate(udapteSQL);

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
    public Collection<UserFavorites> getAllArticleFavs(String userMailAddress) {
        List<UserFavorites> userFav = new ArrayList<>();
        Statement stmt = null;
        Connection connection = null;

        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM userFavorites WHERE userMailAddress='"+userMailAddress.toLowerCase()+"'");
            while (rs.next()) {
                userFav.add(
                        new UserFavorites(
                                rs.getInt("favoriteId"),
                                rs.getString("userMailAddress"),
                                rs.getString("favoriteArticle"),
                                uFavUtils.favoriteBowlToList("")
                        )
                );
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
        return userFav;
    }

    @Override
    public Collection<UserFavorites> getAllBowlFavs(String userMailAddress) {
        List<UserFavorites> userFav = new ArrayList<>();
        Statement stmt = null;
        Connection connection = null;

        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM userFavorites WHERE userMailAddress='"+userMailAddress.toLowerCase()+"'");
            while (rs.next()) {
                userFav.add(
                        new UserFavorites(
                                rs.getInt("favoriteId"),
                                rs.getString("userMailAddress"),
                                "",
                                uFavUtils.favoriteBowlToList(rs.getString("favoriteBowl"))
                        )
                );
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
        return userFav;
    }
}
