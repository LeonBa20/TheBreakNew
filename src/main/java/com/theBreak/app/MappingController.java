package com.theBreak.app;

import com.theBreak.app.dataManager.OrderManager;
import com.theBreak.app.dataManagerImpl.PostgresOrderManagerImpl;
import com.theBreak.app.dataManagerImpl.PropertyFileOrderManagerImpl;
import com.theBreak.app.model.order.Order;
import com.theBreak.app.model.order.OrderList;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1.0")

public class MappingController {
    OrderManager orderManager = PostgresOrderManagerImpl.getPostgresOrderManagerImpl();


    @PostMapping(
            path = "/order",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    @ResponseStatus(HttpStatus.OK)
    public String createTask(@RequestBody Order order) {
        orderManager.addOrder(order);
        return order.getName();
    }

    @GetMapping("/order/all")
    public OrderList getOrders(@RequestParam(value = "userMailAddress") String userMailAddress) {

        OrderList orderList = new OrderList(userMailAddress);
        orderList.setOrders();

        return orderList;
    }

    @PostMapping(
            path = "/order/createtable"
    )
    @ResponseStatus(HttpStatus.OK)
    public String createOrder() {

        final PostgresOrderManagerImpl postgresOrderManagerImpl =
                PostgresOrderManagerImpl.getPostgresOrderManagerImpl();
        postgresOrderManagerImpl.createOrdersTable();

        return "Database Table created";
    }
}
