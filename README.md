## Spring-Boot-Microservie-Ecommerce-App

An eCommerce web application based on the microservices architecture built using Spring Boot which places the order based on the available stocks in Inventory.

## TOOLS USED:
1. SpringBoot 3.0
2. H2 Database
3. Spring Cloud ApiGateway
4. Netflix Eureka Server


## MICROSERVICES
1. Api-gateway Service: This Service acts as a Single entrypoint for all the microservices.
2. Discovery Service: Eureka discovery Services registers all the eureka clients and used for service discovery between microservices.
3. Product Service: It holds the Product details like product name,product price,product description.
4. Order Service: Handles Order Request from the Client and place the order based on the available stock in Inventory.
5. Inventory Service : Inventory Service holds the Order Stock details, Quantity etc.,
