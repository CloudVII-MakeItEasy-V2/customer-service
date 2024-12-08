

# Customer Management API

This project provides a RESTful API for managing customer information, including registration, login, and retrieval of customer details. The system integrates validation mechanisms and supports extensibility.

---

## Features

- **Customer Registration**: Securely register new customers with validations.
- **Login System**: Support for email/password login.
- **Customer Retrieval**: Fetch details of all customers or by specific ID.
- **Validation**: Address validation using SmartyStreets API.

---

## API Endpoints

### **Customer Operations**
| HTTP Method | Endpoint                        | Description                       | Parameters (if any)               |
|-------------|----------------------------------|-----------------------------------|------------------------------------|
| `POST`      | `/api/customers/register`       | Register a new customer.          | Body: `Customer` JSON object       |
| `POST`      | `/api/customers/login`          | Login using email and password.   | `email`, `passwordHash` (query)    |
| `GET`       | `/api/customers/getAllCustomers`| Retrieve all registered customers.| None                               |
| `GET`       | `/api/customers/getInformationById/{id}` | Get customer details by ID. | Path Variable: `id`               |

### **Request and Response Examples**

#### **Customer Registration**
- **Request**:
```json
POST /api/customers/register
Content-Type: application/json

{
  "name": "John Doe",
  "email": "johndoe@example.com",
  "address": "123 Main St, Springfield",
  "phone": "555-1234",
  "passwordHash": "hashedPassword123"
}
```
- **Response**:
```json
200 OK
{
  "customerId": 1,
  "name": "John Doe",
  "email": "johndoe@example.com",
  "address": "123 Main St, Springfield",
  "phone": "555-1234",
  "passwordHash": "hashedPassword123"
}
```

#### **Login**
- **Request**:
```http
POST /api/customers/login?email=johndoe@example.com&passwordHash=hashedPassword123
```
- **Response**:
```json
200 OK
"Login Successful!"
```

---

## Address Validation

This system integrates with the SmartyStreets API for address validation. The validation logic ensures customer addresses are valid during the registration process.
If customer address is not valid, it would ignore the request


## Project Structure

```
src/main/java/org/group/customer
├── controller
│   └── CustomerController.java   # Handles HTTP requests
├── entity
│   └── Customer.java             # Entity class for customer data
├── repository
│   └── CustomerRepository.java   # JPA repository for customer data
├── service
│   └── CustomerService.java      # Business logic layer
└── util
    └── AddressValidator.java     # Address validation utility
```

---

## Setup Instructions

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/your-repo/customer-management-api.git
   cd customer-management-api
   ```

2. **Install Dependencies**:
   Ensure you have Maven installed, then run:
   ```bash
   mvn clean install
   ```

3. **Configure Database**:
   Update `application.properties` with your database credentials.

4. **Run the Application**:
   ```bash
   mvn spring-boot:run
   ```

5. **Access the API**:
   Visit `http://localhost:8080/api/customers`.

---

## Dependencies

- **Spring Boot**: For RESTful API development.
- **Spring Data JPA**: For database interactions.
- **SmartyStreets API**: For address validation.
- **Jakarta Persistence**: For entity modeling.

---

## Future Enhancements

- Add OAuth 2.0 support (e.g., Google Login).
- Implement rate limiting for API endpoints.
- Extend address validation with more robust error handling.

---

Let me know if you'd like to modify or expand this README!