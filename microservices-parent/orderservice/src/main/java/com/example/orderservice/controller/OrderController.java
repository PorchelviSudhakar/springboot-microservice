package com.example.orderservice.controller;

import com.example.orderservice.service.OrderService;
import com.example.orderservice.dto.OrderRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest) {
        try {
            System.out.println("Inside place Order");
            orderService.placeOrder(orderRequest);
        }catch(Exception e){
            log.info("error==//="+e);
            e.printStackTrace();
        }
        return "Order is Placed";
    }
}
