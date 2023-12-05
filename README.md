
# JJEP CRM System

Small summary about project: Customer Relationship Management (CRM) system for JJEP, built using Spring Boot as a backend and Thymeleaf as a frontend. As database our choice PostgreSQL which deliveries complex database functionality. For making a beautiful and user-friendly UI design to make good user experience, we used tailwind css for styling reusable components, colors, theming website. Security is a top priority as we have we have chosen high level security with Spring Boot Security ensuring authentication and middleware services for making the application highly secure. Also we implemented User role-based authentication for granting admin users full access, regular users access to authenticated and guest sections, and for guests restricted some 
specific pages. The admin dashboard allows administrators to full control of Create, Read, Update, and Delete (CRUD) operations for all entities. Key database models include Users, Applications (for storing form data), and Activity (to manage system activities). We used library called iText for generation PDF documents for playground page. After submitting user details to playground, data securely and immediately analyzes with custom algorithm and sends back to user.


## Key Features

- Role based authentication for admins, users, and guests
- Admin dashboard for full access to system
- Spring Boot Security for secure system
- Beautiful and intuitive UI design
- Custom algorithm
- Generating PDF documents

## Tech Stack

**Client:** Thymeleaf, Tailwind CSS CDN, JavaScript configs

**Server:** Spring Boot, Spring Validation, Spring JDBC, Spring JPA, Spring Security, Lombok, Liquibase, PostgreSQL driver, Spring devtools, iText

**Database:** PostgreSQL

### Requirements
Java 17, SDK 20

If you want to connect your own database: Postgresql server


## Installation

- Open the project on terminal

- Install dependencies
```bash
  ./mvnw clean install
```
- Serve application
```bash
  ./mvnw spring-boot:run
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

