# Secure Product Management System: A Spring Boot & MongoDB Implementation

Let's Play is a production-ready RESTful API developed using Spring Boot and MongoDB, designed to manage users and products in a secure, scalable environment. The system implements token-based authentication (JWT) and role-based authorization to ensure that only authorized users can perform restricted operations.

## Key features include:

- 🔐 **JWT Authentication** - Secure token-based authentication
- 👥 **Role-Based Authorization** - ADMIN and USER roles with granular permissions
- 🛡️ **Owner-Based Protection** - Users can only modify their own resources
- 📝 **Complete CRUD Operations** - Create, Read, Update, Delete for Users and Products
- ⚠️ **Global Exception Handling** - Consistent error responses
- 🎯 **RESTful API Design** - Following REST best practices
- 🗄️ **MongoDB Integration** - NoSQL database for flexible data storage
- 🔍 **Input Validation** - Request validation with meaningful error messages


---

## 🛠️ Tech Stack

### Backend
- **[Spring Boot](https://spring.io/projects/spring-boot)** `3.x` - Application framework
- **[Spring Security](https://spring.io/projects/spring-security)** - Authentication and authorization
- **[Spring Data MongoDB](https://spring.io/projects/spring-data-mongodb)** - Database integration
- **[Java](https://www.oracle.com/java/)** `17+` - Programming language

### Security
- **[JWT (JSON Web Tokens)](https://jwt.io/)** - Token-based authentication
- **[jjwt](https://github.com/jwtk/jjwt)** `0.12.x` - JWT implementation library
- **[BCrypt](https://en.wikipedia.org/wiki/Bcrypt)** - Password hashing

### Database
- **[MongoDB](https://www.mongodb.com/)** `6.x` - NoSQL database

### Build Tools
- **[Maven](https://maven.apache.org/)** `3.8+` - Dependency management and build automation

### DevOps (Optional)
- **[Docker](https://www.docker.com/)** - Containerization
- **[Docker Compose](https://docs.docker.com/compose/)** - Multi-container orchestration


---

## 🚀 Installation

### 1. Clone the Repository
```bash
git clone https://github.com/yourusername/lets-play.git
cd lets-play
```

### 2. Install Dependencies
```bash
mvn clean install
```

This will download all required dependencies defined in `pom.xml`.

---

## 📁 Project Structure
```
lets-play
│   LetsPlayApplication.java
│
├───config
│       AdminAccount.java
│       MongoConfig.java
│
├───controller
│       AuthenticationController.java
│       ProductController.java       
│       UserController.java
│
├───debug
│       MongoDebug.java
│
├───exception
│       AccessDeniedException.java
│       AuthenticationException.java
│       EmailAlreadyExistException.java
│       EmailNotFoundException.java
│       GlobalExceptionHandler.java
│       ProductNotFoundException.java
│       UsernameAlreadyExistsException.java
│
├───model
│   ├───dto
│   │       AuthResponse.java
│   │       HttpErrorResponse.java
│   │       LoginRequestDto.java
│   │       ProductRequestDto.java
│   │       ProductResponseDto.java
│   │       RegisterRequestDto.java
│   │       UserDto.java
│   │
│   └───entities
│           Product.java
│           User.java
│
├───repository
│       ProductRepository.java
│       UserRepository.java
│
├───security
│       JwtAuthenticationFilter.java
│       JwtTokenProvider.java
│       ProductSecurity.java
│       SecurityConfig.java
│       UserPrincipal.java
│
└───service
        AuthenticationService.java
        CustomUserDetailsService.java
        ProductService.java
        UserService.java

```


## ⚙️ Configuration

### Application Properties

Create or modify `src/main/resources/application.properties`:
```properties
# Server Configuration
server.port=8080

# MongoDB Configuration

look at src/main/java/com/example/lets-play/config/MongoConfig.java



---

## 🏃 Running the Application

### Option 1: Using Maven (Development)
```bash
# Start MongoDB first
mongod

# Run the application
mvn spring-boot:run
```

The API will be available at `http://localhost:8080`

### Option 2: Using JAR file
```bash
# Build the JAR
mvn clean package

# Run the JAR
java -jar target/lets-play-0.0.1-SNAPSHOT.jar
```

### Option 3: Using Docker (Recommended for Production)

Run with Docker Compose:
```bash
# Build and start all services
you can update docker-compose.yml
docker-compose up -d



---

## 📚 API Documentation

### Base URL
```
http://localhost:8080/api/v1
```

### Authentication Endpoints

#### Register User
```http
POST /api/v1/auth/register
Content-Type: application/json

{
  "name": "john",
  "email": "john@example.com",
  "password": "password123"
}
```

#### Login
```http
POST /api/v1/auth/login
Content-Type: application/json

{
  "username": "john",
  "password": "password123"
}

Response:
{
    "success":true,
    "message":"Login successful",
    "token":"eyJhbGciOiJIUzUxMiJ9.eyJzdWIi..."
}
```

#### Product
```http
POST /api/v1/products
Content-Type: application/json
Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIi...


{
  "name": "product 1",
  "description": "description for product 1",
  "price":50.99
}

Response:
{
    "success":true,
    "message":"product created succesfuly",
    "product":  {
                    "id":698209cf42f40256a4b6c78d,
                    "name":"product 1",
                    "description":"description for product 1",
                    "price":50.99
                }
}


public endpoints without token:

GET /api/v1/products

Response:
[
    {
        "id":"698121b28f95f865df6e2af1",
        "name":"updated product 1",
        "description":"product 1 description",
        "price":50.99,
        "ownerName":"test1"
    },
    {
        "id":"69820a1242f40256a4b6c78e",
        "name":"product 2",
        "description":"product 2 description",
        "price":150.99,
        "ownerName":"test2"
    }
]
```