# Spring Boot Authentication with Interceptors

This is a simple Spring Boot application demonstrating the use of interceptors for authentication, logging, and error handling.

## Features

- **Authentication**: Validates a Bearer token before processing requests.
- **Pre-Interceptor**: Checks the token in the `Authorization` header.
- **Post-Interceptor**: Logs data after controller execution.
- **After-Completion**: Logs data after the request has been completed.

## Clone and Build

```bash
git clone https://github.com/varun29802/SpringBoot_Interceptors.git
cd spring-boot-authentication-interceptor
mvn clean install
```

### Run the Application

```bash
mvn spring-boot:run
```

The application will run on **http://localhost:8080**.

## Endpoints

### 1. Public Endpoint

**GET** `/api/public`

No authentication required.

**API Call:**

```bash
curl http://localhost:8080/api/public
```

**Response:**

```json
{
  "message": "This is a public endpoint. No authentication needed."
}
```

### 2. Authenticated Endpoint

**GET** `/api/test`

Requires Bearer token in the `Authorization` header.

**API Call:**

```bash
curl -H "Authorization: Bearer valid-token" http://localhost:8080/api/test
```

**Response:**

```json
{
  "message": "Hello Welcome To Interceptor Concept"
}
```

If the token is invalid or missing, you'll get:

**Response:**

```json
{
  "error": "Unauthorized: Invalid token"
}
```

### 3. GET Endpoint

**POST** `/api/test/{name}`

Requires Bearer token and data in the URL as a path variable.

**API Call:**

```bash
curl -X POST -H "Authorization: Bearer valid-token"  http://localhost:8080/api/test/varun
```

**Response:**

```json
{
  "message": "Welcome varun You are a authenticated user, Thank you."
}
```

If the token is invalid or missing, you'll get:

**Response:**

```json
{
  "error": "Unauthorized: Invalid token"
}
```

## Interceptors

- **Pre-Handle**: Checks if the token is valid.
- **Post-Handle**: Logs information after the controller executes.
- **After-Completion**: Logs data after the response is sent.

## Error Handling

If the token is missing or invalid, a `401 Unauthorized` response is returned. If any unexpected errors occur, a `500 Internal Server Error` is sent.
