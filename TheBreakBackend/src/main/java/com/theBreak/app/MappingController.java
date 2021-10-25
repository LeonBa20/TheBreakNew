package com.theBreak.app;

import com.theBreak.app.dataManager.OrderManager;
import com.theBreak.app.dataManagerImpl.PropertyFileOrderManagerImpl;
import com.theBreak.app.model.order.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1.0")

public class MappingController {
    OrderManager orderManager = PropertyFileOrderManagerImpl.getPropertyFileOrderManagerImpl("src/main/resources/orderList.properties");


    @PostMapping(
            path = "/order",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    @ResponseStatus(HttpStatus.OK)
    public String createTask(@RequestBody Order order) {
        orderManager.addOrder(order);
        return order.getName();
    }
}
