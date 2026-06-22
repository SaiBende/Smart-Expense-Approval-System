# Smart Expense Approval System

A multi-level Expense Approval Management System built using Spring Boot, Spring Security, JWT Authentication, PostgreSQL, Docker, and JPA.

The system allows employees to submit expense requests, managers to review and approve them, finance teams to perform final approval, and administrators to monitor system statistics.

---

## Tech Stack

### Backend
- Java 17
- Spring Boot 3
- Spring Security
- Spring Data JPA
- Hibernate
- JWT Authentication
- Maven

### Database
- PostgreSQL

### DevOps
- Docker
- Docker Compose

### Documentation
- Swagger / OpenAPI

---

## Features

### Authentication & Authorization
- User Registration
- User Login
- JWT Token Generation
- JWT Authentication Filter
- Password Encryption using BCrypt
- Role-Based Access Control (RBAC)

### Roles

- EMPLOYEE
- MANAGER
- FINANCE
- ADMIN

---

## Expense Workflow

```text
EMPLOYEE
    ↓
Create Expense
    ↓
PENDING_MANAGER_APPROVAL
    ↓
MANAGER APPROVES
    ↓
PENDING_FINANCE_APPROVAL
    ↓
FINANCE APPROVES
    ↓
APPROVED
```

Expense can also be:

```text
REJECTED
```

---

## Expense Features

### Employee
- Create Expense
- View My Expenses

### Manager
- View Pending Expenses
- Approve Expense
- Reject Expense

### Finance
- View Finance Pending Expenses
- Final Approval of Expenses

### Admin
- View Dashboard Statistics
- View All Expenses
- Search Expenses
- Filter Expenses
- Paginated Expense Listing

---

## Dashboard Statistics

Admin Dashboard provides:

- Total Expenses
- Pending Manager Approvals
- Pending Finance Approvals
- Approved Expenses
- Rejected Expenses
- Total Users

---

## Search Functionality

Search expenses by:

- Title
- Description

Example:

```http
GET /api/expenses/search?keyword=laptop
```

---

## Pagination & Filtering

Get all expenses:

```http
GET /api/expenses/all?page=0&size=10
```

Filter by status:

```http
GET /api/expenses/all?status=APPROVED
```

```http
GET /api/expenses/all?status=PENDING_MANAGER_APPROVAL
```

---

## Security

Implemented using:

- Spring Security
- JWT Authentication
- Stateless Session Management
- Role-Based Endpoint Authorization
- Password Hashing with BCrypt

---

## API Documentation

Swagger UI:

```text
http://localhost:8080/swagger-ui/index.html
```

Authorize using:

```text
Bearer <JWT_TOKEN>
```

---

## Database Schema

### User

| Field | Type |
|---------|---------|
| id | Long |
| fullname | String |
| username | String |
| email | String |
| password | String |
| role | Role |

---

### Expense

| Field | Type |
|---------|---------|
| id | Long |
| title | String |
| description | String |
| amount | BigDecimal |
| status | ExpenseStatus |
| submittedAt | LocalDateTime |
| managerApprovedAt | LocalDateTime |
| financeApprovedAt | LocalDateTime |
| rejectedAt | LocalDateTime |

---

## Expense Status

```java
PENDING_MANAGER_APPROVAL
PENDING_FINANCE_APPROVAL
APPROVED
REJECTED
```

---

## Docker Setup

Run PostgreSQL:

```bash
docker compose up -d
```

Verify:

```bash
docker ps
```

---

## Run Application

```bash
mvn clean install
```

```bash
mvn spring-boot:run
```

Application:

```text
http://localhost:8080
```

---

## Current Implementations

- JWT Authentication
- Spring Security
- User Registration
- User Login
- BCrypt Password Encoding
- RBAC
- Expense Submission
- Manager Approval Flow
- Finance Approval Flow
- Dashboard Statistics
- Expense Search
- Pagination
- Filtering
- Global Exception Handling
- Swagger Integration
- PostgreSQL Integration
- Docker Setup

---

## Future Enhancements

- Expense Attachments (Invoice Upload)
- AWS S3 Integration
- Email Notifications
- Audit Logs
- Unit Testing (JUnit + Mockito)
- Integration Testing
- Dockerized Spring Boot Application
- CI/CD with GitHub Actions
- Redis Caching
- Expense Reports Export (PDF/Excel)

---

## Author

Sai Rajesh Bende

Full Stack Developer | Java | Spring Boot | PostgreSQL | Docker