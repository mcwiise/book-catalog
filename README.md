# Book Catalog 

Book Catalog is a REST API that allows to perform CRUD operations (Create, Read, Update, and Delete) on a collection of books.
It provides endpoints to manage book information such as title, author, summary, stock, and ratings.

## Available Endpoints

#### GET /book-catalog/v1/books/{id}
* Retrieve a single book by its unique identifier.
* Book Fields:
  * id ->  The book’s unique identifier. 
  * title -> The title of the book. 
  * author -> The author of the book. 
  * summary -> A brief description of the book’s content or topic.
  * stockCount -> The number of copies available in stock. 
  * rating -> An overall rating based on readers’ reviews.

#### GET /book-catalog/v1/books
* Retrieve all available books in the catalog.

#### POST /book-catalog/v1/books
* Create a new book and return its unique identifier.
* book id is not required; it is automatically generated when the book is saved.

#### DELETE /book-catalog/v1/books/{id}
* Delete a book by its unique identifier.

#### PUT /book-catalog/v1/books/{id}
* Partially update an existing book.
* Only the following fields can be updated:
    * summary -> A brief description of the book’s content or topic.
    * stockCount -> The number of copies available in stock.
    * rating -> An overall rating based on readers’ reviews.

## How to Run the Application

Book Catalog is a Spring Boot application written in Java 17 that exposes a REST API on port 8080.

#### Prerequisites

* [Install Java 17](https://formulae.brew.sh/formula/openjdk@17)
* Make sure your OS already has curl, [otherwise install it](https://formulae.brew.sh/formula/curl).
* [Install Postman (Optional)](https://www.postman.com/)

#### Clone the repository

```bash
# Create a workspace folder
mkdir wsapps
cd wsapps

# Clone the repository
git clone git@github.com:mcwiise/book-catalog.git  
```

#### Start the book catalog REST API

```bash
cd book-catalog

# Clean previous builds
./gradlew clean

# Build the application
./gradlew build

# Run the application
./gradlew bootRun
```

#### Test App is running

Open a terminal and execute the health check endpoint to verify that the application is up and running:

```bash
curl --location 'http://localhost:8080/actuator/health'
```

You should receive a response similar to:
```json
{
    "status": "UP"
}
```

## Executing Unit Test

Book Catalog includes a comprehensive set of unit tests covering approximately 80% of the codebase.

```bash
# Clean previous builds
./gradlew clean

# Build the project
./gradlew build

# Execute all unit tests
./gradlew test
```

## Swagger Documentation
The Book Catalog REST API is fully documented using Swagger (OpenAPI).
You can use the Swagger UI to explore all available endpoints, see request and response schemas.

#### Swagger UI
http://localhost:8080/swagger-ui.html

#### OpenAPI JSON specification:
http://localhost:8080/v3/api-docs

## Executing the API Endpoints

You can execute the following API requests using curl.
Alternatively, you can import these curl commands into Postman, which allows you to run the same requests 
directly from the Postman interface.

#### Create a new Book
```bash
curl --location 'http://localhost:8080/book-catalog/v1/books' \
--header 'Content-Type: application/json' \
--data '{
    "title": "berlin 1976",
    "author": "martin davidson",
    "summary": "the history of berlin",
    "stockCount": 10,
    "rating": 5
}'
```
#### List all available books
```bash
curl --location 'http://localhost:8080/book-catalog/v1/books'
```

#### Retrieve a book by ID
```
curl --location 'http://localhost:8080/book-catalog/v1/books/de85c2e0-8a6e-409d-b3fd-5f113a36fe24'
```

#### Partially update and existing book

Notice that only "summary", "stockCound" or "rating" can be updated

```bash
curl --location --request PUT 'http://localhost:8080/book-catalog/v1/books/6aa48e42-7b3b-405f-980b-f8e11465b6db' \
--header 'Content-Type: application/json' \
--data '{
    "summary": "the history of berlinin since 1976",
    "stockCount": 1,
    "rating": 4
}'
```

#### Delete a book
```
curl --location --request DELETE 'http://localhost:8080/book-catalog/v1/books/35dcba75-58f6-40df-8a1f-b76199918a12'
```