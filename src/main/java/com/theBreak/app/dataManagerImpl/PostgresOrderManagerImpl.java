package com.theBreak.app.dataManagerImpl;

import com.theBreak.app.dataManager.OrderManager;
import com.theBreak.app.model.order.Order;
import com.theBreak.app.utils.OrderUtils;
import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgresOrderManagerImpl implements OrderManager {

    OrderUtils oUtils = new OrderUtils();


    String databaseURL = "jdbc:postgresql://ec2-44-199-83-229.compute-1.amazonaws.com:5432/d96tmqltf5lrg2";
    String username = "jdvrqgzhrdpulq";
    String password = "2657b5862765dfe661063ff6f4673f7993f55367948ef32022a28215a32b2d89";
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

        // Be carefull: It deletes data if table already exists.
        //
        Statement stmt = null;
        Connection connection = null;

        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();

            // String dropTable = "DROP TABLE tasks";

            String createTable = "CREATE TABLE orders (" +
                    "orderId SERIAL PRIMARY KEY, " +
                    "firstname varchar(100) NOT NULL, " +
                    "name varchar(100) NOT NULL, " +
                    "userMailAddress varchar(100) NOT NULL, " +
                    "streetandNr varchar(150) NOT NULL, " +
                    "city varchar(100) NOT NULL, " +
                    "postcode varchar(20) NOT NULL, " +
                    "orderedArticle1 varchar(30), " +
                    "orderedArticle2 varchar(30), " +
                    "orderedArticle3 varchar(30), " +
                    "orderedArticle4 varchar(30), " +
                    "orderedArticle5 varchar(30), " +
                    "orderedArticle6 varchar(30), " +
                    "orderedArticle7 varchar(30), " +
                    "orderedArticle8 varchar(30), " +
                    "configBowl1 varchar(300), " +
                    "configBowl2 varchar(300), " +
                    "configBowl3 varchar(300), " +
                    "sum double precision, " +
                    "orderPaid boolean NOT NULL, " +
                    "pickupDate varchar(15) NOT NULL, " +
                    "pickupTime varchar(15) NOT NULL, " +
                    "orderTime varchar(35) NOT NULL)";


            // stmt.executeUpdate(dropTable);

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

    @Override
    public void addOrder(Order order) {

        Statement stmt = null;
        Connection connection = null;

        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();
            String udapteSQL = "INSERT into orders (firstname, name, userMailAddress, streetandNr, city, postcode, " +
                    "orderedArticle1, orderedArticle2, orderedArticle3, orderedArticle4, orderedArticle5, orderedArticle6, " +
                    "orderedArticle7, orderedArticle8, configBowl1, configBowl2, configBowl3, sum, orderPaid, pickupDate, " +
                    "pickupTime, orderTime) VALUES (" +
                    "'" + order.getFirstname() + "', " +
                    "'" + order.getName() + "', " +
                    "'" + order.getUserMailAddress() + "', " +
                    "'" + order.getStreetAndNr() + "', " +
                    "'" + order.getCity() + "', " +
                    "'" + order.getPostcode() + "', " +
                    "'" + oUtils.getSelectedOrderArticle(order,1) + "', " +
                    "'" + oUtils.getSelectedOrderArticle(order,2) + "', " +
                    "'" + oUtils.getSelectedOrderArticle(order,3) + "', " +
                    "'" + oUtils.getSelectedOrderArticle(order,4) + "', " +
                    "'" + oUtils.getSelectedOrderArticle(order,5) + "', " +
                    "'" + oUtils.getSelectedOrderArticle(order,6) + "', " +
                    "'" + oUtils.getSelectedOrderArticle(order,7) + "', " +
                    "'" + oUtils.getSelectedOrderArticle(order,8) + "', " +
                    "'" + oUtils.configuredBowlsToString(order,1) + "', " +
                    "'" + oUtils.configuredBowlsToString(order,2) + "', " +
                    "'" + oUtils.configuredBowlsToString(order,3) + "', " +
                    "'" + order.getSum() + "', " +
                    "'" + order.isOrderPaid() + "', " +
                    "'" + order.getPickUpDate() + "', " +
                    "'" + order.getPickupTime() + "', " +
                    "'" + order.getOrdertime() + "')";

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

   /* @Override
    public Collection<Task> getAllTasks(Student student) {

        List<Task> tasks = new ArrayList<>();
        Statement stmt = null;
        Connection connection = null;

        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM tasks");
            while (rs.next()) {
                tasks.add(
                        new Task(
                                rs.getString("name"),
                                rs.getString("description"),
                                rs.getInt("priority")
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


        return tasks;
    }

    @Override
    public void addTask(Task task, Student student) {

        Statement stmt = null;
        Connection connection = null;

        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();
            String udapteSQL = "INSERT into tasks (name, description, priority) VALUES (" +
                    "'" + task.getName() + "', " +
                    "'" + task.getDescription() + "', " +
                    "'" + task.getPriority() + "')";

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

   */
}
