package com.example.inventoryservice.repository;

import com.example.inventoryservice.model.Inventory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class InventoryRepositoryTest {

    @Autowired
    InventoryRepository inventoryRepository;

    @Test
    @DisplayName("Should save the Inventory data")
    void save() {
        //Arrange
        Inventory inventory = new Inventory();
        inventory.setQuantity(100);
        inventory.setSkuCode("Iphone_13");

        Inventory inventory1 = new Inventory();
        inventory1.setQuantity(1);
        inventory1.setSkuCode("Iphone_13_red");

        //Act
        Inventory newInventory = inventoryRepository.save(inventory);

        //Assert
        assertNotNull(newInventory);
        assertThat(newInventory.getId()).isNotEqualTo(null);

    }

    @Test
    @DisplayName("get the list of Inventory with skuCode as input list")
    void getInventoryBySkuCode() {
        //Arrange
        Inventory inventory = new Inventory();
        inventory.setQuantity(100);
        inventory.setSkuCode("Iphone_13");

        Inventory inventory1 = new Inventory();
        inventory1.setQuantity(1);
        inventory1.setSkuCode("Iphone_13_red");


        inventoryRepository.save(inventory);
        inventoryRepository.save(inventory1);

        List<String> skuCodes = Arrays.asList("Iphone13","Iphone_13_red");
        List<Inventory> inventoryList = inventoryRepository.findBySkuCodeIn(skuCodes);

        //Assert
        assertNotNull(inventoryList);
        assertEquals(2,inventoryList.size());
    }


}