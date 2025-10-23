# 📰 Spring Boot Blog Backend

![Java](https://img.shields.io/badge/Java-17-blue?logo=java)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.5-green?logo=springboot)
![Maven](https://img.shields.io/badge/Maven-3.9.3-orange?logo=apachemaven)
![JWT](https://img.shields.io/badge/Security-JWT-brightgreen?logo=jsonwebtokens)
![License](https://img.shields.io/badge/License-MIT-yellow)
![Build](https://img.shields.io/badge/Build-Success-success)
![Database](https://img.shields.io/badge/Database-H2-blue)

---

## 📘 Overview

**Spring Boot Blog Backend** is a secure, scalable REST API built with **Spring Boot**, featuring **JWT-based authentication**, **robust validation**, and **exception handling**.  
It powers a simple blog platform supporting user registration, authentication, and CRUD operations on posts.

This project follows a clean architecture with modular packages for configuration, controllers, services, repositories, and models — making it ideal as a learning reference or a production-ready base.

---

## ✨ Key Features

✅ **User Authentication & Authorization** — Secure endpoints using Spring Security & JWT  
✅ **CRUD Blog Management** — Create, read, update, and delete blog posts  
✅ **Validation & Error Handling** — Centralized via `GlobalExceptionHandler`  
✅ **Swagger UI** — Explore and test APIs interactively  
✅ **H2 Database** — In-memory DB for quick development and testing  
✅ **Comprehensive Testing** — Includes unit and integration test coverage  

---

## 🧱 Project Structure

com.blog.blogapp
├── BlogappApplication.java # Main Spring Boot entry point
├── config/
│ └── SwaggerConfig.java # Swagger/OpenAPI setup
├── controller/
│ ├── AuthController.java # Handles login & registration
│ └── PostController.java # Handles blog post CRUD operations
├── exception/
│ ├── GlobalExceptionHandler.java # Centralized exception handling
│ ├── ErrorResponse.java
│ ├── ResourceNotFoundException.java
│ └── InvalidSortFieldException.java
├── model/
│ └── Post.java # Entity class for posts
├── repository/
│ ├── PostRepository.java
│ └── UserRepository.java
├── security/
│ ├── JwtAuthenticationFilter.java
│ └── SecurityConfiguration.java
├── service/
│ ├── PostService.java
│ └── CustomUserDetailsService.java
└── user/
└── User.java # User entity


---

## ⚙️ Installation & Setup

### 1️⃣ Clone the Repository
```bash
git clone https://github.com/BHARATH1408/springboot-blog-backend.git
cd springboot-blog-backend

2️⃣ Build and Run
mvn clean install
mvn spring-boot:run

3️⃣ Access Swagger UI
http://localhost:8080/swagger-ui/index.html

4️⃣ Access H2 Database Console
http://localhost:8080/h2-console


JDBC URL: jdbc:h2:mem:blogdb
Username: sa
Password: (leave blank)

🧩 API Endpoints
🔐 Authentication
Method	Endpoint	Description
POST	/auth/register	Register a new user
POST	/auth/login	Authenticate user and get JWT token
📝 Posts
Method	Endpoint	Description	Auth Required
GET	/posts	Get all posts	❌ No
GET	/posts/{id}	Get post by ID	❌ No
POST	/posts	Create new post	✅ Yes
PUT	/posts/{id}	Update post	✅ Yes
DELETE	/posts/{id}	Delete post	✅ Yes

Use the “Authorize” button in Swagger UI to input your JWT token for protected routes.

🧪 Testing

The project includes:

Unit tests (e.g. JwtUtilTest.java)

Integration tests (e.g. PostControllerIntegrationTest.java)

Run all tests:

mvn clean verify

🔄 Recent Changes

🛡️ Implemented JWT authentication and request filtering

🧰 Added global exception handling with detailed error responses

🧾 Integrated Swagger for API documentation

🧪 Added JUnit tests for authentication and post APIs

🧱 Refactored service layer for better modularity

📜 License

This project is licensed under the MIT License.
See the LICENSE
 file for more details.

👤 Author

Bharath M H
📧 GitHub Profile

💬 Passionate about backend development and Spring Boot microservices.
