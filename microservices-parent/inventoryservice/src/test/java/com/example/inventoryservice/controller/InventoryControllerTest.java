package com.example.inventoryservice.controller;

import com.example.inventoryservice.dto.InventoryResponse;
import com.example.inventoryservice.model.Inventory;
import com.example.inventoryservice.repository.InventoryRepository;
import com.example.inventoryservice.service.InventoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class InventoryControllerTest {
    @MockBean
    InventoryService inventoryService;

    @MockBean
    InventoryRepository inventoryRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Should check product is in Stock")
    void inStock() throws Exception {
        Inventory inventory = new Inventory();
        inventory.setQuantity(100);
        inventory.setSkuCode("Iphone_13");

        Inventory inventory1 = new Inventory();
        inventory1.setQuantity(1);
        inventory1.setSkuCode("Iphone_13_red");

        inventoryRepository.save(inventory);
        inventoryRepository.save(inventory1);

        List<Inventory> inventoryList = new ArrayList<>();
        inventoryList.add(inventory);
        inventoryList.add(inventory);

        List<String> skuCodes = Arrays.asList("Iphone13","Iphone_13_red");
        List<InventoryResponse> inventoryResponseList = inventoryList.stream().map(this::mapToInventoryResponse).toList();
        when(inventoryService.inStock(skuCodes)).thenReturn(inventoryResponseList);
      //  objectMapper.writeValueAsString(Inventory);

        this.mockMvc.perform(get("/api/inventory")
                        .param("skuCode", new String[]{"Iphone13", "Iphone_13_red"}))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(inventoryResponseList.size())));
    }

    private InventoryResponse mapToInventoryResponse(Inventory inventory) {
        return InventoryResponse.builder()
                .skuCode(inventory.getSkuCode())
                .isInStock(inventory.getQuantity() > 0)
                .build();
    }
}