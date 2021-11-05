package com.theBreak.app;

import com.theBreak.app.dataManager.OrderManager;
import com.theBreak.app.dataManager.UserFavManager;
import com.theBreak.app.dataManager.UserManager;
import com.theBreak.app.dataManagerImpl.PostgresOrderManagerImpl;
import com.theBreak.app.dataManagerImpl.PostgresUserFavManagerImpl;
import com.theBreak.app.dataManagerImpl.PostgresUserManagerImpl;
import com.theBreak.app.model.order.Order;
import com.theBreak.app.model.order.OrderList;
import com.theBreak.app.model.user.User;
import com.theBreak.app.model.userFavorites.UserFavorites;
import com.theBreak.app.model.userFavorites.UserFavoritesList;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1.0")

public class MappingController {
    OrderManager orderManager = PostgresOrderManagerImpl.getPostgresOrderManagerImpl();
    UserManager userManager = PostgresUserManagerImpl.getPostgresUserManagerImpl();
    UserFavManager userFavManager = PostgresUserFavManagerImpl.getPostgresUserFavManagerImpl();

    //Order-Mapping
    @PostMapping(
            path = "/order",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    @ResponseStatus(HttpStatus.OK)
    public String createOrder(@RequestBody Order order) {
        orderManager.addOrder(order);
        return order.getPickUpDate() + " " + order.getPickupTime();
    }

    @GetMapping("/order/all/unpaid")
    public OrderList getUnpaidOrders(@RequestParam(value = "userMailAddress") String userMailAddress) {
        User checkLogin = new User();
        checkLogin.setUserMailAddress(userMailAddress);
        if (userManager.userLoggedIn(checkLogin)){
            OrderList orderList = new OrderList(userMailAddress);
            orderList.setUnpaidOrders();

            return orderList;
        } else {
            return null;
        }
    }

    @GetMapping("/order/all/paid")
    public OrderList getPaidOrders(@RequestParam(value = "userMailAddress") String userMailAddress) {
        User checkLogin = new User();
        checkLogin.setUserMailAddress(userMailAddress);
        if (userManager.userLoggedIn(checkLogin)){
            OrderList orderList = new OrderList(userMailAddress);
            orderList.setPaidOrders();

            return orderList;
        } else {
            return null;
        }
    }

    @PatchMapping(
            path = "/order/timeEdit",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    @ResponseStatus(HttpStatus.OK)
    public String timeEdit(@RequestBody Order order) {
        User checkLogin = new User();
        checkLogin.setUserMailAddress(order.getUserMailAddress());
        if (userManager.userLoggedIn(checkLogin)){
            orderManager.editCollectTime(order);
            return "Pickuptime changed";
        } else {
            return "User not logged in";
        }
    }

    @PatchMapping(
            path = "/order/timeEditByBot",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    @ResponseStatus(HttpStatus.OK)
    public String timeEditByBot(@RequestBody Order order) {
            orderManager.editCollectTimeWithBot(order);
            return "Pickuptime changed";
    }

    @PostMapping(
            path = "/order/createtable"
    )
    @ResponseStatus(HttpStatus.OK)
    public String createOrders() {

        final PostgresOrderManagerImpl postgresOrderManagerImpl =
                PostgresOrderManagerImpl.getPostgresOrderManagerImpl();
        postgresOrderManagerImpl.createOrdersTable();

        return "Database Table created";
    }

    @DeleteMapping(
            path = "/order/deletetable"
    )
    @ResponseStatus(HttpStatus.OK)
    public String deleteOrders() {

        final PostgresOrderManagerImpl postgresOrderManagerImpl =
                PostgresOrderManagerImpl.getPostgresOrderManagerImpl();
        postgresOrderManagerImpl.dropOrdersTable();

        return "Database Table deleted";
    }


    //User-Mapping
    @PostMapping(
            path = "/user/signup",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    @ResponseStatus(HttpStatus.OK)
    public String createTask(@RequestBody User user) {
        userManager.addUser(user);

        return user.getUserMailAddress() + " is now registered";
    }

    @PatchMapping(
            path = "/user/login",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    @ResponseStatus(HttpStatus.OK)
    public String login(@RequestBody User user) {
        if (userManager.checkIfUserExists(user)){
            if (!userManager.userLoggedIn(user)){
                if (userManager.checkIfPasswordIsCorrect(user)){
                    userManager.login(user);
                    return "User logged in";
                } else {
                    return "Password wrong";
                }
            } else {
                return "User already logged in";
            }
        } else {
            return "There is no User existing with this E-Mail";
        }
    }

    @PatchMapping(
            path = "/user/logout",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    @ResponseStatus(HttpStatus.OK)
    public String logout(@RequestBody User user) {
        if (userManager.userLoggedIn(user)){
            userManager.logout(user);
            return "User logged out";
        } else {
            return "User is already logged out";
        }
    }

    @GetMapping("/user/show")
    public User getUser(@RequestParam(value = "userMailAddress") String userMailAddress) {
        User checkLogin = new User();
        checkLogin.setUserMailAddress(userMailAddress);
        if (userManager.userLoggedIn(checkLogin)){
            return userManager.getUser(userMailAddress);
        } else {
            return null;
        }
    }

    @PutMapping(
            path = "/user/edit",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    @ResponseStatus(HttpStatus.OK)
    public String editUser(@RequestBody User user) {
        User checkLogin = new User();
        checkLogin.setUserMailAddress(user.getUserMailAddress());
        if (userManager.userLoggedIn(checkLogin)){
            userManager.editUser(user);
            return "Userdata changed";
        } else {
            return "User not logged in";
        }
    }

    @DeleteMapping(
            path = "/user/deleteUser"
    )
    @ResponseStatus(HttpStatus.OK)
    public String deleteUser(@RequestParam(value = "userMailAddress") String userMailAddress) {
        User checkLogin = new User();
        checkLogin.setUserMailAddress(userMailAddress);
        if (userManager.userLoggedIn(checkLogin)){
            userManager.deleteUser(userMailAddress);
            return "Userdata deleted";
        } else {
            return "User not logged in";
        }
    }

    @PostMapping(
            path = "/user/createtable"
    )
    @ResponseStatus(HttpStatus.OK)
    public String createUsers() {

        final PostgresUserManagerImpl postgresUserManagerImpl =
                PostgresUserManagerImpl.getPostgresUserManagerImpl();
        postgresUserManagerImpl.createUserTable();

        return "Database Table created";
    }

    @DeleteMapping(
            path = "/user/deletetable"
    )
    @ResponseStatus(HttpStatus.OK)
    public String deleteUsers() {

        final PostgresUserManagerImpl postgresUserManagerImpl =
                PostgresUserManagerImpl.getPostgresUserManagerImpl();
        postgresUserManagerImpl.dropUsersTable();

        return "Database Table deleted";
    }

    //Favorites-Mapping
    @PostMapping(
            path = "/fav",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    @ResponseStatus(HttpStatus.OK)
    public String createFav(@RequestBody UserFavorites favorite) {
        User checkLogin = new User();
        checkLogin.setUserMailAddress(favorite.getUserMailAddress());
        if (userManager.userLoggedIn(checkLogin)){
            userFavManager.addUserFav(favorite);
            return "Favorite added";
        } else {
            return "User not logged in";
        }
    }

    @DeleteMapping(
            path = "/fav/deleteFav"
    )
    @ResponseStatus(HttpStatus.OK)
    public String deleteFav(@RequestParam(value = "userMailAddress") String userMailAddress, @RequestParam(value = "favoriteId") int favoriteId) {
        User checkLogin = new User();
        checkLogin.setUserMailAddress(userMailAddress);
        if (userManager.userLoggedIn(checkLogin)){
            userFavManager.deleteUserFav(favoriteId);
            return "User-Favorite deleted";
        } else {
            return "User not logged in";
        }
    }

    @GetMapping("/fav/all")
    public UserFavoritesList getAllFavs(@RequestParam(value = "userMailAddress") String userMailAddress) {
        User checkLogin = new User();
        checkLogin.setUserMailAddress(userMailAddress);
        if (userManager.userLoggedIn(checkLogin)){
            UserFavoritesList userFavList = new UserFavoritesList(userMailAddress);
            userFavList.setArticleFavs();

            return userFavList;
        } else {
            return null;
        }
    }

    @PostMapping(
            path = "/fav/createtable"
    )
    @ResponseStatus(HttpStatus.OK)
    public String createFavorites() {

        final PostgresUserFavManagerImpl postgresUserFavManagerImpl =
                PostgresUserFavManagerImpl.getPostgresUserFavManagerImpl();
        postgresUserFavManagerImpl.createUserFavsTable();

        return "Database Table created";
    }

    @DeleteMapping(
            path = "/fav/deletetable"
    )
    @ResponseStatus(HttpStatus.OK)
    public String deleteFavorites() {

        final PostgresUserFavManagerImpl postgresUserFavManagerImpl =
                PostgresUserFavManagerImpl.getPostgresUserFavManagerImpl();
        postgresUserFavManagerImpl.dropUserFavsTable();

        return "Database Table deleted";
    }

}

