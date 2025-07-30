A comprehensive task management system built with Spring Boot, featuring role-based authentication, automated notifications, and high-performance API endpoints.

## Features

- ✅ Role-based authentication and authorization (Admin, Manager, User)
- ✅ JWT-based security
- ✅ Automated email notifications
- ✅ Deadline tracking and alerts
- ✅ High-performance API (sub-200ms response times)
- ✅ Comprehensive error handling
- ✅ API documentation with Swagger
- ✅ Database migrations with Flyway
- ✅ Unit and integration tests

## Tech Stack

- **Backend**: Java 17, Spring Boot 3.2
- **Security**: Spring Security, JWT
- **Database**: PostgreSQL
- **Build Tool**: Maven
- **Email**: Spring Mail
- **Documentation**: OpenAPI 3 (Swagger)
- **Testing**: JUnit 5, Testcontainers

## Project Structure

```
src/
├── main/
│   ├── java/com/nazar/taskmanagement/
│   │   ├── TaskManagementApplication.java
│   │   ├── config/
│   │   │   ├── SecurityConfig.java
│   │   │   ├── JwtConfig.java
│   │   │   └── EmailConfig.java
│   │   ├── controller/
│   │   │   ├── AuthController.java
│   │   │   ├── TaskController.java
│   │   │   ├── UserController.java
│   │   │   └── ProjectController.java
│   │   ├── dto/
│   │   │   ├── request/
│   │   │   └── response/
│   │   ├── entity/
│   │   │   ├── User.java
│   │   │   ├── Task.java
│   │   │   ├── Project.java
│   │   │   └── Role.java
│   │   ├── repository/
│   │   ├── service/
│   │   ├── security/
│   │   ├── exception/
│   │   └── util/
│   └── resources/
│       ├── application.yml
│       └── db/migration/
└── test/
