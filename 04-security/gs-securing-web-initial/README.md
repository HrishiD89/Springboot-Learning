# Securing Web Initial

A demo project for Spring Boot showing how to secure a web application using Spring Security.

## Overview
This repository contains a simple web application built with Spring Boot that demonstrates basic web security features. It includes a home page, a secured "hello" page, and a custom login page.

## Stack
- **Language:** Java 17
- **Framework:** Spring Boot 4.0.0
- **Security:** Spring Security
- **Template Engine:** Thymeleaf
- **Build Tools:** Maven and Gradle (both wrappers provided)

## Project Structure
```text
gs-securing-web-initial/
├── gradle/                  # Gradle wrapper files
├── src/
│   ├── main/
│   │   ├── java/            # Java source files
│   │   │   └── com/example/securingweb/
│   │   │       ├── MvcConfig.java              # MVC configuration for view controllers
│   │   │       ├── SecuringWebApplication.java # Main application entry point
│   │   │       └── WebSecurityConfig.java      # Spring Security configuration
│   │   └── resources/
│   │       ├── static/      # Static resources (CSS, JS)
│   │       └── templates/   # Thymeleaf templates (HTML)
│   └── test/                # Unit and integration tests (TODO: verify tests existence)
├── build.gradle             # Gradle build configuration
├── gradlew                  # Gradle wrapper script
├── mvnw                     # Maven wrapper script
├── pom.xml                  # Maven project object model
└── README.md                # Project documentation
```

## Requirements
- JDK 17 or higher
- Internet connection (to download dependencies)

## Setup and Run

### Using Maven
To run the application using the Maven wrapper:
```bash
./mvnw spring-boot:run
```

### Using Gradle
To run the application using the Gradle wrapper:
```bash
./gradlew bootRun
```

The application will be available at `http://localhost:8080`.

## Scripts and Commands

### Maven
- `./mvnw clean install`: Build the project and install to local repository.
- `./mvnw test`: Run tests.
- `./mvnw spring-boot:run`: Launch the application.

### Gradle
- `./gradlew build`: Build the project.
- `./gradlew test`: Run tests.
- `./gradlew bootRun`: Launch the application.

## Environment Variables
Currently, the application does not require any specific environment variables to run. 
*TODO: Add any specific environment variables if needed for production.*

## Security Configuration
The application is configured with a simple in-memory user for demonstration purposes:
- **Username:** `user`
- **Password:** `password`

The `/` and `/home` paths are accessible to everyone, while `/hello` requires authentication.

## How it Works

### 1. Request Mapping (`MvcConfig.java`)
The application uses `MvcConfig` to map specific URLs to Thymeleaf templates without needing a separate Controller class.
- `/` and `/home` -> `home.html`
- `/hello` -> `hello.html`
- `/login` -> `login.html`

### 2. Security Logic (`WebSecurityConfig.java`)
The `securityFilterChain` bean defines the security constraints:
- **Public Access**: `.requestMatchers("/", "/home").permitAll()` allows anyone to see the home page.
- **Restricted Access**: `.anyRequest().authenticated()` ensures that any other page (like `/hello`) requires the user to be logged in.
- **Form Login**: `.formLogin(...)` specifies that the application should use a custom login page at `/login`.
- **Logout**: `.logout(...).permitAll()` enables the logout feature and allows everyone to access it.

### 3. User Authentication
The `userDetailsService` bean creates an in-memory user with the username `user` and password `password`. In a real application, this would typically be replaced by a database-backed service.

### 4. Application Flow
1. **Initial Visit**: A user goes to `http://localhost:8080/`. They see the home page.
2. **Accessing Secured Content**: The user clicks a link to the "hello" page. Spring Security detects the user is not authenticated and redirects them to `/login`.
3. **Logging In**: The user enters the credentials (`user`/`password`). Upon success, Spring Security redirects them back to the originally requested `/hello` page.
4. **Logging Out**: The user clicks the "Sign Out" button (on the `hello` page). The session is invalidated, and the user is redirected to the home page or login page depending on configuration (defaults to `/login?logout`).

## Tests
To run the tests, use:
- Maven: `./mvnw test`
- Gradle: `./gradlew test`

*Note: Tests are expected to be located in `src/test/java`.*

## License
*TODO: Specify the license for this project.*
