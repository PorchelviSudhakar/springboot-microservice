package com.example.inventoryservice.service;

import com.example.inventoryservice.dto.InventoryResponse;
import com.example.inventoryservice.model.Inventory;
import com.example.inventoryservice.repository.InventoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InventoryServiceTest {
   @Mock
   InventoryRepository inventoryRepository;
   @InjectMocks
   InventoryService inventoryService;



    @Test
    @DisplayName("Check whether the Item is in Stock")
    void inStock() {
        Inventory inventory = new Inventory();
        inventory.setQuantity(100);
        inventory.setSkuCode("Iphone_13");

        Inventory inventory1 = new Inventory();
        inventory1.setQuantity(1);
        inventory1.setSkuCode("Iphone_13_red");

        List<Inventory> inventoryList = new ArrayList<>();
        inventoryList.add(inventory);
        inventoryList.add(inventory);
        List<String> skuCodes = Arrays.asList("Iphone13","Iphone_13_red");
        when(inventoryRepository.findBySkuCodeIn(skuCodes)).thenReturn(inventoryList);

        //Act
        List<InventoryResponse> inventoryResponseLists = inventoryService.inStock(skuCodes);

        assertNotNull(inventoryResponseLists);
        assertEquals(2,inventoryResponseLists.size());
    }

}