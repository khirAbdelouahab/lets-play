# Secure Product Management System: A Spring Boot & MongoDB Implementation

Let's Play is a production-ready RESTful API developed using Spring Boot and MongoDB, designed to manage users and products in a secure, scalable environment. The system implements token-based authentication (JWT) and role-based authorization to ensure that only authorized users can perform restricted operations.

## ✨ Key Features

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
- **[Java](https://www.oracle.com/java/)** `24` - Programming language

### Security
- **[JWT (JSON Web Tokens)](https://jwt.io/)** - Token-based authentication
- **[jjwt](https://github.com/jwtk/jjwt)** `0.12.x` - JWT implementation library
- **[BCrypt](https://en.wikipedia.org/wiki/Bcrypt)** - Password hashing

### Database
- **[MongoDB](https://www.mongodb.com/)** `6.x` - NoSQL database

### Build Tools
- **[Maven](https://maven.apache.org/)** `3.9+` - Dependency management and build automation

### DevOps
- **[Docker](https://www.docker.com/)** - Containerization
- **[Docker Compose](https://docs.docker.com/compose/)** - Multi-container orchestration

---

## 📦 Prerequisites

Before running this application, make sure you have the following installed:

### Required
- **Java 24 or higher**
```bash
  java --version
```
  Expected output:
```
  java 24.0.2 2025-07-15
  Java(TM) SE Runtime Environment (build 24.0.2+12-54)
  Java HotSpot(TM) 64-Bit Server VM (build 24.0.2+12-54, mixed mode, sharing)
```
  Download: [Oracle JDK](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://adoptium.net/)

- **Maven 3.9+**
```bash
  mvn -version
```
  Download: [Maven](https://maven.apache.org/download.cgi)

- **MongoDB 6.x**
```bash
  mongod --version
```
  Download: [MongoDB Community Server](https://www.mongodb.com/try/download/community)

### Optional (for Docker deployment)
- **Docker 20.x+**
```bash
  docker --version
```
  Download: [Docker Desktop](https://www.docker.com/products/docker-desktop/)

- **Docker Compose 2.x+**
```bash
  docker-compose --version
```
  Included with Docker Desktop

---

## 🚀 Installation

### 1. Clone the Repository
```bash
git clone https://github.com/khirAbdelouahab/lets-play.git
cd lets-play
```

### 2. Install Dependencies
```bash
mvn clean install
```

This will download all required dependencies defined in `pom.xml`.

---

## ⚙️ Configuration

### MongoDB Configuration

The MongoDB configuration is managed in `src/main/java/com/example/lets_play/config/MongoConfig.java`.

You can customize the connection settings by modifying this file or by setting environment variables.

### Application Properties (Optional)

You can also create `src/main/resources/application.properties` for additional configuration:
```properties
# Server Configuration
server.port=8080

# Logging
logging.level.com.example.lets_play=DEBUG
logging.level.org.springframework.security=DEBUG
```

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

### Option 2: Using JAR File
```bash
# Build the JAR
mvn clean package

# Run the JAR
java -jar target/lets-play-0.0.1-SNAPSHOT.jar
```

### Option 3: Using Docker (Recommended for Production)

You can customize `docker-compose.yml` to fit your needs.
```bash
# Build and start all services
docker-compose up -d

# View logs
docker-compose logs -f

# Stop services
docker-compose down
```

---

## 📁 Project Structure
```
lets-play/
├── src/
│   └── main/
│       └── java/com/example/lets_play/
│           ├── config/
│           │   ├── AdminAccount.java
│           │   └── MongoConfig.java
│           ├── controller/
│           │   ├── AuthenticationController.java
│           │   ├── ProductController.java
│           │   └── UserController.java
│           ├── debug/
│           │   └── MongoDebug.java
│           ├── exception/
│           │   ├── AccessDeniedException.java
│           │   ├── AuthenticationException.java
│           │   ├── EmailAlreadyExistException.java
│           │   ├── EmailNotFoundException.java
│           │   ├── GlobalExceptionHandler.java
│           │   ├── ProductNotFoundException.java
│           │   └── UsernameAlreadyExistsException.java
│           ├── model/
│           │   ├── dto/
│           │   │   ├── AuthResponse.java
│           │   │   ├── HttpErrorResponse.java
│           │   │   ├── LoginRequestDto.java
│           │   │   ├── ProductRequestDto.java
│           │   │   ├── ProductResponseDto.java
│           │   │   ├── RegisterRequestDto.java
│           │   │   └── UserDto.java
│           │   └── entities/
│           │       ├── Product.java
│           │       └── User.java
│           ├── repository/
│           │   ├── ProductRepository.java
│           │   └── UserRepository.java
│           ├── security/
│           │   ├── JwtAuthenticationFilter.java
│           │   ├── JwtTokenProvider.java
│           │   ├── ProductSecurity.java
│           │   ├── SecurityConfig.java
│           │   └── UserPrincipal.java
│           ├── service/
│           │   ├── AuthenticationService.java
│           │   ├── CustomUserDetailsService.java
│           │   ├── ProductService.java
│           │   └── UserService.java
│           └── LetsPlayApplication.java
├── docker-compose.yml
├── Dockerfile
├── pom.xml
└── README.md
```

---

## 📚 API Documentation

### Base URL
```
http://localhost:8080/api/v1
```

---

### 🔐 Authentication Endpoints

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

**Response:**
```json
{
  "success": true,
  "message": "User registered successfully"
}
```

---

#### Login
```http
POST /api/v1/auth/login
Content-Type: application/json

{
  "username": "john",
  "password": "password123"
}
```

**Response:**
```json
{
  "success": true,
  "message": "Login successful",
  "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIi..."
}
```

---

### 📦 Product Endpoints

#### Create Product (Authenticated)
```http
POST /api/v1/products
Content-Type: application/json
Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIi...

{
  "name": "product 1",
  "description": "description for product 1",
  "price": 50.99
}
```

**Response:**
```json
{
  "success": true,
  "message": "product created succesfuly",
  "product": {
    "id": "698209cf42f40256a4b6c78d",
    "name": "product 1",
    "description": "description for product 1",
    "price": 50.99
  }
}
```

---

#### Get All Products (Public)
```http
GET /api/v1/products
```

**Response:**
```json
[
  {
    "id": "698121b28f95f865df6e2af1",
    "name": "updated product 1",
    "description": "product 1 description",
    "price": 50.99,
    "ownerName": "test1"
  },
  {
    "id": "69820a1242f40256a4b6c78e",
    "name": "product 2",
    "description": "product 2 description",
    "price": 150.99,
    "ownerName": "test2"
  }
]
```

---

#### Get Product by ID (Public)
```http
GET /api/v1/products/{id}
```

**Response:**
```json
{
  "id": "698121b28f95f865df6e2af1",
  "name": "product 1",
  "description": "product 1 description",
  "price": 50.99,
  "ownerName": "test1"
}
```

---

#### Get Products by Owner (Public)
```http
GET /api/v1/products/ownedBy/{username}
```

**Response:**
```json
[
  {
    "id": "698121b28f95f865df6e2af1",
    "name": "product 1",
    "description": "product 1 description",
    "price": 50.99,
    "ownerName": "test1"
  }
]
```

---

#### Update Product (Owner or Admin)
```http
PUT /api/v1/products/{id}
Authorization: Bearer {token}
Content-Type: application/json

{
  "name": "Updated Product",
  "description": "Updated description",
  "price": 99.99
}
```

**Response:**
```json
{
  "success": true,
  "message": "Product updated successfully",
  "product": {
    "id": "698121b28f95f865df6e2af1",
    "name": "Updated Product",
    "description": "Updated description",
    "price": 99.99
  }
}
```

---

#### Delete Product (Owner or Admin)
```http
DELETE /api/v1/products/{id}
Authorization: Bearer {token}
```

**Response:**
```json
{
  "success": "true",
  "message": "product deleted successfuly"
}
```

---

### 👥 User Endpoints (Admin Only)

#### Get All Users
```http
GET /api/v1/users
Authorization: Bearer {admin-token}
```

**Response:**
```json
[
  {
    "id": "698121b28f95f865df6e2af2",
    "name": "john",
    "email": "john@example.com",
    "role": "USER"
  },
  {
    "id": "698121b28f95f865df6e2af3",
    "name": "admin",
    "email": "admin@example.com",
    "role": "ADMIN"
  }
]
```

---

### ⚠️ Error Responses

All error responses follow this format:
```json
{
  "timestamp": "2026-02-04T12:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "Product with id 123 not found",
  "path": "/api/v1/products/123"
}
```

**Common Status Codes:**
- `200` - Success
- `201` - Created
- `204` - No Content (successful deletion)
- `400` - Bad Request (validation error)
- `401` - Unauthorized (authentication required)
- `403` - Forbidden (insufficient permissions)
- `404` - Not Found
- `500` - Internal Server Error

---

## 🔐 Security

### Authentication Flow

1. **User Registration**: User creates account with username, email, and password
2. **Password Hashing**: Password is encrypted using BCrypt
3. **Login**: User provides credentials
4. **Token Generation**: Server generates JWT token
5. **Token Usage**: Client includes token in `Authorization: Bearer {token}` header
6. **Token Validation**: Server validates token on each request

### Authorization Levels

| Role | Permissions |
|------|-------------|
| **Public** | View all products, view products by owner, view product by ID |
| **USER** | All public permissions + Create products, Edit own products, Delete own products |
| **ADMIN** | All USER permissions + View all users, Delete any product |

---

## 🧪 Testing

### Using cURL

**Register:**
```bash
curl -X POST http://localhost:8080/api/v1/auth/register \
  -H "Content-Type: application/json" \
  -d '{"name":"john","email":"john@example.com","password":"password123"}'
```

**Login:**
```bash
curl -X POST http://localhost:8080/api/v1/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"john","password":"password123"}'
```

**Get Products (Public):**
```bash
curl http://localhost:8080/api/v1/products
```

**Create Product (Authenticated):**
```bash
curl -X POST http://localhost:8080/api/v1/products \
  -H "Authorization: Bearer YOUR_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{"name":"Product","description":"Description","price":99.99}'
```

---

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## 👤 Author

**Khir Abdelouahab**
- GitHub: [@khirAbdelouahab](https://github.com/khirAbdelouahab)

---

## 🙏 Acknowledgments

- Spring Boot documentation
- MongoDB documentation
- JWT.io for JWT resources
- Stack Overflow community

---

**Made with ❤️ using Spring Boot**