# Chat Application

## Overview
This application is a secure, real-time messaging platform built with Spring Boot. It enables users to register, authenticate, and exchange messages in real-time via WebSockets. All communications are protected with JWT authentication, and message history is persisted in a database.

## Technical Stack
- **Java 23**
- **Spring Boot 3.4.3**
- **Spring WebSocket**
- **Spring Data JPA**
- **H2 Database** (in-memory database)
- **JWT Authentication** (JSON Web Tokens)
- **Project Lombok**

## Core Features
- user registration and authentication
- Real-time messaging via WebSockets
- Persistent message storage
- User-to-user direct messaging
- Database-backed chat history

## Architecture
The application follows a standard Spring Boot architecture with the following key components:
- **User Management**: Registration, authentication, and JWT token generation
- **WebSocket Communication**: Real-time messaging between connected users
- **Data Persistence**: Storage of users, chat rooms, and messages

## Entity Model
The application is built around three primary entities:
1. **AppUser**: Stores user information including credentials and profile data
2. **ChatRoom**: Represents a conversation between users
3. **ChatMessage**: Individual messages within a chat room

## API Documentation

### Authentication Endpoints

#### User Registration
```
GET http://localhost:8080/register
```
**Request Body**:
```json
{
  "email": "user@example.com",
  "password": "securePassword",
  "firstName": "John",
  "lastName": "Doe"
}
```

#### User Login
```
GET http://localhost:8080/login
```
**Request Body**:
```json
{
  "email": "user@example.com",
  "password": "securePassword"
}
```
**Response**: Authentication token in the format:
```
Authentication token: eyJhbGciOiJIUzI1NiJ9...
```

### WebSocket Communication

To establish a WebSocket connection with another user:

1. Connect to the WebSocket endpoint:
```
ws://localhost:8080/user/{recipient-email}
```

2. Include the JWT token in the request headers:
```
Authentication: eyJhbGciOiJIUzI1NiJ9...
```

3. Once connected, messages can be sent and received in real-time through the established WebSocket connection.

## Getting Started

### Prerequisites
- Java 23 JDK
- Maven

### Installation and Setup
1. Clone the repository
2. Navigate to the project directory
3. Build the application:
```bash
mvn clean install
```
4. Run the application:
```bash
mvn spring-boot:run
```

### Usage Example
1. Register two users via the `/register` endpoint
2. Login with each user to obtain JWT tokens
3. Establish WebSocket connections from each user to communicate with the other
4. Exchange messages in real-time

## Security Considerations
- All passwords are securely hashed using Spring Security's crypto utilities
- Communication is secured with JWT tokens
- Tokens have a limited validity period
- WebSocket connections require valid authentication

## Development
The application is configured with H2 in-memory database by default, making it easy to set up for development. For production use, it is recommended to configure a persistent database solution.

## Dependencies
The application relies on the following key dependencies:
- spring-boot-starter-web
- spring-boot-starter-websocket
- spring-boot-starter-data-jpa
- spring-security-crypto
- jjwt (JSON Web Token implementation)
- jackson-core and jackson-databind for JSON processing
- H2 database for persistence
- lombok for reducing boilerplate code
