
# JJ CRM System

Description: A comprehensive Customer Relationship Management (CRM) system tailored for JJEP, built using Spring Boot for the backend and Thymeleaf for the frontend. The system leverages PostgreSQL as its database, delivering a beautiful and user-friendly UI design to enhance the overall user experience. Security is a top priority, with Spring Boot Security ensuring robust authentication and middleware services, making the application highly secure. User role-based authentication is implemented, granting admin users full access, regular users access to authenticated and guest sections, and guests restricted to guest-specific pages. The admin dashboard empowers administrators with full control over Create, Read, Update, and Delete (CRUD) operations. Key database models include Users, Applications (for storing form data), and Activity (to manage system activities).


## Features

- Role-based authentication for admin, user, and guest access
- Admin dashboard for full CRUD operations
- Secure Spring Boot Security for heightened protection
- Beautiful and intuitive UI design
- Database models: Users, Applications, and Activity


## Tech Stack

**Client:** Thymeleaf, tailwind css CDN, javasript configs

**Server:** Spring Boot, spring validation, spring JDBC, spring JPA, spring security, lombok, liquibase, PostgreSQL driver, spring devtools

**Database:** PostgreSQL

### Requirements
Java 17, SDK 20

If you want to connect your own database: Postgresql server


## Installation

- Open the project on terminal

- Install dependencies
```bash
  ./mvnm clean install
```
- Serve application
```bash
  ./mvnm spring-boot:run
```

- If you want to run on your database server - change
  src -> main -> resources -> application.properties
```properties
spring.datasource.url=
spring.datasource.username=
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.format_sql=true
spring.datasource.hikari.maximum-pool-size=30
```

## Authors

- [@Dambath](https://github.com/Dambath)
- [@entl](https://github.com/entl)
- [@mirislomovmirjalol](https://github.com/mirislomovmirjalol)
- [@yanchuiko21](https://github.com/yanchuiko21)

