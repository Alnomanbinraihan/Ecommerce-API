# productManagement

This Spring Boot application is designed with Domain-Driven Design (DDD) principles in mind, ensuring a clean separation of concerns between the domain, service, and persistence layers. The application requires the following setup:
 1.JDK 17
 2.IntelliJ IDEA

For data persistence, the application utilizes an in-memory H2 database, with configurations specified in the application.properties file.

Domain Logic & DDD Principles :
 To adhere to DDD principles, business rules are encapsulated directly within the entity classes. Key domain rules implemented include:
 	A.Price Validation: The product price must always be greater than zero.
 	B.Category Enforcement: A product must belong to a category.
	C.Stock Quantity: Only positive integers are allowed for stock quantities.
	D.CreatedAt & updatedAt record: CreatedAt keep record when data is created and updatedAt auto-update when data modified.

 Also this application ensure the unique name for the product.
 The service layer is designed by implementing interfaces, ensuring a clear contract for business operations and maintaining clean separation of concerns.

RESTful APIs:
 The following APIs have been developed to fulfill key requirements:
	1.GET /products
	  Retrieves all products.
	2.GET /category/1
	  Retrieves a product by its ID (in this case, ID = 1).Here 1 is Path variable in the link
        3. POST /products
          Creates a new product. The payload must include a valid category ID, ensuring a separate aggregate for categories.
	  Example Payload:
	   {
  	     "name": "Qtec",
  	     "description": "test",
  	     "price": 500,
 	     "stockQuantity": 100,
   	     "category": { "id": 1 },
  	     "discount": { "discountPercent": 10 }
	  }

	4. PUT /products/1
	   Updates the product with ID = 1.Here 1 is Path variable in the link
	   Example Payload:
	   {
  	    "name": "test1",
  	    "description": "test",
   	    "price": 1000,
   	    "stockQuantity": 10,
   	    "category": { "id": 1 },
  	    "discount": { "discountPercent": 10 }
	   }
	5.  DELETE /products/2
	   Deletes the product with ID = 2.Here 2 is Path variable in the link
	6.  PATCH /products/1/update-stock?stockQuantity=50
	   Updates the stock quantity for the product with ID = 1. The stockQuantity must be a positive integer.stockQuantity is request param.


Bonus Features
  Category as a Separate Aggregate:
	Categories have been modeled as a separate aggregate following DDD principles. The Category entity has its own controller and service,repository with the following APIs available:
	A.GET /category
	  Retrieves all categories.
	B.GET /category/1
	  Retrieves a category by its ID (e.g., ID = 1).Here 1 is Path variable in the link
	C.POST /category?name=category1
	  Creates a new category with the specified name.
	D.PUT /category/1?name=categoryUpdated
	  Updates the category with ID = 1 and name is category name which will be updated name for the category.
	E.DELETE /category/1
	Deletes the category with ID = 1.
Discount Support
  Discounts are modeled as an embedded entity within the Product class, with no separate database table for discounts. However, the discount information is stored in the product table. You can retrieve the discounted price with the following API:
	A.GET /products/discount-price/2
	Retrieves the discounted price for the product with ID = 2.
Pagination & Sorting
  Pagination and sorting are supported for both products and categories, allowing clients to fetch data in a structured manner. The APIs are:
	A.GET /products/paginated?page=0&size=1&isAsc=true
	  Retrieves paginated products. The page, size, and isAsc parameters control the pagination and sorting.
	B.GET /category/paginated?page=0&size=1&isAsc=false
  Retrieves paginated categories, sorted by ID in descending order.
  here page,size and isAsc is request param. page parameter represent the page number, size parameter represent the number of content in the page
  and the isAsc parameter indicates the result will be in ascending order or not. If isAsc value true it will return result in ascending order.Otherwise it will return in descending order.
  The product is sorted based on created_at column in DB and category is sorted based on Id.


Database Access:
 For direct access to the H2 in-memory database, you can use the H2 console at:
  URL: http://localhost:8080/h2-console
  Username: root
  Password: root
  All relevant configuration details are stored in the application.properties file.
