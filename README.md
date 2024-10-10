

# 🎉 TechFest Event Management System

## 📌 Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Implementation Steps](#implementation-steps)
- [Security](#security)
- [Microservices](#microservices)
- [Docker Setup](#docker-setup)

## 🌟 Overview

This project is a Single Page Application (SPA) for managing a tech fest, allowing users to log in, log out, and register for events. It includes an about page and robust security features.

## 🚀 Features

- User authentication (login/logout)
- Event registration
- About page
- Secure API calls
- Single Sign-On (SSO)

## 💻 Tech Stack

- **Frontend**: React.js
- **Backend**: Spring Boot
- **Database**: PostgreSQL
- **Authentication**: Okta Auth
- **Containerization**: Docker, Docker Compose

## 🛠️ Implementation Steps

1. **SPA Creation**: 
   Developed a Single Page Application with login, logout, and event registration functionalities.

2. **Microservices**: 
   Utilized Spring Boot to create two separate microservices:
   - **Event Service**: Handles all event-related operations like registration, event listing, etc.
   - **Authentication Service**: Manages user authentication, login, and token validation.

3. **Database**: 
   Implemented PostgreSQL for data storage and management.

4. **Security Enhancement**: 
   Integrated Okta Auth to bolster application security.

5. **Okta Configuration**: 
   - Created an account on Auth0 website
   - Set up a server for the application

6. **Auth Configuration**: 
   Added AuthProvider in React app with necessary parameters:
   - Domain
   - Client ID
   - Audience
   - Scope
   - Redirect URL
   - Cache Location
   - Use Refresh Tokens

7. **SSO Implementation**: 
   Developed a Single Sign-On page to verify authentication:
   - Authenticated users gain access to the application
   - Non-authenticated users are redirected to the login page

8. **Application Structure**: 
   Wrapped all pages (home, about, etc.) within the SSO page for hierarchical authentication.

9. **API Interceptor**: 
   Created an interceptor to include authentication tokens with every API call.

10. **Backend Middleware**: 
    Implemented a Spring Boot middleware for token authentication before routing to the requested service.

## 🔐 Security

Our application prioritizes security through:
- Okta Auth integration
- Token-based authentication
- Secure API calls
- Single Sign-On (SSO) implementation

## 🔄 Microservices

The system follows a microservice architecture with two distinct services:

- **Event Service**: Manages event-related data such as registration, listing, and updates. It interacts with the PostgreSQL database for event management operations.
  
- **Authentication Service**: Handles user authentication, login, and validation through token-based mechanisms with Okta integration. This service ensures secure access to the event service.

## 🐳 Docker Setup

The entire project has been containerized using Docker and Docker Compose. This allows for easy deployment and scaling of the application across different environments.

### Docker Compose File

The Docker Compose file is located at the root of the repository and includes:

- **Frontend (React)**: The React app is served as a container.
- **Backend (Spring Boot)**: 
   - **Authentication Service**: Runs as a separate container.
   - **Event Service**: Runs as a separate container.
- **Database (PostgreSQL)**: A containerized PostgreSQL instance for handling data storage.

### Steps to Run

1. Ensure Docker and Docker Compose are installed on your machine.
2. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/techfest-event-management.git
   ```
3. Navigate to the project directory:
   ```bash
   cd techfest-event-management
   ```
4. Run the following command to build and start the services:
   ```bash
   docker-compose up --build
   ```
5. Access the application by navigating to `http://localhost:5173` (or the port you have configured) in your browser.

