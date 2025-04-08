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
  - Updates an existing product identified by its ID.
  - Exposed as a PUT API: /products/{id}

6. Delete Product
   - Deletes a product by its ID.
   - Exposed as a DELETE API: /products/{id}
   
Tech Stack

    
