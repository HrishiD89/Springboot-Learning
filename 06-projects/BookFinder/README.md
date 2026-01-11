# BookFinder

BookFinder is a Spring Boot application that demonstrates how to interact with MongoDB using Spring Data MongoDB. It provides a simple way to store and retrieve book information.

## Project Structure

- `src/main/java/com/app/bookfinder/BookFinderApplication.java`: The entry point of the application.
- `src/main/java/com/app/bookfinder/model/Book.java`: The POJO (Plain Old Java Object) representing a Book document in MongoDB.
- `src/main/java/com/app/bookfinder/repository/BookRepository.java`: The repository interface for database operations.
- `src/main/resources/application.properties`: Configuration file for MongoDB connection.

## How it Works (Flow)

1.  **Application Startup**: When the application starts, Spring Boot initializes the application context.
2.  **MongoDB Connection**: The application connects to the MongoDB instance using the URI and database name specified in `application.properties`.
3.  **Repository Initialization**: Spring Data MongoDB creates a proxy implementation of the `BookRepository` interface.
4.  **Execution**: Since `BookFinderApplication` implements `CommandLineRunner`, the `run` method is executed after the application context is fully loaded.
    - It saves a new book to the database.
    - It prints the total count of books in the database to the console.

## Annotations Explained

The project uses several Spring Boot and Spring Data MongoDB annotations:

### Spring Boot Annotations
- `@SpringBootApplication`: A convenience annotation that adds `@Configuration`, `@EnableAutoConfiguration`, and `@ComponentScan`.
- `@Autowired`: Marks a constructor, field, setter method, or config method as to be autowired by Spring's dependency injection facilities.
- `@EnableMongoRepositories`: Enables Spring Data MongoDB repositories.

### Spring Data MongoDB Annotations
- `@Document("Book")`: Applied at the class level to designate this class as a candidate for mapping to the database. It specifies the name of the collection.
- `@Id`: Applied at the field level to mark the field used for identity purposes.
- `@Query`: Allows for custom JSON queries to be executed against the MongoDB database.
    - Example: `@Query("{title:'?0'}")` finds a book by its title.
    - Example: `@Query(value="{author:'?0'}", fields="{'title' : 1, 'description' : 1}")` finds all books by an author and returns only specific fields.

## Configuration

The `application.properties` file contains:
- `spring.mongodb.uri`: The connection string for MongoDB.
- `spring.mongodb.database`: The name of the database to use.

## How to Run

1.  Ensure you have Java and Maven installed.
2.  Update the MongoDB URI in `src/main/resources/application.properties` if necessary.
3.  Run the application using Maven:
    ```bash
    mvn spring-boot:run
    ```
4.  Check the console output for the "Total books in DB" message.
