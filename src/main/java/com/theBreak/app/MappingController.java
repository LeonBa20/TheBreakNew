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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1.0")
@Tag(name = "Mapping-Controller", description = "API Endpunkte für den TheBreak-Webshop")

public class MappingController {
    OrderManager orderManager = PostgresOrderManagerImpl.getPostgresOrderManagerImpl();
    UserManager userManager = PostgresUserManagerImpl.getPostgresUserManagerImpl();
    UserFavManager userFavManager = PostgresUserFavManagerImpl.getPostgresUserFavManagerImpl();

    //Order-Mapping
    @PostMapping(
            path = "/order",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    @Operation(summary = "Erstellt eine Bestellung", description = "Mit diesem Aufruf wird der Tabelle Orders eine neue Bestellung hinzugefügt. Es können bis" +
            " zu 8 Artikel aktuell aufgenommen werden und bis zu 3 konfigurierte Bowls. Eine Bowl wird durch ein Array dargestellt," +
            " in dem die konfigurierten Zutaten gesammelt werden. Es müssen außerdem Vorname, Nachname, Mail-Adresse, Abholdatum und" +
            " -Zeit festgelegt werden")
    @ResponseStatus(HttpStatus.OK)
    public String createOrder(@RequestBody Order order) {
        orderManager.addOrder(order);
        return order.getPickUpDate() + " " + order.getPickupTime();
    }

    @DeleteMapping(
            path = "/order/delete"
    )
    @Operation(summary = "Löscht eine Bestellung", description = "Mit diesem Aufruf eine Order gelöscht. Dazu wird über die URL die User-Mail angegeben " +
            "sowie die passende Bestellnummer. Ist der Nutzer mit der Mail-Adresse nicht angemeldet, wird die Methode jedoch " +
            "nicht aufgerufen.")
    @ResponseStatus(HttpStatus.OK)
    public String deleteOrder(@RequestParam(value = "userMailAddress") String userMailAddress, @RequestParam(value = "orderId") int orderId) {
        User checkLogin = new User();
        checkLogin.setUserMailAddress(userMailAddress);
        if (userManager.userLoggedIn(checkLogin)){
            orderManager.deleteOrder(orderId);
            return "Order deleted";
        } else {
            return "User not logged in";
        }
    }

    @PatchMapping(
            path = "/order/setPickedUp",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    @Operation(summary = "Für Admin: Setzt Bestellstatus auf abgeholt", description = "Mit diesem Aufruf kann ein Admin den Status der Bestellung auf abgeholt stellen. Dazu muss in " +
            "der URL die Bestellnummer mitgegeben werden sowie das Adminpasswort.")
    @ResponseStatus(HttpStatus.OK)
    public String setPickedUp(@RequestParam(value = "orderId") int orderId, @RequestParam(value = "adminPassword") String adminPw) {
        User checkAdminPw = new User();
        checkAdminPw.setUserMailAddress("admin");
        checkAdminPw.setPassword(adminPw);
        if (userManager.checkIfPasswordIsCorrect(checkAdminPw)){
            orderManager.setOrderIsPickedUp(orderId);
            return "Orderstatus changed to picked up";
        } else {
            return "Adminpassword not correct";
        }
    }

    @GetMapping("/order/all/notPickedUp")
    @Operation(summary = "Zeigt alle nicht abgeschlossenen Bestellungen", description = "Bei diesem Aufruf werden alle Bestellungen eines Nutzers ausgegeben, die " +
            "noch nicht abgeholt wurden. Mittels URL wird die Nutzer-Mail übergeben. " +
            "Anschließend wird geprüft, ob der Nutzer eingeloggt ist.")
    public OrderList getNotPickedUpOrders(@RequestParam(value = "userMailAddress") String userMailAddress) {
        User checkLogin = new User();
        checkLogin.setUserMailAddress(userMailAddress);
        if (userManager.userLoggedIn(checkLogin)){
            OrderList orderList = new OrderList(userMailAddress);
            orderList.setOrdersToAllNotPickedUpFromUser();

            return orderList;
        } else {
            return null;
        }
    }

    @GetMapping("/order/all")
    @Operation(summary = "Zeigt alle Bestellungen", description = "Dieser Aufruf gibt alle Bestellungen des Nutzers zurück. Dazu wird per URL die Nutzer-Mail " +
            "übergeben und anschließend überprüft, ob der Nutzer eingeloggt ist.")
    public OrderList getAllOrders(@RequestParam(value = "userMailAddress") String userMailAddress) {
        User checkLogin = new User();
        checkLogin.setUserMailAddress(userMailAddress);
        if (userManager.userLoggedIn(checkLogin)){
            OrderList orderList = new OrderList(userMailAddress);
            orderList.setOrdersToAllFromUser();

            return orderList;
        } else {
            return null;
        }
    }

    @PatchMapping(
            path = "/order/timeEdit",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    @Operation(summary = "Ändert die Abholzeit der Bestellung", description = "Es wird die Abholzeit einer Bestellung angepasst. Dazu muss der Nutzer angemeldet sein." +
            " Dafür muss die Nutzermail, die Bestellnummer und die gewünschte neue Abholzeit mitgeben.")
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
    @Operation(summary = "Für Bots: Ändert die Abholzeit der Bestellung", description = "Dieser Aufruf dient zur Anpassung der Abholzeit durch einen Bot. Dazu müssen Nutzermail," +
            " Abholdatum und gewünschte Abholzeit übergeben werden.")
    @ResponseStatus(HttpStatus.OK)
    public String timeEditByBot(@RequestBody Order order) {
        orderManager.editCollectTimeWithBot(order);
        return "Pickuptime changed";
    }

    @PostMapping(
            path = "/order/createtable"
    )
    @Operation(summary = "Erstellt die Bestellungen-DB", description = "Erstellt eine Tabelle in der Datenbank für alle Bestellungen.")
    @ResponseStatus(HttpStatus.OK)
    public String createOrdersTable() {

        final PostgresOrderManagerImpl postgresOrderManagerImpl =
                PostgresOrderManagerImpl.getPostgresOrderManagerImpl();
        postgresOrderManagerImpl.createOrdersTable();

        return "Database Table created";
    }

    @DeleteMapping(
            path = "/order/deletetable"
    )
    @Operation(summary = "Löscht die Bestellungen-DB", description = "Löscht die Tabelle in der Datenbank für alle Bestellungen.")
    @ResponseStatus(HttpStatus.OK)
    public String deleteOrdersTable() {

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
    @Operation(summary = "Registriert den Nutzer", description = "Dieser Aufruf dient zur Anpassung der Abholzeit durch einen Bot. Dazu müssen Nutzermail," +
            " Abholdatum und gewünschte Abholzeit übergeben werden.")
    @ResponseStatus(HttpStatus.OK)
    public String signUp(@RequestBody User user) {
        userManager.addUser(user);

        return user.getUserMailAddress() + " is now registered";
    }

    @PatchMapping(
            path = "/user/login",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    @Operation(summary = "Loggt den Nutzer ein", description = "Mit diesem Aufruf wird ein Login des Nutzers ausgeführt. Dazu wird die Nutzermail sowie das " +
            "Passwort mitgegeben. Anschleßen wird geprüft, ob ein Nutzer zu der Mail existiert, ob er bereits eingeloggt ist " +
            "und ob das Passwort korrekt ist.")
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
    @Operation(summary = "Loggt den Nutzer aus", description = "Mit diesem Aufruf wird der Nutzer ausgeloggt. Dazu wird die Mail-Adresse übergeben.")
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
    @Operation(summary = "Zeigt alle Nutzerdaten", description = "Es werden die Nutzerdaten eines Benutzers zurückgegeben. Zur Identifizierung des Nutzers wird " +
            "die Mail-Adresse über die URL mitgegeben.")
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
    @Operation(summary = "Verändert Nutzerdaten", description = "Mit diesem Aufruf können die Nutzerdaten komplett verändert werden. Vor einer" +
            " Änderung in der Datenbank wird geprüft, ob der Nutzer eingeloggt ist.")
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
    @Operation(summary = "Löscht einen Nutzer", description = "Mit diesem Aufruf kann ein Nutzerdatensatz gelöscht werden. Vor dem Löschen wird geprüft, ob" +
            " die über die URL übergebene Mail eingeloggt ist.")
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
    @Operation(summary = "Erstellt die Nutzer-DB", description = "Es wird die Datenbank für alle Nutzer erstellt.")
    @ResponseStatus(HttpStatus.OK)
    public String createUsersTable() {

        final PostgresUserManagerImpl postgresUserManagerImpl =
                PostgresUserManagerImpl.getPostgresUserManagerImpl();
        postgresUserManagerImpl.createUserTable();

        return "Database Table created";
    }

    @DeleteMapping(
            path = "/user/deletetable"
    )
    @Operation(summary = "Löscht die Nutzer-DB", description = "Es wird die Nutzer-Datenbank gelöscht.")
    @ResponseStatus(HttpStatus.OK)
    public String deleteUsersTable() {

        final PostgresUserManagerImpl postgresUserManagerImpl =
                PostgresUserManagerImpl.getPostgresUserManagerImpl();
        postgresUserManagerImpl.dropUsersTable();

        return "Database Table deleted";
    }

    //Favorites-Mapping
    @PostMapping(
            path = "/fav/article",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    @Operation(summary = "Fügt einen normalen Artikel zu Nutzerfavoriten hinzu", description = "Es wird ein neuer Favorit für den Nutzer erstellt. Zuvor wird anhand " +
            "der Mail-Adresse geprüft, ob der Nutzer eingeloggt ist. Mit jedem Aufruf wird ein normaler Arikel als Favorit mitgegeben.")
    @ResponseStatus(HttpStatus.OK)
    public String createArticleFav(@RequestBody UserFavorites favorite) {
        User checkLogin = new User();
        checkLogin.setUserMailAddress(favorite.getUserMailAddress());
        if (userManager.userLoggedIn(checkLogin)){
            userFavManager.addUserArticleFav(favorite);
            return "Favorite added";
        } else {
            return "User not logged in";
        }
    }

    @PostMapping(
            path = "/fav/bowl",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    @Operation(summary = "Fügt eine konfigurierte Bowl zu Nutzerfavoriten hinzu", description = "Es wird ein neuer Favorit für den Nutzer erstellt. Zuvor wird anhand " +
            "der Mail-Adresse geprüft, ob der Nutzer eingeloggt ist. Mit jedem Aufruf wird eine konfigurierte Bowl als Favorit mitgegeben.")
    @ResponseStatus(HttpStatus.OK)
    public String createConfigBowlFav(@RequestBody UserFavorites favorite) {
        User checkLogin = new User();
        checkLogin.setUserMailAddress(favorite.getUserMailAddress());
        if (userManager.userLoggedIn(checkLogin)){
            userFavManager.addUserConfigFav(favorite);
            return "Favorite added";
        } else {
            return "User not logged in";
        }
    }

    @DeleteMapping(
            path = "/fav/deleteFav"
    )
    @Operation(summary = "Löscht einen Nutzerfavoriten", description = "Löscht einen Favorit eines Nutzers. Dazu wird in der URL die Mail-Adresse sowie die ID des" +
            "Favoriten übergeben. Anschließend wird der Login-Status überprüft.")
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

    @GetMapping("/fav/showArticles")
    @Operation(summary = "Zeigt alle regulären Artikel aus der Favoriten-Tabelle", description = "Es werden die regulären Artikel aus den Favoriten des Nutzers zurückgegeben. " +
            "Die in der URL übergebene Mail-Adresse wird davor auf ihren Login-Status überprüft.")
    public UserFavoritesList getAllArticleFavs(@RequestParam(value = "userMailAddress") String userMailAddress) {
        User checkLogin = new User();
        checkLogin.setUserMailAddress(userMailAddress);
        if (userManager.userLoggedIn(checkLogin)){
            UserFavoritesList userFavList = new UserFavoritesList(userMailAddress);
            userFavList.setFavsToAllArticlesFromUser();

            return userFavList;
        } else {
            return null;
        }
    }

    @GetMapping("/fav/showBowls")
    @Operation(summary = "Zeigt alle Bowls aus der Favoriten-Tabelle", description = "Es werden die Bowls aus den Favoriten des Nutzers zurückgegeben. " +
            "Die in der URL übergebene Mail-Adresse wird davor auf ihren Login-Status überprüft.")
    public UserFavoritesList getAllBowlFavs(@RequestParam(value = "userMailAddress") String userMailAddress) {
        User checkLogin = new User();
        checkLogin.setUserMailAddress(userMailAddress);
        if (userManager.userLoggedIn(checkLogin)){
            UserFavoritesList userFavList = new UserFavoritesList(userMailAddress);
            userFavList.setFavsToAllBowlsFromUser();

            return userFavList;
        } else {
            return null;
        }
    }

    @PostMapping(
            path = "/fav/createtable"
    )
    @Operation(summary = "Erstellt die Favoriten-DB", description = "Es wird die Datenbank der Nutzerfavoriten erstellt.")
    @ResponseStatus(HttpStatus.OK)
    public String createFavoritesTable() {

        final PostgresUserFavManagerImpl postgresUserFavManagerImpl =
                PostgresUserFavManagerImpl.getPostgresUserFavManagerImpl();
        postgresUserFavManagerImpl.createUserFavsTable();

        return "Database Table created";
    }

    @DeleteMapping(
            path = "/fav/deletetable"
    )
    @Operation(summary = "Löscht die Favoriten-DB", description = "Es wird die Datenbank der Nutzerfavoriten gelöscht.")
    @ResponseStatus(HttpStatus.OK)
    public String deleteFavoritesTable() {

        final PostgresUserFavManagerImpl postgresUserFavManagerImpl =
                PostgresUserFavManagerImpl.getPostgresUserFavManagerImpl();
        postgresUserFavManagerImpl.dropUserFavsTable();

        return "Database Table deleted";
    }

}
