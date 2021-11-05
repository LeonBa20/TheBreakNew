package com.theBreak.app.dataManagerImpl;

import com.theBreak.app.dataManager.OrderManager;
import com.theBreak.app.model.order.Order;
import com.theBreak.app.utils.OrderUtils;
import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PostgresOrderManagerImpl implements OrderManager {

    OrderUtils oUtils = new OrderUtils();

    String databaseURL = "jdbc:postgresql://ec2-54-228-162-209.eu-west-1.compute.amazonaws.com:5432/d96l8d28b825i5";
    String username = "ctqkmudflbnulg";
    String password = "a01798363ea3e4130085e31ed8a608c66359c57ecb85e3a90f478ac09dd713e2";
    BasicDataSource basicDataSource;

    static PostgresOrderManagerImpl postgresOrderManager = null;

    private PostgresOrderManagerImpl() {
        basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(databaseURL);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);
    }

    static public PostgresOrderManagerImpl getPostgresOrderManagerImpl() {
        if (postgresOrderManager == null)
            postgresOrderManager = new PostgresOrderManagerImpl();
        return postgresOrderManager;
    }

    public void createOrdersTable() {

        Statement stmt = null;
        Connection connection = null;

        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();

            String createTable = "CREATE TABLE orders (" +
                    "orderId SERIAL PRIMARY KEY, " +
                    "firstName varchar(100) NOT NULL, " +
                    "lastName varchar(100) NOT NULL, " +
                    "userMailAddress varchar(100) NOT NULL, " +
                    "streetandNr varchar(150), " +
                    "city varchar(100), " +
                    "postcode varchar(20), " +
                    "orderedArticle1 varchar(30), " +
                    "orderedArticle2 varchar(30), " +
                    "orderedArticle3 varchar(30), " +
                    "orderedArticle4 varchar(30), " +
                    "orderedArticle5 varchar(30), " +
                    "orderedArticle6 varchar(30), " +
                    "orderedArticle7 varchar(30), " +
                    "orderedArticle8 varchar(30), " +
                    "configBowl1 varchar(350), " +
                    "configBowl2 varchar(350), " +
                    "configBowl3 varchar(350), " +
                    "sum double precision, " +
                    "orderPaid boolean NOT NULL, " +
                    "pickupDate date NOT NULL, " +
                    "pickupTime time NOT NULL, " +
                    "orderTime timestamp NOT NULL)";

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

    public void dropOrdersTable(){
        Statement stmt = null;
        Connection connection = null;

        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();

            String dropTable = "DROP TABLE orders";
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
    public void addOrder(Order order) {

        Statement stmt = null;
        Connection connection = null;

        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();
            String udapteSQL = "INSERT into orders (firstName, lastName, userMailAddress, streetandNr, city, postcode, " +
                    "orderedArticle1, orderedArticle2, orderedArticle3, orderedArticle4, orderedArticle5, orderedArticle6, " +
                    "orderedArticle7, orderedArticle8, configBowl1, configBowl2, configBowl3, sum, orderPaid, pickupDate, " +
                    "pickupTime, orderTime) VALUES (" +
                    "'" + order.getFirstName() + "', " +
                    "'" + order.getLastName() + "', " +
                    "'" + order.getUserMailAddress() + "', " +
                    "'" + order.getStreetAndNr() + "', " +
                    "'" + order.getCity() + "', " +
                    "'" + order.getPostcode() + "', " +
                    "'" + order.getOrderedArticle1() + "', " +
                    "'" + order.getOrderedArticle2() + "', " +
                    "'" + order.getOrderedArticle3() + "', " +
                    "'" + order.getOrderedArticle4() + "', " +
                    "'" + order.getOrderedArticle5() + "', " +
                    "'" + order.getOrderedArticle6() + "', " +
                    "'" + order.getOrderedArticle7() + "', " +
                    "'" + order.getOrderedArticle8() + "', " +
                    "'" + oUtils.configuredBowlsToString(order,1) + "', " +
                    "'" + oUtils.configuredBowlsToString(order,2) + "', " +
                    "'" + oUtils.configuredBowlsToString(order,3) + "', " +
                    "'" + order.getSum() + "', " +
                    "'" + order.isOrderPaid() + "', " +
                    "'" + order.getPickUpDate() + "', " +
                    "'" + order.getPickupTime() + "', " +
                    "'" + oUtils.getTimestamp() + "')";

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
    public void editCollectTime(Order order) {
        Statement stmt = null;
        Connection connection = null;

        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();
            String udapteSQL = "UPDATE orders SET pickupTime='"+order.getPickupTime()+"' WHERE orderId='" + order.getOrderId() + "'";

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
    public void editCollectTimeWithBot(Order order) {
        Statement stmt = null;
        Connection connection = null;

        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();
            String udapteSQL = "UPDATE orders SET pickupTime='"+order.getPickupTime()+"' " +
                    "WHERE userMailAddress='" + order.getUserMailAddress() + "' AND pickupDate='" + order.getPickUpDate() + "'";

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
    public Collection<Order> getAllUnpaidOrders(String userMailAddress) {
        List<Order> orders = new ArrayList<>();
        Statement stmt = null;
        Connection connection = null;

        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM orders WHERE userMailAddress='"+userMailAddress+"' AND orderPaid='false'");
            while (rs.next()) {
                orders.add(
                        new Order(
                                rs.getString("firstName"),
                                rs.getString("lastName"),
                                rs.getString("userMailAddress"),
                                rs.getString("streetAndNr"),
                                rs.getString("city"),
                                rs.getString("postcode"),
                                rs.getString("orderedArticle1"),
                                rs.getString("orderedArticle2"),
                                rs.getString("orderedArticle3"),
                                rs.getString("orderedArticle4"),
                                rs.getString("orderedArticle5"),
                                rs.getString("orderedArticle6"),
                                rs.getString("orderedArticle7"),
                                rs.getString("orderedArticle8"),
                                oUtils.configuredBowlsToList(rs.getString("configBowl1")),
                                oUtils.configuredBowlsToList(rs.getString("configBowl2")),
                                oUtils.configuredBowlsToList(rs.getString("configBowl3")),
                                rs.getDouble("sum"),
                                rs.getBoolean("orderPaid"),
                                rs.getString("pickUpDate"),
                                rs.getString("pickupTime"),
                                rs.getString("orderTime"),
                                rs.getInt("orderId")
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
        return orders;
    }

    @Override
    public Collection<Order> getAllPaidOrders(String userMailAddress) {
        List<Order> orders = new ArrayList<>();
        Statement stmt = null;
        Connection connection = null;

        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM orders WHERE userMailAddress='"+userMailAddress+"' AND orderPaid='true'");
            while (rs.next()) {
                orders.add(
                        new Order(
                                rs.getString("firstName"),
                                rs.getString("lastName"),
                                rs.getString("userMailAddress"),
                                rs.getString("streetAndNr"),
                                rs.getString("city"),
                                rs.getString("postcode"),
                                rs.getString("orderedArticle1"),
                                rs.getString("orderedArticle2"),
                                rs.getString("orderedArticle3"),
                                rs.getString("orderedArticle4"),
                                rs.getString("orderedArticle5"),
                                rs.getString("orderedArticle6"),
                                rs.getString("orderedArticle7"),
                                rs.getString("orderedArticle8"),
                                oUtils.configuredBowlsToList(rs.getString("configBowl1")),
                                oUtils.configuredBowlsToList(rs.getString("configBowl2")),
                                oUtils.configuredBowlsToList(rs.getString("configBowl3")),
                                rs.getDouble("sum"),
                                rs.getBoolean("orderPaid"),
                                rs.getString("pickUpDate"),
                                rs.getString("pickupTime"),
                                rs.getString("orderTime"),
                                rs.getInt("orderId")
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


        return orders;
    }

}