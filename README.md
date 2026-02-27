# 🏦 Spring Boot Microservices Banking System
A production-style banking backend built using Spring Boot and Spring Cloud, demonstrating a scalable microservices architecture with centralized configuration, service discovery, API gateway routing, and Dockerized deployment.
This project simulates a banking ecosystem with independent services for Accounts, Loans, and Cards, following clean architecture and cloud-native design principles.

# 🚀 Architecture Overview
This system follows a distributed microservices architecture:

Config Server – Centralized configuration management

Eureka Server – Service registry & discovery

API Gateway – Centralized routing & request handling added Resilency4J patterns like Retry Mechanism, Circuit Beaker, Fallback Mechanism, Rate Limiter.

Accounts Service – Manages customer accounts

Loans Service – Handles loan details

Cards Service – Manages card-related operations

Docker Compose – Container orchestration

All services are independently deployable and communicate via service discovery.

# 🧱 Tech Stack

Java 17+,
Spring Boot,
Spring Cloud (Eureka, Config Server, Gateway, Resilency4J),
Maven,
H2 Database,
Docker,
REST APIs,
Spring Actuator,
Microservices,

# 🔍 Key Features

-Centralized configuration using Spring Cloud Config

-Dynamic service registration via Eureka

-API Gateway routing and filtering

-Environment-based configuration support

-Containerized microservices setup

# 📈 Future Enhancements

-Replace H2 with MySQL/PostgreSQL

-Add Redis caching

-Adding Montioring and Logging (Grafan, Healm)

-Add authentication using Spring Security & JWT

-Kubernetes deployment

-Clean and scalable layered architecture
