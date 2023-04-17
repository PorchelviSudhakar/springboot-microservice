package com.example.inventoryservice;

import com.example.inventoryservice.repository.InventoryRepository;
import com.example.inventoryservice.model.Inventory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryserviceApplication {

	public static void main(String[] args) {

		var context = SpringApplication.run(InventoryserviceApplication.class, args);

	}

    @Bean
	public CommandLineRunner load(InventoryRepository inventoryRepository){
          return args->{
			  Inventory inventory = new Inventory();
			  inventory.setSkuCode("Iphone_13");
			  inventory.setQuantity(100);

			  Inventory inventory1 = new Inventory();
			  inventory1.setSkuCode("Iphone_13_red");
			  inventory1.setQuantity(1);

			  inventoryRepository.save(inventory);
			  inventoryRepository.save(inventory1);
		  };
	}

}
