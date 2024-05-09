# Streaming Service
This repository contains the source code for a streaming service

### Project Description:

Our project is a streaming service offering content-only services: movies, series, anime, cartoons, and more. We aim to create a convenient platform that allows users to enjoy their favorite content anytime and anywhere.

### Technology Stack:

- Backend: Java 8, Spring Boot 3.0.4, Spring Data JPA, Lombok, Maven, Spring Security, Liquibase, MySQL.
- Frontend: React, Next.js, TypeScript, Shadcn/ui, Postman, jjwt.

### Key Features:

- User registration and authentication.
- Content browsing and searching.
- Audio and video streaming.
- Playlist management and personalized recommendations.

## Installation and Running

1. Clone the repository:
```bash
git clone <repository_url>
```
2. Install dependencies for the backend:
```bash
cd streaming-service-backend
mvn install
```
3. Run the backend:
```bash
mvn spring-boot:run
```

### Configuration

- The backend is available at `http://localhost:8080`.
- MySQL is used for the database. Check the application.properties file for connection setup.


## Authors

+ Vitalii Natalevych - natalevych.v@gmail.com
+ Michał Pakuła - michalmpakula@gmail.com
+ Łukasz Kuś - thkbenice@gmail.com
+ Vladimir Marianciuc - boosth14@gmail.com

## License

This project is licensed under the MIT License - see the LICENSE.md file for details.
