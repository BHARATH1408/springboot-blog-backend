# Blog Backend (Spring Boot + JWT)

![Java](https://img.shields.io/badge/Java-17-blue?logo=java)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.5-green?logo=springboot)
![Maven](https://img.shields.io/badge/Maven-3.9.3-orange?logo=apachemaven)
![Build Status](https://img.shields.io/badge/Build-Success-brightgreen)
![License](https://img.shields.io/badge/License-MIT-yellow)
![Database](https://img.shields.io/badge/Database-H2-blue)

---

## Project Description

This is a **Spring Boot Blog Backend Application** built with JWT-based authentication and authorization. The project supports:

- User registration and login
- JWT-based authentication for secure API access
- CRUD operations on blog posts
- Global exception handling
- Input validation
- Integration with H2 in-memory database for development
- Swagger/OpenAPI documentation for testing APIs

---

## Features

- **Authentication & Authorization:** JWT token-based security for protected endpoints  
- **Post Management:** Create, Read, Update, Delete blog posts  
- **Validation:** Ensures title and content follow business rules  
- **Exception Handling:** Global error handler with meaningful messages  
- **API Documentation:** Swagger UI to test endpoints

---

## Tech Stack

- **Java 17**  
- **Spring Boot 3.2.5**  
- **Spring Security**  
- **JWT (JSON Web Token)**  
- **Spring Data JPA / Hibernate**  
- **H2 In-memory Database**  
- **Maven**  
- **Swagger/OpenAPI**  

---

## Installation & Setup

1. **Clone the repository:**

```bash
git clone https://github.com/your-username/blog-backend.git
cd blog-backend
Build the project using Maven:

bash
Copy code
mvn clean install
Run the application:

bash
Copy code
mvn spring-boot:run
Access H2 Database Console (for development):
Open http://localhost:8080/h2-console

JDBC URL: jdbc:h2:mem:blogdb

Username: sa

Password: (leave blank)

Swagger UI:
Open http://localhost:8080/swagger-ui/index.html to explore and test APIs.

Usage
Public APIs
POST /auth/register → Register a new user

POST /auth/login → Login and get JWT token

GET /posts → List all posts (public)

Protected APIs (JWT Required)
POST /posts → Create a new post

PUT /posts/{id} → Update a post

DELETE /posts/{id} → Delete a post

Use the Authorize button in Swagger UI to input your JWT token for protected APIs.

Testing
Unit tests and integration tests are included

Run all tests:

bash
Copy code
mvn clean verify
Ensure all tests pass before committing.

License
This project is licensed under the MIT License. See LICENSE for details.

Author
**Bharath M H** – [GitHub Profile](https://github.com/BHARATH1408)

