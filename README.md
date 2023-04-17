## Spring-Boot-Microservie-Ecommerce-App

An eCommerce web application based on the microservices architecture built using Spring Boot which places the order based on the available stocks in Inventory.

## TOOLS USED:
. SpringBoot 3.0
. H2 Database
. Spring Cloud ApiGateway
. Netflix Eureka Server


## MICROSERVICES
. Api-gateway Service: This Service acts as a Single entrypoint for all the microservices.
. Discovery Service: Eureka discovery Services registers all the eureka clients and used for service discovery between microservices.
. Product Service: It holds the Product details like product name,product price,product description.
. Order Service: Handles Order Request from the Client and place the order based on the available stock in Inventory.
. Inventory Service : Inventory Service holds the Order Stock details, Quantity etc.,
