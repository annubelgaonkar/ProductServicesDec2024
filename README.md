# ProductService - E-commerce Backend Microservice

ProductService is a backend microservice designed to manage product-related operations in an e-commerce platform. It handles the creation, updating, retrieval, and deletion of products, providing a seamless API for front-end and other microservices like PaymentService.

Features
- Architecture: Built on the MVC design pattern for clear separation of concerns.
- Caching: Integrated Redis for caching to improve data retrieval speed and reduce database load.
- Persistence: Utilized JPA with Hibernate ORM for seamless database interaction and efficient CRUD operations.
- Database: Configured with MySQL for robust and reliable data storage.
- API Design: Exposed RESTful APIs for interaction with the service.
- Data Handling: Implemented DTOs (Data Transfer Objects) for structured and secure data exchange in API requests and responses.

Core Functionality
1. Get All Products:
  - Retrieves a list of all available products.
  - Exposed as a GET API: /products
    
2. Get Product by ID:
  - Fetches details of a specific product by its unique identifier.
  - Exposed as a GET API: /products/{id}
    
3. Create Product:
  - Allows adding a new product to the database with validation and error handling.
  - Exposed as a POST API: /products
    
4. Update Product
   - Updates an existing product identified by its ID
   - Exposed as a PUT API: /products/{id}

6. Delete Product
   - Deletes a product by its ID.
   - Exposed as a DELETE API: /products/{id}
   
Tech Stack
   - Java Spring Boot: Main framework for developing the backend service.
   - Spring Data JPA: For easy integration with relational databases.
   - Hibernate ORM: Object-relational mapping framework for working with databases.
   - MySQL: Relational database used for persistent data storage.
   - Redis: In-memory data store used for caching product data to reduce database load and speed up retrieval.
   - RESTful API: For communication between microservices and client applications.
   - DTO (Data Transfer Objects): Used for sending and receiving structured data between APIs to ensure data integrity and security.
   - Git & GitHub for version control

Installation and Setup
1. Set Up MySQL and Redis
   - MySQL Database: Create a new database for the ProductService (e.g., product_db).
     CREATE DATABASE product_db;
     
   - Redis: Ensure Redis is running on your local machine or connect to a Redis service.
      - For local Redis setup, install Redis and start the server:
        redis-server
      - Alternatively, use a hosted Redis service like Redis Labs.
      
2. Configure the application.properties File
   - Update the application.properties file with the following configurations:
      - MySQL:
        spring.application.name=Product_Services_Dec2024
        server.port=8080
        logging.level.org.springframework.web=debug
        spring.jpa.hibernate.ddl-auto=update
        spring.datasource.url=jdbc:mysql://localhost:3306/productServiceDec2024
        spring.datasource.username=your_mysql_username
        spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
        spring.jpa.show-sql: true
        
      - Redis:
        spring.redis.host=localhost
        spring.redis.port=6379
        
3. Run the Application
     - Using Maven: You can run the application with the following command:
         - mvn spring-boot:run
     - Using IDE: Alternatively, you can run the application directly from your IDE (such as IntelliJ IDEA or Eclipse).
        
