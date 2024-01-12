# Authorization Server with JWT and Role-Based Authentication

This repository contains the code for a simple Authorization Server implementing JWT (JSON Web Tokens) and role-based authentication. The server is designed to manage user authentication and authorization using tokens.

## Features

- User registration and authentication
- JWT generation and validation for secure communication
- Role-based access control for different levels of authorization

## Technologies Used

- MongoDB 
- JSON Web Tokens (jsonwebtoken)

## Prerequisites

- Java
- MongoDB server running (if you choose MongoDB for the database)

## Getting Started

1. Clone the repository:

    ```bash
    git clone https://github.com/Yashovardhan08/authorization-server.git
    cd authorization-server
    ```
2. Set up environment variables:

   Fill in the empty entries in application.properties in /src/resources file.

3. Start the server:


   The server should be running at http://localhost:8888(in application.properties) by default.

## API Endpoints

- **POST /api/v1/auth/register**

  Register a new user.

- **POST /api/v1/auth/authenticate**

  Authenticate a user and receive a JWT token.

## Role-Based Authentication

The server supports role-based authentication with the following roles:

- `student`: Student role
- `professor`: Professor role
- `admin`: Admin user role
- `superadmin`: SuperAdmin user role

To assign a role to a user, include the `roles` field during registration.

