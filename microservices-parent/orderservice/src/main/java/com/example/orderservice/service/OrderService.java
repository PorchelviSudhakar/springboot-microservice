package com.example.orderservice.service;

import com.example.orderservice.dto.InventoryResponse;
import com.example.orderservice.repository.OrderRepository;
import com.example.orderservice.dto.OrderLineItemsDto;
import com.example.orderservice.dto.OrderRequest;
import com.example.orderservice.model.Order;
import com.example.orderservice.model.OrderLineItems;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;

    private final WebClient.Builder webClient;

    public void placeOrder(OrderRequest orderRequest){

        System.out.println("======order line items======"+orderRequest.getOrderLineItemsDtoList().size());
         Order order = new Order();
         order.setOrderNumber(UUID.randomUUID().toString());
         List<OrderLineItems> orderLineItems =
                 orderRequest.getOrderLineItemsDtoList().stream()
                         .map(orderLineItemsDto -> mapToOrderLineItems(orderLineItemsDto)).toList();
        order.setOrderLineItems(orderLineItems);

        List<String> skuCodes = order.getOrderLineItems()
                .stream()
                .map(orderLineItems1 -> orderLineItems1.getSkuCode())
                .toList();
        // call inventory service to check whether product is present or not
         InventoryResponse[] inventoryResponseArray = webClient.build().get()
                 .uri("http://inventory-service/api/inventory", uriBuilder -> uriBuilder.queryParam("skuCode",skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();
         boolean orderInStock = Arrays.stream(inventoryResponseArray).allMatch(InventoryResponse::isInStock);
        if(orderInStock){
            log.info("Item saved successfully");
            orderRepository.save(order);
        }else{
            throw new IllegalArgumentException("Try again later product is not in Stock");
        }



    }

    private OrderLineItems mapToOrderLineItems(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setPrice(orderLineItemsDto.getPrice());

        return orderLineItems;

    }

}
